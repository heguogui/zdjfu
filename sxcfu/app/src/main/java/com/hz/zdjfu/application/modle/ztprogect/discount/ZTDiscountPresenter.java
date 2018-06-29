package com.hz.zdjfu.application.modle.ztprogect.discount;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.ZTUnUserCouponBean;
import com.hz.zdjfu.application.data.bean.ZTUserCouponBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.modle.discount.DiscountContract;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 直投优惠券接口
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/05/10
 */
public class ZTDiscountPresenter implements ZTDiscountContract.Presenter{


    private static final String TAG =ZTDiscountPresenter.class.getName();
    private ZTDiscountContract.View mView;
    private Context mContext;
    public ZTDiscountPresenter(Context context, ZTDiscountContract.View view){
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

    @Override
    public void allLeftHttpRequest(String product_id, String amount) {

        if(TextUtils.isEmpty(product_id)||TextUtils.isEmpty(amount)){
            return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTHService().ztUserCoupon(product_id,amount).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTUserCouponBean>() {
            @Override
            public void onSuccess(ZTUserCouponBean result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&mView!=null){
                    mView.showLeftCoupon(result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });

    }

    @Override
    public void allRightHttpRequest(String product_id, String amount) {

        if(TextUtils.isEmpty(product_id)||TextUtils.isEmpty(amount)){
            return;
        }
        RetrofitUtil.createZTHService().ztUnUserCoupon(product_id,amount).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTUnUserCouponBean>() {
            @Override
            public void onSuccess(ZTUnUserCouponBean result) {
                if(result!=null&&mView!=null){
                    mView.showRightCoupon(result);
                }
            }
            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }



}
