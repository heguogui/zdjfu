package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *邀请列表数组基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/13 0013.
 */

public class InviteRewordLists {


    private List<InviteRewordBean> dataList;
    private int total;

    public List<InviteRewordBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<InviteRewordBean> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "InviteRewordLists{" +
                "dataList=" + dataList +
                ", total=" + total +
                '}';
    }
}
