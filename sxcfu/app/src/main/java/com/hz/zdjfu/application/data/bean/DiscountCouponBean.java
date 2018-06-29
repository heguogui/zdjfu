package com.hz.zdjfu.application.data.bean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/24 0024.
 */

public class DiscountCouponBean implements Serializable{

    private  DiscountZJZBean mDiscountZJZBean;
    private boolean isCheck;

    public DiscountZJZBean getmDiscountZJZBean() {
        return mDiscountZJZBean;
    }

    public void setmDiscountZJZBean(DiscountZJZBean mDiscountZJZBean) {
        this.mDiscountZJZBean = mDiscountZJZBean;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "DiscountCouponBean{" +
                "mDiscountZJZBean=" + mDiscountZJZBean +
                ", isCheck=" + isCheck +
                '}';
    }
}
