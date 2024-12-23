package com.kojikoji.gpt.manage.domain.prompt.service.list;

import com.alibaba.fastjson.JSON;
import com.kojikoji.gpt.manage.domain.prompt.model.aggregates.PromptDrawAggregates;
import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.domain.prompt.model.req.PromptQueryReq;
import com.kojikoji.gpt.manage.domain.prompt.repository.IPromptRepository;
import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.IDrawAlgorithm;
import com.kojikoji.gpt.manage.types.common.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName AbstractPromptService
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 16:16
 * @Version
 */
@Slf4j
public abstract class AbstractPromptService extends PromptConfig implements IPromptService {

    @Resource
    protected IPromptRepository promptRepository;

    @Override
    public PromptEntity getPromptByMMUId(PromptQueryReq req) {
        // 0.基础信息
        Long mmuId = req.getMmuId();
        Integer strategyCode = req.getStrategyMode().getCode();

        // 查询mmu对应的Prompt集合
        List<PromptEntity> promptEntities = promptRepository.queryPromptsByMMUId(mmuId);
        log.info("promptEntities:{}", JSON.toJSONString(promptEntities));
        if (promptEntities == null || promptEntities.isEmpty()) {
            log.error("不存在合法的Prompt mmuId: {}", mmuId);
            return PromptEntity.defaultPrompt();
        }
        if (promptEntities.size() == 1) {
            // 只有一个Prompt，直接返回
            return promptEntities.get(0);
        }

        // 排除非法的ID集合
        List<Long> excludePrompts = this.queryExcludePrompts(mmuId);
        Set<Long> excludePromptSet = new HashSet<>(excludePrompts);

        // 校验和初始化
        checkAndInitData(mmuId, strategyCode, excludePromptSet, promptEntities);

        // 执行算法
        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategyCode);
        Long promptId = this.execDrawAlgorithm(mmuId, algorithm, excludePromptSet);

        for (PromptEntity promptEntity : promptEntities) {
            if (promptEntity.getPromptId().equals(promptId)) {
                return promptEntity;
            }
        }
        log.error("prompt not exist, list={} promptId={}", JSON.toJSONString(promptEntities), promptId);
        return PromptEntity.defaultPrompt();
    }

    protected abstract List<Long> queryExcludePrompts(Long mmuId);

    protected abstract Long execDrawAlgorithm(Long mmuId, IDrawAlgorithm algorithm, Set<Long> excludePrompts);

    private synchronized void checkAndInitData(Long mmuId, Integer strategyCode, Set<Long> excludeIds, List<PromptEntity> promptEntities) {
        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategyCode);
        if (Objects.equals(strategyCode, Constants.StrategyMode.ENTIRETY.getCode()) && algorithm.isExistRateInfo(mmuId)) {
            // 整体概率且以及初始化
            return;
        }
        if (algorithm.isExistRateTuple(mmuId)) {
            return;
        }

        List<PromptDrawAggregates> promptDrawAggregates = new ArrayList<>(promptEntities.size());
        for (PromptEntity promptEntity : promptEntities) {
            PromptDrawAggregates drawAggregates = PromptDrawAggregates.builder()
                    .promptId(promptEntity.getPromptId())
                    .rate(promptEntity.getRate())
                    .build();
            promptDrawAggregates.add(drawAggregates);
        }

        if (Objects.equals(Constants.StrategyMode.ENTIRETY.getCode(), strategyCode)) {
            algorithm.addStrategy(mmuId, promptDrawAggregates);
        }

        if (Objects.equals(Constants.StrategyMode.SINGLE.getCode(), strategyCode)) {
            algorithm.initRateTuple(mmuId, excludeIds, promptDrawAggregates);
        }
    }
}
