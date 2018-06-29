package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RedpacketBean {


    /**
     * giftId : 1
     * giftName : 新手注册红包券
     * giftAmt : 158.0
     * useType : 1
     * minAmt : 2000.0
     * minIncomeDays : 20
     * startDate : 2018-04-09
     * endDate : 2018-05-01
     * isWillOut : true
     * status : 1
     */

    private int giftId;
    private String giftName;
    private String amount;
    private String useType;
    private String maxAmount;
    private String maxDays;
    private String startDate;
    private String endDate;
    private String willOut;
    private String status;

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(String maxDays) {
        this.maxDays = maxDays;
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

    public String getWillOut() {
        return willOut;
    }

    public void setWillOut(String willOut) {
        this.willOut = willOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RedpacketBean{" +
                "giftId=" + giftId +
                ", giftName='" + giftName + '\'' +
                ", amount='" + amount + '\'' +
                ", useType='" + useType + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                ", maxDays='" + maxDays + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", willOut='" + willOut + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
