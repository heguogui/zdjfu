package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *直投产品基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/7 0007.
 */

public class ZTProductDetailBean implements Serializable{


    /**
     * id : 2342
     * productCode : ZT20180508180040-02企业
     * productName : 测试直投20180508180040-02企业
     * status : 4
     * isFresh : -1
     * debtMoney : 4000.0
     * balance : 4000.0
     * buyerCount : 0
     * payMin : 100.0
     * income : 10.0
     * platformInterest : 1.0
     * incomeMethod : 2
     * returnMethod : 1
     * returnDays : 36
     * fullTimeRemain : 22
     * protectMsg : null
     * protectMode : null
     * productDesc : null
     */

    private int id;
    private String productCode;
    private String productName;
    private int status;
    private int isFresh;
    private double debtMoney;
    private double balance;
    private int buyerCount;
    private int payMin;
    private double income;
    private double platformInterest;
    private int incomeMethod;
    private int returnMethod;
    private int returnDays;
    private int fullTimeRemain;
    private String protectMsg;
    private String protectMode;
    private String productDesc;
    private String lendUse;
    private ZTLoanerBean loaner;
    private String willShowTime;
    private String willShowRemain;
    private List<ZTPledgedBean> pledged;
    private List<ZTLegalOpinionBean> legalOpinion;
    private  List<ZTPactBean> pact;
    private List<ZTOtherBean> other;

    public String getWillShowTime() {
        return willShowTime;
    }

    public void setWillShowTime(String willShowTime) {
        this.willShowTime = willShowTime;
    }

    public String getWillShowRemain() {
        return willShowRemain;
    }

    public void setWillShowRemain(String willShowRemain) {
        this.willShowRemain = willShowRemain;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(int isFresh) {
        this.isFresh = isFresh;
    }

    public double getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(double debtMoney) {
        this.debtMoney = debtMoney;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBuyerCount() {
        return buyerCount;
    }

    public void setBuyerCount(int buyerCount) {
        this.buyerCount = buyerCount;
    }

    public int getPayMin() {
        return payMin;
    }

    public void setPayMin(int payMin) {
        this.payMin = payMin;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(double platformInterest) {
        this.platformInterest = platformInterest;
    }

    public int getIncomeMethod() {
        return incomeMethod;
    }

    public void setIncomeMethod(int incomeMethod) {
        this.incomeMethod = incomeMethod;
    }

    public int getReturnMethod() {
        return returnMethod;
    }

    public void setReturnMethod(int returnMethod) {
        this.returnMethod = returnMethod;
    }

    public int getReturnDays() {
        return returnDays;
    }

    public void setReturnDays(int returnDays) {
        this.returnDays = returnDays;
    }

    public int getFullTimeRemain() {
        return fullTimeRemain;
    }

    public void setFullTimeRemain(int fullTimeRemain) {
        this.fullTimeRemain = fullTimeRemain;
    }

    public String getProtectMsg() {
        return protectMsg;
    }

    public void setProtectMsg(String protectMsg) {
        this.protectMsg = protectMsg;
    }

    public String getProtectMode() {
        return protectMode;
    }

    public void setProtectMode(String protectMode) {
        this.protectMode = protectMode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getLendUse() {
        return lendUse;
    }

    public void setLendUse(String lendUse) {
        this.lendUse = lendUse;
    }

    public ZTLoanerBean getLoaner() {
        return loaner;
    }

    public void setLoaner(ZTLoanerBean loaner) {
        this.loaner = loaner;
    }

    public List<ZTPledgedBean> getPledged() {
        return pledged;
    }

    public void setPledged(List<ZTPledgedBean> pledged) {
        this.pledged = pledged;
    }

    public List<ZTLegalOpinionBean> getLegalOpinion() {
        return legalOpinion;
    }

    public void setLegalOpinion(List<ZTLegalOpinionBean> legalOpinion) {
        this.legalOpinion = legalOpinion;
    }

    public List<ZTPactBean> getPact() {
        return pact;
    }

    public void setPact(List<ZTPactBean> pact) {
        this.pact = pact;
    }

    public List<ZTOtherBean> getOther() {
        return other;
    }

    public void setOther(List<ZTOtherBean> other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ZTProductDetailBean{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", status=" + status +
                ", isFresh=" + isFresh +
                ", debtMoney=" + debtMoney +
                ", balance=" + balance +
                ", buyerCount=" + buyerCount +
                ", payMin=" + payMin +
                ", income=" + income +
                ", platformInterest=" + platformInterest +
                ", incomeMethod=" + incomeMethod +
                ", returnMethod=" + returnMethod +
                ", returnDays=" + returnDays +
                ", fullTimeRemain=" + fullTimeRemain +
                ", protectMsg='" + protectMsg + '\'' +
                ", protectMode='" + protectMode + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", lendUse='" + lendUse + '\'' +
                ", loaner=" + loaner +
                ", willShowTime='" + willShowTime + '\'' +
                ", willShowRemain='" + willShowRemain + '\'' +
                ", pledged=" + pledged +
                ", legalOpinion=" + legalOpinion +
                ", pact=" + pact +
                ", other=" + other +
                '}';
    }
}
