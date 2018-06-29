package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *活动基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/16 0016.
 */

public class PartyBean implements Serializable{

    private String mid;
    private String mImageuUrl;
    private String mH5Url;
    private String mTitle;
    private String mTime;
    private String mState;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getmImageuUrl() {
        return mImageuUrl;
    }

    public void setmImageuUrl(String mImageuUrl) {
        this.mImageuUrl = mImageuUrl;
    }

    public String getmH5Url() {
        return mH5Url;
    }

    public void setmH5Url(String mH5Url) {
        this.mH5Url = mH5Url;
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

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    @Override
    public String toString() {
        return "PartyBean{" +
                "mid='" + mid + '\'' +
                ", mImageuUrl='" + mImageuUrl + '\'' +
                ", mH5Url='" + mH5Url + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mState='" + mState + '\'' +
                '}';
    }
}
