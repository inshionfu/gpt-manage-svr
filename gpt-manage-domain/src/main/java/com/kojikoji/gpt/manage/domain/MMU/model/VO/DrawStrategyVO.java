package com.kojikoji.gpt.manage.domain.MMU.model.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName DrawStrategyVO
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 16:50
 * @Version
 */

@Getter
@AllArgsConstructor
public enum DrawStrategyVO {
    ENTIRETY(1, "整体概率"),
    SINGLE(2, "单项概率"),
    ;
    private Integer code;
    private String info;

    public static DrawStrategyVO of(Integer code) {
        switch (code) {
            case 1:
                return ENTIRETY;
            case 2:
                return SINGLE;
            default:
                return ENTIRETY;
        }
    }
}
