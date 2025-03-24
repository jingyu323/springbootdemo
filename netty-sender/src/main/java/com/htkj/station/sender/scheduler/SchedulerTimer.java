package com.htkj.station.sender.scheduler;


import com.htkj.station.sender.common.Constants;
import com.htkj.station.sender.netty.SenderNettyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class SchedulerTimer {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerTimer.class);

    private static final String s = File.separator;

    private static final String STATE_SUFFIX = "state";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    SenderNettyClient senderNettyClient;



    @Scheduled(fixedRate = 10000)
    public void statusMessagesTasks() {
        System.out.println("执行定时任务: " + LocalDateTime.now());
        System.out.println("timer statusMessagesTasks");
        logger.info("sender msg start...");
        Channel channel = senderNettyClient.getChannel();

        GenericFutureListener sendCallBack = future2 -> {

            if (future2.isSuccess()) {
                logger.info("发送成功!{}", new Date());
            } else {
                logger.info("发送失败!{}", new Date());
                senderNettyClient.reconect();
            }
        };
        ChannelFuture  writeAndFlushFuture =channel.writeAndFlush(" msg  from timer "+  new Date());
        writeAndFlushFuture.addListener(sendCallBack);


    }
}