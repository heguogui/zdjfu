package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 * 设置请求接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RedpacketCouponPresenter implements RedpacketCouponContract.Presenter{


    private static final String TAG =RedpacketCouponPresenter.class.getName();
    private RedpacketCouponContract.View mView;
    private Context mContext;
    public RedpacketCouponPresenter(Context context, RedpacketCouponContract.View view){
        this.mContext =context;
        this.mView =view;
        this.mView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


}
