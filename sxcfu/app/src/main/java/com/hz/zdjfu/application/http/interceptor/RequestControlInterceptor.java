package com.hz.zdjfu.application.http.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.NetworkUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/23 0023
 */
public class RequestControlInterceptor  {

    private static final int TIMEOUT_CONNECT = 5; //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天
    private static Context mContext;
    private static  final  String TAG =RequestControlInterceptor.class.getName();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String RESULT_STATUS = "\"respCode\"";
    public static final String RESULT_HEAD = "\"data\"";
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        if (!NetworkUtils.isConnected(null)) {
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//        }
//        Response response = chain.proceed(request);
//        if (NetworkUtils.isConnected(null)) {
//            int maxAge = 0 * 60;
//            // 有网络时 设置缓存超时时间0个小时
//            response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .build();
//        } else {
//            // 无网络时，设置超时为4周
//            int maxStale = 60 * 60 * 24 * 28;
//            response.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .removeHeader("Pragma")
//                    .build();
//        }
//        return response;
//
//    }

//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        if (!NetworkUtils.isConnected(null)) {
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//        }
//        Response response = chain.proceed(request);
//        if (NetworkUtils.isConnected(null)) {
//            int maxAge = 0 * 60;
//            // 有网络时 设置缓存超时时间0个小时
//            response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .build();
//        } else {
//            // 无网络时，设置超时为4周
//            int maxStale = 60 * 60 * 24 * 28;
//            response.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .removeHeader("Pragma")
//                    .build();
//        }
//        return response;
//    }


    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {

                okhttp3.Response oldResponse =originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build();
                return oldResponse.newBuilder()
                        .body(ResponseBody.create(JSON,controlResponse(originalResponse)))
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .body(ResponseBody.create(JSON,controlResponse(originalResponse)))
                        .build();
            }
        }
    };

    public static  final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected(null)) {
                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
            }else {

                mContext =ZDJFUApplication.getInstance().getApplicationContext();
                if(mContext==null){
                    return null;
                }
                Request oldRequest = chain.request();
                // 添加新的参数
                HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                        .newBuilder()
                        .scheme(oldRequest.url().scheme())
                        .host(oldRequest.url().host())
                        .addQueryParameter(Constants.ACCESSTOKEN,PreferencesUtils.getString(Constants.ACCESS_TOKEN_PREFERENCE, mContext,Constants.ACCESSTOKEN))//参数accesstoken
                        .addQueryParameter(Constants.REQSOURCE,Constants.REQUESTSOURCE_ANDROID)//安卓机型来源
                        .addQueryParameter(Constants.APPVERSION,AppUtils.getAppInfo(mContext).getVersionName());//App版本

                // 新的请求
                request = oldRequest.newBuilder()
                        .method(oldRequest.method(), oldRequest.body())
                        .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                        .url(authorizedUrlBuilder.build())
                        .build();

            }

            return chain.proceed(request);
        }
    };


    private static String controlResponse(Response originalResponse){
        // 拦截返回值并修改为resultStatus为code
        String originalBodyStr = null;
        StringBuffer originalSF =null;
        try {
            originalBodyStr = originalResponse.body().string();
            if (TextUtils.isEmpty(originalBodyStr)) {
                Log.i(TAG+"返回数据:","结果为空");
                return originalBodyStr;
            }
            Log.i(TAG+"返回数据:",originalBodyStr);
            //外围再封装一层data
            originalSF = new StringBuffer();
            String[] lastString =originalBodyStr.split(RESULT_STATUS);
            originalSF.append("{"+RESULT_HEAD+":"+lastString[0].substring(0,lastString[0].length()-1)+"},"+RESULT_STATUS+lastString[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return originalSF.toString();
    }

}
