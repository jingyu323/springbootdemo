package com.htkj.station.sender.netty;


import com.htkj.station.sender.config.SenderConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


@Configuration
public class SenderNettyClient {
    private static final Logger logger = LoggerFactory.getLogger(SenderNettyClient.class);


    @Setter
    private Channel channel;
    @Resource
    SenderConfig config ;

    private int coreCount = Runtime.getRuntime().availableProcessors();

    Bootstrap bootstrap;


    @PostConstruct
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup(this.coreCount *config.getPoolCore());

        try {

            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .remoteAddress(config.getHost(), config.getPort())
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            //得到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入相关handler


                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new LengthFieldBasedFrameDecoder(2097152, 0, 4, 0, 4) });
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new LengthFieldPrepender(4) });
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ObjectDecoder(ClassResolvers.cacheDisabled(null)) });
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ObjectEncoder() });
                            //加入自定义的handler
                            pipeline.addLast(new ClientHandler());
                            pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));

                        }
                    });

            ChannelFuture future = bootstrap.connect(config.getHost(), config.getPort()).sync();
            this.bootstrap = bootstrap;
            future.addListener(new ChannelFutureListener() {


                @Override
                public void operationComplete(ChannelFuture arg0) throws Exception {
                    if (future.isSuccess()) {

                        System.out.println("连接服务器成功");
                        //得到channel

                        System.out.println("========"+channel.id().toString());

                    } else {
                        future.cause().printStackTrace();
                        group.shutdownGracefully(); //关闭线程组
                        throw new Exception("连接Netty服务器失败");
                    }
                }
            });

            Channel channel = future.channel();
            this.channel = channel;

            channel.writeAndFlush("Hello Netty Server, I am a common client");
        }catch (Exception e)  {
            group.shutdownGracefully();
        }
    }


    public void reconect() {

        if(this.bootstrap == null){
            EventLoopGroup group = new NioEventLoopGroup(this.coreCount *config.getPoolCore());

            this.bootstrap = new Bootstrap()
                    .group(group)
                    .remoteAddress(config.getHost(), config.getPort())
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            //得到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入相关handler
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new LengthFieldBasedFrameDecoder(2097152, 0, 4, 0, 4) });
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new LengthFieldPrepender(4) });
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ObjectDecoder(ClassResolvers.cacheDisabled(null)) });
                            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ObjectEncoder() });
                            //加入自定义的handler
                            pipeline.addLast(new ClientHandler());
                            pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));

                        }
                    });
        }
        //Step5: 循环链接服务端
        ChannelFuture f = null;
        boolean connected = false;
        while (!connected) {
            f = this.bootstrap.connect();
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

    public Channel getChannel() {
        return channel;
    }
}
