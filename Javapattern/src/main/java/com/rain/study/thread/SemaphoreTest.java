package com.rain.study.thread;


import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 信号量 测试
 *
 * 我们这里模拟一个当多个线程并发一段代码的时候，如何控制其访问速度
 *
 * 单模拟并发100个线程去访问一段程序，此时要控制最多同时运行的是10个
 *
 */
public class SemaphoreTest {

    private final static Semaphore MAX_SEMA_PHORE = new Semaphore(10);

    public static void main(String []args) {
        for(int i = 0 ; i < 100 ; i++) {
            final int num = i;
            final Random radom = new Random();
            new Thread() {
                public void run() {
                    boolean acquired = false;
                    try {
                        MAX_SEMA_PHORE.acquire();
                        acquired = true;
                        System.out.println("我是线程：" + num + " 我获得了使用权！" + new Date().getTime());
                        long time = 1000 * Math.max(1, Math.abs(radom.nextInt() % 10));
                        Thread.sleep(time);
                        System.out.println("我是线程：" + num + " 我执行完了！" + new Date().getTime());
                    }catch(Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(acquired) {
                            MAX_SEMA_PHORE.release();
                        }
                    }
                }
            }.start();
        }
    }

}
