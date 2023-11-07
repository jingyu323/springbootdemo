package com.rain.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.rain.test.*")
public class MysqlStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlStudyApplication.class, args);
    }
}