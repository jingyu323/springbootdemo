package com.rain.study.hystrix;


import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 *
 * 本项目基本是复制service-ribbon 项目之后 添加 hystrix 包实现熔断功能
 *
 * hystrix 功能可以添加在 service 层 也可以添加在 contrller 层，
 * 不过 controller 层不太好，每个调用方法都要添加
 *
 *
 * Dashboard 后台访问地址 http://localhost:9004/hystrix.stream
 */

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
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

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
