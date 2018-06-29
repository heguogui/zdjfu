package com.hz.zdjfu.application.widget.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hz.zdjfu.application.BuildConfig;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.InvestEven;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.invest.invest.InvestActivity;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.login.regist.RegistActivity;
import com.hz.zdjfu.application.modle.opendeposit.OpenDepositActivity;
import com.hz.zdjfu.application.modle.ztprogect.investamount.InvestAmountActivity;
import com.hz.zdjfu.application.share.ShareDialog;
import com.hz.zdjfu.application.utils.FileUtils;
import com.hz.zdjfu.application.utils.LogUtils;
import com.hz.zdjfu.application.utils.NetworkUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import static android.app.Activity.RESULT_OK;
import static com.hz.zdjfu.application.widget.webview.WebViewActivity.FILECHOOSER_RESULTCODE;


/**
 * [类功能说明]
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class WebViewFragment extends BaseFragment {

    private static  final  String TAG =WebViewFragment.class.getName();

    private List<String> mListUrl = new ArrayList<String>();
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.webview)
    BBWebCore mBBWebCore;
    public String baseUrl = "";
    private String mTitle =null;
    private boolean isBack = false;
    public ValueCallback<Uri> mUploadMessage;

    public ValueCallback<Uri[]> mUploadMessages;


    @BindView(R.id.earningdetail_network_error)
    RelativeLayout networkError;
    private String mCameraFilePath = "";
    private ShareDialog dialog;
    private boolean isFinish =false;
    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        if (mActivity != null&&TextUtils.isEmpty(baseUrl)) {
            baseUrl = mActivity.getIntent().getStringExtra("WebView_URL");
            mTitle =mActivity.getIntent().getStringExtra("WebView_TITLE");
        }
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }


        networkError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkUtils.isConnected(mActivity)) {
                    loadUrl();
                    networkError.setVisibility(View.GONE);
                } else {
                    networkError.setVisibility(View.VISIBLE);
                    ToastUtils.show(mActivity, getString(R.string.common_net_error));
                }
            }
        });

        if (NetworkUtils.isConnected(mActivity)) {
            loadUrl();
        } else {
            networkError.setVisibility(View.VISIBLE);
            ToastUtils.show(mActivity, getString(R.string.common_net_error));
        }
    }


    public void reLoadUrl() {
        if (mActivity != null) {
            baseUrl = mActivity.getIntent().getStringExtra("WebView_URL");
            mTitle =mActivity.getIntent().getStringExtra("WebView_TITLE");
        }
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        loadUrl();
    }


    /**
     * 重新登录刷新页面
     */
    public void reLoad(){

        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
        if(mUserBean!=null&&mUserBean.isLogin()&&!baseUrl.contains("phone")){
            baseUrl =baseUrl+"&phone="+mUserBean.getUserPhone();
        }
        Log.i(TAG,"baseUrl="+baseUrl);
        loadUrl();
    }


    /**
     * 重新登录刷新页面
     */
    public void reLoad(String url){

        if (TextUtils.isEmpty(url)) {
            return;
        }
        baseUrl =url;
        loadUrl();
    }



    private void loadUrl() {

        Log.e("页面baseUrl", "===" + baseUrl);
        mListUrl.add(baseUrl);
        mBBWebCore.setNetworkAvailable(true);
        mBBWebCore.getSettings().setJavaScriptEnabled(true);
        // User settings
        mBBWebCore.getSettings().setJavaScriptEnabled(true);
        mBBWebCore.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mBBWebCore.getSettings().setJavaScriptEnabled(true); // 设置支持javascript脚本
        mBBWebCore.getSettings().setAllowFileAccess(true); // 允许访问文件
        mBBWebCore.getSettings().setBuiltInZoomControls(true); // 设置显示缩放按钮
        mBBWebCore.getSettings().setSupportZoom(true); // 支持缩放
        mBBWebCore.getSettings().setLoadWithOverviewMode(true);
        //设置支持javascript
        mBBWebCore.getSettings().setJavaScriptEnabled(true);

      //  mBBWebCore.getSettings().setDomStorageEnabled(true);
        //启动缓存
        mBBWebCore.getSettings().setAppCacheEnabled(true);
        // 设置可以支持缩放
        mBBWebCore.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mBBWebCore.getSettings().setBuiltInZoomControls(true);
        //设置缓存模式
        mBBWebCore.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mBBWebCore.getSettings().setUseWideViewPort(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mBBWebCore.getSettings().setJavaScriptEnabled(true);

        //解决图片加载显示
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            mBBWebCore.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        if (baseUrl.indexOf("active/sign_in-in.html") != -1) {
            mBBWebCore.clearCache(true);
            mBBWebCore.getSettings().setCacheMode(mBBWebCore.getSettings().LOAD_NO_CACHE);
        }


        mBBWebCore.addJavascriptInterface(new WebviewJSCallJava(mActivity, mBBWebCore), "android");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (BuildConfig.DEBUG) {
                mBBWebCore.setWebContentsDebuggingEnabled(true);
            }
        }

        mBBWebCore.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        mBBWebCore.setVerticalScrollBarEnabled(false);
        mBBWebCore.setWebChromeClient(new CustomChromeClient(mActivity));
        mBBWebCore.setWebViewClient(new BBWebCoreClient(mActivity) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                if (mActivity instanceof WebViewActivity) {
                    ((WebViewActivity) mActivity).setRightTv(getString(R.string.close));
                }

                if (mBBWebCore != null && mBBWebCore.getSettings() != null) {
                    mBBWebCore.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                }

                if ((mActivity != null) && (!NetworkUtils.isConnected(mActivity))) {
                    networkError.setVisibility(View.VISIBLE);
                    ToastUtils.show(mActivity, getString(R.string.common_net_error));
                    if (mActivity != null) {
                        mActivity.setTitle("网路异常");
                    }
                    return;
                }

                if(!TextUtils.isEmpty(url)){
                    Log.e("页面开始加载", "url="+url);
                }



                super.onPageStarted(view, url, favicon);
                if (mActivity != null) {
                    mActivity.setTitle(view.getTitle());
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                if (mActivity != null) {
                    mActivity.setTitle(view.getTitle());
                    Log.e("页面加载完成时", "view.getTitle()="+view.getTitle());
                }

                Log.e("页面加载完成时", "url===" + url);

                if(url.contains("/sina/passwd.action")){
                    if(mActivity!=null){
                        ((WebViewActivity)mActivity).TYPE_URL ="SETTINGSUCCESS";
                    }
                }

                if(url.contains("jump.action")){
                    if(mActivity!=null){
                        ((WebViewActivity)mActivity).TYPE_URL ="SETTINGSUCCESS";
                    }
                }


                if(url.contains("https://shbank.weibopay.com:8007/h5/verifySms")){
                    if(mActivity!=null){
                        ((WebViewActivity)mActivity).TYPE_URL ="SETTINGPAYWDP";
                    }
                }


                if(url.contains("h5/bindCard")){
                    if(mActivity!=null){
                        ((WebViewActivity)mActivity).TYPE_URL ="SETTINGPAYWDP";
                    }
                }


                if(url.contains("toSign.action")){
                    if(mActivity!=null){
                        ((WebViewActivity)mActivity).TYPE_URL ="TOSIGN_ACTION";
                    }
                }

                if(url.contains("buyResult")){
                    if(mActivity!=null){
                        ((WebViewActivity)mActivity).TYPE_URL ="BUY_RESULE";
                    }
                }

                isFinish =true;

                super.onPageFinished(view, url);
            }

            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                resend.sendToTarget();
            }


            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(!TextUtils.isEmpty(url)&&url.contains("toLogin")&&mContext!=null) {//未登录 则跳转去登录
                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Bundle mBundle = new Bundle();
                                mBundle.putString("FROMPARENT", "WEB_VIEW");
                                mContext.startActivity(LoginActivity.makeIntent(mContext, mBundle));
                                if (mActivity != null) {
                                    mActivity.setTitle(view.getTitle()+"");
                                }
                                view.stopLoading();
                            }
                        });
                        return true;
                    }
                }


                if(!TextUtils.isEmpty(url)&&url.contains("toRegister")&&mContext!=null) {//未登注册 则跳转去注册
                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Bundle mBundle = new Bundle();
                                mBundle.putString("FROMPARENT", "WEB_VIEW");
                                mContext.startActivity(RegistActivity.makeIntent(mContext, mBundle));
                                if (mActivity != null) {
                                    mActivity.setTitle("新手活动");
                                }
                                view.stopLoading();
                            }
                        });
                        return true;
                    }
                }



                if(!TextUtils.isEmpty(url)&&url.contains("toShareRegist")&&mContext!=null&&mActivity!=null&&isFinish){//分享
                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mActivity != null) {
                                    mActivity.setTitle("每日签到");
                                }
                                view.stopLoading();
                                UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
                                if(mUserBean!=null&&mUserBean.isLogin()){
                                    dialog = new ShareDialog(mContext,mActivity);
                                    dialog.show();
                                    isFinish =true;
                                }else{
                                    Bundle mBundle = new Bundle();
                                    mBundle.putString("FROMPARENT", "WEB_VIEW");
                                    mContext.startActivity(LoginActivity.makeIntent(mContext, mBundle));
                                    isFinish =false;
                                }
                            }
                        });
                        return true;
                    }
                }


                if(!TextUtils.isEmpty(url)&&url.contains("toInvest")&&mContext!=null&&mActivity!=null){//理财
                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mActivity != null) {
                                    mActivity.setTitle("理财");
                                }
                                view.stopLoading();
                                mContext.startActivity(MainActivity.makeIntent(mContext,null));
                                EventBus.getDefault().post(new MainFromTagEven("TOINVEST"));
                            }
                        });
                        return true;
                    }
                }

                if(!TextUtils.isEmpty(url)&&url.contains("toRealName")&&mContext!=null&&mActivity!=null&&isFinish){//实名认证
                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mActivity != null) {
                                    mActivity.setTitle("开通上海银行存管账户");
                                }
                                view.stopLoading();
                                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="WEBVIEWOPENACCOUNT";
                                mContext.startActivity(OpenDepositActivity.makeIntent(mActivity,null));
                            }
                        });
                        isFinish =false;
                        return true;
                    }
                }


                if(!TextUtils.isEmpty(url)&&url.contains("toBindCard")&&mContext!=null&&mActivity!=null&&isFinish){//绑定银行卡
                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mActivity != null) {
                                    mActivity.setTitle("开通上海银行存管账户");
                                }
                                view.stopLoading();
                                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="WEBVIEWOPENACCOUNT";
                                mContext.startActivity(OpenDepositActivity.makeIntent(mActivity,null));
                            }
                        });
                        isFinish =false;
                        return true;
                    }
                }

                if(!TextUtils.isEmpty(url)&&url.contains("toSetTradePwd")&&mContext!=null&&mActivity!=null&&isFinish){//设置交易密码

                    if(StringUtils.isFastClick()){
                        return true;
                    }

                    if (mContext instanceof WebViewActivity) {
                        ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.stopLoading();
                                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="WEBVIEWOPENACCOUNT";
                                mContext.startActivity(OpenDepositActivity.makeIntent(mActivity,null));
                            }
                        });
                        isFinish =false;
                        return true;
                    }
                }

                try{

                    if(!TextUtils.isEmpty(url)&&url.contains("toUserCenter")&&mContext!=null&&mActivity!=null){
                        if (mContext instanceof WebViewActivity) {
                            ((WebViewActivity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(ZDJFUApplication.getInstance().isCheckParent("MINEFRAGMENT")){//充值成功去个人中心
                                        ZDJFUApplication.getInstance().clearParent("MINEFRAGMENT");
                                        startActivity(MainActivity.makeIntent(mActivity,null));
                                        EventBus.getDefault().post(new MainFromTagEven("RECHANGESUCCESS"));

                                    }else if(ZDJFUApplication.getInstance().isCheckParent("INVESTFRAGMENT")){//充值成功去投资页面
                                        startActivity(InvestActivity.makeIntent(mActivity,null));
                                        ZDJFUApplication.getInstance().clearParent("INVESTFRAGMENT");
                                        EventBus.getDefault().post(new InvestEven("RECHANGESUCCESS"));
                                    }else if(ZDJFUApplication.getInstance().isCheckParent("ZTINVESTFRAGMENT")){//直投充值成功
                                        startActivity(InvestAmountActivity.makeIntent(mActivity,null));
                                        ZDJFUApplication.getInstance().clearParent("ZTINVESTFRAGMENT");
                                        EventBus.getDefault().post(new InvestEven("ZTRECHANGESUCCESS"));
                                    }
                                    mActivity.finish();
                                }
                            });

                            return true;
                        }
                    }


                }catch (Exception e){

                }


                //http://www.bosc.cn/ 返回时 结束界面
                if(!TextUtils.isEmpty(url)&&url.contains("http://www.bosc.cn")){
                    mActivity.finish();
                }

                return false;
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onLoadResource(WebView view, String url) {


                super.onLoadResource(view, url);
            }
        });

        mBBWebCore.loadUrl(baseUrl);

    }


    /**
     * 将cookie同步到WebView
     *
     * @param url    WebView要加载的url
     * @param cookie 要同步的cookie
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */


    @SuppressWarnings("deprecation")
    public static void clearCookies(String url, Context context, String cookie) {

        if (TextUtils.isEmpty(url) || context == null || TextUtils.isEmpty(cookie)) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            LogUtils.d("WebView", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().setAcceptCookie(true);
            CookieManager.getInstance().setCookie(url, cookie);
            CookieManager.getInstance().flush();
            LogUtils.d("cookie2", CookieManager.getInstance().getCookie(url));
        } else {
            LogUtils.d("WebView", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
//            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
//            cookieManager.removeSessionCookie();

//            SystemClock.sleep(800); // 修复低版本4.4以前,某些手机取不到cookie的问题

//            cookieManager.setAcceptCookie(true);
            cookieManager.setCookie(url, cookie);

//            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();

            LogUtils.d("cookie1", cookieManager.getCookie(url));

        }


    }


    public class CustomChromeClient extends BBViewClient {

        public CustomChromeClient(Context context) {
            super(context);
        }

        public CustomChromeClient(String injectedName, Class injectedCls) {
            super(injectedName, injectedCls);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (mProgressBar != null) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
//                    CookieManager cookieManager = CookieManager.getInstance();
//                    String CookieStr = cookieManager.getCookie(view.getUrl());
//                    LogUtils.d("WebViewFragment", "Cookies = " + CookieStr);

                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mActivity != null && !TextUtils.isEmpty(title)) {
                mActivity.setTitle(title);
            }
        }

        // js上传文件的<input type="file" name="fileField" id="fileField" />事件捕获
        // Android > 4.1.1 调用这个方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }

        // 3.0 + 调用这个方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            if (mUploadMessage != null) {
                return;
            }
            mUploadMessage = uploadMsg;
            mActivity.startActivityForResult(createDefaultOpenableIntent(), FILECHOOSER_RESULTCODE);

        }

        // Android < 3.0 调用这个方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");

        }


        //Android 5.0+
        @Override
        @SuppressLint("NewApi")
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (mUploadMessages != null) {
                return true;
            }
            mUploadMessages = filePathCallback;
            mActivity.startActivityForResult(createDefaultOpenableIntent(), FILECHOOSER_RESULTCODE);
            return true;
        }


    }

    private Intent createDefaultOpenableIntent() {
        // Create and return a chooser with the default OPENABLE
        // actions including the camera, camcorder and sound
        // recorder where available.
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");

        Intent chooser = createChooserIntent(createCameraIntent());
        chooser.putExtra(Intent.EXTRA_INTENT, i);
        return chooser;
    }

    private Intent createChooserIntent(Intent... intents) {
        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents);
        chooser.putExtra(Intent.EXTRA_TITLE, "图片选择器");
        return chooser;
    }

    private Intent createCameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mCameraFilePath = FileUtils.BROWSER_PHOTO_PATH +
                System.currentTimeMillis() + ".jpg";
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mCameraFilePath)));
        return cameraIntent;
    }


    public WebView getWebView() {
        return mBBWebCore;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mBBWebCore != null) {
            mBBWebCore.resumeTimers(); // 恢复js循环调用
        }
        //返回重新刷新
       // reLoadUrl();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBBWebCore != null) {
            mBBWebCore.pauseTimers(); // 暂停js循环调用
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBBWebCore != null) {
            mBBWebCore.removeAllViews();
            mBBWebCore.destroy();
        }
    }

    @Override
    public void onDestroyView() {
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
        super.onDestroyView();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0以上
                if (null == mUploadMessages) return;
                Uri result = data == null || resultCode != RESULT_OK ? null
                        : data.getData();
                if (result == null && data == null && resultCode == RESULT_OK) {
                    File cameraFile = new File(mCameraFilePath);
                    if (cameraFile.exists()) {
                        result = Uri.fromFile(cameraFile);
                        // Broadcast to the media scanner that we have a new photo
                        // so it will be added into the gallery for the user.
                        mActivity.sendBroadcast(
                                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, result));
                    }
                }
                if (result == null) {
                    mUploadMessages.onReceiveValue(null);
                } else {
                    mUploadMessages.onReceiveValue(new Uri[]{result});
                }
                mUploadMessages = null;
            } else { // 5.0以下
                if (null == mUploadMessage) return;
                Uri result = data == null || resultCode != RESULT_OK ? null
                        : data.getData();
                if (result == null && data == null && resultCode == RESULT_OK) {
                    File cameraFile = new File(mCameraFilePath);
                    if (cameraFile.exists()) {
                        result = Uri.fromFile(cameraFile);
                        // Broadcast to the media scanner that we have a new photo
                        // so it will be added into the gallery for the user.
                        mActivity.sendBroadcast(
                                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, result));
                    }
                }
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }

        }
    }


    /**
     * 处理拍照/选择的文件
     */
    private File handleFile(File file) {
        DisplayMetrics dMetrics = getResources().getDisplayMetrics();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
        System.out.println("  imageWidth = " + imageWidth + " imageHeight = " + imageHeight);
        int widthSample = (int) (imageWidth / (dMetrics.density * 90));
        int heightSample = (int) (imageHeight / (dMetrics.density * 90));
        System.out.println("widthSample = " + widthSample + " heightSample = " + heightSample);
        options.inSampleSize = widthSample < heightSample ? heightSample : widthSample;
        options.inJustDecodeBounds = false;
        Bitmap newBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        System.out.println("newBitmap.size = " + newBitmap.getRowBytes() * newBitmap.getHeight());
        LogUtils.d("file name : " + file.getName());
        File handleFile = new File(file.getParentFile(), file.getName().replace(".jpg", "_upload.jpg"));
        LogUtils.d("file name : " + handleFile.getName());
        try {
            if (newBitmap.compress(Bitmap.CompressFormat.PNG, 50, new FileOutputStream(handleFile))) {
                System.out.println("保存图片成功");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return handleFile;

    }



    public String getType(){
        if(dialog!=null){
            return dialog.getType();
        }else {
            return "";
        }
    }

}
