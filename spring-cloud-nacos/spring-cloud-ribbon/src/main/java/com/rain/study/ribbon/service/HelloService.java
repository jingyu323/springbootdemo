package com.rain.study.ribbon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestOperations remoteRestTemplate;


    public String sayHelloService(String name) {
        ResponseEntity<String> forEntity = remoteRestTemplate.getForEntity("http://spring-cloud-producer/hello?name=" + name, String.class);
        return "aaaa2323";
    }
}
