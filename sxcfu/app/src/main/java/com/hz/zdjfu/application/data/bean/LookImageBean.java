package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *浏览图片
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/29 0029.
 */

public class LookImageBean implements Serializable{

    private String m_id;
    private String m_url;
    private String m_name;

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_url() {
        return m_url;
    }

    public void setM_url(String m_url) {
        this.m_url = m_url;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    @Override
    public String toString() {
        return "LookImageBean{" +
                "m_id='" + m_id + '\'' +
                ", m_url='" + m_url + '\'' +
                ", m_name='" + m_name + '\'' +
                '}';
    }
}
