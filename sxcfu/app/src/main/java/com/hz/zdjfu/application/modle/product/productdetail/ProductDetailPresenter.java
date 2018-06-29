package com.hz.zdjfu.application.modle.product.productdetail;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.InvestProjectDetailList;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 产品详情
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class ProductDetailPresenter implements ProductDetailContract.Presenter{


    private static final String TAG =ProductDetailPresenter.class.getName();
    private ProductDetailContract.View mView;
    private Context mContext;
    public ProductDetailPresenter(Context context, ProductDetailContract.View view){
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
    public void productDetailHttpRequest(String productid) {



        if(TextUtils.isEmpty(productid)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

//        DialogManager.showProgressDialog(mContext,"加载中");

        RetrofitUtil.createService().productDetail(mIP,Constants.REQUESTSOURCE_ANDROID,productid).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<ProductInformBean>() {
            @Override
            public void onSuccess(ProductInformBean result) {
//                DialogManager.hideProgressDialog();
                mView.showProductDetail(result);
            }

            @Override
            public void _onError(Throwable e) {
 //               DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void userDetailHttpRequest(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.REQUESTSOURCE_ANDROID);
        if(TextUtils.isEmpty(mSign)){
            return;
        }
       // DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createService().userInform(phone,mIP,Constants.REQUESTSOURCE_ANDROID,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<UserDetailBean>() {
            @Override
            public void onSuccess(UserDetailBean result) {
       //         DialogManager.hideProgressDialog();
                if(result==null){
                    return;
                }
                mView.showUserDetail(result);
            }

            @Override
            public void _onError(Throwable e) {
         //       DialogManager.hideProgressDialog();
            }
        });


    }


    @Override
    public void isCheckUnPayOrder(String phone) {



        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().unPayOrder(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<InvestProjectDetailList>() {
            @Override
            public void onSuccess(InvestProjectDetailList result) {
                if(result==null||result.getDataList().isEmpty()){
                    mView.isCheckUnPayOrder(false);
                }else{
                    mView.isCheckUnPayOrder(true);
                }
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void addBankCard() {
        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){

            String mSign = HttpsUtils.getHttpRequestSignNoIp(mBean.getUserPhone());
            if(TextUtils.isEmpty(mSign)){
                return;
            }

            DialogManager.showProgressDialog(mContext,"加载中...");
            RetrofitUtil.createService().authNameAndBindCard(null,null,Constants.ANDROID_SOURCE,mBean.getUserPhone(),mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
                @Override
                public void onSuccess(String result) {
                    DialogManager.hideProgressDialog();
                    if(!TextUtils.isEmpty(result)){
                        mView.authNameResult(result);
                    }else{
                        mView.showErrorTips("返回路径为空");
                    }
                }

                @Override
                public void _onError(Throwable e) {
                    DialogManager.hideProgressDialog();
                    mView.showErrorTips(e.getMessage()+"");
                }
            });

        }
    }


}
