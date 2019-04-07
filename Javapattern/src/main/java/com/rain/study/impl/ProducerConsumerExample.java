package com.rain.study.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 也就是说 blockqueue 同时往进放的时候 也可以往外取，只有在 queue中的消息满足定义的queue长度的时候才会block相关的线程
 */

public class ProducerConsumerExample   {

  public static void main(String[] args) throws InterruptedException  {
      int numProducers = 4;
      int numConsumers = 3;

      BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>(20);


      for (int i = 0; i < numProducers; i++) {
          new Thread(new Producer(myQueue)).start();
      }

      for (int i = 0; i < numConsumers; i++) {
          new Thread(new Consumer(myQueue)).start();
      }


      // Let the simulation run for, say, 10 seconds
      Thread.sleep(10 * 1000);
      System.exit(0);
  }
}
