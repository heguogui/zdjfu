package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/15 0015.
 */

public class ZTRefundLists implements Serializable{


    /**
     * seqNo : 1
     * returnDate : 2018-04-27
     * amount : 0
     * income : 0.7
     * status : -1
     */

    private int seqNo;
    private String returnDate;
    private int amount;
    private double income;
    private int status;

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ZTRefundLists{" +
                "seqNo=" + seqNo +
                ", returnDate='" + returnDate + '\'' +
                ", amount=" + amount +
                ", income=" + income +
                ", status=" + status +
                '}';
    }
}
