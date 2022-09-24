package com.rain.study.utils;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtils {

    public static Double addDouble(Double a,Double b,int scale){
        if(a == null){
            a = 0d;
        }
        if(b == null){
            b = 0d;
        }
        BigDecimal bda = new BigDecimal(a);
        BigDecimal bdb = new BigDecimal(b);
        return bda.add(bdb).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double subtractDouble(Double a,Double b,int scale){
        if(a == null){
            a = 0d;
        }
        if(b == null){
            b = 0d;
        }
        BigDecimal bda = new BigDecimal(a);
        BigDecimal bdb = new BigDecimal(b);
        return bda.subtract(bdb).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double multiplyDouble(Double a,Double b,int scale){
        if(a == null){
            a = 0d;
        }
        if(b == null){
            b = 0d;
        }
        BigDecimal bda = new BigDecimal(a);
        BigDecimal bdb = new BigDecimal(b);
        return bda.multiply(bdb).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double divideDouble(Double a,Double b,int scale){
        if (a == null) {
            a = 0d;
        }
        if (b == null) {
            b = 1d;
        }
        BigDecimal bda = new BigDecimal(a);
        BigDecimal bdb = new BigDecimal(b);
        return bda.divide(bdb, scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static boolean isNumericReg(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String number) {

        int index = number.indexOf(".");
        if (index < 0) {
            return isNumericReg(number);
        } else {
            String num1 = number.substring(0, index);
            String num2 = number.substring(index + 1);

            return isNumericReg(num1) && isNumericReg(num2);
        }

    }

}
