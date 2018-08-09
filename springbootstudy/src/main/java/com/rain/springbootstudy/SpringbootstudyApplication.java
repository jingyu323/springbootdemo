package com.rain.springbootstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rain.springbootstudy.domain")
public class SpringbootstudyApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootstudyApplication.class, args);
		
		
	}
}
