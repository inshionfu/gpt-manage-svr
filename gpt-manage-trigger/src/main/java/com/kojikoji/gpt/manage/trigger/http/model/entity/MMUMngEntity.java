package com.kojikoji.gpt.manage.trigger.http.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MMUEntity
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:28
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MMUMngEntity {
    @JsonProperty("id")
    private String mmuId;
    @JsonProperty("role_name")
    private String mmuName;
    @JsonProperty("description")
    private String desc;
    @JsonProperty("avatar")
    private String icon;
}
