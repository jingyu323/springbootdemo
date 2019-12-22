package com.rain.test.test1212;

public class MinEatingSpeed2 {

    public int minEatingSpeed(int[] piles, int H) {
        long sum = 0;
        for (int pi:piles){
            sum +=pi;
        }

        long avgSeepd =sum/H;
        if(avgSeepd ==0){
            avgSeepd =1;
        }

        while (avgSeepd<H){

        }

        return 0;

    }

    public static void main(String[] args) {
        int[] arr = {3,6,7,11};
        int H= 8;
        int res = new MinEatingSpeed2().minEatingSpeed(arr,H);

        System.out.println(res);
    }
}
