package com.rain.study.ribbon.controller;


import com.rain.study.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/sayHi")
    public String sayHi(@RequestParam String name){
        return helloService.sayHelloService(name);
    }
    @RequestMapping(value = "/sayHi2")
    public String sayHi2(@RequestParam String name){
        return helloService.sayHelloConsumer(name);
    }
}
