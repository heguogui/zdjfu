package com.hz.zdjfu.application.modle.mine.personcenter.paypwdmanager;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;


/**
 * [类功能说明]
 * 忘记密码请求接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class PayPwdManagerPresenter implements PayPwdManagerContract.Presenter{


    private static final String TAG =PayPwdManagerPresenter.class.getName();
    private PayPwdManagerContract.View mView;
    private Context mContext;
    public PayPwdManagerPresenter(Context context, PayPwdManagerContract.View view){
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
    public void allPayPwdManagerHttpRequest(String phone) {

    }

    @Override
    public void fundAttestationPhone(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        String mSign =HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().fundAttestationPhone(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DataStringList>() {
            @Override
            public void onSuccess(DataStringList result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&!TextUtils.isEmpty(result.getDataList())){
                    mView.fundAttestationPhone(result.getDataList());
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
    public void resetPayPwd(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        String mSign =HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createService().resetPayPwd(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DataStringList>() {
            @Override
            public void onSuccess(DataStringList result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&!TextUtils.isEmpty(result.getDataList())){
                    mView.resetPayPwd(result.getDataList());
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
    public void updataPayPwd(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        String mSign =HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().updataPayPwd(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DataStringList>() {
            @Override
            public void onSuccess(DataStringList result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&!TextUtils.isEmpty(result.getDataList())){
                    mView.updataPayPwd(result.getDataList());
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
    public void withoutCodePwd(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        String mSign =HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().withoutCodePwd(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DataStringList>() {
            @Override
            public void onSuccess(DataStringList result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&!TextUtils.isEmpty(result.getDataList())){
                    mView.withoutCodePwd(result.getDataList());
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
