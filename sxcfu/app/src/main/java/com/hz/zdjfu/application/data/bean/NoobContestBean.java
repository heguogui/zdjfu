package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/18 0018.
 */

public class NoobContestBean implements Serializable{

    private String mid;
    private String mName;
    private String mContent;
    private boolean mState;
    private String mImage;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public boolean ismState() {
        return mState;
    }

    public void setmState(boolean mState) {
        this.mState = mState;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "NoobContestBean{" +
                "mid='" + mid + '\'' +
                ", mName='" + mName + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mState=" + mState +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
