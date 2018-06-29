package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *直投红包卡券
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTGiftBean implements Serializable{


    /**
     * giftId : 1
     * giftName : 新手注册红包券
     * amount : 158.0
     * useType : 1
     * useProdType : 2
     * minAmount : 2000.0
     * minDays : 20
     * startDate : 2018-04-09
     * endDate : 2018-05-01
     * willOut : false
     */

    private int giftId;
    private String giftName;
    private double amount;
    private int useType;
    private int useProdType;
    private double minAmount;
    private int minDays;
    private String startDate;
    private String endDate;
    private boolean willOut;

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public int getUseProdType() {
        return useProdType;
    }

    public void setUseProdType(int useProdType) {
        this.useProdType = useProdType;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public int getMinDays() {
        return minDays;
    }

    public void setMinDays(int minDays) {
        this.minDays = minDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isWillOut() {
        return willOut;
    }

    public void setWillOut(boolean willOut) {
        this.willOut = willOut;
    }

    @Override
    public String toString() {
        return "ZTGiftBean{" +
                "giftId=" + giftId +
                ", giftName='" + giftName + '\'' +
                ", amount=" + amount +
                ", useType=" + useType +
                ", useProdType=" + useProdType +
                ", minAmount=" + minAmount +
                ", minDays=" + minDays +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", willOut=" + willOut +
                '}';
    }
}
