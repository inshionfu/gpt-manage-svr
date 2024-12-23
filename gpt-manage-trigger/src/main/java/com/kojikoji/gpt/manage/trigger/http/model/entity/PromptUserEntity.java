package com.kojikoji.gpt.manage.trigger.http.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PromptEntity
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 16:09
 * @Version
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromptUserEntity {
    private String name;
    private String content;
}
