官网：
https://github.com/alibaba/Sentinel/tree/v1.8.0

sentinel启动：
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.4.jar
必须使用启动命令，否则看不到dashboard

怎么查看一个组件：
1.它是什么？能解决什么问题？
2.有什么优缺点
3.实现原理是啥
4.怎么使用

## 1. 1.8.4的dashboard必须用1.8.4的依赖，需要配置 1.8.4
还需要配置对应的serve地址才能对接上 后台
csp.sentinel.dashboard.server=localhost:8080


      <!-- dashborad 依赖结束-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-core</artifactId>
            <version>1.8.4</version>
        </dependency>

 