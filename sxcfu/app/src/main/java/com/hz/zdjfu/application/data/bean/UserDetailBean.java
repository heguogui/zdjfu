package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class UserDetailBean {


    /**
     * id : 69
     * user_name : 15026554967
     * passwd : cc520c8c5ad7d26996e746f6dce8aa0d
     * pay_passwd : null
     * invite_code : null
     * phone : null
     * real_name :
     * idcard_no :
     * real_name_auth : 1
     * idcard_auth_time : 1507796294000
     * pay_account : null
     * sex : 0
     * update_time : 1508826308000
     * create_time : 1507692760000
     * inviter_phone : null
     * birthday : null
     * new_hand : 0
     * return_status : 0
     * remark : register:{"response_code":"APPLY_SUCCESS","response_msg":"提交成功","response_time":"20171011113240","remark":null,"ticket":null};audit:{"response_code":"APPLY_SUCCESS","response_msg":"提交成功","response_time":"20171012161813","remark":null,"ticket":null};audit:{"response_code":"DUPLICATE_VERIFY","response_msg":"实名已认证","response_time":"20171012162622","remark":null,"ticket":null};audit:{"response_code":"DUPLICATE_VERIFY","response_msg":"实名已认证","response_time":"20171012162654","remark":null,"ticket":null};audit:{"response_code":"REALNAME_CONFIRM_FAILED","response_msg":"实名认证不通过","response_time":"20171012162751","remark":null,"ticket":null};audit:{"response_code":"ILLEGAL_ARGUMENT","response_msg":"请输入正确的身份证号","response_time":"20171012162802","remark":null,"ticket":null}
     * user_type : 0
     * invite_source : null
     * status : 0
     * reg_source : 3
     * last_login_time : 1507692760000
     * login_ip : null
     * reg_ip : 172.16.2.100
     * open_account : 0
     * open_account_time : null
     */

    private int id;
    private String user_name;
    private String passwd;
    private String pay_passwd;
    private String invite_code;
    private String phone;
    private String real_name;
    private String idcard_no;
    private int real_name_auth;
    private long idcard_auth_time;
    private String pay_account;
    private int sex;
    private long update_time;
    private long create_time;
    private String inviter_phone;
    private String birthday;
    private int new_hand;
    private int return_status;
    private String remark;
    private int user_type;
    private String invite_source;
    private int status;
    private int reg_source;
    private long last_login_time;
    private String login_ip;
    private String reg_ip;
    private int open_account;
    private String open_account_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getReal_name_auth() {
        return real_name_auth;
    }

    public void setReal_name_auth(int real_name_auth) {
        this.real_name_auth = real_name_auth;
    }

    public long getIdcard_auth_time() {
        return idcard_auth_time;
    }

    public void setIdcard_auth_time(long idcard_auth_time) {
        this.idcard_auth_time = idcard_auth_time;
    }

    public String getPay_account() {
        return pay_account;
    }

    public void setPay_account(String pay_account) {
        this.pay_account = pay_account;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
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

    public int getNew_hand() {
        return new_hand;
    }

    public void setNew_hand(int new_hand) {
        this.new_hand = new_hand;
    }

    public int getReturn_status() {
        return return_status;
    }

    public void setReturn_status(int return_status) {
        this.return_status = return_status;
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

    public String getInvite_source() {
        return invite_source;
    }

    public void setInvite_source(String invite_source) {
        this.invite_source = invite_source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReg_source() {
        return reg_source;
    }

    public void setReg_source(int reg_source) {
        this.reg_source = reg_source;
    }

    public long getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(long last_login_time) {
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

    public int getOpen_account() {
        return open_account;
    }

    public void setOpen_account(int open_account) {
        this.open_account = open_account;
    }

    public String getOpen_account_time() {
        return open_account_time;
    }

    public void setOpen_account_time(String open_account_time) {
        this.open_account_time = open_account_time;
    }

    @Override
    public String toString() {
        return "UserDetailBean{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", pay_passwd='" + pay_passwd + '\'' +
                ", invite_code='" + invite_code + '\'' +
                ", phone='" + phone + '\'' +
                ", real_name='" + real_name + '\'' +
                ", idcard_no='" + idcard_no + '\'' +
                ", real_name_auth=" + real_name_auth +
                ", idcard_auth_time=" + idcard_auth_time +
                ", pay_account='" + pay_account + '\'' +
                ", sex=" + sex +
                ", update_time=" + update_time +
                ", create_time=" + create_time +
                ", inviter_phone='" + inviter_phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", new_hand=" + new_hand +
                ", return_status=" + return_status +
                ", remark='" + remark + '\'' +
                ", user_type=" + user_type +
                ", invite_source='" + invite_source + '\'' +
                ", status=" + status +
                ", reg_source=" + reg_source +
                ", last_login_time=" + last_login_time +
                ", login_ip='" + login_ip + '\'' +
                ", reg_ip='" + reg_ip + '\'' +
                ", open_account=" + open_account +
                ", open_account_time='" + open_account_time + '\'' +
                '}';
    }
}
