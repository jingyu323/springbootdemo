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

}
