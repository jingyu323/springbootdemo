package com.rain.test.dbindex;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class hashedWheelTimerTest2 {

    public static void main(String[] args) throws InterruptedException {
        final HashedWheelTimer timer = new HashedWheelTimer(
                           Executors.defaultThreadFactory(), 100, TimeUnit.MILLISECONDS, 32);


        for(int i=1;i<65;i++){
            // 添加时间轮片，
            int finalI = i;
            timer.newTimeout(new TimerTask() {
                @Override
                public void run(final Timeout timeout) throws Exception {
                    System.out.println("lee"+ finalI);   //打印名字
                }
            }, 1000*i, TimeUnit.MILLISECONDS);
        }
        System.out.println("task add finished ... ");



        Thread.sleep(10000);

    }
}
