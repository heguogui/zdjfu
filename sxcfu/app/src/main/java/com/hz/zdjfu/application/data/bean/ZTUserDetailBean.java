package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTUserDetailBean implements Serializable{


    /**
     * phone : 134****0980
     * realName : **女
     * idCardNo : 360***********4825
     * bankNo : ***************3766
     * balance : 36801.86
     * authStatus : 1
     * bindStatus : 3
     */

    private String phone;
    private String realName;
    private String idCardNo;
    private String bankNo;
    private double balance;
    private int authStatus;
    private int bindStatus;
    private long createTime;
    private int newHand;
    private int userType;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public int getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(int bindStatus) {
        this.bindStatus = bindStatus;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getNewHand() {
        return newHand;
    }

    public void setNewHand(int newHand) {
        this.newHand = newHand;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "ZTUserDetailBean{" +
                "phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", balance=" + balance +
                ", authStatus=" + authStatus +
                ", bindStatus=" + bindStatus +
                ", createTime=" + createTime +
                ", newHand=" + newHand +
                ", userType=" + userType +
                '}';
    }
}
