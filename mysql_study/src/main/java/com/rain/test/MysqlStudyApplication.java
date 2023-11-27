package com.rain.test;


import com.rain.test.thread.MsgConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


@SpringBootApplication
@ComponentScan("com.rain.test.*")
public class MysqlStudyApplication {
    @Component
    public class ConsumerListener {
        @KafkaListener(topics = "test", groupId = "default-group")
        public void onMessage(String msg) {
            System.out.println("----收到消息：" + msg + "----");
        }
    }
 

    @Component
    public class KafkaProducer {
        @Autowired
        private KafkaTemplate<String, Object> kafkaTemplate;

        public String send(@RequestParam String msg) {
            kafkaTemplate.send("test", msg);
            return "ok";
        }
    }

    public static void main(String[] args) {
        Thread task = new Thread(new MsgConsumer());
        task.start();
        SpringApplication.run(MysqlStudyApplication.class, args);

    }
}