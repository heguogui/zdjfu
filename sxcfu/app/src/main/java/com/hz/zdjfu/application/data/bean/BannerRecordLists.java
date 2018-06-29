package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *广告数组类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class BannerRecordLists {

    private List<BannerRecordsBean> records;

    public List<BannerRecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<BannerRecordsBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "BannerRecordLists{" +
                "records=" + records +
                '}';
    }
}
