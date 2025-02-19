package com.htkj.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * The type Lettuce connection valid config.
 */
@Component
@Slf4j
public class LettuceConnectionValidConfig implements InitializingBean {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void afterPropertiesSet(){
        if(redisConnectionFactory instanceof LettuceConnectionFactory){
            LettuceConnectionFactory c=(LettuceConnectionFactory)redisConnectionFactory;
            c.setValidateConnection(true);
        }
    }
}