package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *理财跑马灯基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class FinancialMarqueeBean implements Serializable{


    /**
     * phone : 15526554960
     * amount : 102
     * pay_time : 1510793594394
     * pay_time_ago : 13秒前
     * type : 充值
     */

    private String phone;
    private String amount;
    private String tradeType;
    private long createTime;
    private String tradeTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    public String toString() {
        return "FinancialMarqueeBean{" +
                "phone='" + phone + '\'' +
                ", amount='" + amount + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", createTime=" + createTime +
                ", tradeTime='" + tradeTime + '\'' +
                '}';
    }
}
