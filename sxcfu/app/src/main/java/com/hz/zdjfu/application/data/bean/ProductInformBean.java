package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *债转产品类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/16 0016.
 */

public class ProductInformBean implements Serializable{

    private ProductDetail product;
    private LenderBean lender;
    private LoanBean loan;
    private List<ReturnPlayBean> list_income;
    private List<LenderImageBean> lenderImgList;
    private List<LoanImageBean> loanerImgList;
    private List<ProductImageBean> productImgList;


    public List<LenderImageBean> getLenderImgList() {
        return lenderImgList;
    }

    public void setLenderImgList(List<LenderImageBean> lenderImgList) {
        this.lenderImgList = lenderImgList;
    }

    public List<LoanImageBean> getLoanImgList() {
        return loanerImgList;
    }

    public void setLoanImgList(List<LoanImageBean> loanImgList) {
        this.loanerImgList = loanImgList;
    }

    @Override
    public String toString() {
        return "ProductInformBean{" +
                "product=" + product +
                ", lender=" + lender +
                ", loan=" + loan +
                ", list_income=" + list_income +
                ", loanImgList=" + loanerImgList +
                ", lenderImgList=" + lenderImgList +
                ", productImgList=" + productImgList +
                '}';
    }

    public ProductDetail getProduct() {
        return product;
    }

    public void setProduct(ProductDetail product) {
        this.product = product;
    }

    public LenderBean getLender() {
        return lender;
    }

    public void setLender(LenderBean lender) {
        this.lender = lender;
    }

    public LoanBean getLoan() {
        return loan;
    }

    public void setLoan(LoanBean loan) {
        this.loan = loan;
    }

    public List<ReturnPlayBean> getList_income() {
        return list_income;
    }

    public void setList_income(List<ReturnPlayBean> list_income) {
        this.list_income = list_income;
    }



    public List<ProductImageBean> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImageBean> productImgList) {
        this.productImgList = productImgList;
    }


}
