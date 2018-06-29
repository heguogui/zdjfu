package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *我的页面参数
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/13 0013.
 */

public class MyIndexDataBean implements Serializable{


    /**
     * calendar : 今日无回款
     * news : 0
     * incomed : 0.0
     * pend_income : 0.0
     * total_assets : 0.0
     * balance : 0.0
     * giftCoupon : 0
     * assessmen : 进取型
     * investment : 0
     * invitfriends : 获好友投资额2%正经值
     */

    private String calendar;
    private String news;
    private String incomed;
    private String pend_income;
    private String pend_amount;
    private String total_assets;
    private String balance;
    private String giftCoupon;
    private String assessmen;
    private String investment;
    private String invitfriends;
    private String hf_balance;

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getIncomed() {
        return incomed;
    }

    public void setIncomed(String incomed) {
        this.incomed = incomed;
    }

    public String getPend_income() {
        return pend_income;
    }

    public void setPend_income(String pend_income) {
        this.pend_income = pend_income;
    }

    public String getPend_amount() {
        return pend_amount;
    }

    public void setPend_amount(String pend_amount) {
        this.pend_amount = pend_amount;
    }

    public String getTotal_assets() {
        return total_assets;
    }

    public void setTotal_assets(String total_assets) {
        this.total_assets = total_assets;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getGiftCoupon() {
        return giftCoupon;
    }

    public void setGiftCoupon(String giftCoupon) {
        this.giftCoupon = giftCoupon;
    }

    public String getAssessmen() {
        return assessmen;
    }

    public void setAssessmen(String assessmen) {
        this.assessmen = assessmen;
    }

    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    public String getInvitfriends() {
        return invitfriends;
    }

    public void setInvitfriends(String invitfriends) {
        this.invitfriends = invitfriends;
    }

    public String getHf_balance() {
        return hf_balance;
    }

    public void setHf_balance(String hf_balance) {
        this.hf_balance = hf_balance;
    }

    @Override
    public String toString() {
        return "MyIndexDataBean{" +
                "calendar='" + calendar + '\'' +
                ", news='" + news + '\'' +
                ", incomed='" + incomed + '\'' +
                ", pend_income='" + pend_income + '\'' +
                ", pend_amount='" + pend_amount + '\'' +
                ", total_assets='" + total_assets + '\'' +
                ", balance='" + balance + '\'' +
                ", giftCoupon='" + giftCoupon + '\'' +
                ", assessmen='" + assessmen + '\'' +
                ", investment='" + investment + '\'' +
                ", invitfriends='" + invitfriends + '\'' +
                ", hf_balance='" + hf_balance + '\'' +
                '}';
    }
}
