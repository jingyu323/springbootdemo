#springcloud 流程

``` flow
st=>start: 开始
e=>end: 结束
op=>operation: 操作
sub=>subroutine: 子程序
cond=>condition: 是或者不是?
io=>inputoutput: 输出

st(right)->op->cond
cond(yes)->io(right)->e
cond(no)->sub(right)->op
```


组件介绍：
Erurca server: 注册中心  <br>
Ribbon 负载均衡 <br>
Producer 服务提供者  <br>
Consumer 服务调用者，调用  <br>


# 服务调用流程
前端-》ribbon(实现负载均衡)-》producer（服务提供者）->返回结果

开启服务发现
@EnableDiscoveryClient

#  方法调用流程
##  ribbon 
ribbon  调用producer 使用fenclient 做负载均衡


consumer 调用producer 自动负载均衡,如果需要对consumer负载均衡需要在 ribbon类似实现方法代理
# 注册中心集群
## 开启注册中心集群配置

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.serviceUrl.defaultZone=http://localhost:${server.port}/eureka/,http://localhost:8000/eureka/,http://localhost:8001/eureka


# 实现功能

@EnableDiscoveryClient 
开启服务提供者或消费者，客户端的支持，用来注册服务或连接到如Eureka之类的注册中心