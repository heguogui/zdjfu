package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/25 0025
 */
public class SplashRecordBeans {

    private String imageUrl;
    private String hrefUrl;
    private String title;
    private String id;
    private String alt;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "SplashRecordBeans{" +
                "imageUrl='" + imageUrl + '\'' +
                ", hrefUrl='" + hrefUrl + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", alt='" + alt + '\'' +
                '}';
    }
}
