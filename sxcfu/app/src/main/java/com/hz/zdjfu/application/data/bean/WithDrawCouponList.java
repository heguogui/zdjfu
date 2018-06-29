package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class WithDrawCouponList {

    private List<WithDrawCouponBean> dataList;

    public List<WithDrawCouponBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<WithDrawCouponBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "WithDrawCouponList{" +
                "dataList=" + dataList +
                '}';
    }
}
