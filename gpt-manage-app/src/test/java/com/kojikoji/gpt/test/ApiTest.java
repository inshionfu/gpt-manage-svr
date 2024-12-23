package com.kojikoji.gpt.test;

import com.kojikoji.gpt.manage.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ApiTest
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 15:16
 * @Version
 */

@Slf4j
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ApiTest {
    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void testArrayList() {
        List<Integer> list = new ArrayList<Integer>(128);
        list.set(28, 1);
        logger.info("list : {}", list);
    }
}
