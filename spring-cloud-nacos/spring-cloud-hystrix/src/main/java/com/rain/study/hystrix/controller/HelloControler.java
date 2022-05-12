package com.rain.study.hystrix.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rain.study.hystrix.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;


    //增加熔断功能
    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping(value = "/sayHi")
    public String sayHi(@RequestParam String name) {
        return helloService.sayHelloService(name);
    }

    @RequestMapping(value = "/sayHi2")
    @HystrixCommand(fallbackMethod = "listByHystirx", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //请求次数
    })
    public String sayHi2(@RequestParam String name) {
        return helloService.sayHelloService(name);
    }

    public String listByHystirx() {
        System.out.println("listUserByHystirx executed...");
        return "listByHystirx exexuted";
    }


    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

}
