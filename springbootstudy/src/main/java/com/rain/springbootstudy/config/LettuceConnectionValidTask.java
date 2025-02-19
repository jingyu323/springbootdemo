package com.htkj.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The type Lettuce connection valid task.
 */
@Component
@Slf4j
public class LettuceConnectionValidTask   {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Task.
     */
    @Scheduled(cron="0/2 * * * * ?")
    public void task() {
        if(redisConnectionFactory instanceof LettuceConnectionFactory){
            LettuceConnectionFactory c=(LettuceConnectionFactory)redisConnectionFactory;
            c.validateConnection();
        }
    }
}