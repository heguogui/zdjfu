package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *直投优惠券
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTCouponBean implements Serializable{


    /**
     * couponId : 199890
     * couponName : 新手首投加息券
     * interest : 1.2
     * minDays : 20
     * minAmount : null
     * startDate : 2018-03-29
     * endDate : 2019-03-28
     * dayRemain : 324
     * willOut : false
     * useType : 1
     * useProdType : 0
     */

    private int couponId;
    private String couponName;
    private double interest;
    private int minDays;
    private String minAmount;
    private String startDate;
    private String endDate;
    private int dayRemain;
    private boolean willOut;
    private int useType;
    private int useProdType;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getMinDays() {
        return minDays;
    }

    public void setMinDays(int minDays) {
        this.minDays = minDays;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
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

    public int getDayRemain() {
        return dayRemain;
    }

    public void setDayRemain(int dayRemain) {
        this.dayRemain = dayRemain;
    }

    public boolean isWillOut() {
        return willOut;
    }

    public void setWillOut(boolean willOut) {
        this.willOut = willOut;
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

    @Override
    public String toString() {
        return "ZTCouponBean{" +
                "couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", interest=" + interest +
                ", minDays=" + minDays +
                ", minAmount='" + minAmount + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", dayRemain=" + dayRemain +
                ", willOut=" + willOut +
                ", useType=" + useType +
                ", useProdType=" + useProdType +
                '}';
    }
}
