package com.htkj.station.sender.netty;

import com.htkj.station.sender.config.SenderConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
@Slf4j
//@Component
public class NettyRetryClientTwo {

    private static final Logger logger = LoggerFactory.getLogger(SenderNettyClient.class);

    private Channel channel;
//    @Resource
    SenderConfig config ;

    private int coreCount = Runtime.getRuntime().availableProcessors();

    public   void start() {
        EventLoopGroup workerGroup = new NioEventLoopGroup(this.coreCount * config.getPoolCore());
        RetryStrategy retryStrategy = new RetryStrategy();
        retryStrategy.setRetryAddTime(1);
        retryStrategy.setRetryMaxCount(3);
        Bootstrap bootstrap = retryStrategy.buildBootstrapAndReturnBootstrap(new Bootstrap());

        bootstrap = bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ClientHandler());
                        // 添加自定义重试连接处理器
                        pipeline.addLast(new NettyRetryClientHandle(retryStrategy));
                    }
                });

        bootstrap.remoteAddress(new InetSocketAddress(config.getHost(), config.getPort()));

        try {
            ChannelFuture channelFuture = bootstrap.connect();
            // 添加监听, 最初启动时候是否成功!
            channelFuture.addListener(el->{
                // 失败则进行重试!
                if (!el.isSuccess()) {
                    ChannelHandlerContext context = channelFuture.channel().pipeline().context(NettyRetryClientHandle.class);
                    retryStrategy.processRetryConnect(context);
                }else {
                    log.error("NettyTcpClient-启动成功了!!!");
                }
            });

            channelFuture.sync().channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("NettyTcpClient-发生异常, 信息:", e);
        }
    }


    public Channel getChannel() {
        return channel;
    }
}
