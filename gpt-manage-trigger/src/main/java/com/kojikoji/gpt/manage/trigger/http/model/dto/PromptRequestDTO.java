package com.kojikoji.gpt.manage.trigger.http.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PromptRequestDTO
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 16:10
 * @Version
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromptRequestDTO {
    private Long mmuId;
}
