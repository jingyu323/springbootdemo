package com.rain.study.hystrix.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * HystrixCommand 指定了对应的方处理方法所以如果controller层是调用的是同一个service方法那么还是会用指定的command去处理
 */
@Service
public class HelloService {

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //增加熔断功能
    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHelloService(String name) {
        return restTemplate.getForObject("http://spring-cloud-producer/hello?name=" + name, String.class);
    }

    //增加熔断功能
    @HystrixCommand(fallbackMethod = "listByHystirx")
    public String sayHelloService2(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-producer");
        String url = String.format("http://%s:%s/hello/?name=%s", serviceInstance.getHost(), serviceInstance.getPort(), name);

        System.out.println("url from :" + url);
        return restTemplate.getForObject(url + name, String.class);
    }

    public String listByHystirx(String name) {
        System.out.println("listUserByHystirx executed...");
        return "listByHystirx exexuted   2222  3333" + name;
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
