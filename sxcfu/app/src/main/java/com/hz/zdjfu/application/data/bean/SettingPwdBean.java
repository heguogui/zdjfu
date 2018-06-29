package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/25 0025.
 */

public class SettingPwdBean implements Serializable{

    private String dataList;

    public String getDataList() {
        return dataList;
    }

    public void setDataList(String dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "SettingPwdBean{" +
                "dataList='" + dataList + '\'' +
                '}';
    }
}
