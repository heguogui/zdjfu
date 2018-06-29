package com.hz.zdjfu.application.modle.invest;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.data.bean.InvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordRankList;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class InvestRewordPresenter implements InvestRewordContract.Presenter{

    private static final String TAG =InvestRewordPresenter.class.getName();
    private InvestRewordContract.View mView;
    private Context mContext;
    private int mPage ;
    public InvestRewordPresenter(Context context, InvestRewordContract.View view){
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

        RetrofitUtil.createService().investRewordDetail(productid,mPage+"").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<InvestRewordLists>() {
            @Override
            public void onSuccess(InvestRewordLists result) {
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
        RetrofitUtil.createService().investRewordRank(productid).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<InvestRewordRankList>() {
            @Override
            public void onSuccess(InvestRewordRankList result) {
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
