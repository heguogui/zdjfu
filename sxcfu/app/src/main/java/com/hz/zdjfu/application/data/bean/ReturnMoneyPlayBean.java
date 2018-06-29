package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/24 0024.
 */

public class ReturnMoneyPlayBean implements Serializable{


    /**
     * id : 1346
     * product_id : 1006
     * start_date : 1509033600000
     * end_date : 1511625600000
     * days : 31
     * pay_date : 1511625600000
     * income_dailly : null
     * income : null
     * amount : null
     * create_time : 1508981509000
     * status : 1
     * buy_id : null
     * income_id : 3205
     * old_product_id : 9233576449034240
     * productIncomeList : null
     */

    private int id;
    private int product_id;
    private long start_date;
    private long end_date;
    private int days;
    private long pay_date;
    private String income_dailly;
    private String income;
    private String amount;
    private long create_time;
    private int status;
    private String buy_id;
    private int income_id;
    private long old_product_id;
    private String productIncomeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public long getPay_date() {
        return pay_date;
    }

    public void setPay_date(long pay_date) {
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

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(String buy_id) {
        this.buy_id = buy_id;
    }

    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }

    public long getOld_product_id() {
        return old_product_id;
    }

    public void setOld_product_id(long old_product_id) {
        this.old_product_id = old_product_id;
    }

    public String getProductIncomeList() {
        return productIncomeList;
    }

    public void setProductIncomeList(String productIncomeList) {
        this.productIncomeList = productIncomeList;
    }

    @Override
    public String toString() {
        return "ReturnMoneyPlayBean{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", days=" + days +
                ", pay_date=" + pay_date +
                ", income_dailly='" + income_dailly + '\'' +
                ", income='" + income + '\'' +
                ", amount='" + amount + '\'' +
                ", create_time=" + create_time +
                ", status=" + status +
                ", buy_id='" + buy_id + '\'' +
                ", income_id=" + income_id +
                ", old_product_id=" + old_product_id +
                ", productIncomeList='" + productIncomeList + '\'' +
                '}';
    }
}
