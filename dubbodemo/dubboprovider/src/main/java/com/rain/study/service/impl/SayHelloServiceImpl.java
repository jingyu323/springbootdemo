package com.rain.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.rain.study.service.SayHelloService;
import org.springframework.stereotype.Component;


@Service(interfaceClass = SayHelloService.class)
@Component
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String name) {
        return"Hello "+ name;
    }
}
