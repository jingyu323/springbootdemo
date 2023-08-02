package com.rain;

import io.netty.channel.Channel;

public class NettyClientTest {
    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient("192.168.99.132", 1818);
        //启动client服务
        client.start();
        Channel channel = client.getChannel();
        //消息体
        DeviceProtocol protocol = new DeviceProtocol();
        protocol.setCmd(ConstantValue.CMD_HEART);
        protocol.setVersion(ConstantValue.VERSION_02);
        if(ConstantValue.VERSION_02.equals(ConstantValue.VERSION_02)){
            protocol.setRetain("00000000");
        }
        protocol.setMsgData("485,67.07,,0.99");

        //channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(protocol);
    }

}
