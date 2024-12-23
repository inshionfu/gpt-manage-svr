package com.kojikoji.gpt.manage.domain.MMU.model.entity;

import com.kojikoji.gpt.manage.domain.MMU.model.VO.DrawStrategyVO;
import com.kojikoji.gpt.manage.domain.MMU.model.VO.MMUStatusVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MMUEntity
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:36
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MMUEntity {
    private String mmuId;
    private String mmuName;
    private Integer totalFlow;
    private MMUStatusVO status;
    private String desc;
    private String icon;
    private DrawStrategyVO drawStrategy;
}
