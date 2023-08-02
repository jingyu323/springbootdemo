package com.rain;

import java.io.IOException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端启动类
 *
 * @author Administrator
 *
 */
public class HeartNettyClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        // 首先，netty通过Bootstrap启动客户端
        Bootstrap client = new Bootstrap();

        // 第1步 定义线程组，处理读写和链接事件，没有了accept事件
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        // 第2步 绑定客户端通道
        client.channel(NioSocketChannel.class);

        // 第3步 给NIoSocketChannel初始化handler， 处理读写事件
        client.handler(new HeartNettyClientFilter());

        // 连接服务端
        Channel future = client.connect("localhost", 8090).sync().channel();

        //给服务端发送数据
        String str = "Hello Netty";
        future.writeAndFlush(str);
        System.out.println("客户端发送数据:" + str);
    }

}