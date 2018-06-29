package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *我的投资基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public class MyInvestBean implements Serializable{


    /**
     * id : 133634
     * productCode : 车财道90001期
     * payTime : 2018-04-03
     * willIncome : 1.57
     * status : 4
     * type : 1
     * incomeDays : 54
     * restDays : 13
     * payAmount : 100.0
     */

    private int id;
    private String productCode;
    private String payTime;
    private String willIncome;
    private String status;
    private String type;
    private String incomeDays;
    private String restDays;
    private String payAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getWillIncome() {
        return willIncome;
    }

    public void setWillIncome(String willIncome) {
        this.willIncome = willIncome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIncomeDays() {
        return incomeDays;
    }

    public void setIncomeDays(String incomeDays) {
        this.incomeDays = incomeDays;
    }

    public String getRestDays() {
        return restDays;
    }

    public void setRestDays(String restDays) {
        this.restDays = restDays;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    @Override
    public String toString() {
        return "MyInvestBean{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", payTime='" + payTime + '\'' +
                ", willIncome='" + willIncome + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", incomeDays='" + incomeDays + '\'' +
                ", restDays='" + restDays + '\'' +
                ", payAmount='" + payAmount + '\'' +
                '}';
    }
}
