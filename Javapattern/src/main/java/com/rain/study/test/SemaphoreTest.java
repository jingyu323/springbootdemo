package com.rain.study.test;


import java.util.Random;
import java.util.concurrent.Semaphore;

/***
 * 信号量练习
 *
 * 信号量用来控制同时执行的线程数量
 */
public class SemaphoreTest {
    private final static Semaphore MAX_SEMA_PHORE = new Semaphore(10);
    public static void main(String []args) {

        for(int i=0;i<100;i++){
            final int num = i;
            final Random radom = new Random();

            new Thread() {
                @Override
                public void run() {
                    boolean acquired = false;

                    try {
                        MAX_SEMA_PHORE.acquire();
                        acquired = true;
                        System.out.println("我是线程：" + num + " 我获得了使用权！" );
                        long time = 1000 * Math.max(1, Math.abs(radom.nextInt() % 10));
                        Thread.sleep(time);
                        System.out.println("我是线程：" + num + " 我执行完了！");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally{
                        if(acquired) {
                            MAX_SEMA_PHORE.release();
                        }
                    }


                }
            }.start();


        }





    }


}
