package com.doj.server.infrastructure.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 类描述：时间工具类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class DateTimeUtil {

    /**
     * 获取当前UTC 毫秒
     *
     * @return
     */
    public static long currentEpochMilli() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取间隔时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Duration getDuration(Long startTime, Long endTime) {
        Instant startTimeInstant = Instant.ofEpochMilli(startTime);
        Instant endTimeInstant = Instant.ofEpochMilli(endTime);
        Duration duration = Duration.between(startTimeInstant, endTimeInstant);
        return duration;
    }

    /**
     * 时间戳转日期格式
     *
     * @param time
     * @return
     */
    public static String timeStampToDate(Long time, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(time);
    }

    /**
     * 获得当天零时零分零秒
     *
     * @return
     */
    public static long initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当前的结束时间
     *
     * @param timestamp
     * @return
     */
    public static long getEndTimeByDay(long timestamp) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        // 防止毫秒位>500进位秒, 导致日期到第二天
        calendar.set(Calendar.MILLISECOND, 499);
        return calendar.getTimeInMillis();
    }

    /**
     * 开始时间结束时间合法性校验
     * 合法：true
     * 不合法： false
     * @param startTime
     * @param endTime
     * @return
     */
    public static Boolean legalStartTimeAndEndTime(Long startTime, Long endTime) {
        Duration duration = getDuration(startTime, endTime);
        return !duration.isNegative();
    }
}
