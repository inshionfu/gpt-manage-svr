package com.kojikoji.gpt.test.dao;

import com.alibaba.fastjson2.JSON;
import com.kojikoji.gpt.manage.Application;
import com.kojikoji.gpt.manage.infrastructure.dao.IPromptDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName TestPromptDao
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 14:42
 * @Version
 */

@Slf4j
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestPromptDao {

    @Resource
    private IPromptDao promptDao;

    @Test
    public void testGetExclude() {
        List<Long> ids = promptDao.queryExcludePrompts(414522626L);
        log.info("ids: {}", JSON.toJSON(ids));
    }

}
