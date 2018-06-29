package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class NewsBean implements Serializable{


    private String mid;
    private String mTitle;
    private String mTime;
    private String mImageUrl;
    private String mH5Url;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmH5Url() {
        return mH5Url;
    }

    public void setmH5Url(String mH5Url) {
        this.mH5Url = mH5Url;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "mid='" + mid + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mH5Url='" + mH5Url + '\'' +
                '}';
    }
}
