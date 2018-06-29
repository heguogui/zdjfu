/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.http;

import com.google.gson.GsonBuilder;
import com.hz.zdjfu.application.config.URLController;
import com.hz.zdjfu.application.utils.json.MyDateDeserializer;

import java.text.DateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * [类功能说明]
 *Retrofit工具类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class RetrofitUtil {


    private static volatile Retrofit zzretrofit;
    private static volatile Retrofit ztretrofit;
    private static volatile Retrofit ztheadretrofit;


    /**
     * 创建债转接口的retrofit和service
     *
     * @return
     */
    public static ApiService createService() {
        if (zzretrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (zzretrofit == null) {
                    zzretrofit = new Retrofit.Builder()
                            .baseUrl(URLController.URL_ZZ)
                            .addConverterFactory(GsonConverterFactory.create())//对返回体时间参数Data转换字符串时间
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpUtil.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return zzretrofit.create(ApiService.class);
    }


    /**
     * 创建直投接口的retrofit和service
     *
     * @return
     */
    public static ApiService createZTService() {
        if (ztretrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (ztretrofit == null) {
                    ztretrofit = new Retrofit.Builder()
                            .baseUrl(URLController.URL_ZT)
                            .addConverterFactory(GsonConverterFactory.create())//对返回体时间参数Data转换字符串时间
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpUtil.defaultztOkHttpClient())
                            .build();
                }
            }
        }
        return ztretrofit.create(ApiService.class);
    }


    /**
     * 创建直投接口的retrofit和service
     *
     * @return
     */
    public static ApiService createZTHService() {
        if (ztheadretrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (ztheadretrofit == null) {
                    ztheadretrofit = new Retrofit.Builder()
                            .baseUrl(URLController.URL_ZT)
                            .addConverterFactory(GsonConverterFactory.create())//对返回体时间参数Data转换字符串时间
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpUtil.defaultztzthhOkHttpClient())
                            .build();
                }
            }
        }
        return ztheadretrofit.create(ApiService.class);
    }




}
