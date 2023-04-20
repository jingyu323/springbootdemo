package com.rain.study.hystrix.controller;

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
//    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping(value = "/sayHi")
    public String sayHi(@RequestParam String name){
        return helloService.sayHelloService(name);
    }


    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
