package com.rain.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {


    private final static String TOPIC_NAME = "test";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg");
    }
}
