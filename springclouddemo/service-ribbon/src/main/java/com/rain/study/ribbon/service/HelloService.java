package com.rain.study.ribbon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    RestTemplate restTemplate;


    public String sayHelloService(String name) {
        return restTemplate.getForObject("http://spring-cloud-producer/hello?name="+name,String.class);
    }
}
