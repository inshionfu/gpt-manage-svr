package com.kojikoji.gpt.manage.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName MMUStatus
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:32
 * @Version
 */

@Getter
@AllArgsConstructor
public enum MMUMngStatus {

    CLOSE(0, "关闭"),
    OPEN(1, "实验中"),
    FINISH(2, "实验已推全"),
    ;

    private Integer code;
    private String info;

    public static MMUMngStatus of(Integer code) {
        switch (code) {
            case 0:
                return CLOSE;
            case 1:
                return OPEN;
            case 2:
                return FINISH;
            default:
                return CLOSE;
        }
    }

}
