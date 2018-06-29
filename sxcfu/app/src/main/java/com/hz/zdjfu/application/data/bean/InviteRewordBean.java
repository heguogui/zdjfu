package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *邀请列表基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/13 0013.
 */

public class InviteRewordBean implements Serializable{


    /**
     * user_name : 15255851113
     * register_time : 2017-11-29
     * coin : .00
     */

    private String user_name;
    private String register_time;
    private String coin;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "InviteRewordBean{" +
                "user_name='" + user_name + '\'' +
                ", register_time='" + register_time + '\'' +
                ", coin='" + coin + '\'' +
                '}';
    }
}
