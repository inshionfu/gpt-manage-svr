package com.kojikoji.gpt.manage.domain.prompt.service.algorithm;

import com.kojikoji.gpt.manage.domain.prompt.model.aggregates.PromptDrawAggregates;

import java.util.List;
import java.util.Set;

public interface IDrawAlgorithm {

    /**
     * 生成随机Prompt接口
     * @param mmuId
     * @param excludePromptIds
     * @return promptId
     */
    Long randomDraw(Long mmuId, Set<Long> excludePromptIds);

    /**
     * 程序启动时初始化元组，初始化完成后不做修改，将百分比数据转换为一整条数组上的分区数据
     * @param mmuId
     */
    void initRateTuple(Long mmuId, Set<Long> excludeIds, List<PromptDrawAggregates> promptDrawAggregates);

    /**
     * 添加mmu策略明细
     * @param mmuId
     * @param promptEntityList
     */
    void addStrategy(Long mmuId, List<PromptDrawAggregates> promptEntityList);

    /**
     * 是否添加过mmu策略
     * @param mmuId
     * @return
     */
    boolean isExistRateInfo(Long mmuId);

    /**
     * 判断是否已经，做了数据初始化
     * @param mmuId
     * @return
     */
    boolean isExistRateTuple(Long mmuId);
}
