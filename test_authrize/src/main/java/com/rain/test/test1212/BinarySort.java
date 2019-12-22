package com.rain.test.test1212;

public class BinarySort {

    public static int binarySort(int[]  arr,int target){

        int left = 0;
        int  right = arr.length-1;

        while (left<right){
            int mid = (left+right) >>> 1;
            if(arr[mid]>target){
                right = mid-1;
            }else if(arr[mid]<target){
                left = mid+1;
            }else if(arr[mid]==target ){
                return  mid;
            }

        }

        return  -1;


    }
}
