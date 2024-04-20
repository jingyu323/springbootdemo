package com.rain.study.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HClient {

    public ChannelFuture connect(String host,int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast("decoder", new StringDecoder());
                        p.addLast("encoder", new StringEncoder());
                        p.addLast("ping", new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                        p.addLast(new ClientHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect(host,port).sync();

        return future;

    }

    public static void main(String[] args) throws InterruptedException {
        HClient client  = new HClient();
        ChannelFuture future = client.connect("127.0.0.1",4473);
        future.channel().writeAndFlush("hello,I am from client 1111");
        while (true){
            Thread.sleep(1000);
            future.channel().writeAndFlush("hello,I am from client");
        }
    }
}
