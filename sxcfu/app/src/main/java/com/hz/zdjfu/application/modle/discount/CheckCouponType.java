package com.hz.zdjfu.application.modle.discount;

import com.hz.zdjfu.application.data.bean.DiscountZJZBean;

import java.io.Serializable;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/24 0024.
 */

public class CheckCouponType implements Serializable{

    private boolean mZjzNum;
    private boolean mRedPacket;
    private boolean mAddInterest;
    private DiscountZJZBean mCheckRedPacket;
    private DiscountZJZBean mCheckAddInterest;
    private DiscountZJZBean mCheckZJZBean;




    public boolean ismZjzNum() {
        return mZjzNum;
    }

    public void setmZjzNum(boolean mZjzNum) {
        this.mZjzNum = mZjzNum;
    }

    public boolean ismRedPacket() {
        return mRedPacket;
    }

    public void setmRedPacket(boolean mRedPacket) {
        this.mRedPacket = mRedPacket;
    }

    public boolean ismAddInterest() {
        return mAddInterest;
    }

    public void setmAddInterest(boolean mAddInterest) {
        this.mAddInterest = mAddInterest;
    }

    public DiscountZJZBean getmCheckRedPacket() {
        return mCheckRedPacket;
    }

    public void setmCheckRedPacket(DiscountZJZBean mCheckRedPacket) {
        this.mCheckRedPacket = mCheckRedPacket;
    }

    public DiscountZJZBean getmCheckAddInterest() {
        return mCheckAddInterest;
    }

    public void setmCheckAddInterest(DiscountZJZBean mCheckAddInterest) {
        this.mCheckAddInterest = mCheckAddInterest;
    }

    public DiscountZJZBean getmCheckZJZBean() {
        return mCheckZJZBean;
    }

    public void setmCheckZJZBean(DiscountZJZBean mCheckZJZBean) {
        this.mCheckZJZBean = mCheckZJZBean;
    }

    @Override
    public String toString() {
        return "CheckCouponType{" +
                "mZjzNum=" + mZjzNum +
                ", mRedPacket=" + mRedPacket +
                ", mAddInterest=" + mAddInterest +
                ", mCheckRedPacket=" + mCheckRedPacket +
                ", mCheckAddInterest=" + mCheckAddInterest +
                ", mCheckZJZBean=" + mCheckZJZBean +
                '}';
    }
}
