package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *消息基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class MessageBean implements Serializable{


    /**
     * is_read : 1
     * update_time :
     * create_time : 2017-11-21 16:19:52
     * user_id : 98910
     * id : 8
     * title : 汇款
     * content : 返现成功
     * url : 上海
     */

    private String is_read;
    private String update_time;
    private String create_time;
    private String user_id;
    private String id;
    private String title;
    private String content;
    private String url;

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
