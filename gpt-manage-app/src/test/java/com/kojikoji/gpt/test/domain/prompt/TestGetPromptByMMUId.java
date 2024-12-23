package com.kojikoji.gpt.test.domain.prompt;

import com.alibaba.fastjson.JSON;
import com.kojikoji.gpt.manage.Application;
import com.kojikoji.gpt.manage.domain.prompt.model.entity.PromptEntity;
import com.kojikoji.gpt.manage.domain.prompt.model.req.PromptQueryReq;
import com.kojikoji.gpt.manage.domain.prompt.service.list.IPromptService;
import com.kojikoji.gpt.manage.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName TestGetPromptByMMUId
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 14:30
 * @Version
 */

@Slf4j
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestGetPromptByMMUId {

    @Resource
    private IPromptService promptService;

    @Resource
    private ThreadPoolExecutor executor;

    @Test
    public void testGetPromptByMMUId() {
        Map<Long, Integer> cnt = new ConcurrentHashMap<>();
        PromptQueryReq req = PromptQueryReq.builder()
                .mmuId(414522626L)
                .strategyMode(Constants.StrategyMode.SINGLE)
                .build();
        List<Future<PromptEntity>> futureList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            futureList.add(executor.submit(()->promptService.getPromptByMMUId(req)));
        }
        for (Future<PromptEntity> future : futureList) {
            try {
                PromptEntity promptEntity = future.get();
                cnt.merge(promptEntity.getPromptId(), 1, Integer::sum);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.info("cnt : {}", JSON.toJSONString(cnt));

    }

}
