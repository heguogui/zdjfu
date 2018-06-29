package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AddRateLists;
import com.hz.zdjfu.application.data.bean.RedpackAndCouponBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class CouponPresenter implements CouponContract.Presenter {


    private static final String TAG =RedpacketPresenter.class.getName();
    private CouponContract.View mView;
    private Context mContext;
    private int currentPage =0;
    public CouponPresenter(Context context, CouponContract.View view){
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
    public void couponHttpRequest(boolean isRefresh) {


        if(isRefresh){
            currentPage =1;
        }

     RetrofitUtil.createZTHService().ztRedPacketAndCoupon("2","true",currentPage+"","10").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<RedpackAndCouponBean>() {
         @Override
         public void onSuccess(RedpackAndCouponBean result) {
             if(result==null){
                 return;
             }
             if(result.getCouponList()!=null&&result.getCouponList().size()>0){
                 currentPage++;
                 if(!TextUtils.isEmpty(result.getTotalCount())){
                     mView.setAllTotalNum(Integer.parseInt(result.getTotalCount()));
                 }
                 if(!TextUtils.isEmpty(result.getCouponNum())){
                     mView.setEnableUsedNum(Integer.parseInt(result.getCouponNum()));
                 }
                 mView.showCouponData(result.getCouponList(),isRefresh);
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
                 mView.showErrorTips(e.getMessage()+"");
             }
         }
     });

    }
}
