package com.kojikoji.gpt.manage.domain.prompt.service.algorithm.impl;

import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.BaseAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.beans.ExceptionListener;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName SingleRateRandomDrawAlgorithm
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/8/8 21:41
 * @Version
 */

@Slf4j
@Component("singleRateRandomAlgorithm")
public class SingleRateRandomAlgorithm extends BaseAlgorithm {

    @Override
    public Long randomDraw(Long mmuId, Set<Long> excludePromptIds) {
        // 获取元组
        List<Long> rateTuple = rateTupleMap.get(mmuId);
        if (Objects.isNull(rateTuple)) {
            log.error("概率元组未初始化 mmuId {}", mmuId);
            return null;
        }
        // 生成随机索引
        int randomVal = new SecureRandom().nextInt(100) + 1;
        int idx = hashIdx(randomVal);

        // 返回结果
        Long promptId = rateTuple.get(idx);
        return excludePromptIds.contains(promptId) ? null : promptId;
    }
}
