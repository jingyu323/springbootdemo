package com.htkj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * The type Redis listener config.
 */
@Configuration
public class RedisListenerConfig {
    /**
     * Gets container.
     *
     * @param redisConnectionFactory the redis connection factory
     * @return the container
     */
    @Bean
    RedisMessageListenerContainer getContainer(RedisConnectionFactory redisConnectionFactory)
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }
}
