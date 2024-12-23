package com.kojikoji.gpt.manage.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MMUPO
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 12:00
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MMUPO {
    private Long id;
    private String mmuId;
    private String mmuName;
    private Integer totalFlow;
    private Integer status;
    private Integer drawStrategy;
    private String desc;
    private String icon;
    private String createTime;
    private String updateTime;
}
