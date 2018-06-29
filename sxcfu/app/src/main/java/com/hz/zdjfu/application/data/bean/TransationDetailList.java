package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/16 0016.
 */

public class TransationDetailList implements Serializable{

    /**
     * pageSize : 10
     * pageNum : 1
     * totalCount : 0
     * pageList : []
     */

    private int pageSize;
    private int pageNum;
    private int totalCount;
    private List<TransationDetailBean> pageList;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<TransationDetailBean> getPageList() {
        return pageList;
    }

    public void setPageList(List<TransationDetailBean> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "TransationDetailList{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                ", pageList=" + pageList +
                '}';
    }
}
