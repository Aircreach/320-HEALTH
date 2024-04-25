package com.air.health.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Title: DateUtil
 * @Author Aircreach
 * @Version 1.0.0
 * @Package com.air.health.common.util
 * @Date 2024/4/5 15:30
 * @description:
 */
public class DateUtil {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (strDate == null || StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(strDate, df);
        return Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime date = today.plusWeeks(week);

        LocalDateTime startDate = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endDate = startDate.plusDays(6);
        Date[] dates = new Date[2];
        dates[0] = Date.from(startDate.atZone( ZoneId.systemDefault()).toInstant());
        dates[1] = Date.from(endDate.atZone( ZoneId.systemDefault()).toInstant());
        return dates;
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusSeconds(seconds)).atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        LocalDateTime localDateTime = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusMinutes(minutes)).atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        LocalDateTime localDateTime = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusHours(hours)).atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        LocalDateTime localDateTime = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusDays(days)).atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        LocalDateTime localDateTime = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusWeeks(weeks)).atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        LocalDateTime localDateTime = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusMonths(months)).atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        LocalDateTime localDateTime = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDateTime();
        return Date.from((localDateTime.plusYears(years)).atZone( ZoneId.systemDefault()).toInstant());
    }
}
