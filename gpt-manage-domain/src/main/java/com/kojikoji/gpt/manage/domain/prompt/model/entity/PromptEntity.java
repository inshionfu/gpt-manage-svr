package com.kojikoji.gpt.manage.domain.prompt.model.entity;

import com.kojikoji.gpt.manage.domain.prompt.model.vo.PromptStatusVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName PromptEntity
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 16:01
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromptEntity {
    private Long promptId;
    private String promptName;
    private BigDecimal rate;
    private PromptStatusVO status;
    private String content;
    private Long mmuId;
    private Date createTime;
    private Date updateTime;

    public static PromptEntity defaultPrompt() {
        return PromptEntity.builder()
                .promptName("defaultPrompt")
                .content("您好")
                .build();
    }
}
