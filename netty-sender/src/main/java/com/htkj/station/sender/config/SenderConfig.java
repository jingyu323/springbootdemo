package com.htkj.station.sender.config;


import com.htkj.station.sender.netty.SenderNettyClient;
import io.netty.channel.Channel;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


@Data
@Configuration
public class SenderConfig {
    // 线程池维护线程所允许的空闲时间
    private final int keepAliveSeconds = 300;

   private    static final Logger logger = LoggerFactory.getLogger(SenderConfig.class);
    @Value("${socket.port}")
    private Integer port;

    @Value("${socket.host}")
    private String host;

    @Value("${socket.poolKeep}")
    private String poolKeep;

    @Value("${socket.poolCore}")
    private Integer poolCore;

    @Value("${socket.poolMax}")
    private String poolMax;

    @Value("${socket.poolQueueInit}")
    private String poolQueueInit;

    @Value("${socket.tkStationPath}")
    private String tkStationPath;

    @Value("${socket.version}")
    private String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTkStationPath() {
        return this.tkStationPath;
    }

    public void setTkStationPath(String tkStationPath) {
        this.tkStationPath = tkStationPath;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPoolKeep() {
        return this.poolKeep;
    }

    public void setPoolKeep(String poolKeep) {
        this.poolKeep = poolKeep;
    }

    public Integer getPoolCore() {
        return this.poolCore;
    }

    public void setPoolCore(Integer poolCore) {
        this.poolCore = poolCore;
    }

    public String getPoolMax() {
        return this.poolMax;
    }

    public void setPoolMax(String poolMax) {
        this.poolMax = poolMax;
    }

    public String getPoolQueueInit() {
        return this.poolQueueInit;
    }

    public void setPoolQueueInit(String poolQueueInit) {
        this.poolQueueInit = poolQueueInit;
    }

    public String toString() {
        return "BeanConfig{port='" + this.port + '\'' + ", poolKeep='" + this.poolKeep + '\'' + ", poolCore='" + this.poolCore + '\'' + ", poolMax='" + this.poolMax + '\'' + ", poolQueueInit='" + this.poolQueueInit + '\'' + '}';
    }


    @Bean(name = "threadPoolTaskExecutor")
    public TaskExecutor taskExecutor() {
        logger.info("processors num:{}",Runtime.getRuntime().availableProcessors());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize( poolCore*Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Integer.parseInt(poolMax));
        executor.setQueueCapacity(Integer.parseInt(poolQueueInit));
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("sender-thread-");
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return (TaskExecutor)executor;
    }


}
