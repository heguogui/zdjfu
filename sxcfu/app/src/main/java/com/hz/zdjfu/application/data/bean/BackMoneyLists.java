package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/28 0028.
 */

public class BackMoneyLists implements Serializable{

    private String totalIncome;
    private String amt;
    private String interest;
    private String willIncome;
    private String willAmt;
    private String willInterest;
    private List<BackMoneyMonthBean> refundForDay;

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getWillIncome() {
        return willIncome;
    }

    public void setWillIncome(String willIncome) {
        this.willIncome = willIncome;
    }

    public String getWillAmt() {
        return willAmt;
    }

    public void setWillAmt(String willAmt) {
        this.willAmt = willAmt;
    }

    public String getWillInterest() {
        return willInterest;
    }

    public void setWillInterest(String willInterest) {
        this.willInterest = willInterest;
    }

    public List<BackMoneyMonthBean> getRefundForDay() {
        return refundForDay;
    }

    public void setRefundForDay(List<BackMoneyMonthBean> refundForDay) {
        this.refundForDay = refundForDay;
    }

    @Override
    public String toString() {
        return "BackMoneyLists{" +
                "totalIncome='" + totalIncome + '\'' +
                ", amt='" + amt + '\'' +
                ", interest='" + interest + '\'' +
                ", willIncome='" + willIncome + '\'' +
                ", willAmt='" + willAmt + '\'' +
                ", willInterest='" + willInterest + '\'' +
                ", refundForDay=" + refundForDay +
                '}';
    }
}
