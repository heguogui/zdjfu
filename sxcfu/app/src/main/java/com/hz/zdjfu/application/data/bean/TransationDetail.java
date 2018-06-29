package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class TransationDetail implements Serializable{

    private String id;
    private String amount;
    private String balance;
    private String operate_type;
    private String status;
    private String create_time;
    private String summary;
    private String mMonth;
    private String action;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getmMonth() {
        return mMonth;
    }

    public void setmMonth(String mMonth) {
        this.mMonth = mMonth;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "TransationDetail{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", balance='" + balance + '\'' +
                ", operate_type='" + operate_type + '\'' +
                ", status='" + status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", summary='" + summary + '\'' +
                ", mMonth='" + mMonth + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
