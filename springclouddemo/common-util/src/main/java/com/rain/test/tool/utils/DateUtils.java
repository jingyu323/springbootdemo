package com.rain.test.tool.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author yzg
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
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
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 获取这个月最后一天
     */
    public static Date getMonthLastDate(String period)
    {
        String[] arr = period.split("-");
        String[] arr1 = {"01","03","05","07","08","10","12"};
        String[] arr2 = {"04","06","09","11"};
        if(Arrays.asList(arr1).contains(arr[1])){
            return dateTime("yyyy-MM-dd",period+"-31");
        }else if(Arrays.asList(arr2).contains(arr[1])){
            return dateTime("yyyy-MM-dd",period+"-30");
        }else {
            long value = Long.parseLong(arr[0]);
            if(value%4==0){
                return dateTime("yyyy-MM-dd",period+"-29");
            }else {
                return dateTime("yyyy-MM-dd",period+"-28");
            }
        }
    }
    /**
     * 获取下一个会计区间
     */
    public static String getNextPeriod(String period)
    {
        String[] arr = period.split("-");
        int i = Integer.parseInt(arr[1]);
        String s= null;
        if(i>=1 && i<9){
            i++;
            s= arr[0]+"-0"+i;
        }else if(i>=9 && i<12){
            s= arr[0]+"-"+i;
        }else if(i==12){
            int y = Integer.parseInt(arr[0])+1;
            s= y+"-01";
        }
        return s;
    }

    /**
     * 获取上一个会计区间
     */
    public static String getLastPeriod(String period)
    {
        String[] arr = period.split("-");
        int i = Integer.parseInt(arr[1]);
        String s= null;
        if(i>=2 && i<=10){
            i--;
            s= arr[0]+"-0"+i;
        }else if(i==11 || i==12){
            i--;
            s= arr[0]+"-"+i;
        }else if(i==1){
            int y = Integer.parseInt(arr[0])-1;
            s= y+"-12";
        }
        return s;
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param dateStr
     * @return
     */
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }
    /**
     * 获得某月的天数
     *
     * @param year
     *            int
     * @param month
     *            int
     * @return int
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("01") || month.equals("03") || month.equals("05")
                || month.equals("07") || month.equals("08") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("04") || month.equals("06") || month.equals("09")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        return days;
    }


    // yyyy-MM-dd HH:mm:00
    public static Date getMinuteStart(Date date, int minutes) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date);
        startCal.set(12, startCal.get(12) + minutes);
        startCal.set(13, 0);
        startCal.set(14, 0);
        return startCal.getTime();
    }

    // yyyy-MM-dd HH:mm:59
    public static Date getMinuteEnd(Date date, int minutes) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date);
        startCal.set(12, startCal.get(12) + minutes);
        startCal.set(13, 59);
        startCal.set(14, 999);
        return startCal.getTime();
    }

    // yyyy-MM-dd 0:00:00
    // days=0 今天,-1昨天,1明天下面的都一样
    public static Date getDateStart(Date date, int days) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date);
        startCal.set(5, startCal.get(5) + days);
        startCal.set(11, 0);
        startCal.set(14, 0);
        startCal.set(13, 0);
        startCal.set(12, 0);
        return startCal.getTime();
    }

    // yyyy-MM-dd 23:59:59
    public static Date getDateEnd(Date date, int days) {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(date);
        endCal.set(5, endCal.get(5) + days);
        endCal.set(11, 23);
        endCal.set(14, 59);
        endCal.set(13, 59);
        endCal.set(12, 59);
        return endCal.getTime();
    }

    // yyyy-MM-1 00:00:00
    public static Date getMonthStart(Date date, int n) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date);
        startCal.set(5, 1);
        startCal.set(11, 0);
        startCal.set(14, 0);
        startCal.set(13, 0);
        startCal.set(12, 0);
        startCal.set(2, startCal.get(2) + n);
        return startCal.getTime();
    }

    // yyyy-MM-end 23:59:59
    public static Date getMonthEnd(Date date, int n) {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(date);
        endCal.set(5, 1);
        endCal.set(11, 23);
        endCal.set(14, 59);
        endCal.set(13, 59);
        endCal.set(12, 59);
        endCal.set(2, endCal.get(2) + n + 1);
        endCal.set(5, endCal.get(5) - 1);
        return endCal.getTime();
    }

    // yyyy-1-1 00:00:00
    public static Date getYearStart(Date date, int n) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date);
        startCal.set(2, 0);// JANUARY which is 0;
        startCal.set(5, 1);
        startCal.set(11, 0);
        startCal.set(12, 0);
        startCal.set(14, 0);
        startCal.set(13, 0);
        startCal.set(1, startCal.get(1) + n);
        return startCal.getTime();
    }

    // yyyy-12-31 23:59:59
    public static Date getYearEnd(Date date, int n) {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(date);
        endCal.set(2, 12);
        endCal.set(5, 1);
        endCal.set(11, 23);
        endCal.set(14, 59);
        endCal.set(13, 59);
        endCal.set(12, 59);
        endCal.set(1, endCal.get(1) + n);
        endCal.set(5, endCal.get(5) - 1);
        return endCal.getTime();
    }
    /**
     * 获取某年某月的天数
     *
     * @param year
     *            int
     * @param month
     *            int 月份[1-12]
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static int getCurDaysOfMonth() {
        Date curDate = getNowDate();

        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(curDate), getMonth(curDate) - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期
     *
     * @return int
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
    //yyyy-MM-dd
    public static String getYesterday(String date) {
        String[] arr=date.split("-");
        String day= null;
        if(arr[2].equals("01")){
            if(!arr[1].equals("01")){
                day=dateTime(getMonthLastDate(getLastPeriod(arr[0]+"-"+arr[1])));
            }else {
                int year = Integer.parseInt(arr[0])-1;
                day = year+"-12-31";
            }
        }else {
            int days = Integer.parseInt(arr[2])-1;
            if(days<=9){
                day = arr[0]+"-"+arr[1]+"-0"+days;
            }else {
                day = arr[0]+"-"+arr[1]+"-"+days;
            }
        }
        return day;
    }

    /**
     * 获得当前月份
     *
     * @return int
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     *
     * @return int
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天
     *
     * @param date
     *            Date
     * @return int
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的年
     *
     * @param date
     *            Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的月份，1-12
     *
     * @param date
     *            Date
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     *
     * @param date1
     *            Date
     * @param date2
     *            Date
     * @return long
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }


    public static Date getDateDiif(int dayIndex, int monthIndex, int yearIndex) {
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, yearIndex);//当前时间减去一年，即一年前的时间
        calendar.add(Calendar.MONTH, monthIndex);//当前时间前去一个月，即一个月前的时间
        calendar.add(Calendar.DATE, dayIndex);//当前时间前去一个月，即一个月前的时间
        return calendar.getTime();//获取一年前的时间，或者一个月前的时间
    }

    public static BigDecimal getRestDayRateCurmonth(Date curtime) {
        if (curtime == null) {
            curtime = new Date();
        }
        int year = getYear(curtime);
        int month = getMonth(curtime);
        int totalDays = getDaysOfMonth(year, month);
        int curDay = getDay(curtime);
        int leftDay = totalDays - curDay;
        if (leftDay == 0) {
            return BigDecimal.ZERO;
        }
        // 31-1 = 30 ,从一号开始这样减的话少一天，如果是最后一天开始可以认为这期不用摊销，如果摊销比例为直接跳过不用摊销
        if (curDay != totalDays) {
            leftDay = leftDay + 1;
        }
        BigDecimal rate = new BigDecimal(leftDay).divide(new BigDecimal(totalDays), 2, BigDecimal.ROUND_DOWN);
        return rate;
    }
    //根据会计区间计算月份差
    public static int getSubstratMonth(String startPeriod,String endPeriod){
        String[] arr1 = startPeriod.split("-");
        int startYear = Integer.parseInt(arr1[0]);
        int startMonth = Integer.parseInt(arr1[1]);
        String[] arr2 = endPeriod.split("-");
        int endYear = Integer.parseInt(arr2[0]);
        int endMonth = Integer.parseInt(arr2[1]);
        int num = (endYear-startYear)*12+endMonth-startMonth;
        return num;
    }
    //计算本年期数
    public static int getNumberForYear(String startPeriod,String endPeriod){
        String startYear = startPeriod.substring(0,4);
        String endYear = endPeriod.substring(0,4);
        if(startYear.equals(endYear)){
            return DateUtils.getSubstratMonth(startPeriod,endPeriod);
        }else {
            return DateUtils.getSubstratMonth(endYear+"-01",endPeriod);
        }
    }


    //根据会计区间计算月份差
    public static String getStopPeriod(String period,long num){
        String[] arr = period.split("-");
        long year = Integer.parseInt(arr[0]);
        long month = Integer.parseInt(arr[1]);
        year = year + (month+num)/12;
        month = (month+num)%12;
        String stopPeriod = year+"-"+month;
        return stopPeriod;
    }

    //根据会计区间计算月份差
    public static String getPeriod(Date date){
        String date1 = parseDateToStr("yyyy-MM-dd",date);
        String period =  date1.substring(0,7);
        return period;
    }


    public static void main(String[] args) {
        String str = "2021-03";

        Date cur = stringtoDate(str, YYYY_MM);

        BigDecimal rate = getRestDayRateCurmonth(stringtoDate(str, YYYY_MM_DD));

//        System.out.println(getToday());
//        System.out.println(getCurrentMonth());
//        System.out.println(getCurDaysOfMonth());
        System.out.println(getDaysOfMonth("2021", "01"));
//        System.out.println(getRestDayRateCurmonth(new Date()));

        System.out.println(getSubstratMonth("2020-12","2021-01"));

    }
}
