package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *红包卡券数量
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RedpacketCouponCountBean implements Serializable{

    private String coinBalance;
    private String coinCost;
    private String coinCount;
    private String giftCount;
    private String couponCount;

    public String getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(String coinBalance) {
        this.coinBalance = coinBalance;
    }

    public String getCoinCost() {
        return coinCost;
    }

    public void setCoinCost(String coinCost) {
        this.coinCost = coinCost;
    }

    public String getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

    public String getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(String giftCount) {
        this.giftCount = giftCount;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    @Override
    public String toString() {
        return "RedpacketCouponCountBean{" +
                "coinBalance='" + coinBalance + '\'' +
                ", coinCost='" + coinCost + '\'' +
                ", coinCount='" + coinCount + '\'' +
                ", giftCount='" + giftCount + '\'' +
                ", couponCount='" + couponCount + '\'' +
                '}';
    }
}
