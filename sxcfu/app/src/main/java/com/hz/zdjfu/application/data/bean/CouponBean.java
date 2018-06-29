package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class CouponBean {


    /**
     * couponId : 1
     * couponName : 新手注册加息券
     * interest : 1.3%
     * useType : 1
     * minAmt : 2000.0
     * minIncomeDays : 20
     * startDate : 2018-04-09
     * endDate : 2018-05-01
     * isWillOut : true
     * status : 1
     */

    private int couponId;
    private String couponName;
    private String interest;
    private String useType;
    private String minAmount;
    private String minDays;
    private String startDate;
    private String endDate;
    private String willOut;
    private String status;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMinDays() {
        return minDays;
    }

    public void setMinDays(String minDays) {
        this.minDays = minDays;
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
        return "CouponBean{" +
                "couponId=" + couponId +
                ", couponName='" + couponName + '\'' +
                ", interest='" + interest + '\'' +
                ", useType='" + useType + '\'' +
                ", minAmount='" + minAmount + '\'' +
                ", minDays='" + minDays + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", willOut='" + willOut + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
