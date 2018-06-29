package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *直投 我的资产
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/16 0016.
 */

public class MyAssetsBean implements Serializable{


    /**
     * totalAssets : 46129.93
     * balance : 32801.86
     * pendAmount : 13328.07
     * freezeAmount : 0
     * totalInvest : 5500
     * totalIncome : 1.86
     * totalRecharge : 40000
     * totalWithdraw : 200
     * totalCoupon : 0
     */

    private String totalAssets;
    private String balance;
    private String pendAmount;
    private String freezeAmount;
    private String totalInvest;
    private String totalIncome;
    private String totalRecharge;
    private String totalWithdraw;
    private String totalCoupon;
    private String registerDays;

    public String getRegisterDays() {
        return registerDays;
    }

    public void setRegisterDays(String registerDays) {
        this.registerDays = registerDays;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPendAmount() {
        return pendAmount;
    }

    public void setPendAmount(String pendAmount) {
        this.pendAmount = pendAmount;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getTotalInvest() {
        return totalInvest;
    }

    public void setTotalInvest(String totalInvest) {
        this.totalInvest = totalInvest;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(String totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public String getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(String totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public String getTotalCoupon() {
        return totalCoupon;
    }

    public void setTotalCoupon(String totalCoupon) {
        this.totalCoupon = totalCoupon;
    }

    @Override
    public String toString() {
        return "MyAssetsBean{" +
                "totalAssets='" + totalAssets + '\'' +
                ", balance='" + balance + '\'' +
                ", pendAmount='" + pendAmount + '\'' +
                ", freezeAmount='" + freezeAmount + '\'' +
                ", totalInvest='" + totalInvest + '\'' +
                ", totalIncome='" + totalIncome + '\'' +
                ", totalRecharge='" + totalRecharge + '\'' +
                ", totalWithdraw='" + totalWithdraw + '\'' +
                ", totalCoupon='" + totalCoupon + '\'' +
                ", registerDays=" + registerDays +
                '}';
    }
}
