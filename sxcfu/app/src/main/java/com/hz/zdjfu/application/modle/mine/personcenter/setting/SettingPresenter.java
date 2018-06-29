package com.hz.zdjfu.application.modle.mine.personcenter.setting;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 * 设置请求接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class SettingPresenter implements SettingContract.Presenter{


    private static final String TAG =SettingPresenter.class.getName();
    private SettingContract.View mView;
    private Context mContext;
    public SettingPresenter(Context context, SettingContract.View view){
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
    public void checkVersionDetail() {

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().checkVersion(mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AppVersionBean>() {
            @Override
            public void onSuccess(AppVersionBean result) {


                if (result == null) {
                    return;
                }
                AppUtils.AppInfo appInfo = AppUtils.getAppInfo(ZDJFUApplication.getInstance().getApplicationContext());
                if (appInfo == null) {
                    return;
                }

                Log.i(TAG,"当前的版本"+appInfo.getVersionCode());

                if (Integer.parseInt(result.getRelease_version()) <= appInfo.getVersionCode()) {

                    mView.showVersionMsg();
                    return;
                }
                mView.showVersionState(result);

            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });




    }
}
