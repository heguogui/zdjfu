package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class BindBankCardPresenter implements BindBankCardContract.Presenter{

    private Context mContext;
    private BindBankCardContract.View mView;

    public BindBankCardPresenter(Context context, BindBankCardContract.View view){
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
    public void bindBankCardHttpRequest(String phone, String bankcardno,String validcode, String otherphone, String cardattribute, String province, String city,String bank_alias) {


        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(validcode)||TextUtils.isEmpty(otherphone)||TextUtils.isEmpty(cardattribute)||TextUtils.isEmpty(city)||TextUtils.isEmpty(province)||TextUtils.isEmpty(bank_alias)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,validcode);
        if(TextUtils.isEmpty(mSign)){
            return;
        }
        DialogManager.showProgressDialog(mContext,"绑卡中...");
        RetrofitUtil.createService().bindBankCard(phone,mIP,validcode,mSign,otherphone,cardattribute,province,city,bankcardno,bank_alias,Constants.ANDROID_SOURCE).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                if(!TextUtils.isEmpty(result)){
                    mView.showBindBankCardSuccess(result);
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
    public void sendCode(String phone,String bankno,String otherphone,String cardattribute,String province,String city,String bank_alias) {

        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(bankno)||TextUtils.isEmpty(otherphone)||TextUtils.isEmpty(cardattribute)||TextUtils.isEmpty(city)||TextUtils.isEmpty(province)){
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSignNoIp(phone,bankno,otherphone);
        if(TextUtils.isEmpty(mSign)){
            return;
        }



        RetrofitUtil.createService().addBankCardSendCode(phone,bankno,otherphone,cardattribute,province,city,bank_alias,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.setCodeTips(otherphone);
                mView.setSendCodeBtnEnable(true);
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

    @Override
    public void getProviceHttpRequest(String phone) {

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().getProvince(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<ProviceLists>() {
            @Override
            public void onSuccess(ProviceLists result) {
                if(result!=null){
                    mView.showProviceLists(result);
                }else{
                    mView.showErrorTips("后台无数据");
                }
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }

    @Override
    public void getCityHttpRequest(String phone,String provice) {

        if(TextUtils.isEmpty(provice)){
            ToastUtils.show(mContext,"省份不能为空");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSignNoIp(phone,Constants.ANDROID_SOURCE,provice);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().getCity(phone,provice,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<CityLists>() {
            @Override
            public void onSuccess(CityLists result) {
                if(result!=null){
                    mView.showCityLists(result);
                }else{
                    mView.showErrorTips("后台无数据");
                }
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }

    @Override
    public void getBankCardTypeHttpRequest(String phone) {

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().getBankType(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<BankTypeLists>() {
            @Override
            public void onSuccess(BankTypeLists result) {
                if(result!=null){
                    mView.showBankCardTypeLists(result);
                }else{
                    mView.showErrorTips("后台无数据");
                }
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }

}
