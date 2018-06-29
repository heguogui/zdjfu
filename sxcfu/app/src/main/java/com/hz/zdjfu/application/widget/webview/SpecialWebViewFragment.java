package com.hz.zdjfu.application.widget.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.BuildConfig;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.rechangecenter.rechangedetail.RechangeDetailActivity;
import com.hz.zdjfu.application.utils.NetworkUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


/**
 * [类功能说明]
 *特殊WebView
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/6/25 0025.
 */
public class SpecialWebViewFragment extends BaseFragment {

    private static  final  String TAG =SpecialWebViewFragment.class.getName();
    private List<String> mListUrl = new ArrayList<String>();
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.webview)
    BBWebCore mBBWebCore;
    @BindView(R.id.webview_integral_tv)
    TextView webviewIntegralTv;
    @BindView(R.id.webview_integral_detail_btn)
    TextView webviewIntegralDetailBtn;

    public String baseUrl = "";
    private String mNumber=null;
    @BindView(R.id.earningdetail_network_error)
    RelativeLayout networkError;

    public static SpecialWebViewFragment newInstance() {
        return new SpecialWebViewFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_specialwebview;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        if (mActivity != null) {
            baseUrl = mActivity.getIntent().getStringExtra("WebView_URL");
            mNumber =mActivity.getIntent().getStringExtra("WebView_NUM");
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

            if(webviewIntegralTv!=null){
                webviewIntegralTv.setText("积分: 0");
            }

            webviewIntegralDetailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(RechangeDetailActivity.makeIntent(mActivity, null));
                }
            });

        } else {
            networkError.setVisibility(View.VISIBLE);
            ToastUtils.show(mActivity, getString(R.string.common_net_error));
        }




    }


    private void loadUrl() {

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
        mBBWebCore.setWebChromeClient(new SpecialWebViewFragment.CustomChromeClient(mActivity));
        mBBWebCore.setWebViewClient(new BBWebCoreClient(mActivity) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

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

                if(!TextUtils.isEmpty(mNumber)){
                    webviewIntegralTv.setText("积分: "+mNumber);
                }

                if(!TextUtils.isEmpty(url)){
                    Log.e("页面开始加载", "url="+url);
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mActivity != null) {
                    mActivity.setTitle(view.getTitle());
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                resend.sendToTarget();
            }


            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //拦截兑吧 立即使用 跳转至理财列表
                if(!TextUtils.isEmpty(url)&&url.contains("www.zdjfu.com")&&mContext!=null){//理财
                    if (mContext instanceof SpecialWebViewActivity) {
                        ((SpecialWebViewActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.stopLoading();
                                mContext.startActivity(MainActivity.makeIntent(mContext,null));
                                EventBus.getDefault().post(new MainFromTagEven("TOINVEST"));
                            }
                        });
                        return true;
                    }
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
        super.onDestroyView();
    }

}
