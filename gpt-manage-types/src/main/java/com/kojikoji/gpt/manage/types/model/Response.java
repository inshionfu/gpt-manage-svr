package com.kojikoji.gpt.manage.types.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 11:14
 * @Version
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private String code;
    private String info;
    private T data;
}
