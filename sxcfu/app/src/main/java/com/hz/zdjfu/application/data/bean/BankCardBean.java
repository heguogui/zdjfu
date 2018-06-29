package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *银行卡基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/22 0022
 */
public class BankCardBean implements Serializable{


    /**
     * id : 41
     * user_id : 69
     * bank_no : 6212261001052797476
     * bank_alias : null
     * card_user_name : null
     * phone : 15026554967
     * status : 1
     * remark : null
     * create_time : 1508936462000
     * type : 1
     * uid : null
     * bank_open : null
     * update_time : null
     * province : 上海
     * city : 上海
     * request_no : zdjfu1508936429894918738
     * card_id : 208759
     * ticket : 6489468ab58949758bb945383019be85
     * unbind_ticket : null
     * card_attribute : C
     * userBankList : null
     */

    private int id;
    private int user_id;
    private String bank_no;
    private String bank_alias;
    private String card_user_name;
    private String phone;
    private int status;
    private String remark;
    private long create_time;
    private int type;
    private String uid;
    private String bank_open;
    private String update_time;
    private String province;
    private String city;
    private String request_no;
    private String card_id;
    private String ticket;
    private String unbind_ticket;
    private String card_attribute;
    private String userBankList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getBank_alias() {
        return bank_alias;
    }

    public void setBank_alias(String bank_alias) {
        this.bank_alias = bank_alias;
    }

    public String getCard_user_name() {
        return card_user_name;
    }

    public void setCard_user_name(String card_user_name) {
        this.card_user_name = card_user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBank_open() {
        return bank_open;
    }

    public void setBank_open(String bank_open) {
        this.bank_open = bank_open;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRequest_no() {
        return request_no;
    }

    public void setRequest_no(String request_no) {
        this.request_no = request_no;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUnbind_ticket() {
        return unbind_ticket;
    }

    public void setUnbind_ticket(String unbind_ticket) {
        this.unbind_ticket = unbind_ticket;
    }

    public String getCard_attribute() {
        return card_attribute;
    }

    public void setCard_attribute(String card_attribute) {
        this.card_attribute = card_attribute;
    }

    public String getUserBankList() {
        return userBankList;
    }

    public void setUserBankList(String userBankList) {
        this.userBankList = userBankList;
    }

    @Override
    public String toString() {
        return "BankCardBean{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", bank_no='" + bank_no + '\'' +
                ", bank_alias='" + bank_alias + '\'' +
                ", card_user_name='" + card_user_name + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", create_time=" + create_time +
                ", type=" + type +
                ", uid='" + uid + '\'' +
                ", bank_open='" + bank_open + '\'' +
                ", update_time='" + update_time + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", request_no='" + request_no + '\'' +
                ", card_id='" + card_id + '\'' +
                ", ticket='" + ticket + '\'' +
                ", unbind_ticket='" + unbind_ticket + '\'' +
                ", card_attribute='" + card_attribute + '\'' +
                ", userBankList='" + userBankList + '\'' +
                '}';
    }
}
