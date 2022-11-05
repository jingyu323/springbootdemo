package com.rain.test.thread;

public class MutiThread extends  Thread {
    private int ticket=100;//每个线程都拥有100张票


    public MutiThread (){}
    public MutiThread (String name){
        super(name);
    }

    @Override
    public void run() {
        while(ticket>0){
            System.out.println(ticket--+" is saled by "+Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) {
        MutiThread m1=new MutiThread("Window 1");
        MutiThread m2=new MutiThread("Window 2");
        MutiThread m3=new MutiThread("Window 3");
        m1.start();
        m2.start();
        m3.start();
    }

}
