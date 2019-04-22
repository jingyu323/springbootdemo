package com.rain.study.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 *
 * 本项目基本是复制service-ribbon 项目之后 添加 hystrix 包实现熔断功能
 *
 * hystrix 功能可以添加在 service 层 也可以添加在 contrller 层，
 * 不过 controller 层不太好，每个调用方法都要添加
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class SpringCloudHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixApplication.class,
                args);
    }

    @Bean(name="remoteRestTemplate")
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
