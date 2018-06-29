package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/18 0018.
 */

public class CoinBalanceBean implements Serializable{

    private String coinBalance;

    public String getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(String coinBalance) {
        this.coinBalance = coinBalance;
    }

    @Override
    public String toString() {
        return "CoinBalanceBean{" +
                "coinBalance='" + coinBalance + '\'' +
                '}';
    }
}
