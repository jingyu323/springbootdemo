package com.rain.test;

import java.util.ArrayList;
import java.util.List;


/**
 * 总体思路就是 不断比较两个区间，先循环 一重的 再循序 两重的
 */
public class MyCalendarTwo1 {
    List<int[]> calendar;
    List<int[]> overlaps;

    MyCalendarTwo1() {
        calendar = new ArrayList();
        overlaps = new ArrayList();
    }

    public boolean book(int start, int end) {
        for (int[] iv: overlaps) {  // 先判断 有没有三重的 没有三重再走两重的
            if (iv[0] < end && start < iv[1]) return false;
        }
        for (int[] iv: calendar) {
            if (iv[0] < end && start < iv[1])
                overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
        }
        calendar.add(new int[]{start, end});
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo1 m  = new MyCalendarTwo1();

    }
}
