package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/5/9 0009.
 */

public class ZTRankList implements Serializable{

    private List<ZTRankBean> fastList;
    private List<ZTRankBean> heavyList;
    private ZTRankBean lastInvtor;

    public List<ZTRankBean> getFastList() {
        return fastList;
    }

    public void setFastList(List<ZTRankBean> fastList) {
        this.fastList = fastList;
    }

    public List<ZTRankBean> getHeavyList() {
        return heavyList;
    }

    public void setHeavyList(List<ZTRankBean> heavyList) {
        this.heavyList = heavyList;
    }

    public ZTRankBean getLastInvtor() {
        return lastInvtor;
    }

    public void setLastInvtor(ZTRankBean lastInvtor) {
        this.lastInvtor = lastInvtor;
    }

    @Override
    public String toString() {
        return "ZTRankList{" +
                "fastList=" + fastList +
                ", heavyList=" + heavyList +
                ", lastInvtor=" + lastInvtor +
                '}';
    }
}
