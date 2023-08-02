package com.rain;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class DeviceDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// 可读长度大于等于报文头长度
		if(in.readableBytes() >= ConstantValue.BASE_LENGTH) {
	        // 防止socket字节流攻击  
            // 防止，客户端传来的数据过大  
            // 因为，太大的数据，是不合理的  
            if (in.readableBytes() > 2048) {  
                in.skipBytes(in.readableBytes());  
            }
            // 记录包头开始的index  
            int beginReader;
            while(true) {
            	// 获取包头开始的index  
            	beginReader = in.readerIndex();
            	// 标记抱头开始index
            	in.markReaderIndex();
            	// 读到了协议的开始标志，结束while循环 
            	byte[] headNode = new byte[2];
            	in.readBytes(headNode,0,2);
            	if(ByteBufUtil.hexDump(headNode).equals(ConstantValue.HEAD_NODE)) {
            		break;
            	}
                // 未读到包头，略过一个字节  
                // 每次略过，一个字节，去读取，包头信息的开始标记  
                in.resetReaderIndex();  
                in.readByte();
                // 当略过，一个字节之后，  
                // 数据包的长度，又变得不满足  
                // 此时，应该结束。等待后面的数据到达  
                if (in.readableBytes() < ConstantValue.BASE_LENGTH) {  
                    return;  
                }  
            }
            //除去头结点的头报文
            byte[] head = new byte[ConstantValue.BASE_LENGTH-2];
            in.readBytes(head);
            String headMsg = ByteBufUtil.hexDump(head);
            //数据长度
            int length = Integer.parseInt(headMsg.substring(headMsg.length()-4),16);
            
            if(in.readableBytes() < length) {
            	in.readerIndex(beginReader);//数据包未接收完整,还原读指针
            	return;
            }
            byte[] data = new byte[length];
            in.readBytes(data);
            //数据包转换为字符串
            String dataMsg = new String(data,StandardCharsets.UTF_8);
            String version = headMsg.substring(0,2);//协议版本号
            String cmd = headMsg.substring(2,4);//操作符
            String clientId = "";
            if("02".equals(version)){
                clientId = headMsg.substring(4, 24);
            }else {
                clientId = headMsg.substring(4, 20);
            }
            String devCode = CodeUtils.hexStr2Str(clientId);//获取网关编码
            
            DeviceProtocol protocol = new DeviceProtocol();
            protocol.setDataLength(length);
            protocol.setCmd(cmd);
            protocol.setVersion(version);
            protocol.setMsgData(dataMsg);
            out.add(protocol);
		}
		
	}

}
