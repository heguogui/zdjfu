package com.hz.zdjfu.application.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount.OpenSHBankAccountActivity;
import com.hz.zdjfu.application.widget.dialog.OpenBankAccountDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

/**
 * [类功能说明]
 * 是否认证完成
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class CheckAttestaionUtils {

    private static CheckAttestaionUtils mInstance;
    public static Context mContext;
    public CheckAttestaionUtils(Context context) {
        mContext = context;
    }

    private static OpenBankAccountDialog dialog;
    /**
     * 创建字体工具类示例
     * @param context 上下文
     * @return
     */
    public static synchronized CheckAttestaionUtils createInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CheckAttestaionUtils(context);
        }
        return mInstance;
    }


    /**
     * 获取文件工具类实例
     *
     * @return
     */
    public static synchronized CheckAttestaionUtils getInstance() {
        if (mInstance == null)
            throw new IllegalStateException("CheckAttestaionUtils must be create by call createInstance(Context context)");
        return mInstance;
    }


    public  boolean isCheckAttestation(UserDetailBean bean){

        if(bean.getStatus()==0||bean.getStatus()==1||bean.getStatus()==2){

                dialog = new OpenBankAccountDialog(mContext,new OpenBankAccountDialog.OpenBankAccountCancleListener() {
                    @Override
                    public void callback() {
                        dialog.dismiss();
                        dialog =null;
                    }
                }, new OpenBankAccountDialog.OpenBankAccountSureListener() {
                    @Override
                    public void callback() {


                        if(bean.getStatus()==2){
                            if(!TextUtils.isEmpty(bean.getPhone())){
                                settingPaySetting(bean.getPhone());
                            }
                        }else{

                            Bundle mBundle = new Bundle();
                            if(bean.getReal_name_auth()== Constants.REAL_NAME_AUTH_OK){
                                mBundle.putString("RZSTATE","1");
                            }
                            mBundle.putString("OPENCARDSTATE","");
                            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="PERSONCENTER";
                            mContext.startActivity(OpenSHBankAccountActivity.makeIntent(mContext,mBundle));
                        }

                        dialog.dismiss();
                        dialog =null;
                        return;

                    }
                });
                dialog.show();

            return false;

        }else if(bean.getStatus()==3||bean.getStatus()==4){
            return true;
        }

        return false;

    }



    private void settingPaySetting(String phone) {

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

        RetrofitUtil.createService().settingBankCardPayPwd(phone,Constants.ANDROID_SOURCE,mIP,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<DataStringList>() {
            @Override
            public void onSuccess(DataStringList result) {
                if(result!=null&&!TextUtils.isEmpty(result.getDataList())){
                    Intent mIntent = new Intent(mContext, WebViewActivity.class);
                    mIntent.putExtra("WebView_URL",result.getDataList());
                    mContext.startActivity(mIntent);
                }
            }

            @Override
            public void _onError(Throwable e) {
               ToastUtils.show(mContext,e.getMessage()+"");
            }
        });


    }




}




