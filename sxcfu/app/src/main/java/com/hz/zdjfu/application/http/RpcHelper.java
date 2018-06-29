/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.http;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.utils.ActivityManagerUtil;
import com.hz.zdjfu.application.utils.LogUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.json.GsonUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * [类功能说明]
 *调用接口助手类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class RpcHelper {

    public static final String OPERATION_TYPE = "operationType"; // http请求中参数key
    public static final String REQUEST_DATA = "requestData"; // http请求中参数key
    public static final String D = "d"; // http请求中参数key



    /**
     * 生成http请求参数map
     *
     * @param method      api接口方法全路径
     * @param requestBean 请求对象
     * @return 请求参数map
     */
    public static Map<String, String> getParamMap(
            String method,
            Object requestBean
    ) {
        String requestData = GsonUtils.DateBean2Json(requestBean);
        Map<String, String> map = new ArrayMap<>();
        map.put(OPERATION_TYPE, method);
        map.put(REQUEST_DATA, requestData);
        return map;
    }

    /**
     * session过期处理,如果过期,跳转到登录页面
     *
     * @return 是否过期  true 过期  false 不过期
     */
    public static void sessionExpiredProcess() {

        List<Activity> activities = ActivityManagerUtil.getActivityManager().getActivities();
        if (activities == null || activities.isEmpty()) {
            return;
        }
        Activity currentActivity = activities.get(activities.size() - 1);
        if (currentActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
//        bundle.putString(LoginActivity.SESSION_TIPS, "会话过期,请重新登录!");
//        currentActivity.startActivity(LoginActivity.makeIntent(currentActivity, bundle));
        try {
            for (Activity activity :
                    activities) {
                if (activity != null) {
                    activity.finish();
                }
            }
//            DataCleanManager.clearCurrentUserData();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }

    }


//    /**
//     * Session过期处理
//     */
//    public static void startSessionExpired(Context context) {
//        Intent intent = new Intent(ACTION_RECEIVER_SESSION_EXPIRED_FAILED);
//        context.sendBroadcast(intent);
//    }

    /**
     * 获取需要上传的文件组装后的map
     *
     * @param filePathList
     * @return
     */
    public static LinkedHashMap<String, RequestBody> getFileMap(ArrayList<String> filePathList) {
        if (filePathList == null || filePathList.isEmpty()) {
            return null;
        }
        LinkedHashMap<String, RequestBody> map = new LinkedHashMap<>();

        String filePath;
        for (int i = 0; i < filePathList.size(); i++) {
            filePath = filePathList.get(i);
            if (filePath == null) {
                continue;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                ToastUtils.show(ZDJFUApplication.getInstance().getApplicationContext(), file.getName() + "不存在！");
                continue;
            }
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);


            map.put(i + file.getName() + "\"; filename=\"" + i + file.getName(), requestFile);
        }
        
        return map;
    }

}
