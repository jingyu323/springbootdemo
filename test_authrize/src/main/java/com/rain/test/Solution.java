package com.rain.test;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] nums ={2, 7, 11, 15};
        int target = 15;

        int[] res =  twoSum(nums,target);
        System.out.println(Arrays.toString(res));

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums != null && nums.length >0){
            for(int i=1;i<nums.length;i++){
                for(int j=0;j<i;j++){
                    if(target ==nums[i]+ nums[j]){
                        res[0]=i;
                        res[1]=j;
                    }

                }
            }
        }
        return  res;

    }
}
