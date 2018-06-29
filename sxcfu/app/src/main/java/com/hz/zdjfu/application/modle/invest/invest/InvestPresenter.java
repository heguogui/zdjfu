package com.hz.zdjfu.application.modle.invest.invest;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class InvestPresenter implements InvestContract.Presenter{

    private static final String TAG =InvestPresenter.class.getName();
    private InvestContract.View mView;
    private Context mContext;

    public InvestPresenter(Context context, InvestContract.View view){
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
    public void investHttpRequest(String phone, String amount, String goods_id, String gift_money, String trade_no, String user_coupon_id, String coin, String user_gift_id) {

        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(amount)||TextUtils.isEmpty(goods_id)||TextUtils.isEmpty(trade_no)){
            return;
        }

        String mSign =HttpsUtils.getHttpRequestSignNoIp(phone,goods_id,amount);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().investStart(phone,Constants.ANDROID_SOURCE,amount,"投标","1",mSign,goods_id,gift_money,trade_no,user_coupon_id,coin,user_gift_id).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.investState(result);
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void tokenHttpRequest(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.REQUESTSOURCE_ANDROID);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().investToken(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.showToken(result);
            }

            @Override
            public void _onError(Throwable e) {
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
                    mView.accountBalance(result.getDataList().getBalance());
                }
            }
            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });
    }

    @Override
    public void investDiscount(String phone, String income_days, String amount,String product_id) {

        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(income_days)||TextUtils.isEmpty(amount)){
            return;
        }

        String mSign =HttpsUtils.getHttpRequestSignNoIp(phone,income_days,amount);

        RetrofitUtil.createService().investDiscount(phone,income_days,Constants.ANDROID_SOURCE,mSign,amount,product_id).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DiscountBean>() {
            @Override
            public void onSuccess(DiscountBean result) {
                mView.showDiscountCoupon(result);
            }
            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }

    @Override
    public void canInvestBalance(String goods_id,boolean state) {
        if(TextUtils.isEmpty(goods_id)){
            return;
        }
        DialogManager.showProgressDialog(mContext,"申请中...");
        RetrofitUtil.createService().canInvestBalance(goods_id).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                mView.showCanInvestBalance(result,state);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });

    }


}
