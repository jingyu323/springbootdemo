package com.rain.test;

public class NoDuplicateSubStr {

    public int lengthOfLongestSubstring(String s) {

        int a_start =0, b_index = 0;
        int length = 0;
        int repeat =0; //下一个字符在子串中的位置
        for (; b_index < s.length(); b_index++) {

            repeat = s.substring(a_start,b_index).indexOf(s.charAt(b_index));
            if(repeat > -1){
    a_start = a_start+repeat+1;
            }

            length =Math.max(length,b_index -a_start+1);

        }

        return length;


    }
}
