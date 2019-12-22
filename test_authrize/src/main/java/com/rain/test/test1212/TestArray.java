package com.rain.test.test1212;

import java.util.Arrays;

public class TestArray {

    public static void main(String[] args) {
        int[][]  res = new int[2][3];
        for(int i=0;i<res.length;i++){
            System.out.println(Arrays.toString(res[i]));
        }

        int[] xy = {2,3};
        System.out.println(Arrays.toString(xy));

    }
}
