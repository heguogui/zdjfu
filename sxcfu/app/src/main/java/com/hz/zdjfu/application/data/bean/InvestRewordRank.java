package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *投资排行数组
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/13 0013.
 */

public class InvestRewordRank {

    private List<InvestRewordRankBean> fast;
    private List<InvestRewordRankBean> heavyweight;
    private InvestRewordRankBean tail;

    public List<InvestRewordRankBean> getFast() {
        return fast;
    }

    public void setFast(List<InvestRewordRankBean> fast) {
        this.fast = fast;
    }

    public List<InvestRewordRankBean> getHeavyweight() {
        return heavyweight;
    }

    public void setHeavyweight(List<InvestRewordRankBean> heavyweight) {
        this.heavyweight = heavyweight;
    }

    public InvestRewordRankBean getTail() {
        return tail;
    }

    public void setTail(InvestRewordRankBean tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "InvestRewordRank{" +
                "fast=" + fast +
                ", heavyweight=" + heavyweight +
                ", tail=" + tail +
                '}';
    }
}
