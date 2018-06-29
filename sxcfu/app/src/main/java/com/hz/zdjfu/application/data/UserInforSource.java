package com.hz.zdjfu.application.data;

import android.support.annotation.NonNull;

import com.hz.zdjfu.application.data.bean.UserBean;

import java.util.Map;


/**
 * [类功能说明]
 *用户信息数据源接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public interface UserInforSource {


    interface LoadNodeCallback {

        void onNodeLoaded(UserBean node);

        void onDataNotAvailable(String errorTip);

    }

    /**
     * 获取用户信息
     * @param paramMap
     * @param callback
     */
    void getUserInfor(@NonNull Map<String, String> paramMap, @NonNull LoadNodeCallback callback);


    /**
     * 保存用户信息
     */
    void saveUserInfor(UserBean node);

    /**
     * 读取用户信息
     */
    UserBean getUserInfo();


}
