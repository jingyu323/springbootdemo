package com.rain.test.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RandmTest {
    private static Logger logger = LoggerFactory.getLogger(RandmTest.class);
    public static Map<String, Long> status = new ConcurrentHashMap<String, Long>();
    public static void main(String[] args) throws InterruptedException {


       for (int i=0;i<100;i++){

           Thread.sleep(1000);
            long cur = SystemClock.now();

           status.put("ssss",cur);

           System.out.println("cur:"+cur+","+status);

       }


    }
}
