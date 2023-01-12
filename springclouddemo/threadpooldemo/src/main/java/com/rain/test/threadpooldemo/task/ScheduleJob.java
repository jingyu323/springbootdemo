package com.rain.test.threadpooldemo.task;


import com.rain.test.threadpooldemo.utils.EhCacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduleJob {
    private static Logger logger = LoggerFactory.getLogger(ScheduleJob.class);

    @Value("${serverInfoURl}")
    private String serverInfoURl;


    @Autowired
    EhCacheUtils ehcacheUtils;


    /**
     * 检查服务器状态信息
     */





    @Scheduled(fixedRate = 1000)
    private void pushFileList() {
        try {
            ehcacheUtils.put("sssss", "ssss");

            logger.info("cache value is:{}", (String) ehcacheUtils.get("sssss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
