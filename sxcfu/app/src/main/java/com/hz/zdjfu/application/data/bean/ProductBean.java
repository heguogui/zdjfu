package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/2 0002.
 */

public class ProductBean implements Serializable{


    /**
     * id : 8146751384280760
     * product_code : 车财道12011期
     * product_name : 奔驰S350（3.5L）汽车借款债权项目
     * status : 4
     * debt_code : RAH201612005
     * is_serial : 1
     * serial_no : 2
     * is_fresh : 1
     * ensure_type : 2
     * mortgage_type : 1
     * start_date : 1482768000000
     * will_end_date : 1486051200000
     * end_date : 1486051200000
     * debt_money : 220000.0
     * money : 20000.0
     * sale_money : 5000.0
     * balance : 0.0
     * buyer_count : 8
     * pay_min : 100.0
     * pay_add : 1.0
     * pay_max : 20000.0
     * income : 13.58
     * income_offline : 16.8
     * income_method : 2
     * return_method : 2
     * online : 1
     * remark : 000成功
     * create_id : 201612261177905404
     * create_time : 1483069103000
     * audit_id : 201612261177905404
     * audit_time : 1483081747000
     * issue_time : 1483938000000
     * full_time : 1484710866000
     * order_no : 2
     * photo : https://www.zdjfu.com/upload/product/8146751384280760/photo/封面.jpg
     * will_issue_time : 1483936200000
     * will_show_time : 1483936200000
     * act_show_time : 1482750000000
     * advance_status : 0
     * advance_date : 1482681600000
     * is_send_msg : 0
     * platform_interest : 1.0
     * product_type : 1
     * return_days : 0
     * label : 2017-1-9 13:00:00
     * product_id : null
     * home_order : 2
     * home_stats : 1
     * status_text : null
     * speed : 75.0
     * lender_id : 3
     * can_buy_money : 0.0
     */

    private long id;
    private String product_code;
    private String product_name;
    private int status;
    private String debt_code;
    private int is_serial;
    private String serial_no;
    private int is_fresh;
    private int ensure_type;
    private int mortgage_type;
    private long start_date;
    private long will_end_date;
    private long end_date;
    private double debt_money;
    private double money;
    private double sale_money;
    private double balance;
    private int buyer_count;
    private int pay_min;
    private double pay_add;
    private double pay_max;
    private double income;
    private double income_offline;
    private int income_method;
    private int return_method;
    private int online;
    private String remark;
    private long create_id;
    private long create_time;
    private long audit_id;
    private long audit_time;
    private long issue_time;
    private long full_time;
    private int order_no;
    private String photo;
    private long will_issue_time;
    private long will_show_time;
    private long act_show_time;
    private int advance_status;
    private long advance_date;
    private int is_send_msg;
    private double platform_interest;
    private int product_type;
    private int return_days;
    private String label;
    private String product_id;
    private int home_order;
    private int home_stats;
    private String status_text;
    private double speed;
    private int lender_id;
    private double can_buy_money;
    private int incomeDays;

    public int getIncomeDays() {
        return incomeDays;
    }

    public void setIncomeDays(int incomeDays) {
        this.incomeDays = incomeDays;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDebt_code() {
        return debt_code;
    }

    public void setDebt_code(String debt_code) {
        this.debt_code = debt_code;
    }

    public int getIs_serial() {
        return is_serial;
    }

    public void setIs_serial(int is_serial) {
        this.is_serial = is_serial;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public int getIs_fresh() {
        return is_fresh;
    }

    public void setIs_fresh(int is_fresh) {
        this.is_fresh = is_fresh;
    }

    public int getEnsure_type() {
        return ensure_type;
    }

    public void setEnsure_type(int ensure_type) {
        this.ensure_type = ensure_type;
    }

    public int getMortgage_type() {
        return mortgage_type;
    }

    public void setMortgage_type(int mortgage_type) {
        this.mortgage_type = mortgage_type;
    }

    public long getStart_date() {
        return start_date;
    }

    public void setStart_date(long start_date) {
        this.start_date = start_date;
    }

    public long getWill_end_date() {
        return will_end_date;
    }

    public void setWill_end_date(long will_end_date) {
        this.will_end_date = will_end_date;
    }

    public long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    public double getDebt_money() {
        return debt_money;
    }

    public void setDebt_money(double debt_money) {
        this.debt_money = debt_money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getSale_money() {
        return sale_money;
    }

    public void setSale_money(double sale_money) {
        this.sale_money = sale_money;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBuyer_count() {
        return buyer_count;
    }

    public void setBuyer_count(int buyer_count) {
        this.buyer_count = buyer_count;
    }

    public int getPay_min() {
        return pay_min;
    }

    public void setPay_min(int pay_min) {
        this.pay_min = pay_min;
    }

    public double getPay_add() {
        return pay_add;
    }

    public void setPay_add(double pay_add) {
        this.pay_add = pay_add;
    }

    public double getPay_max() {
        return pay_max;
    }

    public void setPay_max(double pay_max) {
        this.pay_max = pay_max;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getIncome_offline() {
        return income_offline;
    }

    public void setIncome_offline(double income_offline) {
        this.income_offline = income_offline;
    }

    public int getIncome_method() {
        return income_method;
    }

    public void setIncome_method(int income_method) {
        this.income_method = income_method;
    }

    public int getReturn_method() {
        return return_method;
    }

    public void setReturn_method(int return_method) {
        this.return_method = return_method;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreate_id() {
        return create_id;
    }

    public void setCreate_id(long create_id) {
        this.create_id = create_id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(long audit_id) {
        this.audit_id = audit_id;
    }

    public long getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(long audit_time) {
        this.audit_time = audit_time;
    }

    public long getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(long issue_time) {
        this.issue_time = issue_time;
    }

    public long getFull_time() {
        return full_time;
    }

    public void setFull_time(long full_time) {
        this.full_time = full_time;
    }

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getWill_issue_time() {
        return will_issue_time;
    }

    public void setWill_issue_time(long will_issue_time) {
        this.will_issue_time = will_issue_time;
    }

    public long getWill_show_time() {
        return will_show_time;
    }

    public void setWill_show_time(long will_show_time) {
        this.will_show_time = will_show_time;
    }

    public long getAct_show_time() {
        return act_show_time;
    }

    public void setAct_show_time(long act_show_time) {
        this.act_show_time = act_show_time;
    }

    public int getAdvance_status() {
        return advance_status;
    }

    public void setAdvance_status(int advance_status) {
        this.advance_status = advance_status;
    }

    public long getAdvance_date() {
        return advance_date;
    }

    public void setAdvance_date(long advance_date) {
        this.advance_date = advance_date;
    }

    public int getIs_send_msg() {
        return is_send_msg;
    }

    public void setIs_send_msg(int is_send_msg) {
        this.is_send_msg = is_send_msg;
    }

    public double getPlatform_interest() {
        return platform_interest;
    }

    public void setPlatform_interest(double platform_interest) {
        this.platform_interest = platform_interest;
    }

    public int getProduct_type() {
        return product_type;
    }

    public void setProduct_type(int product_type) {
        this.product_type = product_type;
    }

    public int getReturn_days() {
        return return_days;
    }

    public void setReturn_days(int return_days) {
        this.return_days = return_days;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getHome_order() {
        return home_order;
    }

    public void setHome_order(int home_order) {
        this.home_order = home_order;
    }

    public int getHome_stats() {
        return home_stats;
    }

    public void setHome_stats(int home_stats) {
        this.home_stats = home_stats;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getLender_id() {
        return lender_id;
    }

    public void setLender_id(int lender_id) {
        this.lender_id = lender_id;
    }

    public double getCan_buy_money() {
        return can_buy_money;
    }

    public void setCan_buy_money(double can_buy_money) {
        this.can_buy_money = can_buy_money;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id=" + id +
                ", product_code='" + product_code + '\'' +
                ", product_name='" + product_name + '\'' +
                ", status=" + status +
                ", debt_code='" + debt_code + '\'' +
                ", is_serial=" + is_serial +
                ", serial_no='" + serial_no + '\'' +
                ", is_fresh=" + is_fresh +
                ", ensure_type=" + ensure_type +
                ", mortgage_type=" + mortgage_type +
                ", start_date=" + start_date +
                ", will_end_date=" + will_end_date +
                ", end_date=" + end_date +
                ", debt_money=" + debt_money +
                ", money=" + money +
                ", sale_money=" + sale_money +
                ", balance=" + balance +
                ", buyer_count=" + buyer_count +
                ", pay_min=" + pay_min +
                ", pay_add=" + pay_add +
                ", pay_max=" + pay_max +
                ", income=" + income +
                ", income_offline=" + income_offline +
                ", income_method=" + income_method +
                ", return_method=" + return_method +
                ", online=" + online +
                ", remark='" + remark + '\'' +
                ", create_id=" + create_id +
                ", create_time=" + create_time +
                ", audit_id=" + audit_id +
                ", audit_time=" + audit_time +
                ", issue_time=" + issue_time +
                ", full_time=" + full_time +
                ", order_no=" + order_no +
                ", photo='" + photo + '\'' +
                ", will_issue_time=" + will_issue_time +
                ", will_show_time=" + will_show_time +
                ", act_show_time=" + act_show_time +
                ", advance_status=" + advance_status +
                ", advance_date=" + advance_date +
                ", is_send_msg=" + is_send_msg +
                ", platform_interest=" + platform_interest +
                ", product_type=" + product_type +
                ", return_days=" + return_days +
                ", label='" + label + '\'' +
                ", product_id='" + product_id + '\'' +
                ", home_order=" + home_order +
                ", home_stats=" + home_stats +
                ", status_text='" + status_text + '\'' +
                ", speed=" + speed +
                ", lender_id=" + lender_id +
                ", can_buy_money=" + can_buy_money +
                ", incomeDays=" + incomeDays +
                '}';
    }
}
