package com.rain.netty.demo.nettyclient.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Client01Handler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client01Handler Active");
//        ctx.fireChannelActive();  // 若把这一句注释掉将无法将event传递给下一个ClientHandler
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client01Handler read Message: "+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Client01Handler error: "+cause);
        cause.printStackTrace();
        ctx.close();
    }
}
