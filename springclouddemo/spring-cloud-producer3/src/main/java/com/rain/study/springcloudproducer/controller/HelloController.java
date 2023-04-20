package com.rain.study.springcloudproducer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component
public class HelloController {
    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Value("${server.port}")
    private String port ;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        logger.info("index param is:{}",name);
        return "hello "+name+"，this is first messge  from HelloController  producer， from port "+port;
    }


}
