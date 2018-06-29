package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/13 0013.
 */

public class RechangeDetail implements Serializable{


    /**
     * single_max : 20000.0
     * balance : 0.0
     * bankno : 6228483625847528563
     * alias : ABC
     * day_max : 500000.0
     * other_phone : 15026554967
     */

    private String single_max;
    private String balance;
    private String bankno;
    private String alias;
    private String day_max;
    private String other_phone;

    public String getSingle_max() {
        return single_max;
    }

    public void setSingle_max(String single_max) {
        this.single_max = single_max;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDay_max() {
        return day_max;
    }

    public void setDay_max(String day_max) {
        this.day_max = day_max;
    }

    public String getOther_phone() {
        return other_phone;
    }

    public void setOther_phone(String other_phone) {
        this.other_phone = other_phone;
    }

    @Override
    public String toString() {
        return "RechangeDetail{" +
                "single_max='" + single_max + '\'' +
                ", balance='" + balance + '\'' +
                ", bankno='" + bankno + '\'' +
                ", alias='" + alias + '\'' +
                ", day_max='" + day_max + '\'' +
                ", other_phone='" + other_phone + '\'' +
                '}';
    }
}
