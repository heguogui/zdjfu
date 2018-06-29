package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *投资排行榜
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/17 0017.
 */

public class InvestRewordRankList implements Serializable{

    private InvestRewordRank  dataList;

    public InvestRewordRank getDataList() {
        return dataList;
    }

    public void setDataList(InvestRewordRank dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "InvestRewordRankList{" +
                "dataList=" + dataList +
                '}';
    }
}
