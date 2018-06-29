/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hz.zdjfu.application.BuildConfig;
import com.hz.zdjfu.application.http.interceptor.HandleRequestInterceptor;
import com.hz.zdjfu.application.http.interceptor.HandleResponseInterceptor;
import com.hz.zdjfu.application.http.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * [类功能说明]
 *okhttp 工具类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class OkHttpUtil {

    private static OkHttpClient client;
    private static OkHttpClient zthClient;
    private static OkHttpClient zzhClient;
    private static OkHttpClient fileClient;
    /**
     * 获取默认okhttp客户端
     *
     * @return
     */
    public static OkHttpClient defaultOkHttpClient() {
        if (client != null) {
            return client;
        }

        //设置打印请求到的json字符串和查看log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new HandleResponseInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(logging)
                .build();
        return client;
    }



    public static OkHttpClient defaultztOkHttpClient() {
        if (zzhClient != null) {
            return zzhClient;
        }
        //设置打印请求到的json字符串和查看log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        zzhClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new HandleRequestInterceptor())
                .addInterceptor(new HandleResponseInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(logging)
                .build();
        return zzhClient;
    }

    public static OkHttpClient defaultztzthhOkHttpClient() {
        if (zthClient != null) {
            return zthClient;
        }

        //设置打印请求到的json字符串和查看log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        zthClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new HandleRequestInterceptor())
                .addInterceptor(new HandleResponseInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(logging)
                .build();
        return zthClient;
    }


    /**
     * 上传下载文件的okhttp客户端
     *
     * @return
     */
    public static OkHttpClient createFileClient() {
        if (fileClient != null) {
            return fileClient;
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        fileClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return fileClient;
    }

}
