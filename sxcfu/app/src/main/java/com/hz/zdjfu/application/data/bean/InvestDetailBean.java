package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *购买的项目详情
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class InvestDetailBean implements Serializable{



    private String return_method;
    private InvestProductBean product;
    private List<InvestReturnDayBean> buyIncome;

    public String getReturn_method() {
        return return_method;
    }

    public void setReturn_method(String return_method) {
        this.return_method = return_method;
    }

    public InvestProductBean getProduct() {
        return product;
    }

    public void setProduct(InvestProductBean product) {
        this.product = product;
    }

    public List<InvestReturnDayBean> getBuyIncome() {
        return buyIncome;
    }

    public void setBuyIncome(List<InvestReturnDayBean> buyIncome) {
        this.buyIncome = buyIncome;
    }

    @Override
    public String toString() {
        return "InvestDetailBean{" +
                "return_method='" + return_method + '\'' +
                ", product=" + product +
                ", buyIncome=" + buyIncome +
                '}';
    }
}
