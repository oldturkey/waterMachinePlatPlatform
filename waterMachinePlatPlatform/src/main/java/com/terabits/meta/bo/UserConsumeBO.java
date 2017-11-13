package com.terabits.meta.bo;

/**
 * Created by Administrator on 2017/11/8.
 */
public class UserConsumeBO {
    private String displayid;
    private String location;
    private String beginTime;
    private String endTime;
    private String phone;

    public String getDisplayid() {
        return displayid;
    }

    public void setDisplayid(String displayid) {
        this.displayid = displayid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserConsumeBO{" +
                "displayid='" + displayid + '\'' +
                ", location='" + location + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
