package com.terabits.meta.vo;

import com.terabits.meta.po.User.UserPO;

public class UserVO{
    private String phone;
    private String openid;
    private String nickname;
    private int sex;
    private String location;
    private String time;
    private double remain;
    private double consume;
    private double recharge;
    private double present;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
    }

    public double getConsume() {
        return consume;
    }

    public void setConsume(double consume) {
        this.consume = consume;
    }

    public double getRecharge() {
        return recharge;
    }

    public void setRecharge(double recharge) {
        this.recharge = recharge;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "phone='" + phone + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", remain=" + remain +
                ", consume=" + consume +
                ", recharge=" + recharge +
                ", present=" + present +
                '}';
    }
}
