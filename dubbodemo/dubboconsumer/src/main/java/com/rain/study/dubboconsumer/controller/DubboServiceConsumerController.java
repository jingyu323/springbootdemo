package com.rain.study.dubboconsumer.controller;


import com.rain.study.dubboconsumer.consumer.HelloConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * nishuo
 */
@RestController
public class DubboServiceConsumerController {

    @Autowired
    private HelloConsumer sayHelloService;


    @RequestMapping(value = "/test")
    public String hello(){
        String a = "";
        if (1 == 1) {

        }

        return  sayHelloService.sayHello("test -->>  ");

    }

}
