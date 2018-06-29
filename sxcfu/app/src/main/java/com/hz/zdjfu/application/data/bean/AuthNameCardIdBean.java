package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/1/19 0019.
 */

public class AuthNameCardIdBean implements Serializable{

    private int real_name_auth;
    private String real_name;
    private String idcard_no;

    public int getReal_name_auth() {
        return real_name_auth;
    }

    public void setReal_name_auth(int real_name_auth) {
        this.real_name_auth = real_name_auth;
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

    @Override
    public String toString() {
        return "AuthNameCardIdBean{" +
                "real_name_auth=" + real_name_auth +
                ", real_name='" + real_name + '\'' +
                ", idcard_no='" + idcard_no + '\'' +
                '}';
    }
}
