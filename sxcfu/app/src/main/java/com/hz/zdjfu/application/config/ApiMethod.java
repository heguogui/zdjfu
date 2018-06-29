/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.config;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;

/**
 * H5网络请求接口方法
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class ApiMethod {


//    /**
//     * 组装token
//     * @param context
//     * @return
//     */
//    public  static String getAccessToken(Context context){
//        String requestType = getAppVersionInfo(context);
//        String access_token =PreferencesUtils.getString(Constants.ACCESS_TOKEN_PREFERENCE,context,Constants.ACCESSTOKEN);
//        String refresh_token =PreferencesUtils.getString(Constants.REFRESH_TOKEN_PREFERENCE,context,Constants.REFRESHTOKEN);
//        if( TextUtils.isEmpty(refresh_token)|| TextUtils.isEmpty(access_token)){
//            return requestType; // 没有登录的时候，就只有版本号信息
//        }else{ // 有登录的时候，拼接token信息
//            return requestType+"&"+Constants.ACCESSTOKEN+"="+access_token+"&"+Constants.REFRESHTOKEN+"="+refresh_token;
//        }
//    }
//
//
//    // 获取app版本号信息 请求来源安卓为3 友盟注册设备Token
//    public static String getAppVersionInfo(Context context){
//        String device_token =PreferencesUtils.getString(Constants.DEVICE_TOKEN_PREFERENCE,context,Constants.DEVICETOKEN);
//        String requestType = "device_token="+device_token+"&reqSource=3&appVersion="+ AppUtils.getAppInfo(context).getVersionName();
//        return requestType;
//    }




}
