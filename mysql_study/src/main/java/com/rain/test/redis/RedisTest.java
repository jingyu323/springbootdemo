package com.rain.test.redis;


import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisTest {
    public static void main(String[] args) {
//        RedisProperties.Jedis jedis = new RedisProperties.Jedis();
        // 设置密码
//		jedis.auth("root");

        Jedis jedis = new Jedis("192.168.99.132", 6379);
// 测试服务是否连接
        System.out.println(jedis.ping());

        Map<String, String> hashValue = new HashMap<>();
        hashValue.put("key1", "key");
        hashValue.put("key2", "key2");
        hashValue.put("key3", "key3");
        jedis.hset("testHash", hashValue);

        Map<String, String> res = jedis.hgetAll("testHash");

        System.out.println(res.values());

        jedis.hset("employee", "name", "NewBoy");
        jedis.hset("employee", "salary", "3000");
        //使用hgetall读取hash对象输出
        Map<String, String> employee = jedis.hgetAll("employee");
        System.out.println(employee);


        Map<String, Double> zsetTest = new HashMap<>();
        zsetTest.put("zetes1", 1d);
        zsetTest.put("zetes2", 4d);
        zsetTest.put("zetes3", 3d);
        jedis.zadd("zsetTest", zsetTest);
        // 获取排序后的数据

        List ss = jedis.zrange("zsetTest", 0, 8);

        System.out.println(ss);

    }
}
