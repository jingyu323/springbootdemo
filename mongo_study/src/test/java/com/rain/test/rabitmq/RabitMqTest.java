package com.rain.test.rabitmq;

import com.rabbitmq.client.*;
import com.rain.test.common.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Consumer;

public class RabitMqTest {
    private static final String HOST_MQ = "192.168.20.131";

    //  private static final int PORT_MQ = 5672;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123456";


    private static AMQP.Channel channel = null;

    private static AMQP.Connection connection = null;

    private final static String QUEUE_NAME = "q_test_01";

    public static void getConnection(byte[] body) {
        String EXCHANGE_NAME = "MQ_1";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST_MQ);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            /**
             * 这里没有指定交换机，消息将发送给默认交换机，每个队列也会绑定那个默认的交换机，但是不能显示绑定或解除绑定
             *　默认的交换机，routingKey等于队列名称
             */
            channel.basicPublish(EXCHANGE_NAME, "", null, body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test1() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }

    @Test
    public void testSend() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!3333";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }


    @Test
    public void testRecive() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        boolean autoAck = false;
        GetResponse response = channel.basicGet(QUEUE_NAME, autoAck);
        if (response == null) {
            // No message retrieved.
        } else {
            AMQP.BasicProperties props = response.getProps();
            byte[] body = response.getBody();
            System.out.println("ssssssss--" + new String(body));
            long deliveryTag = response.getEnvelope().getDeliveryTag();
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }


    @Test
    public void testReciveDefaultConsumer() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();


        channel.basicConsume(QUEUE_NAME, false, "myConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                               Envelope envelope,
                                               AMQP.BasicProperties properties,
                                               byte[] body)
                            throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        String contentType = properties.getContentType();
                        long deliveryTag = envelope.getDeliveryTag();
                        // (process the message components here ...)
                        channel.basicAck(deliveryTag, false);
                        System.out.println("------" + new String(body));
                    }
                });


        // 声明队列


        //关闭通道和连接
        channel.close();
        connection.close();
    }

}
