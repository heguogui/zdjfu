package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.RedPacketLists;
import com.hz.zdjfu.application.data.bean.RedpackAndCouponBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RedpacketPresenter implements RedpacketContract.Presenter{


    private static final String TAG =RedpacketPresenter.class.getName();
    private RedpacketContract.View mView;
    private Context mContext;
    private int currentPage =0;
    public RedpacketPresenter(Context context, RedpacketContract.View view){
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
    public void redpacketHttpRequest(String type,boolean isRefresh) {

        if(TextUtils.isEmpty(type)){
            return;
        }

        if(isRefresh){
            currentPage =1;
        }


        DialogManager.showProgressDialog(mContext,"加载中...");

        RetrofitUtil.createZTHService().ztRedPacketAndCoupon("1",true+"",currentPage+"","10").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<RedpackAndCouponBean>() {
            @Override
            public void onSuccess(RedpackAndCouponBean result) {
                DialogManager.hideProgressDialog();
                if(result==null){
                    return;
                }

                if(!TextUtils.isEmpty(result.getCoinBalance())){
                    mView.showCoinNum(result.getCoinBalance());
                }


                if(result.getGiftList()!=null&&result.getGiftList().size()>0){
                    currentPage++;
                    if(!TextUtils.isEmpty(result.getTotalCount())){
                        mView.setAllTotalNum(Integer.parseInt(result.getTotalCount()));
                    }
                    if(!TextUtils.isEmpty(result.getGiftNum())){
                        mView.setEnableUsedNum(Integer.parseInt(result.getGiftNum()));
                    }

                    mView.showRedPacketData(result.getGiftList(),isRefresh);

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
                if(mView!=null){
                    DialogManager.hideProgressDialog();
                    mView.showErrorTips(e.getMessage());
                }
            }
        });



    }


}
