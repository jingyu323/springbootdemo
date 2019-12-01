package com.rain.test;

public class Reverse {
    public int reverse(int x) {
        if(x/10 ==0){
            return  x;
        }

        long y=0;



        while (x!=0){
            y = y *10;

            if(y>Integer.MAX_VALUE || y < Integer.MIN_VALUE){
                return 0;
            }
             int a = x%10;

            y=y+a;
            x=x/10;

        }

        return  (int) y;

    }
}
