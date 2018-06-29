package com.hz.zdjfu.application.data.even;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/1/4 0004.
 */

public class WeiXinShareEven {

    private boolean mState;//分享状态

    public WeiXinShareEven(boolean mState) {
        this.mState = mState;
    }

    public boolean ismState() {
        return mState;
    }

    public void setmState(boolean mState) {
        this.mState = mState;
    }


}
