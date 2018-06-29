package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24
 */

public class FinancialNoticationList implements Serializable{

    private List<FinancialMarqueeBean> list;

    public List<FinancialMarqueeBean> getList() {
        return list;
    }

    public void setList(List<FinancialMarqueeBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FinancialNoticationList{" +
                "list=" + list +
                '}';
    }
}
