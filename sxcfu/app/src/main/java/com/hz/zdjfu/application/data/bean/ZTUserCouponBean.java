package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *直投可用优惠券
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTUserCouponBean implements Serializable{

    private String coinNeed;
    private String coinBlance;
    private String availableNum;
    private List<ZTGiftBean>  giftList;
    private List<ZTCouponBean> couponList;

    public String getCoinNeed() {
        return coinNeed;
    }

    public void setCoinNeed(String coinNeed) {
        this.coinNeed = coinNeed;
    }

    public String getCoinBlance() {
        return coinBlance;
    }

    public void setCoinBlance(String coinBlance) {
        this.coinBlance = coinBlance;
    }

    public String getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(String availableNum) {
        this.availableNum = availableNum;
    }

    public List<ZTGiftBean> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<ZTGiftBean> giftList) {
        this.giftList = giftList;
    }

    public List<ZTCouponBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<ZTCouponBean> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "ZTUserCouponBean{" +
                "coinNeed='" + coinNeed + '\'' +
                ", coinBlance='" + coinBlance + '\'' +
                ", availableNum='" + availableNum + '\'' +
                ", giftList=" + giftList +
                ", couponList=" + couponList +
                '}';
    }
}
