package com.kojikoji.gpt.manage.domain.prompt.service.algorithm.impl;
import com.kojikoji.gpt.manage.domain.prompt.model.aggregates.PromptDrawAggregates;
import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.BaseAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName DefaultRateRandomDrawAlgorithm
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/8/8 21:40
 * @Version
 */

@Slf4j
@Component("entiretyRateRandomAlgorithm")
public class EntiretyRateRandomAlgorithm extends BaseAlgorithm {

    @Override
    public Long randomDraw(Long mmuId, Set<Long> excludePromptIds) {
        // 排除非法Id，计算概率总和
        BigDecimal differenceDenominator = BigDecimal.ZERO;
        List<PromptDrawAggregates> promptRateInfoList = promptRateInfoMap.get(mmuId);
        List<PromptDrawAggregates> validPromptRateInfoList = new ArrayList<>();

        for (PromptDrawAggregates promptRateInfo : promptRateInfoList) {
            if (excludePromptIds.contains(promptRateInfo.getPromptId())) {
                continue;
            }
            differenceDenominator = differenceDenominator.add(promptRateInfo.getRate());
            validPromptRateInfoList.add(promptRateInfo);
        }

        // 集合大小小于2，直接返回结果
        if (validPromptRateInfoList.isEmpty()) {
            log.error("不存在可执行的prompt mmuId {}", mmuId);
            return null;
        }
        if (validPromptRateInfoList.size() == 1) {
            log.info("只存在一个prompt mmuId {}", mmuId);
            return validPromptRateInfoList.get(0).getRate().longValue();
        }

        // 生成随机值
        int randomVal = new SecureRandom().nextInt(100) + 1;

        Long promptId = null;
        int cursor = 0;
        for (PromptDrawAggregates promptRateInfo : validPromptRateInfoList) {
            int rate = promptRateInfo.getRate().divide(differenceDenominator, 2, RoundingMode.UP).multiply(new BigDecimal(100)).intValue();
            if (randomVal < (cursor + rate)) {
                promptId = promptRateInfo.getPromptId();
                break;
            }
            cursor += rate;
        }

        return promptId;
    }
}
