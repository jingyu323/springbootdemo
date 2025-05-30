package com.rain.test.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test001 {
    public static void main(String[] args) {
        List<Integer> list =  new ArrayList<>();
        list.add(Integer.valueOf("1"));
        list.add(Integer.valueOf("3"));
        list.add(Integer.valueOf("6"));
        list.add(Integer.valueOf("5"));
        list.add(Integer.valueOf("4"));
        list.add(Integer.valueOf("2"));

        list.add(Integer.valueOf("7"));
        Collections.sort(list);
        System.out.println(list);
    }
}
