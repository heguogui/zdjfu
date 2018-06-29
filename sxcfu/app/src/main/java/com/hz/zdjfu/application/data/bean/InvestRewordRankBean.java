package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/17 0017.
 */

public class InvestRewordRankBean {

    private String id;
    private String phone;
    private String propervalue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPropervalue() {
        return propervalue;
    }

    public void setPropervalue(String propervalue) {
        this.propervalue = propervalue;
    }

    @Override
    public String toString() {
        return "InvestRewordRankBean{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", propervalue='" + propervalue + '\'' +
                '}';
    }
}
