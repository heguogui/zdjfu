package com.hz.zdjfu.application.http.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.http.DiskCacheUtil;
import com.hz.zdjfu.application.http.cache.DiskLruCacheHelper;
import com.hz.zdjfu.application.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * [类功能说明]
 *返回体拦截器 保存cookie
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class HandleResponseInterceptor implements Interceptor {

    private static  final  String TAG =HandleResponseInterceptor.class.getName();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String RESULT_STATUS = "\"data\"";
    public static final String RESULT_EMPTY = "\"无数据\"";
    private String KEY_URL;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        //获取当前请求路径
        KEY_URL =chain.request().url().toString();
        Log.i(TAG,"KEY_URL="+KEY_URL);
        if(NetworkUtils.isConnected(null)){//有网时
            return originalResponse.newBuilder().body(ResponseBody.create(JSON,controlResponse(originalResponse))).build();
        }
        return originalResponse;
    }


    private String controlResponse(Response originalResponse){
        // 拦截返回值并修改为resultStatus为code
        String originalBodyStr = null;
        StringBuffer originalSF =null;
        try {
            originalBodyStr = originalResponse.body().string();
            if (TextUtils.isEmpty(originalBodyStr)) {
                return originalBodyStr;
            }
            if(originalBodyStr.contains("{}")&&(originalBodyStr.contains("200")||originalBodyStr.contains("1002")||originalBodyStr.contains("400")||originalBodyStr.contains("401")||originalBodyStr.contains("1000")||originalBodyStr.contains("304")||originalBodyStr.contains("5000")||originalBodyStr.contains("402")||originalBodyStr.contains("601")||originalBodyStr.contains("501")||originalBodyStr.contains("502")||originalBodyStr.contains("503"))){


                if(originalBodyStr.contains("lastInvtor")){
                    return originalBodyStr;
                }

                if(originalBodyStr.contains("false")){
                    return originalBodyStr;
                }

                originalSF = new StringBuffer();
                String[] lastString =originalBodyStr.split(RESULT_STATUS);
                originalSF.append(lastString[0]+RESULT_STATUS+":"+RESULT_EMPTY+"}");
                originalBodyStr =originalSF.toString();
                Log.i(TAG+"重装数据:","originalBodyStr="+originalBodyStr);
            }

         } catch (IOException e) {
            e.printStackTrace();
        }

        return originalBodyStr;
    }


}
