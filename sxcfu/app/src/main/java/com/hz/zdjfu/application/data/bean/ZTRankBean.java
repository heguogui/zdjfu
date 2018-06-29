package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *直投排行榜基类
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTRankBean implements Serializable{

    /**
     * rankingNo : 1
     * userName : 137****2345
     * coinValue : 5
     */

    private int rankingNo;
    private String userName;
    private String coinValue;

    public int getRankingNo() {
        return rankingNo;
    }

    public void setRankingNo(int rankingNo) {
        this.rankingNo = rankingNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(String coinValue) {
        this.coinValue = coinValue;
    }

    @Override
    public String toString() {
        return "ZTRankBean{" +
                "rankingNo=" + rankingNo +
                ", userName='" + userName + '\'' +
                ", coinValue='" + coinValue + '\'' +
                '}';
    }
}
