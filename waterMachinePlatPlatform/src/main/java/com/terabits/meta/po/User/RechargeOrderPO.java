package com.terabits.meta.po.User;

/**
 * Created by Administrator on 2017/6/27.
 * 每笔交易都需要存入数据库，交易人的openid，该笔交易在微信端的订单号，在平台的订单号，交易金额。
 * 注意统一下单时，会先将不含微信订单号的订单插入数据库，在前端支付成功通知后端时，根据orderId更新该笔交易，填上空缺的tradeno字段。
 * 充值成功后，要更新用户的余额
 */
public class RechargeOrderPO {
    private int id;
    //交易在平台端的订单号
    private String orderId;
    //交易在微信端的订单号
    private String tradeNo;
    //发生该笔交易的用户
    private String openId;
    //交易金额
    private double present;
    private double payment;
    private String gmtCreate;
    private String gmtModified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public double getPresent() {
        return present;
    }

    public void setPresent(double present) {
        this.present = present;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
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
        return "RechargeOrderPO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", openId='" + openId + '\'' +
                ", present=" + present +
                ", payment=" + payment +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                '}';
    }
}
