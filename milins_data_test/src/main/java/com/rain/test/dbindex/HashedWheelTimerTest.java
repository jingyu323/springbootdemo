package com.rain.test.dbindex;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;


import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        //在一个格子里面的并不会区分的很细,而会依次顺序执行,所以适用于对时间精度要求不高的任务
        //构建时间轮对象
        HashedWheelTimer timer = new HashedWheelTimer(5, TimeUnit.SECONDS, 10);
        //添加定时任务1,延迟2s执行
        timer.newTimeout((TimerTask) timeout -> {
            System.out.println("任务1执行");
            System.out.println("线程名称:"+Thread.currentThread().getName());

        },2,TimeUnit.SECONDS);
        //添加定时任务2,延迟2s执行
        timer.newTimeout((TimerTask) timeout -> {
            System.out.println("任务2执行");
            System.out.println("线程名称:"+Thread.currentThread().getName());
        },5,TimeUnit.SECONDS);

        //等待定时任务执行完毕后,将时间轮内部工作线程停止,这里只是粗略的等待,也可以使用CountDownLatch
        Thread.sleep(6000);

        timer.stop();


        //构建时间轮对象
        HashedWheelTimer timer1 = new HashedWheelTimer(1, TimeUnit.SECONDS, 10);
        //添加定时任务1
        Timeout newTimeout = timer1.newTimeout((TimerTask) timeout -> {
            System.out.println("任务3执行");
            System.out.println("线程名称:" + Thread.currentThread().getName());

        }, 5, TimeUnit.SECONDS);

        //现在又想取消掉这个任务
        if(!newTimeout.isExpired()){
            newTimeout.cancel();
        }

    }

}
