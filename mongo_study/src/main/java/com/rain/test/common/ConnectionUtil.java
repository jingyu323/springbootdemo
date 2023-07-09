package com.rain.test.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    private static final String HOST_MQ = "192.168.20.131";

    private static final int PORT_MQ = 5672;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(HOST_MQ);
        //端口
        factory.setPort(PORT_MQ);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
