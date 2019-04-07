package com.rain.study.test;

public class DeadLockDemo {

    public static  String A= "A";
    public static  String B= "B";

      public static void main(String[] args) {

          TheadA a = new TheadA();
          TheadB b = new TheadB();
          a.start();
          b.start();

          new DeadLockDemo().deadLock();

      }


       private  void deadLock(){
          Thread threadA = new Thread(new Runnable() {
              @Override
              public void run() {
                  synchronized (A){
                      try {
                          Thread.currentThread().sleep(2000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      synchronized(B){
                          System.out.println("AB");
                      }
                  }
              }
          });

    Thread threadB =
        new Thread(
            new Runnable() {
              @Override
              public void run() {

                synchronized (B) {
                  try {
                    Thread.currentThread().sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }

                  synchronized (B) {
                    System.out.println("  BA  ");
                  }
                }
              }
            });

    threadA.start();
    threadB.start();

       }


}

class  TheadA extends  Thread{
    @Override
    public void run() {

        synchronized (DeadLockDemo.A){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DeadLockDemo.B){
                System.out.println("AB");
            }
        }

    }
}
class  TheadB extends  Thread{
    @Override
    public void run() {
        synchronized (DeadLockDemo.B){

            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (DeadLockDemo.A){
                System.out.println(" B in  A");
            }
        }

    }
}
