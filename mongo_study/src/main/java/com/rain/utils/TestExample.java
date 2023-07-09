package com.rain.utils;

public class TestExample {


    public static void main(String[] args) {

        SnowflakeIdGenerator worker = new SnowflakeIdGenerator(1, 1, 1);
        for (int i = 0; i < 22; i++) {
            System.out.println(worker.nextId());
        }

    }

}
