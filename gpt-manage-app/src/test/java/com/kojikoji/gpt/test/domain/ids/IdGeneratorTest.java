package com.kojikoji.gpt.test.domain.ids;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Random;

/**
 * @ClassName IdGeneratorTest
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/12/22 15:17
 * @Version
 */

@SpringBootTest
public class IdGeneratorTest {
    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorTest.class);

    @Test
    public void testShortCode() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder idStr = new StringBuilder();
        idStr.append(year - 2020);
        idStr.append(hour);
        idStr.append(String.format("%02d", week));
        idStr.append(day);
        idStr.append(String.format("%03d", new Random().nextInt(1000)));

        logger.info("{}", Long.parseLong(idStr.toString()));
    }
}
