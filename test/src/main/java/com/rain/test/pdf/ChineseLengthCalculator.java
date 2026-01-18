package com.rain.test.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseLengthCalculator {



    public static int calculateLength(String input) {
        int length = 0;
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5\\p{Punct}]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            length += 2; // 中文和符号按2计
        }
        return length + input.replaceAll("[\\u4e00-\\u9fa5\\p{Punct}]", "").length(); // 英文按1计
    }

    public static void main(String[] args) {
        String text = "（1）　 收缩压≥180mmHg 和 / 或舒张压≥110mmHg，出现身体不适的症状啊啊啊噶及考核结果哈克干哈更何况";
//        System.out.println(text.substring( 0,61));
        System.out.println("计算长度: " + calculateLength(text)); // 输出50


        List<String> ct =  new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {

            sb.append(c);
            int length = calculateLength(sb.toString());
            if (length >= 61) {
                ct.add(sb.toString());
                sb = new StringBuilder();
            }


        }
        ct.add(sb.toString());

        for (String s : ct) {
            System.out.println(s);
        }

        System.out.println();
    }
}
