package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/1 0001.
 */

public class BankLists {

    private List<BankBean> dataList;

    public List<BankBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<BankBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "BankLists{" +
                "dataList=" + dataList +
                '}';
    }
}
