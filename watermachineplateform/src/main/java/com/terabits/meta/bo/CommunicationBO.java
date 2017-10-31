package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/6/30.
 */
public class CommunicationBO {
    private String imei;
    private String deviceId;

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

    @Override
    public String toString() {
        return "CommunicationBO[" +
                "imei='" + imei + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ']';
    }
}
