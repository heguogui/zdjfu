package com.hz.zdjfu.application.modle.mine.personcenter.withdrawdeposit;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.WithDrawBean;
import com.hz.zdjfu.application.data.bean.WithDrawCouponList;
import com.hz.zdjfu.application.data.bean.WithDrawFreeBean;
import com.hz.zdjfu.application.data.bean.ZTSHBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 登录接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class WithDrawDepositPresenter implements WithDrawDepositContract.Presenter{


    private static final String TAG =WithDrawDepositPresenter.class.getName();
    private WithDrawDepositContract.View mView;
    private Context mContext;
    public WithDrawDepositPresenter(Context context, WithDrawDepositContract.View view){
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
    public void withDrawDepositHttpRequest(String phone, String amount,String couponid) {


        if(TextUtils.isEmpty(phone)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }


        RetrofitUtil.createService().withdrawAmount(phone,mIP,Constants.ANDROID_SOURCE,mSign,amount,couponid).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<WithDrawFreeBean>() {
            @Override
            public void onSuccess(WithDrawFreeBean result) {
                mView.showWithdrawFree(result);
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void getUserCoupon(String phone) {


        if(TextUtils.isEmpty(phone)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }


        RetrofitUtil.createService().withdrawCoupon(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<WithDrawCouponList>() {
            @Override
            public void onSuccess(WithDrawCouponList result) {
                mView.showUserWithDrawCoupon(result);
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }

    @Override
    public void userBankCard(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        DialogManager.showProgressDialog(mContext,"提交中...");

        RetrofitUtil.createService().withdrawBefore(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<WithDrawBean>() {
            @Override
            public void onSuccess(WithDrawBean result) {
                DialogManager.hideProgressDialog();
                mView.showBankCardDetail(result);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });



    }

    @Override
    public void submitWithDrawApply(String phone, String amount, String couponid) {

        DialogManager.showProgressDialog(mContext,"提现中...");
        RetrofitUtil.createZTHService().ztWithdrawApply("1",amount).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null&&mView!=null){
                    DialogManager.hideProgressDialog();
                    mView.showWhitDrawApplyState(result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });


    }
}
