package com.htkj.station.receiver.scheduler;

import com.htkj.station.receiver.netty.ReceiverServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;

@Component
public class SchedulerTimer {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerTimer.class);

    private static final String s = File.separator;

    private static final String STATE_SUFFIX = "state";

    @Autowired
    private ReceiverServerConfig config;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Async
    @Scheduled(fixedRate = 600000L)
    public void statusMessagesTasks() {

    }

}