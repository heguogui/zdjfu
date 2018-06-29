package com.hz.zdjfu.application.modle.mine.transationdetail;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.TransationDetailList;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

import java.util.List;


/**
 * [类功能说明]
 *交易明细
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/10 0010.
 */

public class TransationdetailPresenter implements TransationdetailContract.Presenter{

    private static final String TAG =TransationdetailPresenter.class.getName();
    private TransationdetailContract.View mView;
    private Context mContext;

    public TransationdetailPresenter(Context context, TransationdetailContract.View view){
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
    public void transationDetailHttpRequest(String phone,int type, int page) {


        DialogManager.showProgressDialog(mContext,"加载中...");

        RetrofitUtil.createZTHService().ztTransactionDetail(type+"",page+"","10").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<TransationDetailList>() {
            @Override
            public void onSuccess(TransationDetailList result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&result.getPageList()!=null){
                    mView.showTransationDetailListsData(result.getPageList());
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
