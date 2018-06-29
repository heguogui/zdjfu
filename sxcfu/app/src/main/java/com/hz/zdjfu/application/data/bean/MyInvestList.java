package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class MyInvestList implements Serializable{


    private String pageNum;
    private String pageSize;
    private String totalCount;
    private List<MyInvestBean> pageList;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<MyInvestBean> getPageList() {
        return pageList;
    }

    public void setPageList(List<MyInvestBean> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "MyInvestList{" +
                "pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", pageList=" + pageList +
                '}';
    }
}
