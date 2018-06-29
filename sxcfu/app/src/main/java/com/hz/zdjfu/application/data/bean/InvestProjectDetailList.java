package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/22 0022.
 */

public class InvestProjectDetailList {

   private  List<InvestProjectDetailBean> dataList;

    public List<InvestProjectDetailBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<InvestProjectDetailBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "InvestProjectDetailList{" +
                "dataList=" + dataList +
                '}';
    }
}
