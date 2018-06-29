package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/17 0017.
 */

public class ReturnPlayBean implements Serializable{


    /**
     * id : 1321
     * product_id : 962
     * start_date : 1507996800000
     * end_date : 1511107200000
     * days : 37
     * pay_date : 1511107200000
     * income_dailly : null
     * income : null
     * amount : null
     * create_time : 1508758391000
     * status : 1
     * buy_id : null
     * income_id : 3136
     * old_product_id : 9187292939416125
     * productIncomeList : null
     */

    private String id;
    private String product_id;
    private String start_date;
    private String end_date;
    private String days;
    private String pay_date;
    private String income_dailly;
    private String income;
    private String amount;
    private String create_time;
    private String status;
    private String buy_id;
    private String income_id;
    private String old_product_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getIncome_dailly() {
        return income_dailly;
    }

    public void setIncome_dailly(String income_dailly) {
        this.income_dailly = income_dailly;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
    }

    public String getIncome_id() {
        return income_id;
    }

    public void setIncome_id(String income_id) {
        this.income_id = income_id;
    }

    public String getOld_product_id() {
        return old_product_id;
    }

    public void setOld_product_id(String old_product_id) {
        this.old_product_id = old_product_id;
    }


    @Override
    public String toString() {
        return "ReturnPlayBean{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", days='" + days + '\'' +
                ", pay_date='" + pay_date + '\'' +
                ", income_dailly='" + income_dailly + '\'' +
                ", income='" + income + '\'' +
                ", amount='" + amount + '\'' +
                ", create_time='" + create_time + '\'' +
                ", status='" + status + '\'' +
                ", buy_id='" + buy_id + '\'' +
                ", income_id='" + income_id + '\'' +
                ", old_product_id='" + old_product_id + '\'' +
                '}';
    }
}
