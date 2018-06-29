package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 *用户信息类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ProductRecordsBean implements Serializable {

    private int status;
    private String productCode;
    private String speed;
    private String income;
    private String money;
    private String payMin;
    private String platformInterest;
    private String willIssueTime;
    private String productName;
    private String photo;
    private int isFresh;
    private int incomeDays;
    private String productId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPayMin() {
        return payMin;
    }

    public void setPayMin(String payMin) {
        this.payMin = payMin;
    }

    public String getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(String platformInterest) {
        this.platformInterest = platformInterest;
    }

    public String getWillIssueTime() {
        return willIssueTime;
    }

    public void setWillIssueTime(String willIssueTime) {
        this.willIssueTime = willIssueTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(int isFresh) {
        this.isFresh = isFresh;
    }

    public int getIncomeDays() {
        return incomeDays;
    }

    public void setIncomeDays(int incomeDays) {
        this.incomeDays = incomeDays;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductRecordsBean{" +
                "status=" + status +
                ", productCode='" + productCode + '\'' +
                ", speed='" + speed + '\'' +
                ", income='" + income + '\'' +
                ", money='" + money + '\'' +
                ", payMin='" + payMin + '\'' +
                ", platformInterest='" + platformInterest + '\'' +
                ", willIssueTime='" + willIssueTime + '\'' +
                ", productName='" + productName + '\'' +
                ", photo='" + photo + '\'' +
                ", isFresh=" + isFresh +
                ", incomeDays=" + incomeDays +
                ", productId='" + productId + '\'' +
                '}';
    }
}
