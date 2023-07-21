package com.rain;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class DeviceEncoder extends MessageToByteEncoder<DeviceProtocol> {

	@Override
	protected void encode(ChannelHandlerContext ctx, DeviceProtocol msg, ByteBuf out) throws Exception {
		out.writeBytes(msg.toBytes());
	}

}
