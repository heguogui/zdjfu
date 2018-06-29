package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class BackMoneyBean implements Serializable{


    /**
     * productName : 广州本田
     * productType : 1
     * returnTotal : 1009.04
     * returnAmt : 1000.0
     * returnIncome : 9.04
     * buyTime : 2018-03-29
     */

    private String productName;
    private int productType;
    private double returnTotal;
    private double returnAmt;
    private double returnIncome;
    private String buyTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public double getReturnTotal() {
        return returnTotal;
    }

    public void setReturnTotal(double returnTotal) {
        this.returnTotal = returnTotal;
    }

    public double getReturnAmt() {
        return returnAmt;
    }

    public void setReturnAmt(double returnAmt) {
        this.returnAmt = returnAmt;
    }

    public double getReturnIncome() {
        return returnIncome;
    }

    public void setReturnIncome(double returnIncome) {
        this.returnIncome = returnIncome;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }
}
