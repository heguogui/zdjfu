package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class AppVersionBean implements Serializable{
    /**
     * id : 2
     * release_version : 2017-8
     * sub_version : 最新
     * release_content : 2017-8
     * is_release : 1
     * down_url : 2017-8
     * release_channel : 2017-8
     * re_release_time : 1510726465000
     * create_time : 1510726465000
     * appRelease : null
     */

    private int id;
    private String release_version;
    private String sub_version;
    private String release_content;
    private int is_release;
    private String down_url;
    private String release_channel;
    private long re_release_time;
    private long create_time;
    private String appRelease;
    private int is_force;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelease_version() {
        return release_version;
    }

    public void setRelease_version(String release_version) {
        this.release_version = release_version;
    }

    public String getSub_version() {
        return sub_version;
    }

    public void setSub_version(String sub_version) {
        this.sub_version = sub_version;
    }

    public String getRelease_content() {
        return release_content;
    }

    public void setRelease_content(String release_content) {
        this.release_content = release_content;
    }

    public int getIs_release() {
        return is_release;
    }

    public void setIs_release(int is_release) {
        this.is_release = is_release;
    }

    public String getDown_url() {
        return down_url;
    }

    public void setDown_url(String down_url) {
        this.down_url = down_url;
    }

    public String getRelease_channel() {
        return release_channel;
    }

    public void setRelease_channel(String release_channel) {
        this.release_channel = release_channel;
    }

    public long getRe_release_time() {
        return re_release_time;
    }

    public void setRe_release_time(long re_release_time) {
        this.re_release_time = re_release_time;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getAppRelease() {
        return appRelease;
    }

    public void setAppRelease(String appRelease) {
        this.appRelease = appRelease;
    }

    public int getIs_force() {
        return is_force;
    }

    public void setIs_force(int is_force) {
        this.is_force = is_force;
    }

    @Override
    public String toString() {
        return "AppVersionBean{" +
                "id=" + id +
                ", release_version='" + release_version + '\'' +
                ", sub_version='" + sub_version + '\'' +
                ", release_content='" + release_content + '\'' +
                ", is_release=" + is_release +
                ", down_url='" + down_url + '\'' +
                ", release_channel='" + release_channel + '\'' +
                ", re_release_time=" + re_release_time +
                ", create_time=" + create_time +
                ", appRelease='" + appRelease + '\'' +
                ", is_force=" + is_force +
                '}';
    }
}
