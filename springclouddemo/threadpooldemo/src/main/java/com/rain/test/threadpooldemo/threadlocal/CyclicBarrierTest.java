package com.rain.test.threadpooldemo.threadlocal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(() -> {
            try {
                Thread.sleep(10_000);
                System.out.println("t1 在准备 ");
                cyclicBarrier.await();   // 等另外一个一个线程准备好 然后开始做事情
                System.out.println("t1 准备好了  ");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(5_000);
                System.out.println("t2 在准备  ");
                cyclicBarrier.await(); // 等另外一个一个线程准备好 然后开始做事情
                System.out.println("t2 准备好了  ");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();


        System.out.println(" 裁判 在准备 ");
        cyclicBarrier.await();
        System.out.println(" 裁判 准备好了 ");
    }
}
