package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/15 0015.
 */

public class QuickBankCardList implements Serializable{


    private BankCardBean dataList;
    public BankCardBean getDataList() {
        return dataList;
    }

    public void setDataList(BankCardBean dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "QuickBankCardList{" +
                "dataList=" + dataList +
                '}';
    }
}
