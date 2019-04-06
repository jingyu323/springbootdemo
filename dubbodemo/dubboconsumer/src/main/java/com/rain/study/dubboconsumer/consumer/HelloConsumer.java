package com.rain.study.dubboconsumer.consumer;


import com.alibaba.dubbo.config.annotation.Reference;
import com.rain.study.service.SayHelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloConsumer {
    @Reference(version = "1.0.0")
    private SayHelloService sayHelloService;


    public  String sayHello(String name){
         return sayHelloService.sayHello(name);

    }

}
