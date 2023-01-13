package com.rain.test.method;

import java.util.ArrayList;
import java.util.List;

public class TestSubList {

    public static void main(String[] args) {
        List<String> parentList = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            parentList.add(String.valueOf(i));
        }


        System.out.println(parentList.subList(0,3));
        System.out.println(parentList.subList(3,parentList.size()-1));
    }
}
