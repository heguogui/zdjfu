package com.hz.zdjfu.application.modle.mine.myasset;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.MyAssetsBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 我的资产接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class MyAssetPresenter implements MyAssetContract.Presenter{


    private static final String TAG =MyAssetPresenter.class.getName();
    private MyAssetContract.View mView;
    private Context mContext;

    public MyAssetPresenter(Context context, MyAssetContract.View view){
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
    public void allAssetHttpRequest() {


        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTHService().ztMyAsset().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<MyAssetsBean>() {
            @Override
            public void onSuccess(MyAssetsBean result) {
                DialogManager.hideProgressDialog();
                if(result!=null){
                    mView.showPieData(result);
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
