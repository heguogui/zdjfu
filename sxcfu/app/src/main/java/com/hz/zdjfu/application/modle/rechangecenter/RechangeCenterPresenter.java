package com.hz.zdjfu.application.modle.rechangecenter;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.CoinBalanceBean;
import com.hz.zdjfu.application.data.bean.RechangeZJZLists;
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
public class RechangeCenterPresenter implements RechangeCenterContract.Presenter{


    private static final String TAG =RechangeCenterPresenter.class.getName();
    private RechangeCenterContract.View mView;
    private Context mContext;
    public RechangeCenterPresenter(Context context, RechangeCenterContract.View view){
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
    public void rechangeCenterHttpRequest() {



        RetrofitUtil.createZTHService().ztRechangeCenter().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<RechangeZJZLists>() {
            @Override
            public void onSuccess(RechangeZJZLists result) {
                if(mView!=null&&result!=null){
                    mView.showData(result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                if(mView!=null){
                    mView.showErrorTips(e.getMessage());
                }
            }
        });

    }

    @Override
    public void rechangeHttpRequest(String goodsid) {

        DialogManager.showProgressDialog(mContext,"兑换中...");
        RetrofitUtil.createZTHService().ztRechangeCoinStream(goodsid).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<CoinBalanceBean>() {
            @Override
            public void onSuccess(CoinBalanceBean result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&mView!=null&&!TextUtils.isEmpty(result.getCoinBalance())){
                    mView.rechangeState(result.getCoinBalance());
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
