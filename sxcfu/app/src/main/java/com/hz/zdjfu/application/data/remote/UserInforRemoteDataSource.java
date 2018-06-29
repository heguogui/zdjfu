package com.hz.zdjfu.application.data.remote;

import android.support.annotation.NonNull;

import com.hz.zdjfu.application.data.UserInforSource;
import com.hz.zdjfu.application.data.bean.UserBean;

import java.util.Map;

/**
 * [类功能说明]
 *个人数据 远程获取
 * @author Administrator
 * @version v 1.0.0 2017/5/17 9:18 HaoZhuoKeJi
 * @email heguogui2013@163.com
 */

public class UserInforRemoteDataSource implements UserInforSource {


    private static UserInforRemoteDataSource INSTANCE;

    private UserInforRemoteDataSource() {
    }

    public static UserInforRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserInforRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUserInfor(@NonNull Map<String, String> paramMap, @NonNull LoadNodeCallback callback) {

    }

    @Override
    public void saveUserInfor(UserBean node) {

    }

    @Override
    public UserBean getUserInfo() {
        return null;
    }
}
