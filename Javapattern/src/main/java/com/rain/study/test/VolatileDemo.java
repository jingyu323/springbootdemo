package com.rain.study.test;

public class VolatileDemo {

    private static volatile boolean stopFlag = false;

      public static void main(String[] args) throws InterruptedException {

    new Thread(
            new Runnable() {
              @Override
              public void run() {

                  ///值已经发生了变化 而 该线程没有去住内存中去读值 所以

                while (!stopFlag) {}

                System.out.println(" -------1");
              }
            })
            .start();

          Thread.sleep(1000);


          if(1==1){}

          stopFlag = true;
          System.out.println(" -------2");


      }
}
