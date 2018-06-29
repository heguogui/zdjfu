package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/6 0006.
 */

public class MyInvestLists implements Serializable{

    private MyInvestList dataList;

    public MyInvestList getDataList() {
        return dataList;
    }

    public void setDataList(MyInvestList dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "MyInvestLists{" +
                "dataList=" + dataList +
                '}';
    }
}
