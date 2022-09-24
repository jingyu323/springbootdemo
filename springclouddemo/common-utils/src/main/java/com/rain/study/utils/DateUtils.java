package com.rain.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 将数据库中time类型的字段转化成DateTime类型的字段
     *
     * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date dateTrans(Date date) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");

		String str1 = sdf2.format(new Date());
		String str2 = sdf3.format(date);

		return sdf1.parse(str1 + " " + str2);
	}

	public static Date dateAdd(Date date,String type,Integer interval){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (type){
            case "YEAR" :
                calendar.add(Calendar.YEAR,interval);
                break;
            case "MONTH" :
                calendar.add(Calendar.MONTH,interval);
                break;
            case "DATE" :
                calendar.add(Calendar.DATE,interval);
                break;
            case "MIN" :
                calendar.add(Calendar.MINUTE,interval);
        }
        return calendar.getTime();
    }
	/**
	 * 计算结束时间经过秒数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int calSeconds(Date startTime,Date endTime) {
		long a = endTime.getTime();
		long b = startTime.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }


    public static final Date parseStrToDate(final String format, final String date) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }
}
