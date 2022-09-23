package com.rain.study.springclouddemo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudproviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudproviderApplication.class, args);
	}

}
