package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/23 0023.
 */

public class DiscountZJZBean implements Serializable{


    /**
     * amount : 1.0
     * strAmount : 1.0
     * invest_dates : null
     * invest_amount : null
     * type : 3
     * name : 正经值
     * node_id : null
     * dates : null
     * use_type : null
     * overdue : null
     */

    private double amount;
    private String strAmount;
    private String invest_dates;
    private String invest_amount;
    private String type;
    private String name;
    private String node_id;
    private String dates;
    private String use_type;
    private String overdue;



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStrAmount() {
        return strAmount;
    }

    public void setStrAmount(String strAmount) {
        this.strAmount = strAmount;
    }

    public String getInvest_dates() {
        return invest_dates;
    }

    public void setInvest_dates(String invest_dates) {
        this.invest_dates = invest_dates;
    }

    public String getInvest_amount() {
        return invest_amount;
    }

    public void setInvest_amount(String invest_amount) {
        this.invest_amount = invest_amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getUse_type() {
        return use_type;
    }

    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }


    @Override
    public String toString() {
        return "DiscountZJZBean{" +
                "amount=" + amount +
                ", strAmount='" + strAmount + '\'' +
                ", invest_dates='" + invest_dates + '\'' +
                ", invest_amount='" + invest_amount + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", node_id='" + node_id + '\'' +
                ", dates='" + dates + '\'' +
                ", use_type='" + use_type + '\'' +
                ", overdue='" + overdue + '\'' +
                '}';
    }
}
