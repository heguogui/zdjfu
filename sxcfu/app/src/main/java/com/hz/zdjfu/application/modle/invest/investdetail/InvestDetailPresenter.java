package com.hz.zdjfu.application.modle.invest.investdetail;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.InvestDetailBean;
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
public class InvestDetailPresenter implements InvestDetailContract.Presenter{

    private static final String TAG =InvestDetailPresenter.class.getName();
    private InvestDetailContract.View mView;
    private Context mContext;
    private int mPage ;
    public InvestDetailPresenter(Context context, InvestDetailContract.View view){
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
    public void investDetailHttpRequest(String phone,String productid,String buy_id) {

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
        DialogManager.showProgressDialog(mContext,"加载中...");

        RetrofitUtil.createService().investProgectDetail(phone,mIP,Constants.REQUESTSOURCE_ANDROID,mSign,productid,buy_id).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<InvestDetailBean>() {
            @Override
            public void onSuccess(InvestDetailBean result) {
                DialogManager.hideProgressDialog();
                if(result==null){
                    return;
                }
                mView.showInvestDetailData(result);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });



    }

    @Override
    public void resetPayOrder(String phone, String orderid) {




    }

    @Override
    public void cancleInvestOrder(String orderId) {

        if(TextUtils.isEmpty(orderId)){
            return;
        }
        DialogManager.showProgressDialog(mContext,"取消中...");
        RetrofitUtil.createService().cancleInvestOrder(orderId).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                mView.cancleOrderState(true);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.cancleOrderState(false);
            }
        });


    }
}
