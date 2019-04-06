package com.rain.study.dubboprovider;


import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo
@DubboComponentScan("com.rain.study.service.impl")
@SpringBootApplication
public class DubboproviderApplication {
  public static void main(String[] args) {
    SpringApplication.run(DubboproviderApplication.class, args);
  }
}


