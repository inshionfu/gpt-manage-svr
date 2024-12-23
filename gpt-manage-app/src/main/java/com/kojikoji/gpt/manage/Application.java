package com.kojikoji.gpt.manage;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName Application
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 14:54
 * @Version
 */

@Configurable
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
