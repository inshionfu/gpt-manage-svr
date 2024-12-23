package com.kojikoji.gpt.manage.domain.prompt.service.list;

import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.IDrawAlgorithm;
import com.kojikoji.gpt.manage.types.common.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName PromptConfig
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 16:30
 * @Version
 */

public class PromptConfig {

    @Resource
    private IDrawAlgorithm singleRateRandomAlgorithm;

    @Resource
    private IDrawAlgorithm entiretyRateRandomAlgorithm;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(Constants.StrategyMode.ENTIRETY.getCode(), entiretyRateRandomAlgorithm);
        drawAlgorithmMap.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomAlgorithm);
    }

}
