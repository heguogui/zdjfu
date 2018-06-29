package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *正经值数组基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/12 0012.
 */

public class RechangeZJZLists implements Serializable{

    private List<RechangeCenterBean> goodsList;
    private String coinBalance;

    public List<RechangeCenterBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<RechangeCenterBean> goodsList) {
        this.goodsList = goodsList;
    }

    public String getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(String coinBalance) {
        this.coinBalance = coinBalance;
    }

    @Override
    public String toString() {
        return "RechangeZJZLists{" +
                "goodsList=" + goodsList +
                ", coinBalance='" + coinBalance + '\'' +
                '}';
    }
}
