package com.htkj.station.sender.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler  extends ChannelInboundHandlerAdapter {
    //当前Channel 已从对方读取消息时调用。
    public static List<ChannelFuture> channelFuture = new ArrayList<ChannelFuture>();

    public static  List<EventLoopGroup> eventLoopGroup = new ArrayList();



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        ConstantValue.channelMap.add(ctx);
    }

    /**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 表示channel 处于活动状态, 既刚出生 提示 xx上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + " 上线了~");
    }

    /**
     * 表示channel 处于不活动状态, 既死亡状态 提示 xx离线了
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + " 离线了~");
    }

    public void pubMsg(String msgData, ChannelHandlerContext ctx) {

        ctx.writeAndFlush(msgData);
    }

}
