package com.kojikoji.gpt.manage.domain.prompt.model.req;

import com.kojikoji.gpt.manage.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName PromptDrawReq
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 17:25
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptQueryReq {
    private Long mmuId;
    private Constants.StrategyMode strategyMode;
}
