package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/22 0022.
 */

public class RechangeDetailList implements Serializable{


    /**
     * coinName : 新手注册
     * coinAmount : +10.00
     * time : 2018-03-27 03:39
     */

    private String pageSize;
    private String pageNum;
    private String totalCount;
    private List<RechangeDetailBean> pageList;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<RechangeDetailBean> getPageList() {
        return pageList;
    }

    public void setPageList(List<RechangeDetailBean> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "RechangeDetailList{" +
                "pageSize='" + pageSize + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", pageList=" + pageList +
                '}';
    }
}
