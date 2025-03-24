package com.htkj.station.receiver.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.zip.GZIPInputStream;

public class ReceiverNettyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverNettyChannelInboundHandlerAdapter.class);

    private static final String SEP = File.separator;

    private static final String MESSAGE_SUFFIX = "message";

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("注册通道id:"+ ctx.channel().id().toString());
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, String rotate, int targetWidth, int targetHeight, String isHorz, String isVert) {
        ImageProcessor ip = new ImageProcessor();
        originalImage = ImageProcessor.rotate(originalImage, Integer.parseInt(rotate));
        originalImage = ip.flip(originalImage, Boolean.parseBoolean(isHorz), Boolean.parseBoolean(isVert));
        return originalImage;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            logger.info("msg from client:"+msg.toString());
        } catch (Exception e) {
            System.out.println("channelRead--" + e.toString());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws IOException {
        logger.info("exceptionCaught");
        cause.printStackTrace();
        ReceiverNettyChannel receiverNettyChannel = ReceiverNettyChannelCache.get("server:" + ctx.channel().id().toString());
        if (receiverNettyChannel != null)
            ReceiverNettyChannelCache.remove("server:" + ctx.channel().id().toString());
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().read();
        InetSocketAddress inSocket = (InetSocketAddress)ctx.channel().remoteAddress();
        String clientIp = inSocket.getAddress().getHostAddress();
        logger.info("channelActive:" + clientIp + ctx.name());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        InetSocketAddress inSocket = (InetSocketAddress)ctx.channel().remoteAddress();
        String clientIp = inSocket.getAddress().getHostAddress();
        logger.info("channelInactive:" + clientIp);
        ReceiverNettyChannel receiverNettyChannel = ReceiverNettyChannelCache.get("server:" + ctx.channel().id().toString());
        if (receiverNettyChannel != null)
            ReceiverNettyChannelCache.remove("server:" + ctx.channel().id().toString());
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        InetSocketAddress inSocket = (InetSocketAddress)ctx.channel().remoteAddress();
        String clientIp = inSocket.getAddress().getHostAddress();
        ctx.close();
        logger.info("userEventTriggered:" + clientIp);
    }
}
