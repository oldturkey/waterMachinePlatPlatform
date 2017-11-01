package com.terabits.utils;

import com.terabits.meta.bo.TimeSpanBO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSpanUtil {
    public static TimeSpanBO generateTodayTimeSpan() {
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String begin = dfs.format(date) + " 00:00:00";
        String end = dfs.format(date) + " 23:59:59";
        timeSpanBO.setBeginTime(begin);
        timeSpanBO.setEndTime(end);
        return timeSpanBO;
    }

    public static TimeSpanBO generateMonthTimeSpan() {
        TimeSpanBO timeSpanBO = new TimeSpanBO();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String begin = dfs.format(date) + "-01" + " 00:00:00";
        dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end = dfs.format(date) + " 23:59:59";
        timeSpanBO.setBeginTime(begin);
        timeSpanBO.setEndTime(end);
        return timeSpanBO;
    }
}
