package com.rain.study.consumer.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "spring-cloud-producer")
public interface  HelloConsumerClient {


    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);

}
