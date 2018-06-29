package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *首页公告数组类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class PublicNoticeRecordLists {

    private List<PublicNoticeRecordsBean> records;

    public List<PublicNoticeRecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<PublicNoticeRecordsBean> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "PublicNoticeRecordLists{" +
                "records=" + records +
                '}';
    }
}
