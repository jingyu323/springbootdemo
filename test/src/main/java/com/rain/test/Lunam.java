package com.rain.test;

import java.io.UnsupportedEncodingException;

public class Lunam {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String name  = "HXD3D0146_??????ά_01_20220702_234500.mp4";

        System.out.println(new String(name.getBytes("UTF-8"),"GBK"));
        System.out.println(name.getBytes("ISO-8859-1"));


    }
}
