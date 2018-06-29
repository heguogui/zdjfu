package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *红包卡券
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class RedpacketCouponActivity extends ToolbarActivity{


    private static final String TAG =RedpacketCouponActivity.class.getName();
    public RedpacketCouponFragment mRedpacketCouponFragment;
    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RedpacketCouponActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_redpacketcoupon;
    }

    @Override
    protected BaseFragment getFragment() {
        mRedpacketCouponFragment =RedpacketCouponFragment.newInstance();
        return mRedpacketCouponFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.redpacketcoupon_top_title));
        showBackBtn(true);
    }



    public void refreshRedpacketData(int num){
        if(mRedpacketCouponFragment!=null){
            mRedpacketCouponFragment.refreshRedpacketData(num);
        }
    }

    public void refreshCouponData(int num){
        if(mRedpacketCouponFragment!=null){
            mRedpacketCouponFragment.refreshCouponData(num);
        }
    }



    public void showCoinNum(String num){
//        if(mRedpacketCouponFragment!=null){
//            mRedpacketCouponFragment.showAllZJZNum(num);
//        }
    }



}
