package com.kojikoji.gpt.manage.domain.MMU.model.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MMUStatusVO {

    CLOSE(0, "关闭"),
    OPEN(1, "实验中"),
    FINISH(2, "实验已推全"),
    ;

    private Integer code;
    private String info;

    public static MMUStatusVO of(Integer code) {
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
