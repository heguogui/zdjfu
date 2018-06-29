package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class dataList {


    /**
     * recharged : 0.0
     * pend_income : 0.0
     * pend_withdraw : 0.0
     * pend_return : 0.0
     * incomed : 0.0
     * uid : 0
     * update_time : 2017-11-01 12:00:02
     * balance : 2456.0
     * user_id : 69
     * invest_sum : 0.0
     * coin_balance : 0.0
     * withdraw_coupons : 0
     * withdrawed : 0.0
     * coin_total : 0.0
     * invest_frequency : 0
     * locked_money : 0.0
     * id : 9
     * returned : 0.0
     * coin_cost : 0.0
     * coin_invest : 0.0
     */

    private String recharged;
    private String pend_income;
    private String pend_withdraw;
    private String pend_return;
    private String incomed;
    private String uid;
    private String update_time;
    private String balance;
    private String user_id;
    private String invest_sum;
    private String coin_balance;
    private String withdraw_coupons;
    private String withdrawed;
    private String coin_total;
    private String invest_frequency;
    private String locked_money;
    private String id;
    private String returned;
    private String coin_cost;
    private String coin_invest;

    public String getRecharged() {
        return recharged;
    }

    public void setRecharged(String recharged) {
        this.recharged = recharged;
    }

    public String getPend_income() {
        return pend_income;
    }

    public void setPend_income(String pend_income) {
        this.pend_income = pend_income;
    }

    public String getPend_withdraw() {
        return pend_withdraw;
    }

    public void setPend_withdraw(String pend_withdraw) {
        this.pend_withdraw = pend_withdraw;
    }

    public String getPend_return() {
        return pend_return;
    }

    public void setPend_return(String pend_return) {
        this.pend_return = pend_return;
    }

    public String getIncomed() {
        return incomed;
    }

    public void setIncomed(String incomed) {
        this.incomed = incomed;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInvest_sum() {
        return invest_sum;
    }

    public void setInvest_sum(String invest_sum) {
        this.invest_sum = invest_sum;
    }

    public String getCoin_balance() {
        return coin_balance;
    }

    public void setCoin_balance(String coin_balance) {
        this.coin_balance = coin_balance;
    }

    public String getWithdraw_coupons() {
        return withdraw_coupons;
    }

    public void setWithdraw_coupons(String withdraw_coupons) {
        this.withdraw_coupons = withdraw_coupons;
    }

    public String getWithdrawed() {
        return withdrawed;
    }

    public void setWithdrawed(String withdrawed) {
        this.withdrawed = withdrawed;
    }

    public String getCoin_total() {
        return coin_total;
    }

    public void setCoin_total(String coin_total) {
        this.coin_total = coin_total;
    }

    public String getInvest_frequency() {
        return invest_frequency;
    }

    public void setInvest_frequency(String invest_frequency) {
        this.invest_frequency = invest_frequency;
    }

    public String getLocked_money() {
        return locked_money;
    }

    public void setLocked_money(String locked_money) {
        this.locked_money = locked_money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    public String getCoin_cost() {
        return coin_cost;
    }

    public void setCoin_cost(String coin_cost) {
        this.coin_cost = coin_cost;
    }

    public String getCoin_invest() {
        return coin_invest;
    }

    public void setCoin_invest(String coin_invest) {
        this.coin_invest = coin_invest;
    }

    @Override
    public String toString() {
        return "dataList{" +
                "recharged='" + recharged + '\'' +
                ", pend_income='" + pend_income + '\'' +
                ", pend_withdraw='" + pend_withdraw + '\'' +
                ", pend_return='" + pend_return + '\'' +
                ", incomed='" + incomed + '\'' +
                ", uid='" + uid + '\'' +
                ", update_time='" + update_time + '\'' +
                ", balance='" + balance + '\'' +
                ", user_id='" + user_id + '\'' +
                ", invest_sum='" + invest_sum + '\'' +
                ", coin_balance='" + coin_balance + '\'' +
                ", withdraw_coupons='" + withdraw_coupons + '\'' +
                ", withdrawed='" + withdrawed + '\'' +
                ", coin_total='" + coin_total + '\'' +
                ", invest_frequency='" + invest_frequency + '\'' +
                ", locked_money='" + locked_money + '\'' +
                ", id='" + id + '\'' +
                ", returned='" + returned + '\'' +
                ", coin_cost='" + coin_cost + '\'' +
                ", coin_invest='" + coin_invest + '\'' +
                '}';
    }
}
