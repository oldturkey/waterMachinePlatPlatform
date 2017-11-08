package com.terabits.meta.vo;

/**
 * Created by Administrator on 2017/11/8.
 */
public class UserConsumeVO {
    //该笔消费产生的时间
    private String gmtCreate;
    //该笔消费对应的设备
    private String displayid;
    //发起该笔消费的用户
    private String phone;
    //该笔消费的金额
    private Double payment;


    public String getGmtcreate() {
        return gmtCreate;
    }

    public void setGmtcreate(String gmtcreate) {
        this.gmtCreate = gmtcreate;
    }

    public String getDisplayid() {
        return displayid;
    }

    public void setDisplayid(String displayid) {
        this.displayid = displayid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "UserConsumeVO{" +
                "gmtcreate='" + gmtCreate + '\'' +
                ", displayid='" + displayid + '\'' +
                ", phone='" + phone + '\'' +
                ", payment=" + payment +
                '}';
    }
}
