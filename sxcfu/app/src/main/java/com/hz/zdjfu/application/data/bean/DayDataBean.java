package com.hz.zdjfu.application.data.bean;

/**
 * [类功能说明]
 *每天的属性
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/28 0028.
 */

public class DayDataBean {

    private int day;//日期
    private int state;//状态
    private int size;//多少

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public DayDataBean(int day, int state,int size) {
        this.day = day;
        this.size = size;
        this.state = state;
    }


    @Override
    public String toString() {
        return "DayDataBean{" +
                "day=" + day +
                ", size=" + size +
                ", state=" + state +
                '}';
    }
}
