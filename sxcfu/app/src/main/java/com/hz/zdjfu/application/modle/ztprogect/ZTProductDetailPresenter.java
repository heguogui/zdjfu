package com.hz.zdjfu.application.modle.ztprogect;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.InvestProjectDetailList;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.data.bean.ZTSHBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailContract;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 直投产品详情接口
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/05/07
 */
public class ZTProductDetailPresenter implements ZTProductDetailContract.Presenter{


    private static final String TAG =ZTProductDetailPresenter.class.getName();
    private ZTProductDetailContract.View mView;
    private Context mContext;
    public ZTProductDetailPresenter(Context context, ZTProductDetailContract.View view){
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

        RetrofitUtil.createZTService().ztProductDetail(productid).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTProductDetailBean>() {
            @Override
            public void onSuccess(ZTProductDetailBean result) {
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

       RetrofitUtil.createZTHService().ztUserInform(phone).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTUserDetailBean>(){

           @Override
           public void onSuccess(ZTUserDetailBean result) {

               if(result==null){
                   return;
               }
               mView.showUserDetail(result);
           }

           @Override
           public void _onError(Throwable e) {

           }
       });




    }

    @Override
    public void settingPaySetting(String phone) {
//        if(TextUtils.isEmpty(phone)){
//            return;
//        }
//
//        String mIP = HttpsUtils.getMobileHostIP();
//        if(TextUtils.isEmpty(mIP)){
//            ToastUtils.show(mContext,"获取手机IP失败");
//            return;
//        }
//
//        String mSign = HttpsUtils.getHttpRequestSign(phone,Constants.ANDROID_SOURCE);
//        if(TextUtils.isEmpty(mSign)){
//            return;
//        }
//
//        RetrofitUtil.createService().settingBankCardPayPwd(phone,Constants.ANDROID_SOURCE,mIP,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DataStringList>() {
//            @Override
//            public void onSuccess(DataStringList result) {
//                if(result!=null&&!TextUtils.isEmpty(result.getDataList())){
//                    mView.showSettingPayPwdH5(result.getDataList());
//                }
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                mView.showErrorTips(e.getMessage());
//            }
//        });
    }




    @Override
    public void addBankCard() {

        RetrofitUtil.createZTHService().ztAddBankCard().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null&&mView!=null){
                    mView.addBankCardResult(result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage()+"");
            }
        });

    }


}
