package com.hz.zdjfu.application.modle.mine.personcenter.forgetpsd;

import android.content.Context;
import android.text.TextUtils;

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
public class ForgetPsdPresenter implements ForgetPsdContract.Presenter{


    private static final String TAG =ForgetPsdPresenter.class.getName();
    private ForgetPsdContract.View mView;
    private Context mContext;
    public ForgetPsdPresenter(Context context, ForgetPsdContract.View view){
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
    public void sureUpdatePsdHttpRequest(String phone, String code, String newpassword) {


        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,newpassword);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        DialogManager.showProgressDialog(mContext,"提交中...");

        RetrofitUtil.createService().forgetPasswd(phone,mIP,newpassword,mSign,code).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&result.equals("密码修改成功")){
                    mView.forgetpPwdSuccess(true);

                }else{
                    mView.forgetpPwdSuccess(false);
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
    public void sendCodeHttpRequest(String phone) {
        mView.setSendCodeBtnEnable(false);

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            mView.setSendCodeBtnEnable(true);
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,"2");
        if(TextUtils.isEmpty(mSign)){
            mView.setSendCodeBtnEnable(true);
            return;
        }

        RetrofitUtil.createService().send(phone,"2",mIP,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.setSendCodeBtnEnable(true);
                mView.setCodeTips(phone);
                mView.triggerCountDown();
                mView.focusOnCodeEdit();
            }

            @Override
            public void _onError(Throwable e) {
                mView.setSendCodeBtnEnable(true);
                mView.showErrorTips(e.getMessage());
            }
        });
    }
}
