package com.rain.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.rain.study.service.SayHelloService;
import org.springframework.stereotype.Component;


@Service(version = "1.0.0",interfaceClass = SayHelloService.class)
@Component
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        return"Hello "+ name;
    }
}
