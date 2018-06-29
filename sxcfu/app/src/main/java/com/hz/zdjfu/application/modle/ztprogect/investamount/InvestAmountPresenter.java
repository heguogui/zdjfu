package com.hz.zdjfu.application.modle.ztprogect.investamount;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.InvestResultBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.modle.invest.invest.InvestContract;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 直投投资金额接口
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/4/26
 */
public class InvestAmountPresenter implements InvestAmonutContract.Presenter{

    private static final String TAG =InvestAmountPresenter.class.getName();
    private InvestAmonutContract.View mView;
    private Context mContext;

    public InvestAmountPresenter(Context context, InvestAmonutContract.View view){
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
    public void investHttpRequest(String productId, String investAmt, String couponId , String giftId, String coinStatus) {

        if(TextUtils.isEmpty(productId)||TextUtils.isEmpty(investAmt)){
            return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTHService().ztInvestStart(productId,investAmt,couponId,giftId,coinStatus).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>(){
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                if(mView!=null&&!TextUtils.isEmpty(result)){
                    mView.investState(result);
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
    public void userInform(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }
        RetrofitUtil.createZTHService().ztUserInform(phone).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTUserDetailBean>(){

            @Override
            public void onSuccess(ZTUserDetailBean result) {

                if(result==null){
                    return;
                }
                mView.showZTUserDetail(result);
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });

    }

    @Override
    public void investDiscount(String productId, String investAmt) {

        if(TextUtils.isEmpty(productId)||TextUtils.isEmpty(investAmt)){
            return;
        }

       RetrofitUtil.createZTService().ztInvestDiscount(productId,investAmt).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<DiscountBean>(){
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



}
