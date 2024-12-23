package com.kojikoji.gpt.manage.domain.prompt.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName PromptDrawAggregates
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 17:28
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptDrawAggregates {
    private Long promptId;
    private BigDecimal rate;
}
