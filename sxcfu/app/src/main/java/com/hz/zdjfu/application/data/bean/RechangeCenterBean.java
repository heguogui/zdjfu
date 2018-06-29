package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *兑换中心
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RechangeCenterBean {


    /**
     * minDay : 30
     * amount : 0.99
     * id : 2
     * useType : 2
     * goodsName : 兑换加息券
     * coinCost : 5
     * goodsType : 2
     * effectiveDay : 50
     */

    private String minDay;
    private String amount;
    private String id;
    private int useType;
    private String goodsName;
    private String coinCost;
    private int goodsType;
    private int effectiveDay;
    private String investmentQuota;

    public String getMinDay() {
        return minDay;
    }

    public void setMinDay(String minDay) {
        this.minDay = minDay;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCoinCost() {
        return coinCost;
    }

    public void setCoinCost(String coinCost) {
        this.coinCost = coinCost;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public int getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(int effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    public String getInvestmentQuota() {
        return investmentQuota;
    }

    public void setInvestmentQuota(String investmentQuota) {
        this.investmentQuota = investmentQuota;
    }

    @Override
    public String toString() {
        return "RechangeCenterBean{" +
                "minDay='" + minDay + '\'' +
                ", amount=" + amount +
                ", id='" + id + '\'' +
                ", useType=" + useType +
                ", goodsName='" + goodsName + '\'' +
                ", coinCost='" + coinCost + '\'' +
                ", goodsType=" + goodsType +
                ", effectiveDay=" + effectiveDay +
                ", investmentQuota='" + investmentQuota + '\'' +
                '}';
    }
}
