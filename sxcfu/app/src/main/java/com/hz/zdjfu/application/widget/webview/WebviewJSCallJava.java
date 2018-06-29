package com.hz.zdjfu.application.widget.webview;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.data.even.InvestEven;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.home.HomeFragment;
import com.hz.zdjfu.application.modle.invest.invest.InvestActivity;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailActivity;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.PersonCenterActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.BankCardActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.addbankcard.AddBankCardActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail.ZTBuyProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.investamount.InvestAmountActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;


public class WebviewJSCallJava {

    private Context context;
    private BBWebCore webView;
    private Handler handler = new Handler();

    public WebviewJSCallJava(Context context, BBWebCore webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void savingTakingData(String callBack) {

    }


    @JavascriptInterface
    public void nativeCallJavaScript(final String callbackName) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                webView.callJavascript("App.nativeCallbacks." + callbackName,
                        null);
            }
        });
    }


    /**
     * 提供给h5页面的umeng统计
     *
     * @param eventId
     */
    @JavascriptInterface
    public void onEvent(String eventId) {
//        UMAgentUtils.onEvent(HZKJApplication.getInstance().getApplicationContext(), eventId);
    }

    /**
     * 提供给h5页面设置标题栏右端文字
     *
     * @param text
     */
    @JavascriptInterface
    public void setRightText(String text) {
        if (context instanceof WebViewActivity) {
            ((WebViewActivity) context).runOnUiThread(
                    () -> ((WebViewActivity) context).setRightTv(text)
            );

        }
    }



    /**
     * 关闭当前webview activity
     */
    @JavascriptInterface
    public void close() {
        if (context instanceof WebViewActivity) {
            ((WebViewActivity) context).runOnUiThread(
                    () -> ((WebViewActivity) context).finish()
            );
        }
    }


    /**
     * h5页面跳转登录页面
     */
    @JavascriptInterface
    public void login() {
        if (context instanceof WebViewActivity) {
            ((WebViewActivity) context).runOnUiThread(
                    () -> context.startActivity(LoginActivity.makeIntent(context, null))
            );
        }
    }

    /**
     * H5提现成功页面
     */
    @JavascriptInterface
    public void showWithDrawSuccessState(boolean state) {
        if(context instanceof WebViewActivity){
            ((WebViewActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(MainActivity.makeIntent(context,null));
                    EventBus.getDefault().post(new MainFromTagEven("WITHDRAWDEPOSIT"));
                }
            });

        }
    }

    /**
     * H5设置密码成功页面
     */
    @JavascriptInterface
    public void showPayPasswordSuccessState(boolean state) {

        if(context instanceof WebViewActivity){
            ((WebViewActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    checkActivity();
                }
            });

        }
    }


    /**
     * H5重新绑卡成功页面
     */
    @JavascriptInterface
    public void showAddBindCardSuccess(boolean state) {

        if(context instanceof WebViewActivity){
            ((WebViewActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   //从哪来回哪去
                    checkFromParent();
                }
            });

        }
    }

    private void checkFromParent() {

        if(ZDJFUApplication.getInstance().isCheckParent("BANKCARDFRAGMENT")){
            context.startActivity(BankCardActivity.makeIntent(context,null));
            ZDJFUApplication.getInstance().clearParent("BANKCARDFRAGMENT");
        }else if(ZDJFUApplication.getInstance().isCheckParent("INVESTDETAIL")){
            context.startActivity(ProductDetailActivity.makeIntent(context,null));
            ZDJFUApplication.getInstance().clearParent("INVESTDETAIL");
        }

        ((WebViewActivity) context).finish();
    }


    /**
     * H5投资成功页面
     */
    @JavascriptInterface
    public void showInvestSuccessState(String produci_id,String id) {

        if(StringUtils.isFastClick()){
            return;
        }
//        if(context instanceof WebViewActivity){
//            if(!TextUtils.isEmpty(produci_id)&&!TextUtils.isEmpty(id)){
//                Bundle mBundle = new Bundle();
//                mBundle.putString("PRODUCT_ID",produci_id);
//                mBundle.putString("ID",id);
//                context.startActivity(InvestDetailActivity.makeIntent(context,mBundle));
//                ((WebViewActivity) context).finish();
//            }
//        }
        if(context instanceof WebViewActivity){
            if(!TextUtils.isEmpty(id)){
                Bundle mBundle = new Bundle();
                mBundle.putString("BUY_ID",id);
                context.startActivity(ZTBuyProductDetailActivity.makeIntent(context,mBundle));
                ((WebViewActivity) context).finish();
            }
        }
    }


    /**
     * H5直投投资成功页面
     */
    @JavascriptInterface
    public void investSuccess(String buyId){
        if(StringUtils.isFastClick()){
            return;
        }
        if(context instanceof WebViewActivity){
            if(!TextUtils.isEmpty(buyId)){
                Bundle mBundle = new Bundle();
                mBundle.putString("BUY_ID",buyId);
                context.startActivity(ZTBuyProductDetailActivity.makeIntent(context,mBundle));
                ((WebViewActivity) context).finish();
            }
        }
    }

    @JavascriptInterface
    public void rechangeSuccess(){
        if(StringUtils.isFastClick()){
            return;
        }
        if(context instanceof WebViewActivity){
            ((WebViewActivity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(ZDJFUApplication.getInstance().isCheckParent("MINEFRAGMENT")){//充值成功去个人中心
                        ZDJFUApplication.getInstance().clearParent("MINEFRAGMENT");
                        context.startActivity(MainActivity.makeIntent(context,null));
                        EventBus.getDefault().post(new MainFromTagEven("RECHANGESUCCESS"));
                    }else if(ZDJFUApplication.getInstance().isCheckParent("INVESTFRAGMENT")){//充值成功去投资页面
                        context.startActivity(InvestActivity.makeIntent(context,null));
                        ZDJFUApplication.getInstance().clearParent("INVESTFRAGMENT");
                        EventBus.getDefault().post(new InvestEven("RECHANGESUCCESS"));
                    }else if(ZDJFUApplication.getInstance().isCheckParent("ZTINVESTFRAGMENT")){//直投充值成功
                        context.startActivity(InvestAmountActivity.makeIntent(context,null));
                        ZDJFUApplication.getInstance().clearParent("ZTINVESTFRAGMENT");
                        EventBus.getDefault().post(new InvestEven("ZTRECHANGESUCCESS"));
                    }
                    ((WebViewActivity) context).finish();
                }
            });
        }
    }


    /**
     * 继续投资
     */
    @JavascriptInterface
    public void continueInvest(){
        if(ZDJFUApplication.getInstance().isCheckParent("ZT_INVEST")){//直投产品详情
            context.startActivity(ZTProductDetailActivity.makeIntent(context, null));
            ZDJFUApplication.getInstance().clearParent("ZT_INVEST");
        } else if(ZDJFUApplication.getInstance().isCheckParent("INVEST")){//产品详情
            context.startActivity(ProductDetailActivity.makeIntent(context,null));
            ZDJFUApplication.getInstance().clearParent("INVEST");
        }
    }

    /**
     * H5投资成功页面
     */
    @JavascriptInterface
    public void fundBackBtn() {

        EventBus.getDefault().post(new MainFromTagEven("SHOWFUNDBACK"));

    }


    public void checkActivity(){
        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PERSONCENTER")){//个人中心
            context.startActivity(PersonCenterActivity.makeIntent(context,null));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("MINEFRAGMENT")){//我的页面
            context.startActivity(MainActivity.makeIntent(context,null));
            EventBus.getDefault().post(new MainFromTagEven("SETTINGSUCCESS"));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().isCheckParent("ZTPRODUCTDETAIL")){//直投产品详情
            context.startActivity(ZTProductDetailActivity.makeIntent(context, null));
            ZDJFUApplication.getInstance().clearParent("ZTPRODUCTDETAIL");
        } else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PRODUCTDETAIL")){//产品详情
            context.startActivity(ProductDetailActivity.makeIntent(context,null));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWSETTINGPWD")){
            if (HomeFragment.mHomeFragment.mUrl!=null&&!TextUtils.isEmpty(HomeFragment.mHomeFragment.mUrl)) {
                if(context instanceof WebViewActivity){
                    if(((WebViewActivity)context).mWebViewFragment!=null){
                        ((WebViewActivity)context).mWebViewFragment.reLoad(HomeFragment.mHomeFragment.mUrl);
                        ((WebViewActivity)context).TYPE_URL="SETTINGPWD";
                    }
                }
            }
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().isCheckParent("BANKCARDFRAGMENT")){
            context.startActivity(BankCardActivity.makeIntent(context,null));
            ZDJFUApplication.getInstance().clearParent("BANKCARDFRAGMENT");
        } else{
            context.startActivity(MainActivity.makeIntent(context,null));
            EventBus.getDefault().post(new MainFromTagEven("SETTINGSUCCESS"));
        }
    }






}
