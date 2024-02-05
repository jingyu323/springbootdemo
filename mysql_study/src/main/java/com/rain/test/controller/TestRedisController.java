package com.rain.test.controller;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;


@RestController
@RequestMapping("/testRedis")
@Slf4j
public class TestRedisController {

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("name-%d").setDaemon(true).build();

    ThreadFactory fc = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
           

            return null;
        }
    };
    private static final ScheduledExecutorService daemonPool = Executors.newScheduledThreadPool(5, THREAD_FACTORY);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


}
