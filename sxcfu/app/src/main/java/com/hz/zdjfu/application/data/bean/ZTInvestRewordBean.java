package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *直投投资记录基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTInvestRewordBean implements Serializable{

    /**
     * investAmt : 100
     * userName : 134****3004
     * investTime : 2018-01-19 17:50:46
     */

    private String investAmt;
    private String userName;
    private String investTime;

    public String getInvestAmt() {
        return investAmt;
    }

    public void setInvestAmt(String investAmt) {
        this.investAmt = investAmt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInvestTime() {
        return investTime;
    }

    public void setInvestTime(String investTime) {
        this.investTime = investTime;
    }

    @Override
    public String toString() {
        return "ZTInvestRewordBean{" +
                "investAmt='" + investAmt + '\'' +
                ", userName='" + userName + '\'' +
                ", investTime='" + investTime + '\'' +
                '}';
    }
}
