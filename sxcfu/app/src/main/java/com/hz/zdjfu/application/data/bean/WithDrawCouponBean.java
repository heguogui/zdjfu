package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class WithDrawCouponBean implements Serializable{


    /**
     * id : 1000
     * user_id : 98950
     * action : 1
     * type : 1
     * num : 1
     * balance : 1
     * relation_id : 3786
     * remark : null
     * create_time : 1499439523000
     * old_id : 201707070193708339
     * uid : 201707070703304161
     * old_relation_id : 201707071179696451
     * withdrawCouponsList : null
     */

    private String id;
    private String user_id;
    private String action;
    private String type;
    private String num;
    private String balance;
    private String relation_id;
    private String remark;
    private long create_time;
    private long old_id;
    private long uid;
    private long old_relation_id;
    private String withdrawCouponsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
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

    public long getOld_id() {
        return old_id;
    }

    public void setOld_id(long old_id) {
        this.old_id = old_id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getOld_relation_id() {
        return old_relation_id;
    }

    public void setOld_relation_id(long old_relation_id) {
        this.old_relation_id = old_relation_id;
    }

    public String getWithdrawCouponsList() {
        return withdrawCouponsList;
    }

    public void setWithdrawCouponsList(String withdrawCouponsList) {
        this.withdrawCouponsList = withdrawCouponsList;
    }

    @Override
    public String toString() {
        return "WithDrawCouponBean{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", action='" + action + '\'' +
                ", type='" + type + '\'' +
                ", num='" + num + '\'' +
                ", balance='" + balance + '\'' +
                ", relation_id='" + relation_id + '\'' +
                ", remark='" + remark + '\'' +
                ", create_time=" + create_time +
                ", old_id=" + old_id +
                ", uid=" + uid +
                ", old_relation_id=" + old_relation_id +
                ", withdrawCouponsList='" + withdrawCouponsList + '\'' +
                '}';
    }
}
