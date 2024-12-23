package com.kojikoji.gpt.manage.types.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName Constants
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:12
 * @Version
 */

public class Constants {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        TOKEN_ERROR("0003", "权限拦截"),
        MMU_ERR("OE001", "查询垂类失败"),
        PROMPT_ERR("OE002", "查询prompt失败"),
        ;

        private String code;
        private String info;

    }

    @Getter
    @AllArgsConstructor
    public enum StrategyMode {
        ENTIRETY(1, "整体概率"),
        SINGLE(2, "单项概率"),
        ;
        private Integer code;
        private String info;

        public static StrategyMode of(Integer code) {
            switch (code) {
                case 1:
                    return ENTIRETY;
                case 2:
                    return SINGLE;
                default:
                    return SINGLE;
            }
        }
    }
}
