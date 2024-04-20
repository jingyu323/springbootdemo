package com.rain.test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

public class BasicTest {


    @Test
    public void testRand() {

        System.out.println("Byte.SIZE=" + Byte.SIZE / 8);
        System.out.println("Character.SIZE=" + Character.SIZE / 8);
        System.out.println("Short.SIZE=" + Short.SIZE / 8);
        System.out.println("Integer.SIZE=" + Integer.SIZE / 8);
        System.out.println("Long.SIZE=" + Long.SIZE / 8);
        System.out.println("Float.SIZE=" + Float.SIZE / 8);
        System.out.println("Double.SIZE=" + Double.SIZE / 8);
    }

    @Test
    public void deadlockTest() {

        Object object1 = new Object();
        Object object2 = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (object1) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                }
            }
        }, "deadlock-demo-1");

        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (object2) {
                synchronized (object1) {
                }
            }
        }, "deadlock-demo-2");
        t2.start();
    }

}
