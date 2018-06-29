package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *  广告基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class BannerRecordsBean {

    private String id;
    private String web_site;
    private String position;
    private String title;
    private String alt;
    private String image_url;
    private String href_url;
    private String order_number;
    private String is_show;
    private String create_time;
    private String update_time;
    private String view_count;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeb_site() {
        return web_site;
    }

    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getHref_url() {
        return href_url;
    }

    public void setHref_url(String href_url) {
        this.href_url = href_url;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    @Override
    public String toString() {
        return "BannerRecordsBean{" +
                "id='" + id + '\'' +
                ", web_site='" + web_site + '\'' +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                ", alt='" + alt + '\'' +
                ", image_url='" + image_url + '\'' +
                ", href_url='" + href_url + '\'' +
                ", order_number='" + order_number + '\'' +
                ", is_show='" + is_show + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", view_count='" + view_count + '\'' +
                '}';
    }
}
