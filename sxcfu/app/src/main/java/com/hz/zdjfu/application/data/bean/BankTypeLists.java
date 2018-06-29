package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class BankTypeLists implements Serializable{


    private List<BankTypeBean> dataList;

    public List<BankTypeBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<BankTypeBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "BankTypeLists{" +
                "dataList=" + dataList +
                '}';
    }
}
