package com.rain.test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonGeneratorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rain.test.thread.MsgConsumer;
import kafka.utils.Json;
import kafka.utils.json.JsonArray;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;


@SpringBootApplication
@EnableKafka
@ComponentScan("com.rain.test.*")
public class MysqlStudyApplication {

    @Autowired
    private KafkaProperties properties;
    @Resource
    private ObjectMapper objectMapper;

    @Component
    public class ConsumerListener {
        @KafkaListener(topics = "test", groupId = "group22222")
        public void onMessage(String msg, Consumer<String, String> consumer) {
            System.out.println("----收到消息：" + msg + "----");

            try {
                System.out.println(objectMapper.writeValueAsString(properties));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 添加之后 重启之后不再重复消费消息
            consumer.commitAsync();
        }
    }

    @Component
    public class ConsumerListene2r {
        @KafkaListener(topics = "test", groupId = "group2333")
        public void onMessage(ConsumerRecord record, List<String> data) {
            System.out.println("分区2 ：" + record.topic() + " : " + record.value());

            for (String msg : data) {
                System.out.println("data value is ：" + msg);
            }

        }
    }

    @Component
    public class ConsumerListener3 {
        @KafkaListener(topics = "test", groupId = "group3333")
//        public void onMessage(List<ConsumerRecord> records, Acknowledgment acknowledgment, Consumer consumer) {
        public void onMessage(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment, Consumer<String, String> consumer) {
//            for (ConsumerRecord<String, String> record : records) {
//                System.out.println("分区33 ：" + record.topic() + " : " + record.value());
//            }

            try {
                System.out.println("分区333 ：" + objectMapper.writeValueAsString(records));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // 添加之后 重启之后不再重复消费消息
            consumer.commitAsync();

        }
    }

    @Component
    public class ConsumerListener4 {
        @KafkaListener(topics = "test", groupId = "group4")
        public void onMessage(List<String> records) {
            for (String record : records) {
                System.out.println("分区444 ：" + record);
            }

//            try {
//                System.out.println(objectMapper.writeValueAsString(records));
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }

            // 添加之后 重启之后不再重复消费消息
//            consumer.commitAsync();

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

    /**
     *
     * properties:
     * {"bootstrapServers":["localhost:9092"],"clientId":null,"properties":{},"consumer":{"ssl":{"keyPassword":null,"keyStoreCertificateChain":null,"keyStoreKey":null,"keyStoreLocation":null,"keyStorePassword":null,"keyStoreType":null,"trustStoreCertificates":null,"trustStoreLocation":null,"trustStorePassword":null,"trustStoreType":null,"protocol":null},"security":{"protocol":null},"autoCommitInterval":"PT1S","autoOffsetReset":"latest","bootstrapServers":null,"clientId":null,"enableAutoCommit":false,"fetchMaxWait":null,"fetchMinSize":null,"groupId":"default-group","heartbeatInterval":null,"isolationLevel":"READ_UNCOMMITTED","keyDeserializer":"org.apache.kafka.common.serialization.StringDeserializer","valueDeserializer":"org.apache.kafka.common.serialization.StringDeserializer","maxPollRecords":500,"properties":{}},"producer":{"ssl":{"keyPassword":null,"keyStoreCertificateChain":null,"keyStoreKey":null,"keyStoreLocation":null,"keyStorePassword":null,"keyStoreType":null,"trustStoreCertificates":null,"trustStoreLocation":null,"trustStorePassword":null,"trustStoreType":null,"protocol":null},"security":{"protocol":null},"acks":"1","batchSize":{"negative":false},"bootstrapServers":null,"bufferMemory":{"negative":false},"clientId":null,"compressionType":null,"keySerializer":"org.apache.kafka.common.serialization.StringSerializer","valueSerializer":"org.apache.kafka.common.serialization.StringSerializer","retries":3,"transactionIdPrefix":null,"properties":{"linger.ms":"5  "}},"admin":{"ssl":{"keyPassword":null,"keyStoreCertificateChain":null,"keyStoreKey":null,"keyStoreLocation":null,"keyStorePassword":null,"keyStoreType":null,"trustStoreCertificates":null,"trustStoreLocation":null,"trustStorePassword":null,"trustStoreType":null,"protocol":null},"security":{"protocol":null},"clientId":null,"properties":{},"failFast":false},"streams":{"ssl":{"keyPassword":null,"keyStoreCertificateChain":null,"keyStoreKey":null,"keyStoreLocation":null,"keyStorePassword":null,"keyStoreType":null,"trustStoreCertificates":null,"trustStoreLocation":null,"trustStorePassword":null,"trustStoreType":null,"protocol":null},"security":{"protocol":null},"cleanup":{"onStartup":false,"onShutdown":false},"applicationId":null,"autoStartup":true,"bootstrapServers":null,"cacheMaxSizeBuffering":null,"clientId":null,"replicationFactor":null,"stateDir":null,"properties":{}},"listener":{"type":"SINGLE","ackMode":"MANUAL_IMMEDIATE","clientId":null,"concurrency":null,"pollTimeout":null,"noPollThreshold":null,"ackCount":null,"ackTime":null,"idleBetweenPolls":"PT0S","idleEventInterval":null,"monitorInterval":null,"logContainerConfig":null,"onlyLogRecordMetadata":true,"missingTopicsFatal":false},"ssl":{"keyPassword":null,"keyStoreCertificateChain":null,"keyStoreKey":null,"keyStoreLocation":null,"keyStorePassword":null,"keyStoreType":null,"trustStoreCertificates":null,"trustStoreLocation":null,"trustStorePassword":null,"trustStoreType":null,"protocol":null},"jaas":{"enabled":false,"loginModule":"com.sun.security.auth.module.Krb5LoginModule","controlFlag":"REQUIRED","options":{}},"template":{"defaultTopic":"test"},"security":{"protocol":null}}
     */


}