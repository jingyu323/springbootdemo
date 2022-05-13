package com.rain.study.ribbon;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRibbonApplication {


    @Bean(name = "remoteRestTemplate")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //实例化 RestTemplate 实例
//    @Bean
//    public RestTemplate restTemplate() {
//
//        return new RestTemplate();
//    }
    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class,
                args);
    }

}
