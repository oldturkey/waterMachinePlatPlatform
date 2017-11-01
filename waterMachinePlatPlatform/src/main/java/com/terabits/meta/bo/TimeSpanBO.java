package com.terabits.meta.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2017/6/27.
 */
public class TimeSpanBO {
    private String beginTime;
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public TimeSpanBO(String beginTime, String endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public TimeSpanBO() {
        this.beginTime = "2000-01-01 00:0:00";
        this.endTime = "2999-12-12 23:59:59";
    }

    public TimeSpanBO setTimeYesterday() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String dateString = formatter.format(date);
        String beginTime = dateString + "00:00:00";
        String endTime = dateString + "23:59:59";
        return new TimeSpanBO(beginTime, endTime);
    }

    @Override
    public String toString() {
        return "TimeSpanBO[" +
                "beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ']';
    }
}
