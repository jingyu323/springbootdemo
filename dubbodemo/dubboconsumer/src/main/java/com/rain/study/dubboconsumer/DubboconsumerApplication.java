package com.rain.study.dubboconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableDubbo
public class DubboconsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboconsumerApplication.class, args);
  }
}
