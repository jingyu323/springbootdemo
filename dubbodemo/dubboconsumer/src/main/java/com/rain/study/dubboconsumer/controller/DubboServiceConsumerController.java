package com.rain.study.dubboconsumer.controller;


import com.rain.study.dubboconsumer.consumer.HelloConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DubboServiceConsumerController {

    @Autowired
    private HelloConsumer sayHelloService;

    @RequestMapping(value = "/test")
    public String hello(){

        return  sayHelloService.sayHello("test -->>  ");

    }

}
