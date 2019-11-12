package com.rain.test;

import java.util.Arrays;

/**
 * 第k 小对 最小值
 */

public class SmallestDistancePair {

    public int smallestDistancePair(int[] nums, int k) {
     Arrays.sort(nums);  //意义很经典

     int lo  =0;
     int  hi = nums[nums.length-1] - nums[0];

     while (lo<hi){
         int mi = (lo + hi) / 2;  //二分
         int count = 0, left = 0;
         for (int right = 0; right < nums.length ; ++right) {
             while (nums[right] - nums[left] > mi) left++;
             count += right - left;
             
         }
         if (count >= k) hi = mi;
         else lo = mi + 1;




     }


     return lo;

    }







}
