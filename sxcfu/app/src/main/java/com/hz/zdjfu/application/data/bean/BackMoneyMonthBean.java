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

public class BackMoneyMonthBean implements Serializable{

    private String refundDate;
    private String status;
    private String returnNum;
    private String returnAmount;
    private List<BackMoneyBean> refundForDayDtoList;

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(String returnNum) {
        this.returnNum = returnNum;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public List<BackMoneyBean> getRefundForDayDtoList() {
        return refundForDayDtoList;
    }

    public void setRefundForDayDtoList(List<BackMoneyBean> refundForDayDtoList) {
        this.refundForDayDtoList = refundForDayDtoList;
    }

    @Override
    public String toString() {
        return "BackMoneyMonthBean{" +
                "refundDate='" + refundDate + '\'' +
                ", status='" + status + '\'' +
                ", returnNum='" + returnNum + '\'' +
                ", returnAmount='" + returnAmount + '\'' +
                ", refundForDayDtoList=" + refundForDayDtoList +
                '}';
    }
}
