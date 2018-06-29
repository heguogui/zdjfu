package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/17 0017.
 */

public class ZTSHBean implements Serializable{
    private  String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "ZTSHBean{" +
                "redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
