package com.terabits.constant;

public class TerminalListenerConstant {

    public final static int UNUSE_STATUS = 10;

    public final static int INUSE_STATUS = 13;

    public final static int OFFLINE_STATUS = 23;

    public final static int ONLINE_STATUS = 10; //不对数据库进行改动，只是用作函数判断的标记

    /**
     * 判断设备是否掉线的OverTime
     */
    public static final int OVER_TIME = 2*60;

    /**
     * 线程中等待下次检查的等待时间SleepTime,以微秒为单位
     */
    public static final int SLEEP_TIME =  60 * 1000;
}
