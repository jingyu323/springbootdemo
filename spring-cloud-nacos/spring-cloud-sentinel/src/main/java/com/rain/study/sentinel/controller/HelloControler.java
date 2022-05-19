package com.rain.study.sentinel.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.rain.study.sentinel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;


    //增加熔断功能
//    @SentinelResource(value = "sentinel", blockHandler = "requestTooFast")
    @RequestMapping(value = "/sayHi")
    public String sayHi(@RequestParam String name) {
        return helloService.sayHelloService(name);
    }

    @RequestMapping(value = "/sayHi2")
    public String sayHi2(@RequestParam String name) {
        return helloService.sayHelloService2(name);
    }

    public String listByHystirx(String name) {
        System.out.println("listUserByHystirx executed...");
        return "listByHystirx exexuted" + name;
    }


    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

    public String requestTooFast(BlockException e) {
        return "请求太频繁了。。。。";
    }

}
