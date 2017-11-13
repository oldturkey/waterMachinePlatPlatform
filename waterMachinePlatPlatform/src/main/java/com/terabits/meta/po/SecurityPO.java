package com.terabits.meta.po;

import java.sql.Timestamp;

public class SecurityPO {

    public String name;
    public Timestamp outDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getOutDate() {
        return outDate;
    }

    public void setOutDate(Timestamp outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString() {
        return "SecurityPO{" +
                "name='" + name + '\'' +
                ", outDate=" + outDate +
                '}';
    }
}
