package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/23 0023.
 */

public class DiscountBean implements Serializable{

    private List<DiscountZJZBean>  use;
    private List<DiscountZJZBean>  unUser;
    private List<DiscountZJZBean>  recommend;

    public List<DiscountZJZBean> getUse() {
        return use;
    }

    public void setUse(List<DiscountZJZBean> use) {
        this.use = use;
    }

    public List<DiscountZJZBean> getUnUser() {
        return unUser;
    }

    public void setUnUser(List<DiscountZJZBean> unUser) {
        this.unUser = unUser;
    }

    public List<DiscountZJZBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<DiscountZJZBean> recommend) {
        this.recommend = recommend;
    }


}
