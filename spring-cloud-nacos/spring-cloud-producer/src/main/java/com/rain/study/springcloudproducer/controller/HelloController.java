package com.rain.study.springcloudproducer.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 服务提供者
 */
@RestController
@Component
public class HelloController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String index(@RequestParam String name, HttpServletRequest request) {
        System.out.println(request.getParameterNames());
        return "hello " + name + "，this is first messge  from HelloController  producer， from port " + port;
    }


    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return "from " + port + "Hello Nacos Discovery " + string;
    }
}


