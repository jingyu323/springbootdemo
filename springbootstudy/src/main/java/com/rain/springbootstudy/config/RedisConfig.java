package com.htkj.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Redis config.
 *
 * @description:
 */
@Slf4j
@Configuration
public class RedisConfig {
    //    @Resource
//    private RedisClusterProperties redisClusterProperties;
//    @Resource
//    private RedisNode redisNode;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Redis template redis template.
     *
     * @param connectionFactory the connection factory
     * @return the redis template
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        try {
            RedisSerializer<String> stringSerializer = new StringRedisSerializer();
            redisTemplate.setConnectionFactory(connectionFactory);
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.setValueSerializer(stringSerializer);
            redisTemplate.setHashKeySerializer(stringSerializer);
            // 使用 GenericFastJsonRedisSerializer 替换默认序列化
            GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
            redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
            // 设置支持事物
            redisTemplate.setEnableTransactionSupport(true);
            redisTemplate.afterPropertiesSet();
        } catch (Exception e) {
            LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
            RedisSerializer<String> stringSerializer = new StringRedisSerializer();
            redisTemplate.setConnectionFactory(lettuceConnectionFactory);
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.setValueSerializer(stringSerializer);
            redisTemplate.setHashKeySerializer(stringSerializer);
            GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
            redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
        }
        return redisTemplate;
    }

//    @Bean
//    public KeyExpiredListener keyExpiredListener() {
//        return new KeyExpiredListener(this.redisMessageListenerContainer());
//    }
//
//    /* Jedis - 集群、连接池模式 */
//    @Bean
//    public JedisCluster jedisCluster() {
//        /* 切割节点信息 */
//        String[] nodes = redisNode.getNodes().split(",");
//        Set<HostAndPort> hostAndPorts = new HashSet<>();
//        for (String node : nodes) {
//            int index = node.indexOf(":");
//            hostAndPorts.add(new HostAndPort(node.substring(0, index), Integer.parseInt(node.substring(index + 1))));
//        }
//
//        /* Jedis连接池配置 */
//        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();
//        return new JedisCluster(hostAndPorts, redisNode.getCommandTimeout(), 10000, 100, "123456", jedisPoolConfig);
//    }
//
//    /**
//     * 连接池配置
//     *
//     * @return JedisPoolConfig
//     **/
//    private JedisPoolConfig getJedisPoolConfig() {
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setTestOnReturn(true);
//        jedisPoolConfig.setMaxIdle(redisClusterProperties.getMaxIdle());       // 最大空闲连接数, 默认8个
//        jedisPoolConfig.setMaxTotal(redisClusterProperties.getMaxActive());    // 最大连接数, 默认8个
//        jedisPoolConfig.setMinIdle(redisClusterProperties.getMinIdle());       // 最小空闲连接数, 默认0
//        jedisPoolConfig.setMaxWaitMillis(redisClusterProperties.getMaxWait()); // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
//        jedisPoolConfig.setTestOnBorrow(true);                              // 对拿到的connection进行validateObject校验
//        return jedisPoolConfig;
//    }

}