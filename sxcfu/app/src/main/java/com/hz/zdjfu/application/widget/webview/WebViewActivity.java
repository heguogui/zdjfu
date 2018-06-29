package com.hz.zdjfu.application.widget.webview;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.data.even.WeiXinShareEven;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.home.HomeFragment;
import com.hz.zdjfu.application.modle.login.splash.SplashActivity;
import com.hz.zdjfu.application.modle.mine.invitefriend.InviteFriendActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.PersonCenterActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.BankCardActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.ImageAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * [类功能说明]
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class WebViewActivity extends ToolbarActivity {

    private static final String TAG =WebViewActivity.class.getName();

    public static final int FILECHOOSER_RESULTCODE = 11;
    public WebViewFragment mWebViewFragment;
    private String mTitle;
    public String  TYPE_URL =null;
    public  ImageAlertDialog   dialog;
    private String PARENT_FORM;
    private String mUrl=null;
    @Override
    protected void init() {
        super.init();
        mToolbar.setNavigationIcon(R.mipmap.back);
//        setRightTv(getString(R.string.close));
//        showRightTv(true);
        Intent mIntent = getIntent();
        if(mIntent!=null){
            PARENT_FORM =mIntent.getStringExtra("WebView_PARENT");
            mTitle =mIntent.getStringExtra("WebView_TITLE");
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);

        if(intent!=null){
            mUrl  = intent.getStringExtra("WebView_URL");
            PARENT_FORM= intent.getStringExtra("WebView_PARENT");
        }

        if(mWebViewFragment!=null){
            if(!TextUtils.isEmpty(PARENT_FORM)&&PARENT_FORM.equals("BINDBANKCARDSUCCESS")&&!TextUtils.isEmpty(mUrl)){
                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="WEBVIEWSETTINGPWD";
                mWebViewFragment.reLoad(mUrl);
            }else{
                PARENT_FORM ="BANNER";
                mWebViewFragment.reLoad();
            }
        }

    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected BaseFragment getFragment() {
        mWebViewFragment = WebViewFragment.newInstance();
        return mWebViewFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }



    @Override
    public void onBackPressed() {

        if (mWebViewFragment == null || mWebViewFragment.getWebView() == null) {
            return;
        }

        //充值返回键则直接结束充值流程
        if(!TextUtils.isEmpty(mTitle)&&mTitle.equals("充值")){
            this.finish();
            return;
        }



        //投资过来直接 结束
        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("INVEST")||ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PASSWORDMANAGER"))){
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY =null;
            this.finish();
            return;
        }


        if(!TextUtils.isEmpty(TYPE_URL)&&(TYPE_URL.equals("TOSIGN_ACTION")||TYPE_URL.equals("BUY_RESULE"))){
            this.finish();
            return;
        }


        mTitle =this.getIntent().getStringExtra("WebView_TITLE");

        //支付密码成功 跳转至我的页面 (从哪里来回哪里去)
        if(!TextUtils.isEmpty(TYPE_URL)&&TYPE_URL.equals("SETTINGSUCCESS")){
            checkActivity();
        }

        if (showRenzhengDialog()) return;



        if(!TextUtils.isEmpty(TYPE_URL)&&TYPE_URL.equals("SETTINGPWD")){
            this.finish();
            return;
        }

        if(!TextUtils.isEmpty(PARENT_FORM)&&PARENT_FORM.equals("BANNER")){
            this.finish();
            return;
        }

        if (mWebViewFragment.getWebView().canGoBack()) {
            mWebViewFragment.getWebView().goBack();
            return;
        }


        super.onBackPressed();
    }

    private boolean showRenzhengDialog() {
        if(!TextUtils.isEmpty(TYPE_URL)&&TYPE_URL.equals("SETTINGPAYWDP")){//在设置页面

            if(!TextUtils.isEmpty(PARENT_FORM)){
                if(PARENT_FORM.equals("PRODUCTDETAIL")){
                    return false;
                }
            }

            dialog = new ImageAlertDialog(this,"提示",getString(R.string.openshbankaccount_back_notication_title),R.mipmap.notication_bg,getString(R.string.common_sure),getString(R.string.common_cancle),new ImageAlertDialog.ConfirmListener(){

                @Override
                public void callback() {
                    dialog.dismiss();
                    dialog =null;

                    if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PERSONCENTER")){//个人中心
                        startActivity(PersonCenterActivity.makeIntent(WebViewActivity.this,null));
                        ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                    }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("MINEFRAGMENT")){//我的页面
                        startActivity(MainActivity.makeIntent(WebViewActivity.this,null));
                        EventBus.getDefault().post(new MainFromTagEven("UNSETTINGPAYPWD"));
                        ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                    }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PRODUCTDETAIL")){//产品详情
                        startActivity(ProductDetailActivity.makeIntent(WebViewActivity.this,null));
                        ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                    } else if(ZDJFUApplication.getInstance().isCheckParent("ZTPRODUCTDETAIL")){//直投产品详情
                        startActivity(ZTProductDetailActivity.makeIntent(WebViewActivity.this,null));
                        ZDJFUApplication.getInstance().clearParent("ZTPRODUCTDETAIL");
                    } else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWOPENACCOUNT")){
                        if(mWebViewFragment!=null){
                            mWebViewFragment.reLoad();
                        }
                        ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                        return;
                    } else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWSETTINGPWD")){
                        if (HomeFragment.mHomeFragment.mUrl!=null&&!TextUtils.isEmpty(HomeFragment.mHomeFragment.mUrl)) {

                            if(mWebViewFragment!=null){
                                mWebViewFragment.reLoad(HomeFragment.mHomeFragment.mUrl);
                            }
                            TYPE_URL="SETTINGPWD";
                        }
                        ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                        return;
                    } else{
                        startActivity(MainActivity.makeIntent(WebViewActivity.this,null));
                        EventBus.getDefault().post(new MainFromTagEven("UNSETTINGPAYPWD"));
                    }

                    finish();
                }
            },new ImageAlertDialog.CancelListener(){

                @Override
                public void callback() {
                    dialog.dismiss();
                    dialog =null;
                }
            },true);
            dialog.show();
            return true;

        }
        return false;
    }


    private void exit() {
        finish();
    }

    private void goActivity() {

        startActivity(new Intent(this, SplashActivity.class));

    }

    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);

    }

    @Override
    protected void onRightTvClick(TextView view) {
        super.onRightTvClick(view);
        if (view.getText().toString().equals("关闭")) { // 关闭页面

            if(!TextUtils.isEmpty(TYPE_URL)&&TYPE_URL.equals("SETTINGSUCCESS")){
                checkActivity();
            }

            //认证过程中返回 弹出提示
            if (showRenzhengDialog()) return;

            exit();
        } else { // 跳转页面
            String call = "javascript:window.toolBarGoToPage()";
            mWebViewFragment.getWebView().loadUrl(call);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WebViewActivity.FILECHOOSER_RESULTCODE) {
            if (mWebViewFragment != null) {
                mWebViewFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }



    public void checkActivity(){
        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PERSONCENTER")){//个人中心
            startActivity(PersonCenterActivity.makeIntent(WebViewActivity.this,null));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("MINEFRAGMENT")){//我的页面
            startActivity(MainActivity.makeIntent(WebViewActivity.this,null));
            EventBus.getDefault().post(new MainFromTagEven("SETTINGSUCCESS"));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().isCheckParent("ZTPRODUCTDETAIL")){//直投产品详情
            startActivity(ZTProductDetailActivity.makeIntent(WebViewActivity.this,null));
            ZDJFUApplication.getInstance().clearParent("ZTPRODUCTDETAIL");
        } else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("PRODUCTDETAIL")){//产品详情
            startActivity(ProductDetailActivity.makeIntent(WebViewActivity.this,null));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWSETTINGPWD")){
            if (HomeFragment.mHomeFragment.mUrl!=null&&!TextUtils.isEmpty(HomeFragment.mHomeFragment.mUrl)) {
                mWebViewFragment.reLoad(HomeFragment.mHomeFragment.mUrl);
                TYPE_URL="SETTINGPWD";
            }
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("WEBVIEWOPENACCOUNT")){
            mWebViewFragment.reLoad();
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }else if(ZDJFUApplication.getInstance().isCheckParent("BANKCARDFRAGMENT")){
            startActivity(BankCardActivity.makeIntent(WebViewActivity.this,null));
            ZDJFUApplication.getInstance().clearParent("BANKCARDFRAGMENT");
        } else{
            startActivity(MainActivity.makeIntent(WebViewActivity.this,null));
            EventBus.getDefault().post(new MainFromTagEven("SETTINGSUCCESS"));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(WebViewActivity.this)) {
            EventBus.getDefault().register(WebViewActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(WebViewActivity.this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shareWeiXinCallBack(WeiXinShareEven even){


        if(even==null){
            return;
        }
        if(even.ismState()){

            UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
            if(mBean==null||TextUtils.isEmpty(mBean.getUserPhone())){
                return;
            }

            RetrofitUtil.createService().shareWeiXinState(mBean.getUserPhone(),Constants.ANDROID_SOURCE).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.i(TAG,"签到分享通知成功!");
                    //刷新页面
                    if(mWebViewFragment!=null){
                        mWebViewFragment.reLoad();
                    }
                }

                @Override
                public void _onError(Throwable e) {
                    Log.i(TAG,"签到分享通知失败!");
                }
            });

        }


    }

}
