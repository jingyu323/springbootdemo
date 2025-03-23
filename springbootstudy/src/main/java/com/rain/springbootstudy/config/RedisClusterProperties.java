//package com.htkj.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
//public class RedisClusterProperties {
//
//    private Integer maxActive;
//    private Integer maxWait;
//    private Integer maxIdle;
//    private Integer minIdle;
//    private String nodes;
//    private Integer commandTimeout;
//
//    public Integer getMaxActive() {
//        return maxActive;
//    }
//
//    public void setMaxActive(Integer maxActive) {
//        this.maxActive = maxActive;
//    }
//
//    public Integer getMaxWait() {
//        return maxWait;
//    }
//
//    public void setMaxWait(Integer maxWait) {
//        this.maxWait = maxWait;
//    }
//
//    public Integer getMaxIdle() {
//        return maxIdle;
//    }
//
//    public void setMaxIdle(Integer maxIdle) {
//        this.maxIdle = maxIdle;
//    }
//
//    public Integer getMinIdle() {
//        return minIdle;
//    }
//
//    public void setMinIdle(Integer minIdle) {
//        this.minIdle = minIdle;
//    }
//
//    public String getNodes() {
//        return nodes;
//    }
//
//    public void setNodes(String nodes) {
//        this.nodes = nodes;
//    }
//
//    public Integer getCommandTimeout() {
//        return commandTimeout;
//    }
//
//    public void setCommandTimeout(Integer commandTimeout) {
//        this.commandTimeout = commandTimeout;
//    }
//
//    @Override
//    public String toString() {
//        return "RedisPoolProperties{" +
//                "maxActive=" + maxActive +
//                ", maxWait=" + maxWait +
//                ", maxIdle=" + maxIdle +
//                ", minIdle=" + minIdle +
//                '}';
//    }
//}