package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author hgg-pc
 * @version v2.0.0 ${DATA} 15:10 HAOZHUOKEJI
 * @email heguogui2013@163.com
 */

public class WithDrawBean implements Serializable{


    /**
     * withdraw_id :
     * single_max : 20000.0
     * balance : 0.0
     * bankno : 6228485236523654175
     * alias : ABC
     * day_max : 500000.0
     * alias_name : 农业银行
     */

    private String withdraw_id;
    private String single_max;
    private String balance;
    private String bankno;
    private String alias;
    private String day_max;
    private String alias_name;

    public String getWithdraw_id() {
        return withdraw_id;
    }

    public void setWithdraw_id(String withdraw_id) {
        this.withdraw_id = withdraw_id;
    }

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

    public String getAlias_name() {
        return alias_name;
    }

    public void setAlias_name(String alias_name) {
        this.alias_name = alias_name;
    }

    @Override
    public String toString() {
        return "WithDrawBean{" +
                "withdraw_id='" + withdraw_id + '\'' +
                ", single_max='" + single_max + '\'' +
                ", balance='" + balance + '\'' +
                ", bankno='" + bankno + '\'' +
                ", alias='" + alias + '\'' +
                ", day_max='" + day_max + '\'' +
                ", alias_name='" + alias_name + '\'' +
                '}';
    }
}
