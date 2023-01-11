package com.rain.test.thread;

public class MutiRunnableNoShareDate implements  Runnable {




    private int ticket=100;//每个线程都拥有100张票
    private String name;
    MutiRunnableNoShareDate(String name){
        this.name=name;
    }
    public void run(){
        while(ticket>0){
            System.out.println(ticket--+" is saled by "+name);
        }
    }




    public static void main(String[] args) {
        MutiRunnableNoShareDate m1=new MutiRunnableNoShareDate("test ");
        MutiRunnableNoShareDate m2=new MutiRunnableNoShareDate("test ");
        MutiRunnableNoShareDate m3=new MutiRunnableNoShareDate("test ");
        Thread t1=new Thread(m1);
        Thread t2=new Thread(m2);
        Thread t3=new Thread(m3);
        t1.start();
        t2.start();
        t3.start();
    }
}
