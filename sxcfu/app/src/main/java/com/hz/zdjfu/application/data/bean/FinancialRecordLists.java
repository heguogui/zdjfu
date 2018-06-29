package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/30 0030
 */
public class FinancialRecordLists {

    private List<ProductBean> dataList;
    private int limit;
    private int total;
    private int start;

    public List<ProductBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<ProductBean> dataList) {
        this.dataList = dataList;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "FinancialRecordLists{" +
                "dataList=" + dataList +
                ", limit=" + limit +
                ", total=" + total +
                ", start=" + start +
                '}';
    }
}
