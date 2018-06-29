package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/16 0016.
 */

public class LenderBean implements Serializable{


    /**
     * id : 1
     * amount : null
     * lender_type : 1
     * name : 王金涛
     * phone : 18368025612
     * idcard : 341225198904296056
     * marital : 1
     * address : 杭州市西湖区莫干山路639号德源大楼801室
     * comp_alias : null
     * comp_name : null
     * comp_code : null
     * reg_date : null
     * reg_money : null
     * comp_address : null
     * status : 1
     * create_time : 1480996541000
     * remark : null
     * user_type : 2
     * user_id : 1
     * age : 28
     * sex : 男
     * lenderList : null
     */

    private int id;
    private String amount;
    private int lender_type;
    private String name;
    private String phone;
    private String idcard;
    private int marital;
    private String address;
    private String comp_alias;
    private String comp_name;
    private String comp_code;
    private String reg_date;
    private String reg_money;
    private String comp_address;
    private int status;
    private long create_time;
    private String remark;
    private int user_type;
    private int user_id;
    private int age;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getLender_type() {
        return lender_type;
    }

    public void setLender_type(int lender_type) {
        this.lender_type = lender_type;
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

    public int getMarital() {
        return marital;
    }

    public void setMarital(int marital) {
        this.marital = marital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComp_alias() {
        return comp_alias;
    }

    public void setComp_alias(String comp_alias) {
        this.comp_alias = comp_alias;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getComp_code() {
        return comp_code;
    }

    public void setComp_code(String comp_code) {
        this.comp_code = comp_code;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getReg_money() {
        return reg_money;
    }

    public void setReg_money(String reg_money) {
        this.reg_money = reg_money;
    }

    public String getComp_address() {
        return comp_address;
    }

    public void setComp_address(String comp_address) {
        this.comp_address = comp_address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "LenderBean{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", lender_type=" + lender_type +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", marital=" + marital +
                ", address='" + address + '\'' +
                ", comp_alias='" + comp_alias + '\'' +
                ", comp_name='" + comp_name + '\'' +
                ", comp_code='" + comp_code + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", reg_money='" + reg_money + '\'' +
                ", comp_address='" + comp_address + '\'' +
                ", status=" + status +
                ", create_time=" + create_time +
                ", remark='" + remark + '\'' +
                ", user_type=" + user_type +
                ", user_id=" + user_id +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
