package com.hz.zdjfu.application.modle.ztprogect.invest;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.base.ZTInvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordRankList;
import com.hz.zdjfu.application.data.bean.ZTRankList;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.modle.invest.InvestRewordContract;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class ZTInvestRewordPresenter implements ZTInvestRewordContract.Presenter{

    private static final String TAG =ZTInvestRewordPresenter.class.getName();
    private ZTInvestRewordContract.View mView;
    private Context mContext;
    private int mPage ;
    public ZTInvestRewordPresenter(Context context, ZTInvestRewordContract.View view){
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
    public void investRewordHttpRequest(String productid, boolean isRefresh) {

        if(TextUtils.isEmpty(productid)){
            return;
        }

        if(isRefresh){
            mPage =1;
        }

        RetrofitUtil.createZTService().ztInvestRewordDetail(productid,mPage+"",10+"").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTInvestRewordLists>() {
            @Override
            public void onSuccess(ZTInvestRewordLists result) {
                if(result!=null){
                    mView.showInvestRewordDetailData(result,isRefresh);
                    mPage++;
                }
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });



    }


    @Override
    public void investRewordRankHttpRequest(String productid) {
        if(TextUtils.isEmpty(productid)){
            return;
        }

        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTService().ztInvestRewordRank(productid).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTRankList>() {
            @Override
            public void onSuccess(ZTRankList result) {
                DialogManager.hideProgressDialog();
                mView.showInvestRewordRank(result);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });

    }
}
