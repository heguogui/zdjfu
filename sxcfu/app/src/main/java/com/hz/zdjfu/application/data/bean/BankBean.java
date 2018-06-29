package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/1 0001.
 */

public class BankBean implements Serializable{


    /**
     * id : 1
     * create_time : 1507688141000
     * remark : 后台添加银行名称
     * num : ABC
     * name : 农业银行
     * single_max : 0.0
     * day_max : 0.0
     */

    private int id;
    private long create_time;
    private String remark;
    private String num;
    private String name;
    private double single_max;
    private double day_max;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSingle_max() {
        return single_max;
    }

    public void setSingle_max(double single_max) {
        this.single_max = single_max;
    }

    public double getDay_max() {
        return day_max;
    }

    public void setDay_max(double day_max) {
        this.day_max = day_max;
    }

    @Override
    public String toString() {
        return "BankBean{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", remark='" + remark + '\'' +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", single_max=" + single_max +
                ", day_max=" + day_max +
                '}';
    }
}
