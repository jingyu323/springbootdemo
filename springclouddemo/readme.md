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

开启服务发现
@EnableDiscoveryClient

#  方法调用流程
##  ribbon 