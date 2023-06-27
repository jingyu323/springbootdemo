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
        // 添加时间轮片，
        timer.newTimeout(new TimerTask() {
              @Override
             public void run(final Timeout timeout) throws Exception {
                              System.out.println("lee");   //打印名字
                             }
          }, 1000, TimeUnit.MILLISECONDS);


        timer.newTimeout(new TimerTask() {
            @Override
            public void run(final Timeout timeout) throws Exception {
                System.out.println("lee2");   //打印名字
            }
        }, 3000, TimeUnit.MILLISECONDS);



        timer.newTimeout(new TimerTask() {
            @Override
            public void run(final Timeout timeout) throws Exception {
                System.out.println("lee3");   //打印名字
            }
        }, 4000, TimeUnit.MILLISECONDS);
        Thread.sleep(10000);

    }
}
