package com.rain.study.ribbon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Value("${spring.application.name}")
    private String appName;


    public String sayHelloService2(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-producer");
        String url = String.format("http://%s:%s/hello/?name=%s", serviceInstance.getHost(), serviceInstance.getPort(), name);
        System.out.println("request url:" + url);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * loadBalancerClient 是alibaba 项目借鉴的
     *
     * @param name
     * @return
     */
    public String sayHelloService(String name) {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-producer");
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        System.out.println("request url:" + url);
        return restTemplate.getForObject(url, String.class);
    }
}
