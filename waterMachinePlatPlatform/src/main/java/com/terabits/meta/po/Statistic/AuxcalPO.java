package com.terabits.meta.po.Statistic;

public class AuxcalPO {
    private int id;
    private String gmtCreate;
    private Double flow;
    private Double recharge;
    private Double present;
    private Double payment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public Double getRecharge() {
        return recharge;
    }

    public void setRecharge(Double recharge) {
        this.recharge = recharge;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getPresent() {
        return present;
    }

    public void setPresent(Double present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "AuxcalPO{" +
                "id=" + id +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", flow=" + flow +
                ", recharge=" + recharge +
                ", present=" + present +
                ", payment=" + payment +
                '}';
    }
}
