package com.rain.test;

import java.util.Arrays;
import java.util.List;

public class StringName {

    public static void main(String[] args) {

        String name = "2_10X1023_2_10X1024.jpg";
        String[] names = name.split("X");


        System.out.println(names.length);
        if (names.length >2&&names[names.length-1].endsWith(".jpg")){
            for (String string : names) {
                System.out.println(string);
            }
        }


        System.out.println("异物".endsWith("异物"));


        List<String> vchanels = Arrays.asList("L5", "L6", "L7", "L8", "L9");
        if(vchanels.contains("L5")){
            System.out.println(66666);

        }


    }
}
