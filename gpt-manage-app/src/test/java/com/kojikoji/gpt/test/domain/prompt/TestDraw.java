package com.kojikoji.gpt.test.domain.prompt;

import com.kojikoji.gpt.manage.Application;
import com.kojikoji.gpt.manage.domain.prompt.model.aggregates.PromptDrawAggregates;
import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.IDrawAlgorithm;
import com.kojikoji.gpt.manage.domain.prompt.service.algorithm.impl.SingleRateRandomAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName DrawTest
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/23 10:52
 * @Version
 */

@Slf4j
@SpringBootTest(classes = Application.class)
public class TestDraw {

    private IDrawAlgorithm drawAlgorithm = new SingleRateRandomAlgorithm();

    @Test
    public void testDoDraw() {
        Map<Long, Integer> cnt = new HashMap<>();
        List<PromptDrawAggregates> list = new ArrayList<>();
        Set<Long> excludeList = new HashSet<>();
        PromptDrawAggregates drawAggregates1 = PromptDrawAggregates.builder()
                .rate(new BigDecimal("0.5"))
                .promptId(5L).build();
        PromptDrawAggregates drawAggregates2 = PromptDrawAggregates.builder()
                .rate(new BigDecimal("0.1"))
                .promptId(1L).build();
        PromptDrawAggregates drawAggregates3 = PromptDrawAggregates.builder()
                .rate(new BigDecimal("0.2"))
                .promptId(2L).build();
        list.add(drawAggregates1);
        list.add(drawAggregates2);
        list.add(drawAggregates3);
        excludeList.add(drawAggregates1.getPromptId());
        Long mmuId = 1111L;
        drawAlgorithm.initRateTuple(mmuId, excludeList, list);
        for (int i = 0; i < 10000; ++i) {
            Long ans = drawAlgorithm.randomDraw(mmuId, excludeList);
            cnt.merge(ans, 1, Integer::sum);
        }
        log.info("random draw ans: {}", cnt);
    }
}
