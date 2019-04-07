package com.rain.study.test;

public class DeadLockDemo {

    public static  String A= "A";
    public static  String B= "B";

      public static void main(String[] args) {

          TheadA a = new TheadA();
          TheadB b = new TheadB();
          a.start();
          b.start();

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
