package com.htkj.station.receiver.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@ChannelHandler.Sharable
public class ReceiverNettyChannelInitializer<SocketChannel> extends ChannelInitializer<Channel> {
    protected void initChannel(Channel ch) {

        //得到pipeline
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new LengthFieldBasedFrameDecoder(2097152, 0, 4, 0, 4) });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new LengthFieldPrepender(4) });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ObjectDecoder(ClassResolvers.cacheDisabled(null)) });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ObjectEncoder() });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ReceiverNettyChannelInboundHandlerAdapter() });
        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));

    }
}