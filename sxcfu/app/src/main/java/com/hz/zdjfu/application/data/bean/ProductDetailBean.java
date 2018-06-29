package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/8 0008.
 */

public class ProductDetailBean implements Serializable{

    private String lender_name;
    private String lender_age;
    private String lender_sex;
    private String lender_state;
    private String lender_address;
    private String lender_z_image;
    private String lender_f_image;
    private String detail_describe;

    private String borrower_name;
    private String borrower_age;
    private String borrower_sex;
    private String borrower_state;
    private String borrower_address;
    private String borrower_z_image;
    private String borrower_f_image;

    private String use;

    public String getLender_name() {
        return lender_name;
    }

    public void setLender_name(String lender_name) {
        this.lender_name = lender_name;
    }

    public String getLender_age() {
        return lender_age;
    }

    public void setLender_age(String lender_age) {
        this.lender_age = lender_age;
    }

    public String getLender_sex() {
        return lender_sex;
    }

    public void setLender_sex(String lender_sex) {
        this.lender_sex = lender_sex;
    }

    public String getLender_state() {
        return lender_state;
    }

    public void setLender_state(String lender_state) {
        this.lender_state = lender_state;
    }

    public String getLender_address() {
        return lender_address;
    }

    public void setLender_address(String lender_address) {
        this.lender_address = lender_address;
    }

    public String getLender_z_image() {
        return lender_z_image;
    }

    public void setLender_z_image(String lender_z_image) {
        this.lender_z_image = lender_z_image;
    }

    public String getLender_f_image() {
        return lender_f_image;
    }

    public void setLender_f_image(String lender_f_image) {
        this.lender_f_image = lender_f_image;
    }

    public String getDetail_describe() {
        return detail_describe;
    }

    public void setDetail_describe(String detail_describe) {
        this.detail_describe = detail_describe;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public String getBorrower_age() {
        return borrower_age;
    }

    public void setBorrower_age(String borrower_age) {
        this.borrower_age = borrower_age;
    }

    public String getBorrower_sex() {
        return borrower_sex;
    }

    public void setBorrower_sex(String borrower_sex) {
        this.borrower_sex = borrower_sex;
    }

    public String getBorrower_state() {
        return borrower_state;
    }

    public void setBorrower_state(String borrower_state) {
        this.borrower_state = borrower_state;
    }

    public String getBorrower_address() {
        return borrower_address;
    }

    public void setBorrower_address(String borrower_address) {
        this.borrower_address = borrower_address;
    }

    public String getBorrower_z_image() {
        return borrower_z_image;
    }

    public void setBorrower_z_image(String borrower_z_image) {
        this.borrower_z_image = borrower_z_image;
    }

    public String getBorrower_f_image() {
        return borrower_f_image;
    }

    public void setBorrower_f_image(String borrower_f_image) {
        this.borrower_f_image = borrower_f_image;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    @Override
    public String toString() {
        return "ProductDetailBean{" +
                "lender_name='" + lender_name + '\'' +
                ", lender_age='" + lender_age + '\'' +
                ", lender_sex='" + lender_sex + '\'' +
                ", lender_state='" + lender_state + '\'' +
                ", lender_address='" + lender_address + '\'' +
                ", lender_z_image='" + lender_z_image + '\'' +
                ", lender_f_image='" + lender_f_image + '\'' +
                ", detail_describe='" + detail_describe + '\'' +
                ", borrower_name='" + borrower_name + '\'' +
                ", borrower_age='" + borrower_age + '\'' +
                ", borrower_sex='" + borrower_sex + '\'' +
                ", borrower_state='" + borrower_state + '\'' +
                ", borrower_address='" + borrower_address + '\'' +
                ", borrower_z_image='" + borrower_z_image + '\'' +
                ", borrower_f_image='" + borrower_f_image + '\'' +
                ", use='" + use + '\'' +
                '}';
    }
}
