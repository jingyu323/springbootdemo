package com.htkj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * The type Thread config.
 */
@Configuration
public class ThreadConfig {
    /**
     * 解决定时任务单线程排队问题，建立线程池
     *
     * @return task scheduler
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();
        taskScheduler.setPoolSize(50);
        return taskScheduler;
    }
}
