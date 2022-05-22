官网：
https://github.com/alibaba/Sentinel/tree/v1.8.0
web  适配文档
https://github.com/alibaba/Sentinel/wiki/%E4%B8%BB%E6%B5%81%E6%A1%86%E6%9E%B6%E7%9A%84%E9%80%82%E9%85%8D#web-%E9%80%82%E9%85%8D

sentinel启动：
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.4.jar
必须使用启动命令，否则看不到dashboard

怎么查看一个组件：
1.它是什么？能解决什么问题？
2.有什么优缺点
3.实现原理是啥
4.怎么使用

## 1. 1.8.4的dashboard必须用1.8.4的依赖，需要配置 1.8.4
还需要配置对应的serve地址才能对接上后台
csp.sentinel.dashboard.server=localhost:8080


      <!-- dashborad 依赖结束-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-core</artifactId>
            <version>1.8.4</version>
        </dependency>

 ## 限流参数
 1、我们定义的blockHandler限流方法参数没有加 BlockException，放在末尾
 2、blockHandler限流方法返回值和业务方法返回值不一致
 注意：限流回调方法XXXBlockHandler,应与业务方法参数完全一致，并新增一个BlockException参数，放在末尾
 ## 流控规则添加到资源名称上才能匹配到配置的错误提示
 直接添加到url上的话是系统自定义的
 如果添加到资源名上才是自己指定的
  
 