package com.rain.test.thread;

import org.openjdk.jol.info.ClassLayout;

public class Test1 {
    int a = 10;
    int b = 20;

    static int[] arr = {0, 1, 2};

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        System.out.printf(ClassLayout.parseInstance(test1).toPrintable());
        System.out.printf(ClassLayout.parseInstance(arr).toPrintable());
    }


}
