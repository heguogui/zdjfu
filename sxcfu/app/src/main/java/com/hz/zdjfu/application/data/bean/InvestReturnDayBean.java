package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/30 0030.
 */

public class InvestReturnDayBean implements Serializable{


    /**
     * id : 48430
     * buy_id : 86197
     * product_id : 1014
     * num : 1
     * start_date : 1511798400000
     * end_date : 1511798400000
     * days : 3
     * is_return_amount : 1
     * amount : 1000.0
     * interest : 9.0
     * income : 0.75
     * pay_date : 1511971200000
     * act_pay_time : null
     * status : -1
     * create_time : 1511798400000
     * remark : 9.0% = 年化率：9.0%加息券利率：0.0%
     * user_id : 98910
     * income_id : null
     * old_buy_id : null
     * old_product_id : null
     * productBuyIncomeList : null
     */

    private int id;
    private int buy_id;
    private int product_id;
    private int num;
    private long start_date;
    private long end_date;
    private int days;
    private int is_return_amount;
    private double amount;
    private double interest;
    private double income;
    private long pay_date;
    private String act_pay_time;
    private int status;
    private long create_time;
    private String remark;
    private int user_id;
    private String income_id;
    private String old_buy_id;
    private String old_product_id;
    private String productBuyIncomeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getStart_date() {
        return start_date;
    }

    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    public long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getIs_return_amount() {
        return is_return_amount;
    }

    public void setIs_return_amount(int is_return_amount) {
        this.is_return_amount = is_return_amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public long getPay_date() {
        return pay_date;
    }

    public void setPay_date(long pay_date) {
        this.pay_date = pay_date;
    }

    public String getAct_pay_time() {
        return act_pay_time;
    }

    public void setAct_pay_time(String act_pay_time) {
        this.act_pay_time = act_pay_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIncome_id() {
        return income_id;
    }

    public void setIncome_id(String income_id) {
        this.income_id = income_id;
    }

    public String getOld_buy_id() {
        return old_buy_id;
    }

    public void setOld_buy_id(String old_buy_id) {
        this.old_buy_id = old_buy_id;
    }

    public String getOld_product_id() {
        return old_product_id;
    }

    public void setOld_product_id(String old_product_id) {
        this.old_product_id = old_product_id;
    }

    public String getProductBuyIncomeList() {
        return productBuyIncomeList;
    }

    public void setProductBuyIncomeList(String productBuyIncomeList) {
        this.productBuyIncomeList = productBuyIncomeList;
    }

    @Override
    public String toString() {
        return "InvestReturnDayBean{" +
                "id=" + id +
                ", buy_id=" + buy_id +
                ", product_id=" + product_id +
                ", num=" + num +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", days=" + days +
                ", is_return_amount=" + is_return_amount +
                ", amount=" + amount +
                ", interest=" + interest +
                ", income=" + income +
                ", pay_date=" + pay_date +
                ", act_pay_time='" + act_pay_time + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", remark='" + remark + '\'' +
                ", user_id=" + user_id +
                ", income_id='" + income_id + '\'' +
                ", old_buy_id='" + old_buy_id + '\'' +
                ", old_product_id='" + old_product_id + '\'' +
                ", productBuyIncomeList='" + productBuyIncomeList + '\'' +
                '}';
    }
}
