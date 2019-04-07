package com.rain.study.impl;

import java.util.concurrent.BlockingQueue;

public class Consumer implements  Runnable {

    protected BlockingQueue<Object> queue;


    public Consumer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {

        try {
            while (true) {

                System.out.println(Thread.currentThread().getId()+ "Consumed resource -  before  take  Queue size now = "
                        + queue.size());
                Object obj = queue.take();
                System.out.println(Thread.currentThread().getId()+ "Consumed resource - after take  Queue size now = "
                        + queue.size());
                take(obj);
            }
        } catch (InterruptedException ex) {
            System.out.println("CONSUMER INTERRUPTED");
        }

    }

    void take(Object obj) {
        try {
            Thread.sleep(100); // simulate time passing
        } catch (InterruptedException ex) {
            System.out.println("Consumer Read INTERRUPTED");
        }
        System.out.println("Consuming object " + obj);
    }

}
