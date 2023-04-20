package com.rain.test.tool.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimailUtils {
	public static String BigDecimalToString(BigDecimal data) {

		// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
		data = data.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 转化为字符串输出
		return data.toString();

	}
	/**
	 * 只舍不入
	 * */
	public static String BigDecimalToStringDown(BigDecimal data) {

		// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
		data = data.setScale(2, BigDecimal.ROUND_DOWN);
		// 转化为字符串输出
		return data.toString();

	}


	public static BigDecimal StringToBigDecimal(String str) {

		BigDecimal data = new BigDecimal(str);
		return data;

	}

	/**
	 * 把bigdecimal类型的专为bigdecimal类型的 （只保留两位小数，并且直接截断，没有四舍五入）
	 * 
	 * @param num
	 * @return
	 */
	public static BigDecimal BigDecimalToBigDecimal(BigDecimal num) {
		String temData = BigDecimalToStringDown(num);
		return StringToBigDecimal(temData);

	}

	private BigDecimal formatComma2BigDecimal(Object obj) {
		String val = String.valueOf(obj);
		if (val == null)
			return new BigDecimal("0.00");

		val = val.replaceAll(",", "");
		if (!isNumber(val))
			return new BigDecimal("0.00");

		BigDecimal decimal = new BigDecimal(val);
		decimal = decimal.setScale(2, RoundingMode.HALF_UP);

		return decimal;

	}

	private String formatCommaAnd2Point(Object obj) {
		BigDecimal decimal = formatComma2BigDecimal(obj);

		DecimalFormat df = new DecimalFormat("#,###.00");
		String decimalStr = df.format(decimal).equals(".00") ? "0.00" : df
				.format(decimal);
		if (decimalStr.startsWith(".")) {
			decimalStr = "0" + decimalStr;
		}
		return decimalStr;
	}

	private boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}


	public static void main(String[] args) {

		BigDecimal data = new BigDecimal("123123.4252");

		String result = BigDecimalToStringDown(data);

		BigDecimal num = StringToBigDecimal(result);


	}
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;

		return d;
	}

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	public static boolean notZero(BigDecimal decimal){
	    if(decimal.compareTo(BigDecimal.ZERO)==1 || decimal.compareTo(BigDecimal.ZERO)==-1){
	        return true;
        }else
            return false;
    }

	// 这个类不能实例化
	/**
	 * 提供精确的加法运算。
	 *
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 *
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 *
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 *
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/***
	 * 进一法
	 *
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double roundUp(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
	}

	public static BigDecimal addArr(BigDecimal sum,BigDecimal[] bigDecimals){
        for (int i = 0; i <bigDecimals.length ; i++) {
            sum = sum.add(bigDecimals[i]);
        }
        return sum;
    }

    public static BigDecimal subArr(BigDecimal sum,BigDecimal[] bigDecimals){
        for (int i = 0; i <bigDecimals.length ; i++) {
            sum = sum.subtract(bigDecimals[i]);
        }
        return sum;
    }

    public static BigDecimal addAndSub(BigDecimal sum,BigDecimal[] addArr,BigDecimal[] subArr){
        sum = addArr(sum,addArr);
        sum = subArr(sum,subArr);
        return sum;
    }

}
