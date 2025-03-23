package com.rain.test;

public class Main2 {
    public static void main(String[] args){
        //开一个线程，为了不影响主程序运行
        new Thread(){
            @Override
            public void run() {
                JnotifyTest jnotifyTest = new JnotifyTest ();
                jnotifyTest.beginWatch();


            }
        }.start();
        // 主要作用是防止主线程推迟，主线程推迟监听消息无法发送
        while (true) {
            try {
                //主要缓和主线程的执行效率，
                Thread.sleep(600);
            } catch (InterruptedException e) {// ignore it
            }
        }
    }
}
