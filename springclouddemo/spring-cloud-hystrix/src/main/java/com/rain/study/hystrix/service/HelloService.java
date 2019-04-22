package com.rain.study.hystrix.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    RestTemplate restTemplate;



    //增加熔断功能
    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHelloService(String name) {
        return restTemplate.getForObject("http://spring-cloud-producer/hello?name="+name,String.class);
    }


    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
