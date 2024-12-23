package com.kojikoji.gpt.manage.domain.prompt.service.algorithm;

import com.kojikoji.gpt.manage.domain.prompt.model.aggregates.PromptDrawAggregates;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BaseAlgorithm
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 16:32
 * @Version
 */
@Slf4j
public abstract class BaseAlgorithm implements IDrawAlgorithm {
    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int RATE_TUPLE_LENGTH = 128;

    // 存放策略元组，mmuId -> rateTuple
    protected Map<Long, ArrayList<Long>> rateTupleMap = new ConcurrentHashMap<>();

    protected Map<Long, List<PromptDrawAggregates>> promptRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long mmuId, Set<Long> excludeIds, List<PromptDrawAggregates> promptDrawAggregates) {
        // 添加mmu策略明细
        addStrategy(mmuId, promptDrawAggregates);

        // 初始化元组
        ArrayList<Long> rateTuple = rateTupleMap.computeIfAbsent(mmuId, k -> new ArrayList<>(RATE_TUPLE_LENGTH));

        // 计算总概率值
        Optional<BigDecimal> sumRate = promptDrawAggregates.stream()
                .filter(p -> !excludeIds.contains(p.getPromptId()))
                .map(PromptDrawAggregates::getRate)
                .reduce(BigDecimal::add);
        if (!sumRate.isPresent()) {
            log.error("none valid prompt mmuId: {}", mmuId);
        }
        // 循环填充概率值
        int cursor = 0;
        for (PromptDrawAggregates promptEntity : promptDrawAggregates) {
            if (excludeIds.contains(promptEntity.getPromptId())) {
                continue;
            }
            int rate = promptEntity.getRate().divide(sumRate.get(), 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(RATE_TUPLE_LENGTH)).intValue();
            for (int i = cursor; i < rate + cursor; ++i) {
                rateTuple.add(promptEntity.getPromptId());
            }
            cursor += rate;
        }
        log.info("rate tuple init finish size: {}", rateTuple.size());
        Collections.shuffle(rateTuple);
    }

    @Override
    public void addStrategy(Long mmuId, List<PromptDrawAggregates> promptEntityList) {
        promptRateInfoMap.put(mmuId, promptEntityList);
    }

    @Override
    public boolean isExistRateInfo(Long mmuId) {
        return promptRateInfoMap.containsKey(mmuId);
    }

    @Override
    public boolean isExistRateTuple(Long mmuId) {
        return rateTupleMap.containsKey(mmuId);
    }

    /**
     * 斐波那契（Fibonacci）散列法，计算哈希索引下标值
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }
}
