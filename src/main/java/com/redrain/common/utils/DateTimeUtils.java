package com.redrain.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/3/29 10:41
 */
public class DateTimeUtils {
    public static String getCurrentTimeStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    public static String getTimeStrByDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(date);
    }

    public static Date addSeconds(Date date, Integer seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * @param date
     * @param day  想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @return
     */
    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 返回月+日格式日期
     *
     * @param pastDays
     * @return
     */
    public static ArrayList<String> pastDayFormatMonthAndDay(int pastDays) {
        ArrayList<String> dateStrlist = new ArrayList<>();
        pastDay(pastDays).forEach((date) -> {
            SimpleDateFormat df = new SimpleDateFormat("MM月dd日");//设置日期格式
            dateStrlist.add(df.format(date));
        });
        return dateStrlist;
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @return 日期数组
     */
    public static ArrayList<Date> pastDay(int pastDays) {
        ArrayList<Date> pastDaysList = new ArrayList<>();
        if (pastDays <= 0) {
            return pastDaysList;
        }
        //我这里传来的时间是个string类型的，所以要先转为date类型的。
        Date date = new Date();
        for (int i = pastDays; i > 0; i--) {
            pastDaysList.add(getSomeDay(date, -i));
        }
        return pastDaysList;
    }
}
