package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *我的页面
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/12 0012.
 */

public class MyPageBean implements Serializable{


    /**
     * phone : 133****0272
     * totalIncome : 10000.0
     * willIncome : 500.0
     * totalAmount : 35000.0
     * accountBlance : 20000.0
     * validGift : 10
     * isWillReturn : true
     * investNum : 10
     */

    private String phone;
    private double totalIncome;
    private double pendReturn;
    private double totalAmount;
    private double accountBalance;
    private int validGift;
    private boolean willReturn;
    private int investNum;
    private int creditsBalance;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getPendReturn() {
        return pendReturn;
    }

    public void setPendReturn(double pendReturn) {
        this.pendReturn = pendReturn;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getValidGift() {
        return validGift;
    }

    public void setValidGift(int validGift) {
        this.validGift = validGift;
    }

    public boolean isWillReturn() {
        return willReturn;
    }

    public void setWillReturn(boolean willReturn) {
        this.willReturn = willReturn;
    }

    public int getInvestNum() {
        return investNum;
    }

    public void setInvestNum(int investNum) {
        this.investNum = investNum;
    }

    public int getCreditsBalance() {
        return creditsBalance;
    }

    public void setCreditsBalance(int creditsBalance) {
        this.creditsBalance = creditsBalance;
    }

    @Override
    public String toString() {
        return "MyPageBean{" +
                "phone='" + phone + '\'' +
                ", totalIncome=" + totalIncome +
                ", pendReturn=" + pendReturn +
                ", totalAmount=" + totalAmount +
                ", accountBalance=" + accountBalance +
                ", validGift=" + validGift +
                ", willReturn=" + willReturn +
                ", investNum=" + investNum +
                ", creditsBalance=" + creditsBalance +
                '}';
    }
}
