package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/25 0025
 */
public class SplashRecordLists{
    private List<SplashRecordBeans> records;

    public List<SplashRecordBeans> getRecords() {
        return records;
    }

    public void setRecords(List<SplashRecordBeans> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "SplashRecordLists{" +
                "records=" + records +
                '}';
    }
}
