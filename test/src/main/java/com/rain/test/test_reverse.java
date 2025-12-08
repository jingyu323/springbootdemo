package com.rain.test;

import com.alibaba.fastjson2.JSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test_reverse {

    public static void main(String[] args) {
        String strrr=" the sky is  blue";

        String[]  tmp= strrr.split(" ");

        System.out.println(JSON.toJSONString(tmp) );

        List<String> list = Arrays.asList(tmp);
        Collections.reverse(list);


        StringBuilder sb= new StringBuilder();
        for (String s:list){
            if (s.equals("") ){
                sb.append(" ");
            }else {
                sb.append(s).append(" ");
            }

        }

        System.out.println(sb.toString());
    }
}
