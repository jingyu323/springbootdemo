package com.rain.netty.demo.nettyclient.schdule;


import com.rain.netty.demo.nettyclient.netty.NettyClientTest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class SecheduleSender {
    private static final Logger logger = LoggerFactory.getLogger(SecheduleSender.class);
    @Autowired
    NettyClientTest nettyClientTest;


    @Scheduled(fixedRate = 5000)
    public void sendeMsg() {
        Channel channel = nettyClientTest.getChanel();

        // 发送回调监听
        GenericFutureListener sendCallBack = future2 -> {
            if (future2.isSuccess()) {
                logger.info("发送成功!{}", new Date());
            } else {
                logger.info("发送失败!{}", new Date());
                nettyClientTest.reconect();
            }
        };

        ChannelFuture writeAndFlushFuture = channel.writeAndFlush("Hello Netty Server,   client from timer" + new Date());
        writeAndFlushFuture.addListener(sendCallBack);

    }


}
