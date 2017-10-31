package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/6/27.
 */
public class TerminalUpdateBO {
    private String simId;
    private double simRemain;
    private int state;
    private String deviceId;
    private String displayId;

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public double getSimRemain() {
        return simRemain;
    }

    public void setSimRemain(double simRemain) {
        this.simRemain = simRemain;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
        return "TerminalUpdateBO[" +
                "simId='" + simId + '\'' +
                ", simRemain=" + simRemain +
                ", state=" + state +
                ", deviceId='" + deviceId + '\'' +
                ", displayId='" + displayId + '\'' +
                ']';
    }
}
