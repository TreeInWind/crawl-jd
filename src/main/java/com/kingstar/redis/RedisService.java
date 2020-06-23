package com.kingstar.redis;

import com.kingstar.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description:Redis操作方法
 * @Author: myl
 * @Date: 2020/6/16 15:17
 */
@Component
public class RedisService {

    /**
     * 默认Redis数据过期时间
     */
    private final long EXPIRE_TIME = 6 * 60 * 60 * 1000;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 保存爬取的URL值到Redis
     *
     * @param url：入库的URL
     * @return void
     * @author yongliang
     */
    public void saveUrlToRedis(String url) {
        redisTemplate.opsForValue().set(url, Constants.PLACEHOLDER, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 检查Redis中是否有相同url
     *
     * @param url：url值
     * @return boolean
     * @author yongliang
     */
    public boolean checkHaveSameUrl(String url) {
        return redisTemplate.opsForValue().get(url) != null;
    }

    /**
     * 保存不存在的URL到Redis中
     * <p>1、Redis中不存在该URL，则保存URL，返回值为true；2、Redis中已存在该URL，则不保存该URL，返回值为false</p>
     *
     * @param url
     * @return boolean
     * @author yongliang
     */
    public boolean saveNonexistentUrlToRedis(String url) {
        if (redisTemplate.opsForValue().get(url) == null) {
            return false;
        }
        redisTemplate.opsForValue().set(url, Constants.PLACEHOLDER);
        return true;
    }


}
