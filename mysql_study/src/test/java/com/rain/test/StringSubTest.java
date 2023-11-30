package com.rain.test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringSubTest {
    private static final long HOUR = 24;
    private static final long MINUTE = 60;
    private static final long SECOND = 60;
    private static final long MMCOND = 1000;

    @Test
    public void testFileTime() {
        String dateStart = "2013-02-19 09:29:58";
        String dateStop = "2013-02-19 11:30:58";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //毫秒ms
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffSeconds2 = diff / 1000;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffMinutes2 = diff / 1000 / 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffHours2 = diff / 1000 / 60 / 60;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            //指定小数点后面的位数（2位）
//            double result = (d1 / d2) * 100;
//            BigDecimal bd = new BigDecimal(result);
//            double cycleRate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
            System.out.print(diffHours + " 小时, ");
            System.out.print(diffHours2 + " 小时, ");
            System.out.print(diffMinutes + " 分钟, ");
            System.out.print(diffMinutes2 + " 分钟, ");
            System.out.print(diffSeconds + " 秒.");
            System.out.print(diffSeconds2 + " 秒.");
//            System.out.print(cycleRate + "小数点后两位  秒.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
