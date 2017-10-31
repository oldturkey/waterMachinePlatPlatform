package com.terabits.meta.po.Admin;

public class testPO {
    private int id;
    private String test;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "testPO{" +
                "id=" + id +
                ", test='" + test + '\'' +
                ", money=" + money +
                '}';
    }
}
