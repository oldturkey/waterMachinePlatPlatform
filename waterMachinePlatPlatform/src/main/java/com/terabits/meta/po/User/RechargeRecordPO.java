package com.terabits.meta.po.User;

public class RechargeRecordPO {
    private int id;
    private String openId;
    private Double payment;
    private String orderId;
    private String tradeno;
    private String gmtCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeno() {
        return tradeno;
    }

    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "RechargeRecordPO{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", payment=" + payment +
                ", orderId='" + orderId + '\'' +
                ", tradeno='" + tradeno + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                '}';
    }
}
