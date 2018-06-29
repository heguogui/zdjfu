package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *借款对象基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/7 0007.
 */

public class ZTLoanerBean implements Serializable{

    /**
     * id : 24
     * loanerType : 2
     * name : 斯法人
     * phone : 17025892589
     * idcard : 350302199203260612
     * age : 26
     * sex : 1
     * marital : null
     * address : null
     * compAlias : null
     * compName : 浙江测试企业信息（02）
     * compCode : null
     * regDate : 1523462400000
     * regMoney : 300000.0
     * compAddress : 杭州市西湖区科技园区2号楼
     * status : 2
     */

    private int id;
    private int loanerType;
    private String name;
    private String phone;
    private String idcard;
    private String age;
    private String sex;
    private String marital;
    private String address;
    private String compAlias;
    private String compName;
    private String compCode;
    private String regDate;
    private String regMoney;
    private String compAddress;
    private int status;
    private List<ZTLoanerFiles> loanerFiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanerType() {
        return loanerType;
    }

    public void setLoanerType(int loanerType) {
        this.loanerType = loanerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompAlias() {
        return compAlias;
    }

    public void setCompAlias(String compAlias) {
        this.compAlias = compAlias;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegMoney() {
        return regMoney;
    }

    public void setRegMoney(String regMoney) {
        this.regMoney = regMoney;
    }

    public String getCompAddress() {
        return compAddress;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ZTLoanerFiles> getLoanerFiles() {
        return loanerFiles;
    }

    public void setLoanerFiles(List<ZTLoanerFiles> loanerFiles) {
        this.loanerFiles = loanerFiles;
    }

    @Override
    public String toString() {
        return "ZTLoanerBean{" +
                "id=" + id +
                ", loanerType=" + loanerType +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", marital='" + marital + '\'' +
                ", address='" + address + '\'' +
                ", compAlias='" + compAlias + '\'' +
                ", compName='" + compName + '\'' +
                ", compCode='" + compCode + '\'' +
                ", regDate='" + regDate + '\'' +
                ", regMoney='" + regMoney + '\'' +
                ", compAddress='" + compAddress + '\'' +
                ", status=" + status +
                ", loanerFiles=" + loanerFiles +
                '}';
    }
}
