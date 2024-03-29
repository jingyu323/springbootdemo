package com.rain.study.ribbon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 *
 * EnableDiscoveryClient 实现自动发现注册  所以不需要配置文件中添加
 *eureka.client.register-with-eureka=true
 eureka.client.fetchRegistry=true
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    @Bean(name="remoteRestTemplate")
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
