package com.rain.netty.demo.nettyclient.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyClientTest {
    private static final Logger logger = LoggerFactory.getLogger(NettyClientTest.class);
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    Channel channel;
    Bootstrap bootstrap;
    
    @PostConstruct
    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .remoteAddress(HOST, PORT)
                .option(ChannelOption.TCP_NODELAY, true)
                //默认是30s, 如果在给定的时间不能成功建立连接或者被丢弃掉，将抛出ConnectTimeoutException
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast("decoder", new StringDecoder());
                        p.addLast("encoder", new StringEncoder());
                        p.addLast(new FirstClientHandler());
//                        p.addLast(new Client02Handler());
                    }
                });
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(HOST, PORT).sync();

            this.bootstrap = bootstrap;
            //Step5: 循环链接服务端
            ChannelFuture f = null;
            boolean connected = false;
            while (!connected) {
                f = bootstrap.connect();
                f.addListener((ChannelFuture futureListener) -> {
                    if (futureListener.isSuccess()) {
                        logger.info("EchoClient客户端连接成功!");
                    } else {
                        logger.info("EchoClient客户端连接失败!");
                    }
                });
                // sync作用: 因为上面的连接到服务器上以及监听都是异步操作, 执行后马上返回, 可能连接还未完全建立, 所以sync在此等待一下
                // f.sync(); 发生错误会抛异常
                f.awaitUninterruptibly();//发生错误不会抛异常
                if (f.isCancelled()) {
                    logger.info("用户取消连接:");
                    return;
                    // Connection attempt cancelled by user
                } else if (f.isSuccess()) {
                    connected = true;
                }
            }
            this.channel = future.channel();
            future.channel().writeAndFlush("Hello Netty Server, I am a common client");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.spliterator();
        }

    }

    public Channel getChanel() {
        return this.channel;
    }

    public Bootstrap getBootstrap() {
        return this.bootstrap;
    }

    public void reconect() {

        //Step5: 循环链接服务端
        ChannelFuture f = null;
        boolean connected = false;
        while (!connected) {
            f = bootstrap.connect();
            f.addListener((ChannelFuture futureListener) -> {
                if (futureListener.isSuccess()) {
                    logger.info("EchoClient客户端连接成功!");
                } else {
                    logger.info("EchoClient客户端连接失败!");
                }
            });
            // sync作用: 因为上面的连接到服务器上以及监听都是异步操作, 执行后马上返回, 可能连接还未完全建立, 所以sync在此等待一下
            // f.sync(); 发生错误会抛异常
            f.awaitUninterruptibly();//发生错误不会抛异常
            if (f.isCancelled()) {
                logger.info("用户取消连接:");
                return;
                // Connection attempt cancelled by user
            } else if (f.isSuccess()) {
                connected = true;
            }
        }
        this.channel = f.channel();

    }
}
