package com.rain.study.lock;

/**
 * 主要为了测试 volatile  不具有原子性
 * 需要多次执行代码  具有原子性的话 应该是10000
 *
 */
public class VolatileAoticTest {
    private volatile int aa=0;

    void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        aa++;
                    }
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(aa);
    }

    public static void main(String[] args) throws InterruptedException {
       new VolatileAoticTest().test();


    }
}
