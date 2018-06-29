package com.hz.zdjfu.application.modle.mine.rechange;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.RechangeDetail;
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
 * 充值接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RechangePresenter implements RechangeContract.Presenter{


    private static final String TAG =RechangePresenter.class.getName();
    private RechangeContract.View mView;
    private Context mContext;

    public RechangePresenter(Context context, RechangeContract.View view){
        this.mContext =context;
        this.mView =view;
        mView.setPresenter(this);
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
    public void rechangeDetailHttpRequest(String phone) {


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

        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createService().rechangeDetail(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<RechangeDetail>() {
            @Override
            public void onSuccess(RechangeDetail result) {
                DialogManager.hideProgressDialog();
                if(result!=null) {
                    mView.showRechangeDetail(result);
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
    public void sureSMS(String phone,String valid_code) {


        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(valid_code)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        DialogManager.showProgressDialog(mContext,"充值中...");
        String mSign =HttpsUtils.getHttpRequestSign(phone,valid_code);
        RetrofitUtil.createService().rechangeSMSsure(phone,mIP,valid_code,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                mView.validCodeSuccess(true);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
                mView.validCodeSuccess(false);
            }
        });

    }

    @Override
    public void rechange(String amount) {


        DialogManager.showProgressDialog(mContext,"充值中...");

        RetrofitUtil.createZTHService().ztRechange("1",amount).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null&&mView!=null){
                    Log.i(TAG,result+"");
                    DialogManager.hideProgressDialog();
                    mView.showSHrechangeUrl(result);
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
    public void accountBalance(String phone) {
        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        String mSign =HttpsUtils.getHttpRequestSign(phone,Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().accountBalance(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AccountDataList>() {
            @Override
            public void onSuccess(AccountDataList result) {

                if(result!=null&&!TextUtils.isEmpty(result.getDataList().getBalance())){
                    mView.showAccountBalance(result.getDataList().getBalance());
                }
            }
            @Override
            public void _onError(Throwable e) {
               mView.showErrorTips(e.getMessage());
            }
        });
    }
}
