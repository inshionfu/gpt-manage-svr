package com.kojikoji.gpt.manage.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName PromptPO
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 10:38
 * @Version
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromptPO {
    private Long id;
    private Long promptId;
    private String name;
    private BigDecimal rate;
    private Integer status;
    private String content;
    private Long mmuId;
    private Date createTime;
    private Date updateTime;
}
