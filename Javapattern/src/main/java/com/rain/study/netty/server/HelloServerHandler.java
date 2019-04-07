package com.rain.study.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;


/**
 * 该类主要是实现了接收客户端发来的消息，并输出到控制台。
 */
public class HelloServerHandler  extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);

        try{
            ByteBuf in = (ByteBuf)msg;

            System.out.print(in.toString(CharsetUtil.UTF_8));



        }finally{
            // 抛弃收到的数据
           ReferenceCountUtil.release(msg);

        }

    }

    /**
     *  当Netty由于IO错误或者处理器在处理事件时抛出异常时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        cause.printStackTrace();
        ctx.close();
    }
}
