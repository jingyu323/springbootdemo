package com.rain.test.redis;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 如果原始数据删除了怎么办？布隆过滤器二进制数组如何维护？
 * 直接将对应的hash位置0就行了
 */
public class BloomTest {

    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient cient = Redisson.create(config);
        RBloomFilter<String> bloomFilter = cient.getBloomFilter("test5-bloom-filter");
        // 初始化布隆过滤器，数组长度100W，误判率 1%
        // 注意：误判率设置过小，会产生更多次的 Hash 操作，降低系统的性能。通常建议值是 1%
        bloomFilter.tryInit(1000000L, 0.01);
        // 添加数据
        bloomFilter.add("ID1");
        // 判断是否存在
        System.out.println(bloomFilter.contains("ID2"));
        System.out.println(bloomFilter.contains("ID1"));
    }
}
