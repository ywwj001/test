package com.redrain.common.utils;

import com.redrain.common.exception.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Description: User: lin
 */
public final class DateUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YM_FORMAT = "yyyy-MM";
    public static final String YMD_FORMAT = "yyyy-MM-dd";
    public static final String YMD_HMS_SSS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YMD_HMS_SSS_FORMAT2 = "yyyyMMddHHmmssSSS";
    public static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String getCurrentDate(String format) {
        if (StringUtils.isEmpty(format)) {
            format = YMD_FORMAT;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    public static String getStringDate(String format, Date date) {
        return getStringDate(format, date, TimeZone.getDefault());
    }

    public static String getStringDate(String format, Date date, TimeZone timeZone) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
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
     * 根据日期格式动态获取日期
     *
     * @param dateStr
     * @return Date
     */
    public static Date dynamicMatch(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        // 日期格式 yyyy/MM/dd
        if (dateStr.contains("/")) {
            if (dateStr.contains(":")) {
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            } else {
                sdf = new SimpleDateFormat("yyyy/MM/dd");
            }
        } else if (dateStr.contains("-")) {
            if (dateStr.contains(":")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
        } else {
            // 其他格式请添加
        }
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为日期
     *
     * @param format
     * @param strDate
     * @return
     */
    public static Date parseDateString(String format, String strDate) {
        if (StringUtils.isNotEmpty(strDate)) {
            if (strDate.contains("T")) {
                format = UTC_FORMAT;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            try {
                return simpleDateFormat.parse(strDate);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取当前的前一天日期
     *
     * @return
     */
    public static Date getNowPreDate() {
//        LocalDate preDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusDays(1);
//        Instant instant = preDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

//        return Date.from(instant);
        return new Date();
    }

    /**
     * 是否为周六或者周日
     *
     * @param dateStr
     * @return
     */
    public static boolean isWeekend(String dateStr) {
//        Date date = parseDateString(YMD_FORMAT, dateStr);
//        if (date == null) {
//            throw new CustomerException("日期参数为空");
//        }
//        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
//        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
//        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
//            return true;
//        }
        return false;
    }

    /**
     * 校验日期字符串是否符合给定格式
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean isMatchFormatter(String dateStr, String format) {
        boolean isMatched = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            simpleDateFormat.parse(dateStr);
            isMatched = true;
        } catch (Exception e) {
            logger.error("格式化失败", dateStr, format);
        }
        return isMatched;
    }

    /**
     * 获取区间内的日期集合，包括起始时间，时间格式化为 yyyy-MM-dd
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static List<String> getDayListFromScope(String startDate, String endDate) {
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            logger.error("startDate or endDate is null !");
            return null;
        }
        if (startDate.compareTo(endDate) > 0) {
            logger.error("startDate greater than endDate  !");
            return null;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(DateUtils.parseDateString(DateUtils.YMD_FORMAT, startDate));

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(DateUtils.parseDateString(DateUtils.YMD_FORMAT, endDate));

        List<String> dayList = new ArrayList<>();
        while (startCalendar.compareTo(endCalendar) <= 0) {
            dayList.add(DateUtils.getStringDate(DateUtils.YMD_FORMAT, startCalendar.getTime()));
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return dayList;
    }

    /**
     * 获取给定日期年份
     *
     * @param checkDateStr
     * @param format
     * @return
     */
    public static int getYearByStringDate(String checkDateStr, String format) {
//        Date checkDate = DateUtils.parseDateString(format, checkDateStr);
//        return LocalDate.ofInstant(checkDate.toInstant(), ZoneId.systemDefault()).getYear();
        return  1;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int maxDate = calendar.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取两个日期相差的工作日
     */
    public static int getWorkDaysDiff(String strBeginDate, String strEndDate, String format) {
        if (StringUtils.isEmpty(format)) {
            format = YMD_FORMAT;
        }
        Date beginDate = parseDateString(format, strBeginDate);
        Date endDate = parseDateString(format, strEndDate);
        List<String> list = getWorkDayListDiff(beginDate, endDate);
        return list.size();
    }

    public static List<String> getWorkDayListDiff(Date beginDate, Date endDate) {
        List<String> dateList = new ArrayList<>();
        Calendar cl1 = Calendar.getInstance();
        Calendar cl2 = Calendar.getInstance();
        cl1.setTime(beginDate);
        cl2.setTime(endDate);
        while (cl1.compareTo(cl2) <= 0) {
            if (cl1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && cl1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                dateList.add(getStringDate(YMD_FORMAT, cl1.getTime()));
            }
            cl1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateList;
    }

    /**
     * 获取给定时间 减去 daysToSubtract的日期
     *
     * @return
     */
    public static Date getSubtractDate(Date startDate, long daysToSubtract) {
//        if (startDate == null) {
//            startDate = new Date();
//        }
//        LocalDate preDate = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault())
//                .minusDays(daysToSubtract);
//        Instant instant = preDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
//
//        return Date.from(instant);
        return new Date();
    }

    /**
     * 获取一天中剩余的时间（秒数）
     */
    public static Integer getDayRemainingTime(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

    /**
     * 正则表达式验证HH:mm:ss时间格式
     */
    public static boolean checkTimeHour(String str) {
        String timeRegex_2 = "([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        boolean result = Pattern.matches(timeRegex_2, str);
        return result;
    }

    /**
     * 获取当天的开始时间
     */
    public static Long getDayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }

    /**
     * 获取当天的结束时间
     */
    public static Long getDayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis();
    }

    /**
     * 获取本周的开始时间
     *
     * @return
     */
    public static Long getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     *
     * @return
     */
    public static Long getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(getBeginDayOfWeek()));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取某个日期的开始时间
     *
     * @param d
     * @return
     */
    private static Long getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某个日期的结束时间
     *
     * @param d
     * @return
     */
    private static Long getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 判断日期是周几
     *
     * @param pTime
     * @return
     */
    public static int dayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        int w = -1;
        try {
            Date tmpDate = format.parse(pTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(tmpDate);
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekDays[w];
    }

}
