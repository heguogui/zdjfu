package com.hz.zdjfu.application.base;

import com.hz.zdjfu.application.data.bean.ZTInvestRewordBean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *直投投资记录
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTInvestRewordLists implements Serializable{

    private String pageSize;
    private String pageNum;
    private int totalCount;
    private List<ZTInvestRewordBean> pageList;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ZTInvestRewordBean> getPageList() {
        return pageList;
    }

    public void setPageList(List<ZTInvestRewordBean> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "ZTInvestRewordLists{" +
                "pageSize='" + pageSize + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", totalCount=" + totalCount +
                ", pageList=" + pageList +
                '}';
    }
}
