package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *理财基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/30 0030
 */
public class FinancialBean implements Serializable{

    private List<ZTProductBean>  pageList;
    private int pageNum;
    private int pageSize;
    private int totalCount;

    public List<ZTProductBean> getPageList() {
        return pageList;
    }

    public void setPageList(List<ZTProductBean> pageList) {
        this.pageList = pageList;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "FinancialBean{" +
                "pageList=" + pageList +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                '}';
    }
}
