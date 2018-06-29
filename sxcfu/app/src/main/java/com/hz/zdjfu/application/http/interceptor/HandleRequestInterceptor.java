package com.hz.zdjfu.application.http.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.NetworkUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * [类功能说明]
 *http请求拦截器修改
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class HandleRequestInterceptor implements Interceptor {

    private Context mContext;
    @Override
    public Response intercept(Chain chain) throws IOException {

        mContext =ZDJFUApplication.getInstance().getApplicationContext();
        if(mContext==null){
            return null;
        }
        UserBean bean = UserInfoManager.getInstance().getLocationUserData();
        String access_token =null;
        String jwt_token =null;
        if(bean!=null){
            access_token=bean.getAccessToken();
            jwt_token=bean.getJwtToken();
        }

        if(!TextUtils.isEmpty(access_token)&&!TextUtils.isEmpty(jwt_token)){
            // 新的请求
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(Constants.USER_AGENT, Constants.AGENT) // add agent "android"
                    .addHeader(Constants.ACCESSTOKEN,access_token)//参数accesstoken
                    .addHeader(Constants.JWTTOKEN,jwt_token)//参数jwttoken
                    .addHeader(Constants.REQSOURCE,Constants.REQUESTSOURCE_ANDROID)//安卓机型来源
                    .build();

            return chain.proceed(request);
        }else{
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(Constants.REQSOURCE,Constants.REQUESTSOURCE_ANDROID)//安卓机型来源
                    .build();
            return chain.proceed(request);
          //  return chain.proceed(chain.request());
        }


    }


}
