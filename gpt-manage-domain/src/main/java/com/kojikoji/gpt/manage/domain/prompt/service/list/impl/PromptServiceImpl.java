package com.kojikoji.gpt.manage.domain.prompt.service.list.impl;

import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.IDrawAlgorithm;
import com.kojikoji.gpt.manage.domain.prompt.service.list.AbstractPromptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName PromptServiceImpl
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 10:20
 * @Version
 */

@Service
public class PromptServiceImpl extends AbstractPromptService {

    @Override
    protected List<Long> queryExcludePrompts(Long mmuId) {
        return promptRepository.queryClosedPrompts(mmuId);
    }

    @Override
    protected Long execDrawAlgorithm(Long mmuId, IDrawAlgorithm algorithm, Set<Long> excludePrompts) {
        // 执行随机选取算法
        Long promptId = algorithm.randomDraw(mmuId, excludePrompts);

        if (Objects.isNull(promptId)) {
            return null;
        }
        return promptId;
    }
}
