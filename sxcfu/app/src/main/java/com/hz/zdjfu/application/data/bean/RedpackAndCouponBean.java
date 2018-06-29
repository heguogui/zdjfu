package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *直投红包卡券
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/14 0014.
 */

public class RedpackAndCouponBean implements Serializable{

    private String giftNum;
    private String couponNum;
    private String coinBalance;
    private String totalCount;
    private List<CouponBean> couponList;
    private List<RedpacketBean> giftList;


    public String getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(String coinBalance) {
        this.coinBalance = coinBalance;
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<CouponBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponBean> couponList) {
        this.couponList = couponList;
    }

    public List<RedpacketBean> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<RedpacketBean> giftList) {
        this.giftList = giftList;
    }

    @Override
    public String toString() {
        return "RedpackAndCouponBean{" +
                "giftNum='" + giftNum + '\'' +
                ", couponNum='" + couponNum + '\'' +
                ", coinBalance='" + coinBalance + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", couponList=" + couponList +
                ", giftList=" + giftList +
                '}';
    }


}
