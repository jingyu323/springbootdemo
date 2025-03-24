package com.htkj.station.receiver.netty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



@Configuration
public class ReceiverServerConfig {

   private    static final Logger logger = LoggerFactory.getLogger(ReceiverServerConfig.class);
    @Value("${socket.port}")
    private String port;

    @Value("${socket.poolKeep}")
    private String poolKeep;

    @Value("${socket.poolCore}")
    private String poolCore;

    @Value("${socket.poolMax}")
    private String poolMax;

    @Value("${socket.poolQueueInit}")
    private String poolQueueInit;


    @Value("${socket.version}")
    private String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPoolKeep() {
        return this.poolKeep;
    }

    public void setPoolKeep(String poolKeep) {
        this.poolKeep = poolKeep;
    }

    public String getPoolCore() {
        return this.poolCore;
    }

    public void setPoolCore(String poolCore) {
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

    @Bean
    public TaskExecutor taskExecutor() {
        logger.info("processors num:{}",Runtime.getRuntime().availableProcessors());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(poolCore)*Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Integer.parseInt(poolMax));
        executor.setQueueCapacity(Integer.parseInt(poolQueueInit));
        executor.setThreadNamePrefix("netty-thread-");
        executor.initialize();
        return (TaskExecutor)executor;
    }

    public void startNettyServer() {
            taskExecutor().execute(() -> {
            try {
                ReceiverNettyServer nettyServer = new ReceiverNettyServer();
                nettyServer.bind(Integer.parseInt(this.port), Integer.parseInt(this.poolCore), Integer.parseInt(this.poolMax));
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
