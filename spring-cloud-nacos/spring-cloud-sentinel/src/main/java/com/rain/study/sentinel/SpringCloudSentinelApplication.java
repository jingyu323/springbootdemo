package com.rain.study.sentinel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 *

 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudSentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSentinelApplication.class,
                args);
    }

    @Bean(name = "remoteRestTemplate")
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
