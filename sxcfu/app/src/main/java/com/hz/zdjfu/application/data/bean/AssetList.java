package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/27 0027.
 */

public class AssetList {

    List<AssetBean> dataList;

    public List<AssetBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<AssetBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "AssetList{" +
                "dataList=" + dataList +
                '}';
    }
}
