package com.hz.zdjfu.application.modle.rechangecenter.rechangedetail;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.RechangeDetailBean;
import com.hz.zdjfu.application.data.bean.RechangeDetailList;
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
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RechangeDetailPresenter implements RechangeDetailContract.Presenter{

    private static final String TAG =RechangeDetailPresenter.class.getName();
    private RechangeDetailContract.View mView;
    private Context mContext;
    private int currentPage =0;
    public RechangeDetailPresenter(Context context, RechangeDetailContract.View view){
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
    public void rechangeDetailHttpRequest(String phone, boolean isRefresh) {

        if(TextUtils.isEmpty(phone)){
            return;
        }

        if(isRefresh){
            currentPage =1;
        }


        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTHService().ztSpecialList(currentPage+"","20").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<RechangeDetailList>() {
            @Override
            public void onSuccess(RechangeDetailList result) {
                DialogManager.hideProgressDialog();
                if(result==null){
                    return;
                }

                List<RechangeDetailBean> mList=result.getPageList();
                if(mList.size()>0){
                    currentPage++;
                    mView.showRechangeDetailData(mList,isRefresh);
                }else{
                    if(isRefresh){
                        mView.showDateEmptyView(true);
                    }else{
                        mView.showDateEmptyView(false);
                    }
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
