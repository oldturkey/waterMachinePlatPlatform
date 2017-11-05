package com.terabits.service;

public interface DebugService {

    /**
     * 将前端传过来的数据全部送到封装在Service里面的方法通过华为平台发送出去
     * @param displayIdS
     * @param openTime
     * @param coldPulse
     * @param hotPulse
     * @param heartRate
     * @throws Exception
     */
    public int debugManipulate(String displayIdS, String openTime, String coldPulse, String hotPulse, String heartRate) throws Exception;
}
