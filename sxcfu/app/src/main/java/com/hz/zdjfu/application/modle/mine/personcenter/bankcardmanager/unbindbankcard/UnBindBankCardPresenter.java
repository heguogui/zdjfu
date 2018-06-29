package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.unbindbankcard;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.BankCardContract;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 * 银行卡管理网络请求接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class UnBindBankCardPresenter implements UnbindBankCardContract.Presenter{


    private static final String TAG =BankCardContract.class.getName();
    private UnbindBankCardContract.View mView;
    private Context mContext;
    public UnBindBankCardPresenter(Context context, UnbindBankCardContract.View view){
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
    public void getCodeHttpRequest(String otherPhone) {



        mView.setSendCodeBtnEnable(false);

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            mView.setSendCodeBtnEnable(true);
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }


        UserBean mBean = UserInfoManager.getInstance().getUserBean();

        if(mBean==null||TextUtils.isEmpty(mBean.getUserPhone())){
            mView.setSendCodeBtnEnable(true);
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(mBean.getUserPhone(),Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            mView.setSendCodeBtnEnable(true);
            return;
        }


        RetrofitUtil.createZTHService().unBindBnakCard().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.setSendCodeBtnEnable(true);
                mView.setCodeTips(true);
                mView.triggerCountDown();
                mView.focusOnCodeEdit();
                mView.showTicket(result);
            }

            @Override
            public void _onError(Throwable e) {
                mView.setSendCodeBtnEnable(true);
                mView.showErrorTips(e.getMessage());
            }
        });


//        RetrofitUtil.createService().unBindBnakCard(mBean.getUserPhone(),mIP,Constants.ANDROID_SOURCE,otherPhone,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
//            @Override
//            public void onSuccess(String result) {
//                mView.setSendCodeBtnEnable(true);
//                mView.setCodeTips(true);
//                mView.triggerCountDown();
//                mView.focusOnCodeEdit();
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                mView.setSendCodeBtnEnable(true);
//                mView.showErrorTips(e.getMessage());
//            }
//        });



    }

    @Override
    public void unbindBankCardHttpRequest(String ticket,String code) {


        if(TextUtils.isEmpty(ticket)||TextUtils.isEmpty(code)){
            return;
        }


        RetrofitUtil.createZTHService().sureUnBindBnakCard(code,ticket).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.unbindBankCardSuccess(true);
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });


//        RetrofitUtil.createService().sureUnBindBnakCard(phone,mIP,code,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
//            @Override
//            public void onSuccess(String result) {
//                mView.unbindBankCardSuccess(true);
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                mView.showErrorTips(e.getMessage());
//            }
//        });


    }

}
