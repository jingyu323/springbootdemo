package com.rain.test;

import java.io.File;

public class TestFileRename {
    public static void main(String[] args) {
        String filePath = "d:/sss111";
        String filePath2 = "d:/sss222";

        File file = new File(filePath);
        boolean res = file.renameTo(new File(filePath2));
        System.out.println(res);
    }
}
