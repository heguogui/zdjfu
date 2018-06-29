package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class ResultDataList {
    private List<String> dataList;

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "dataList=" + dataList +
                '}';
    }
}
