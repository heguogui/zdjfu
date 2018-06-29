package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/1 0001.
 */

public class AccountDataList {

    private AccountBalanceBean dataList;

    public AccountBalanceBean getDataList() {
        return dataList;
    }

    public void setDataList(AccountBalanceBean dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "AccountDataList{" +
                "dataList=" + dataList +
                '}';
    }
}
