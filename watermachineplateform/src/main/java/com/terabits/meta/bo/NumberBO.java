package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/6/30.
 */
//查询在线设备时，返回imei号,deviceId号和displayId
public class NumberBO {
    private String imei;
    private String deviceId;
    private String displayId;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    @Override
    public String toString() {
        return "NumberBO[" +
                "imei='" + imei + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", displayId='" + displayId + '\'' +
                ']';
    }
}
