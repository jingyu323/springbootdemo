package com.rain.study.consumer.client.impl;


import com.rain.study.consumer.client.HelloConsumerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloConsumerClient HelloConsumerClient;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return HelloConsumerClient.hello(name);
    }

    @RequestMapping("/hello2")
    public String index2() {
        return "---<<< 11";
    }
}
