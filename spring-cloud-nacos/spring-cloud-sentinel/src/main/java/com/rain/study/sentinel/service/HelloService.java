package com.rain.study.sentinel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * HystrixCommand 指定了对应的方处理方法所以如果controller层是调用的是同一个service方法那么还是会用指定的command去处理
 */
@Service
public class HelloService {

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    RestTemplate restTemplate;


    //增加熔断功能
    public String sayHelloService(String name) {
        return restTemplate.getForObject("http://spring-cloud-producer/hello?name=" + name, String.class);
    }

    //增加熔断功能
    public String sayHelloService2(String name) {
        return restTemplate.getForObject("http://spring-cloud-producer/hello?name=" + name, String.class);
    }


    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
