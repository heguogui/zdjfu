package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/27 0027.
 */

public class SafeguardBean {

    private String protectModeTitle;
    private String protectModeText;

    public String getProtectModeTitle() {
        return protectModeTitle;
    }

    public void setProtectModeTitle(String protectModeTitle) {
        this.protectModeTitle = protectModeTitle;
    }

    public String getProtectModeText() {
        return protectModeText;
    }

    public void setProtectModeText(String protectModeText) {
        this.protectModeText = protectModeText;
    }

    @Override
    public String toString() {
        return "SafeguardBean{" +
                "protectModeTitle='" + protectModeTitle + '\'' +
                ", protectModeText='" + protectModeText + '\'' +
                '}';
    }
}
