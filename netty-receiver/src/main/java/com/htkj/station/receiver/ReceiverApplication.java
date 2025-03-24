package com.htkj.station.receiver;

import com.htkj.station.receiver.netty.ReceiverServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ReceiverApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =   SpringApplication.run(ReceiverApplication.class, args);
        ReceiverServerConfig nettyServer = (ReceiverServerConfig)configurableApplicationContext.getBean(ReceiverServerConfig.class);
        nettyServer.startNettyServer();
    }

}
