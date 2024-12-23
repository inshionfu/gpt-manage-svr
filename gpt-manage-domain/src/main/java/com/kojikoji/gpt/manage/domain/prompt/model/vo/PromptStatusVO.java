package com.kojikoji.gpt.manage.domain.prompt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromptStatusVO {
    ABANDON(0, "prompt已废弃"),
    FULL(1, "prompt已推全"),
    EXPERIMENT(2, "实验中"),
    ;
    private Integer code;
    private String info;

    public static PromptStatusVO of(Integer code) {
        switch (code) {
            case 0:
                return ABANDON;
            case 1:
                return FULL;
            case 2:
                return EXPERIMENT;
            default:
                return ABANDON;
        }
    }
}
