package com.rain.test.redis;


import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
//        RedisProperties.Jedis jedis = new RedisProperties.Jedis();
        // 设置密码
//		jedis.auth("root");

        Jedis jedis = new Jedis("192.168.99.132", 6379);
// 测试服务是否连接
        System.out.println(jedis.ping());

    }
}
