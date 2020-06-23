package com.kingstar.service;

import com.kingstar.common.RequestHeaderConstants;
import com.kingstar.model.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * @Description: 爬虫类接口单元测试
 * @Author: myl
 * @Date: 2020/6/12 12:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@MapperScan(value = "com.kingstar.model.dao")
public class CrawlServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CrawlServiceTest.class);

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private RedisTemplate<String, Object> objRedisTemplate;

    @Test
    public void testString() {
        System.out.println(RequestHeaderConstants.userAgentArrays.length);
//        HashSet<String> strings = new HashSet<>(Arrays.asList(RequestHeaderConstants.userAgentArrays));
//        System.out.println(strings.size());
//        strings.forEach(s -> System.out.println(new StringBuilder("\"").append(s).append("\"").append(",")));
        int n = 359;
        Random r = new Random();
        System.out.print("随机生成的数是：");
        for (int i = 0; i < 10; i++) {
            //本来范围是[0,10)，整体+1之后变成了[1,n+1)，也就是[1,n]
            int num = r.nextInt(n) + 1;
            System.out.print(num + "  ");
        }
    }

    @Test
    public void testSerializable() {
    }




}
