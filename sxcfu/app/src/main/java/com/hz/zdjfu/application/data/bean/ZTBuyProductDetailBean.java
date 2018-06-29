package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *直投购买产品的详情
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/15 0015.
 */

public class ZTBuyProductDetailBean implements Serializable{



    /**
     * productId : 2267
     * productCode : 车财道90001期
     * productType : 1
     * buyDate : 2018-04-03
     * interest : 10.50
     * startDate : 2018-03-27
     * endDate : 2018-05-27
     * refundMethod : 2
     * prdStatus : 7
     * refundStatus : 2
     * investAmt : 100.0
     * actPayAmt : 100.0
     * discounts : 0.0
     * income : 1.57
     */

    private int productId;
    private String productCode;
    private int productType;
    private String buyDate;
    private String interest;
    private String startDate;
    private String endDate;
    private int refundMethod;
    private int prdStatus;
    private int refundStatus;
    private double investAmt;
    private double actPayAmt;
    private double discounts;
    private double income;
    private List<ZTRefundLists> returnList;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(int refundMethod) {
        this.refundMethod = refundMethod;
    }

    public int getPrdStatus() {
        return prdStatus;
    }

    public void setPrdStatus(int prdStatus) {
        this.prdStatus = prdStatus;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public double getInvestAmt() {
        return investAmt;
    }

    public void setInvestAmt(double investAmt) {
        this.investAmt = investAmt;
    }

    public double getActPayAmt() {
        return actPayAmt;
    }

    public void setActPayAmt(double actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public List<ZTRefundLists> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ZTRefundLists> returnList) {
        this.returnList = returnList;
    }

    @Override
    public String toString() {
        return "ZTBuyProductDetailBean{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productType=" + productType +
                ", buyDate='" + buyDate + '\'' +
                ", interest='" + interest + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", refundMethod=" + refundMethod +
                ", prdStatus=" + prdStatus +
                ", refundStatus=" + refundStatus +
                ", investAmt=" + investAmt +
                ", actPayAmt=" + actPayAmt +
                ", discounts=" + discounts +
                ", income=" + income +
                ", returnList=" + returnList +
                '}';
    }
}
