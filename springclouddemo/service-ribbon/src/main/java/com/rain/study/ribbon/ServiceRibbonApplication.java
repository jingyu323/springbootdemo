package com.rain.study.ribbon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.client.RestTemplate;

/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class,
                args);
    }

    @Bean(name="remoteRestTemplate")
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
