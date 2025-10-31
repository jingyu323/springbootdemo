package com.rain.test;

public class TestMainInteger {
    public static void main(String[] args) {




        Long l = Long.valueOf("998898");
        Long l1 = Long.valueOf("1000");
        Long l2= Long.valueOf("888888");

        System.out.println(l/1000.0);

       String res = formateDouble(l/1000.0);

        System.out.println(res);



    }

    private static String formateDouble(double number) {
        String formattedNumber = String.format("%.2f", number);
        return formattedNumber;
    }
}
