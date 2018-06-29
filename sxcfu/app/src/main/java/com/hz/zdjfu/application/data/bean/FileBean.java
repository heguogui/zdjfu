package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/1 0001.
 */

public class FileBean {

    private String mUrl;
    private String mName;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "mUrl='" + mUrl + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
