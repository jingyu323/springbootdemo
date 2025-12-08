package com.rain.test;

import java.math.BigDecimal;

public class TestDis {
    public static void main(String[] args) {
        String ss  ="/dev/mapper/centos-home   1745044 70923   1674122   5% /home";
        double totalHD = 0;
        double usedHD = 0;
        String str = null;
        String[] strArray = null;
        while ((str = ss) != null) {
            int m = 0;
            strArray = str.split(" ");
            for (String tmp : strArray) {
                if (tmp.trim().length() == 0)
                    continue;
                ++m;
                if (m == 2) {
                    if (!tmp.equals("") && !tmp.equals("0"))
                        totalHD += Double.parseDouble(tmp);
                }
                if (m == 3) {
                    if (!tmp.equals("none") && !tmp.equals("0"))
                        usedHD += Double.parseDouble(tmp);
                }
            }
            ss = null;
        }

        // 保留2位小数
        double precent = (usedHD / totalHD) * 100;
        BigDecimal b1 = new BigDecimal(precent);
        precent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println((int) precent);
    }
}
