package com.rain.study.consumer.fallback;


import com.rain.study.consumer.client.HelloConsumerClient;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements HelloConsumerClient {
    public String hello(String name) {
        return "sorry "+name +"  this is from Hystrix info";
    }
}
