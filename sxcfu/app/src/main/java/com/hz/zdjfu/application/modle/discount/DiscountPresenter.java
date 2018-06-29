package com.hz.zdjfu.application.modle.discount;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 设置请求接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class DiscountPresenter implements DiscountContract.Presenter{


    private static final String TAG =DiscountPresenter.class.getName();
    private DiscountContract.View mView;
    private Context mContext;
    public DiscountPresenter(Context context, DiscountContract.View view){
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
    public void allCountsHttpRequest(String phone, String income_days, String amount,String product_id) {

        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(income_days)||TextUtils.isEmpty(amount)||TextUtils.isEmpty(product_id)){
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSignNoIp(phone,income_days,amount);
        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createService().investDiscount(phone,income_days, Constants.ANDROID_SOURCE,mSign,amount,product_id).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DiscountBean>() {
            @Override
            public void onSuccess(DiscountBean result) {
                DialogManager.hideProgressDialog();
                mView.showAllDiscountCoupon(result);
            }
            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });

//        DiscountBean bean = new DiscountBean();
//        List<DiscountZJZBean> userLists = new ArrayList<>();
//        List<DiscountCouponBean> user = new ArrayList<>();
//
//        DiscountZJZBean bean00 =new DiscountZJZBean();
//        bean00.setAmount(20);
//        bean00.setInvest_dates(20+"");
//        bean00.setName("红包");
//        bean00.setNode_id(22+"");
//        bean00.setType(1+"");
//        bean00.setOverdue("即将过期");
//        bean00.setUse_type("1");
//        userLists.add(bean00);
//
//
//        DiscountZJZBean bean01 =new DiscountZJZBean();
//        bean01.setAmount(20);
//        bean01.setInvest_dates(20+"");
//        bean01.setName("加息券");
//        bean01.setNode_id(21+"");
//        bean01.setType(2+"");
//        bean01.setOverdue("即将过期");
//        bean01.setUse_type("2");
//        userLists.add(bean01);
//
//        DiscountZJZBean bean02 =new DiscountZJZBean();
//        bean02.setAmount(20);
//        bean02.setInvest_dates(20+"");
//        bean02.setName("正经值");
//        bean02.setNode_id(22+"");
//        bean02.setType(3+"");
//        bean02.setOverdue("即将过期");
//        bean02.setUse_type("1");
//        userLists.add(bean02);
//
//
//        DiscountZJZBean bean03 =new DiscountZJZBean();
//        bean03.setAmount(40);
//        bean03.setInvest_dates(20+"");
//        bean03.setName("红包");
//        bean03.setNode_id(23+"");
//        bean03.setType(1+"");
//        bean03.setOverdue("即将过期");
//        bean03.setUse_type("3");
//        userLists.add(bean03);
//
//
//        DiscountZJZBean bean04 =new DiscountZJZBean();
//        bean04.setAmount(20);
//        bean04.setInvest_dates(20+"");
//        bean04.setName("红包");
//        bean04.setNode_id(24+"");
//        bean04.setType(1+"");
//        bean04.setOverdue("即将过期");
//        bean04.setUse_type("1");
//        userLists.add(bean04);
//
//        bean.setUse(userLists);
//        mView.showAllDiscountCoupon(bean);

    }

    @Override
    public void allRightHttpRequest(String phone, String income_days, String amount,String product_id) {
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(income_days)||TextUtils.isEmpty(amount)||TextUtils.isEmpty(product_id)){
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSignNoIp(phone,income_days,amount);

        RetrofitUtil.createService().investDiscount(phone,income_days, Constants.ANDROID_SOURCE,mSign,amount,product_id).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DiscountBean>() {
            @Override
            public void onSuccess(DiscountBean result) {
                mView.showAllDiscountCoupon(result);
            }
            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });
    }
}
