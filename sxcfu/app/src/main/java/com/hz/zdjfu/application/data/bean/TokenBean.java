package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *TOKEN
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/16 0016.
 */

public class TokenBean implements Serializable{

    private String jwtToken;
    private String accessToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "jwtToken='" + jwtToken + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
