package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *用户信息基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/12 0012.
 */

public class UserInfromBean implements Serializable{


    private String id;
    private String user_name;
    private String passwd;
    private String pay_passwd;
    private String invite_code;
    private String phone;
    private String real_name;
    private String idcard_no;
    private String real_name_auth;
    private String idcard_auth_time;
    private String pay_account;
    private String sex;
    private String update_time;
    private String create_time;
    private String inviter_phone;
    private String birthday;
    private String new_hand;
    private String return_status;
    private String remark;
    private String user_type;
    private String invite_source;
    private String status;
    private String reg_source;
    private String last_login_time;
    private String login_ip;
    private String reg_ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPay_passwd() {
        return pay_passwd;
    }

    public void setPay_passwd(String pay_passwd) {
        this.pay_passwd = pay_passwd;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getReal_name_auth() {
        return real_name_auth;
    }

    public void setReal_name_auth(String real_name_auth) {
        this.real_name_auth = real_name_auth;
    }

    public String getIdcard_auth_time() {
        return idcard_auth_time;
    }

    public void setIdcard_auth_time(String idcard_auth_time) {
        this.idcard_auth_time = idcard_auth_time;
    }

    public String getPay_account() {
        return pay_account;
    }

    public void setPay_account(String pay_account) {
        this.pay_account = pay_account;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getInviter_phone() {
        return inviter_phone;
    }

    public void setInviter_phone(String inviter_phone) {
        this.inviter_phone = inviter_phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNew_hand() {
        return new_hand;
    }

    public void setNew_hand(String new_hand) {
        this.new_hand = new_hand;
    }

    public String getReturn_status() {
        return return_status;
    }

    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getInvite_source() {
        return invite_source;
    }

    public void setInvite_source(String invite_source) {
        this.invite_source = invite_source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReg_source() {
        return reg_source;
    }

    public void setReg_source(String reg_source) {
        this.reg_source = reg_source;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    @Override
    public String toString() {
        return "UserInfromBean{" +
                "id='" + id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", pay_passwd='" + pay_passwd + '\'' +
                ", invite_code='" + invite_code + '\'' +
                ", phone='" + phone + '\'' +
                ", real_name='" + real_name + '\'' +
                ", idcard_no='" + idcard_no + '\'' +
                ", real_name_auth='" + real_name_auth + '\'' +
                ", idcard_auth_time='" + idcard_auth_time + '\'' +
                ", pay_account='" + pay_account + '\'' +
                ", sex='" + sex + '\'' +
                ", update_time='" + update_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", inviter_phone='" + inviter_phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", new_hand='" + new_hand + '\'' +
                ", return_status='" + return_status + '\'' +
                ", remark='" + remark + '\'' +
                ", user_type='" + user_type + '\'' +
                ", invite_source='" + invite_source + '\'' +
                ", status='" + status + '\'' +
                ", reg_source='" + reg_source + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                ", login_ip='" + login_ip + '\'' +
                ", reg_ip='" + reg_ip + '\'' +
                '}';
    }
}
