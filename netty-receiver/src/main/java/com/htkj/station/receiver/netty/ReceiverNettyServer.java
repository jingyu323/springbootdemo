package com.htkj.station.receiver.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ReceiverNettyServer {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverNettyServer.class);

    private int coreCount = Runtime.getRuntime().availableProcessors();

    public void bind(int port, int poolCore, int poolMax) throws Exception {
        logger.info("+ this.coreCount + "+ port);
        NioEventLoopGroup nioEventLoopGroup1 = new NioEventLoopGroup(1);
        NioEventLoopGroup nioEventLoopGroup2 = new NioEventLoopGroup(this.coreCount * poolCore);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap = serverBootstrap.group((EventLoopGroup)nioEventLoopGroup1, (EventLoopGroup)nioEventLoopGroup2);
            serverBootstrap = serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap = (ServerBootstrap)serverBootstrap.option(ChannelOption.SO_BACKLOG, Integer.valueOf(poolMax));
            serverBootstrap = serverBootstrap.childOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
            serverBootstrap = serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true));
            serverBootstrap = serverBootstrap.childHandler((ChannelHandler) new ReceiverNettyChannelInitializer());

            ChannelFuture f = serverBootstrap.bind(port).sync();
            if (f.isSuccess()) {
                logger.info("receiver server start success! port : " + port);
                f.channel().closeFuture().sync();
            }
        } catch (InterruptedException e) {
            logger.info(e.toString());
        } finally {
            nioEventLoopGroup1.shutdownGracefully().sync();
            nioEventLoopGroup2.shutdownGracefully().sync();
        }
    }
}
