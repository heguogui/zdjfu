package com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.InvestDetailBean;
import com.hz.zdjfu.application.data.bean.ZTBuyProductDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailContract;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 直投购买的产品详情
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/5/19
 */
public class ZTBuyProductDetailPresenter implements ZTBuyProductDetailContract.Presenter{

    private static final String TAG =ZTBuyProductDetailPresenter.class.getName();
    private ZTBuyProductDetailContract.View mView;
    private Context mContext;
    private int mPage ;
    public ZTBuyProductDetailPresenter(Context context, ZTBuyProductDetailContract.View view){
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
    public void buyProductDetailHttpRequest(String buy_id) {
        if(TextUtils.isEmpty(buy_id)){
           return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTHService().ztBuyProductDetail(buy_id).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTBuyProductDetailBean>() {
            @Override
            public void onSuccess(ZTBuyProductDetailBean result) {
                DialogManager.hideProgressDialog();
                if(mView!=null&&result!=null){
                    mView.showBuyProductDetailData(result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                if(mView!=null){
                    mView.showErrorTips(e.getMessage());
                }
            }
        });

    }
}
