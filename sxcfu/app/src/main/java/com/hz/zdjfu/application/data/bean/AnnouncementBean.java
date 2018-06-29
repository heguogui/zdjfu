package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *公告
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/28 0028.
 */

public class AnnouncementBean implements Serializable{
    private String id;
    private String title;
    private String create_time;
    private String link;
    private String content;

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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AnnouncementBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", create_time='" + create_time + '\'' +
                ", link='" + link + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
