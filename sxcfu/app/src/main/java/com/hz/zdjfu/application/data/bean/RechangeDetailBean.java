package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *正经值使用详情基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public class RechangeDetailBean implements Serializable{


    /**
     * coinName : 抢标活动
     * coinAmount : +0.60
     * time : 2018-04-18 04:52
     */

    private String remark;
    private String num;
    private String time;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "RechangeDetailBean{" +
                "remark='" + remark + '\'' +
                ", num='" + num + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
