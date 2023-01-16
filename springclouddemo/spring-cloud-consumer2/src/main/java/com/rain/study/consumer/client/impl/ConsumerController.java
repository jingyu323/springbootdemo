package com.rain.study.consumer.client.impl;


import com.rain.study.consumer.client.HelloConsumerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 */
@RestController
public class ConsumerController {
    public static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    HelloConsumerClient helloConsumerClient;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        logger.info("Consumer 2  hello  para is:{}",name);
        return helloConsumerClient.hello(name);
    }

    @RequestMapping("/hello2")
    public String index2() {
        logger.info("hello2 .............." );
        return "---<<< 11";
    }
}
