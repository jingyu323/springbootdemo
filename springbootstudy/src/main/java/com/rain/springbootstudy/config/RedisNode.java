//package com.htkj.config;
//
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@Data
//@ConfigurationProperties(prefix = "spring.redis.cluster")
//public class RedisNode {
//
//    private String nodes;
//    private Integer commandTimeout;
//    private Integer expireSeconds;
//
//
//    @Override
//    public String toString() {
//        return "RedisNode{" +
//                "nodes=" + nodes +
//                '}';
//    }
//}