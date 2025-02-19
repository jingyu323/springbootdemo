//package com.htkj.config;
//
//import io.lettuce.core.TimeoutOptions;
//import io.lettuce.core.cluster.ClusterClientOptions;
//import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
//import io.lettuce.core.resource.ClientResources;
//import io.lettuce.core.resource.DefaultClientResources;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//import java.time.Duration;
//
//@Configuration
//public class RedisReload {
//
//    @Bean
//    public DefaultClientResources lettuceClientResources() {
//        return DefaultClientResources.create();
//    }
//
//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory(RedisProperties redisProperties, ClientResources clientResources) {
//
//        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
//                .enablePeriodicRefresh(Duration.ofSeconds(30))
//                .enableAllAdaptiveRefreshTriggers()
//                .build();
//
//        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
//                .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(10)))
//                .topologyRefreshOptions(topologyRefreshOptions)
//                .build();
//
//        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
//                .clientResources(clientResources)
//                .clientOptions(clusterClientOptions)
//                .build();
//
//        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
//        clusterConfig.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
//        clusterConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));
//
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(clusterConfig, clientConfiguration);
//
//        return lettuceConnectionFactory;
//    }
//
//
//}