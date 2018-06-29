package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/2 0002.
 */

public class HomeDataBean implements Serializable{

    private List<BannerRecordsBean>  advertiseList;
    private UserDetailBean user;
    private List<PublicNoticeRecordsBean> noticeList;
    private List<ProductBean> productList;

    public List<BannerRecordsBean> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(List<BannerRecordsBean> advertiseList) {
        this.advertiseList = advertiseList;
    }

    public UserDetailBean getUser() {
        return user;
    }

    public void setUser(UserDetailBean user) {
        this.user = user;
    }

    public List<PublicNoticeRecordsBean> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<PublicNoticeRecordsBean> noticeList) {
        this.noticeList = noticeList;
    }

    public List<ProductBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductBean> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "HomeDataBean{" +
                "advertiseList=" + advertiseList +
                ", user=" + user +
                ", noticeList=" + noticeList +
                ", productList=" + productList +
                '}';
    }
}
