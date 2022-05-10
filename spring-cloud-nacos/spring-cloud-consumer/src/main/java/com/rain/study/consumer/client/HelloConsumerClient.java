package com.rain.study.consumer.client;


import com.rain.study.consumer.fallback.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/***
 * Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign
 */
@FeignClient(name = "sspring-cloud-producer", fallback = SchedualServiceHiHystric.class)
public interface HelloConsumerClient {


    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);

}
