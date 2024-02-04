package com.rain.test.redis;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

public class RedisTest {
    public static void main(String[] args) {
//        RedisProperties.Jedis jedis = new RedisProperties.Jedis();
        // 设置密码
//		jedis.auth("root");

        RedisProperties.Jedis jedis = new RedisProperties.Jedis();
    }
}
