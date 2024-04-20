package com.rain.study.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public interface MyWebSocketService {
    void handleFrame(ChannelHandlerContext ctx, WebSocketFrame frame);
}
