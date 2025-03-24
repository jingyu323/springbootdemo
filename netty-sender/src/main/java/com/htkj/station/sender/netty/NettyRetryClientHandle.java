package com.htkj.station.sender.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyRetryClientHandle extends ChannelInboundHandlerAdapter {

    private RetryStrategy retryStrategy;

    public NettyRetryClientHandle(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.error("NettyRetryClientHandle===触发通道不活跃, 进行重连!");
        retryStrategy.setConsumer(el->{
            log.info("NettyRetryClientHandle===重连成功,  触发做自己的事情!!!!!!!");
        });
        retryStrategy.processRetryConnect(ctx);
    }
}
