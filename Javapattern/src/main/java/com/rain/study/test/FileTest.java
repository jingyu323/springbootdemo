package com.rain.study.test;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File file   = new File("");
        long lastTime = file.lastModified();
        System.out.println();
    }
}
