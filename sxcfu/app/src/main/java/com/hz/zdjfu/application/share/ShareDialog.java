package com.hz.zdjfu.application.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.URLController;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.utils.UiUtils;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;


/**
 * [类功能说明]
 *  分享
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class ShareDialog extends Dialog implements View.OnClickListener {
    private Activity mActivity;
    private Context mContext;
    private DisplayMetrics dm;
    //UI
    private LinearLayout mWeixinLayout;
    private LinearLayout mWeixinMomentLayout;
    private LinearLayout mSinaLayout;
    private LinearLayout mQQLayout;
    private LinearLayout mQZoneLayout;
    private LinearLayout mLinkLayout;
    private TextView mCancelView;
    //share relative
    private int mShareType; //指定分享类型
    private String mShareTitle; //指定分享内容标题
    private String mShareText; //指定分享内容文本
    private String mSharePhoto; //指定分享本地图片
    private String mShareTileUrl;
    private String mShareSiteUrl;
    private String mShareSite;
    private String mUrl;
    private String mResourceUrl;
    //微信分享
    // 本地分享：0，活动分享：1
    private String SHARETYPE_0 = "0";
    private String SHARETYPE_1 = "1";
    private String shareType = "0";
    private SendMessageToWX.Req req;
    private WXWebpageObject webpage;
    private WXMediaMessage msg;
    private IWXAPI WXapi;
    private String flag = "";


    public ShareDialog(Context context, Activity activity) {
        super(context, R.style.SheetDialogStyle);
        mContext = context;
        mActivity = activity;
        dm = mContext.getResources().getDisplayMetrics();
    }

    public ShareDialog(Context context, Activity activity, String flag) {
        super(context, R.style.SheetDialogStyle);
        mContext = context;
        mActivity = activity;
        dm = mContext.getResources().getDisplayMetrics();
        this.flag = flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_share_dialog);
        initView();
    }

    private void initView() {
        //通过获取到dialog的window来控制dialog的宽高及位置
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = dm.widthPixels; //设置宽度
        dialogWindow.setAttributes(lp);

        mWeixinLayout = (LinearLayout) findViewById(R.id.weixin_layout);
        mWeixinLayout.setOnClickListener(this);
        mWeixinMomentLayout = (LinearLayout) findViewById(R.id.moment_layout);
        mWeixinMomentLayout.setOnClickListener(this);

        mCancelView =findViewById(R.id.cancel_view);
        mCancelView.setOnClickListener(this);


        // 微信分享注册和实例化数据对象
        WXapi = WXAPIFactory.createWXAPI(mContext, Constants.WX_SHARE_APP_ID, true);
        WXapi.registerApp(Constants.WX_SHARE_APP_ID);

        webpage = new WXWebpageObject();
        msg = new WXMediaMessage(webpage);
        req = new SendMessageToWX.Req();
    }

    public void setResourceUrl(String resourceUrl) {
        mResourceUrl = resourceUrl;
    }

    public void setShareTitle(String title) {
        mShareTitle = title;
    }

    public void setImagePhoto(String photo) {
        mSharePhoto = photo;
    }

    public void setShareType(int type) {
        mShareType = type;
    }

    public void setShareSite(String site) {
        mShareSite = site;
    }

    public void setShareTitleUrl(String titleUrl) {
        mShareTileUrl = titleUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setShareSiteUrl(String siteUrl) {
        mShareSiteUrl = siteUrl;
    }

    public void setShareText(String text) {
        mShareText = text;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin_layout://分享给微信朋友
                flag ="2";
                showWXShareAndroid("2");
                break;
            case R.id.moment_layout://朋友圈
                showWXShareAndroid("1");
                flag ="1";
                break;
            case R.id.cancel_view:
                dismiss();
                break;
        }
    }

    //构建一个唯一标志
    private static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : (type + System.currentTimeMillis());
    }

    //这部分是微信分享的，由于之前他做了，所以先用他的 后期再更改
    public void showWXShareAndroid(String type) {


        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean==null){
            return;
        }
        if(!mBean.isLogin()){
            mContext.startActivity(LoginActivity.makeIntent(mActivity,null));
            return;
        }



        if(!TextUtils.isEmpty(URLController.URL_ZZ)) {
            if (URLController.URL_ZZ.contains("pctest")) {
                webpage.webpageUrl = "https://www.zdjfu.com/static/zdjf_app/page/login/reg.html?invite_phone="+ UserInfoManager.getInstance().getUserBean().getUserPhone()+"&invite_source="+Constants.ANDROID_SOURCE;
            } else {
                webpage.webpageUrl = URLController.URL_ZZ + "/zdjf/static/zdjf_app/page/login/reg.html?invite_phone="+ UserInfoManager.getInstance().getUserBean().getUserPhone()+"&invite_source="+Constants.ANDROID_SOURCE;
            }
        }
        msg.title = mContext.getResources().getString(R.string.wx_share_title);
        msg.description = mContext.getResources().getString(R.string.wx_share_content);
        req.transaction = buildTransaction("ZDJFU");
        Bitmap thumb =BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ic_wx_share);
        msg.thumbData = UiUtils.bmpToByteArray(thumb,true);
        req.message = msg;
        thumb.recycle();
        switch (type) {
            case "2":
                // 分享给微信朋友 2,朋友圈 1
                req.scene = SendMessageToWX.Req.WXSceneSession;
                WXapi.sendReq(req);
                ShareDialog.this.dismiss();
                break;
            case "1":
                // 发送到朋友圈 微信4.2以上支持
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                WXapi.sendReq(req);
                ShareDialog.this.dismiss();
                break;
        }
    }

    private PlatformActionListener mListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            Toast.makeText(ZDJFUApplication.getInstance().getApplicationContext(), "分享成功", Toast.LENGTH_SHORT).show();
            ShareDialog.this.dismiss();
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e("分享回调信息", throwable.getMessage());
            Toast.makeText(ZDJFUApplication.getInstance().getApplicationContext(), "分享失败", Toast.LENGTH_SHORT).show();
            ShareDialog.this.dismiss();
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Toast.makeText(ZDJFUApplication.getInstance().getApplicationContext(), "取消分享", Toast.LENGTH_SHORT).show();
            ShareDialog.this.dismiss();
        }
    };

    private void shareData(ShareManager.PlatformType platofrm) {

        if(TextUtils.isEmpty(mShareTitle)||TextUtils.isEmpty(mShareText)||TextUtils.isEmpty(mSharePhoto)){
            return;
        }
        ShareData mData = new ShareData();
        Platform.ShareParams params = new Platform.ShareParams();
        params.setShareType(mShareType);
        params.setTitle(mShareTitle);
        params.setTitleUrl(mShareTileUrl);
        params.setSite(mShareSite);
        params.setSiteUrl(mShareSiteUrl);
        params.setText(mShareText);
        params.setImagePath(mSharePhoto);
        params.setUrl(mUrl);
        mData.type = platofrm;
        mData.params = params;
        ShareManager.getInstance().shareData(mData, mListener);
    }

    /**
     *
     * @return
     */
    public String getType(){
        return flag;
    }

}
