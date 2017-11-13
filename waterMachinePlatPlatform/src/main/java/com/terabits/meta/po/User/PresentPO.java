package com.terabits.meta.po.User;

/**
 * Created by Administrator on 2017/11/8.
 */
public class PresentPO {
    private int id;
    //获得赠送金的用户手机号
    private String phone;
    //当次赠送金额
    private double money;
    //当次赠送类型，10表示注册赠送，11表示充值赠送，12表示邀请赠送
    private int type;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "PresentPO[" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", money=" + money +
                ", type=" + type +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ']';
    }
}
