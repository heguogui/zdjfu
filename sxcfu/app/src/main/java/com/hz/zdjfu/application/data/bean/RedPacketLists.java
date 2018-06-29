package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *红包数组基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/11 0011.
 */

public class RedPacketLists implements Serializable{


    private String total;
    private String start;
    private String limit;
    private String orderByPropertyStart;
    private String orderByPropertyEnd;
    private String orderByPropertyName;
    private String orderByType;
    private String nextStart;
    private String nextPage;
    private String currentPage;
    private String pageCount;
    private List<RedpacketBean> dataList;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOrderByPropertyStart() {
        return orderByPropertyStart;
    }

    public void setOrderByPropertyStart(String orderByPropertyStart) {
        this.orderByPropertyStart = orderByPropertyStart;
    }

    public String getOrderByPropertyEnd() {
        return orderByPropertyEnd;
    }

    public void setOrderByPropertyEnd(String orderByPropertyEnd) {
        this.orderByPropertyEnd = orderByPropertyEnd;
    }

    public String getOrderByPropertyName() {
        return orderByPropertyName;
    }

    public void setOrderByPropertyName(String orderByPropertyName) {
        this.orderByPropertyName = orderByPropertyName;
    }

    public String getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(String orderByType) {
        this.orderByType = orderByType;
    }

    public String getNextStart() {
        return nextStart;
    }

    public void setNextStart(String nextStart) {
        this.nextStart = nextStart;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<RedpacketBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<RedpacketBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "RedPacketLists{" +
                "total='" + total + '\'' +
                ", start='" + start + '\'' +
                ", limit='" + limit + '\'' +
                ", orderByPropertyStart='" + orderByPropertyStart + '\'' +
                ", orderByPropertyEnd='" + orderByPropertyEnd + '\'' +
                ", orderByPropertyName='" + orderByPropertyName + '\'' +
                ", orderByType='" + orderByType + '\'' +
                ", nextStart='" + nextStart + '\'' +
                ", nextPage='" + nextPage + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
