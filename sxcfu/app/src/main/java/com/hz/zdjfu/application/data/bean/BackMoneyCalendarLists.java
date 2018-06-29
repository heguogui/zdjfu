package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/28 0028.
 */

public class BackMoneyCalendarLists implements Serializable{

    private BackMoneyLists dataList;

    public BackMoneyLists getDataList() {
        return dataList;
    }

    public void setDataList(BackMoneyLists dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "BackMoneyCalendarLists{" +
                "dataList=" + dataList +
                '}';
    }
}
