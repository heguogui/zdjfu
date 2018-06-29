package com.hz.zdjfu.application.data.local;

import android.support.annotation.NonNull;


import com.hz.zdjfu.application.data.UserInforSource;
import com.hz.zdjfu.application.data.bean.UserBean;

import java.util.Map;

/**
 * [类功能说明]
 *用户本地数据
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class UserInforLocalDataSource implements UserInforSource {


    private static UserInforLocalDataSource INSTANCE;

    private UserInforLocalDataSource() {
    }

    public static UserInforLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserInforLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUserInfor(@NonNull Map<String, String> paramMap, @NonNull LoadNodeCallback callback) {

    }

    @Override
    public void saveUserInfor(UserBean node) {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.delete(UserBean.class);
//        UserBean userBean = realm.createObject(UserBean.class);
//        userBean.setUserHead(node.getUserHead());
//        userBean.setUserId(node.getUserId());
//        userBean.setUserName(node.getUserName());
//        userBean.setUserNick(node.getUserNick());
//        userBean.setUserPhone(node.getUserPhone());
//        userBean.setUserSex(node.getUserSex());
//        userBean.setUserPassord(node.getUserPassord());
//        realm.commitTransaction();
//        realm.close();
    }

    @Override
    public UserBean getUserInfo() {
//        Realm realm = Realm.getDefaultInstance();
//        RealmQuery<UserBean> query = realm.where(UserBean.class);
//        UserBean nodeBean = query.findFirst();
//        UserBean nodeBean1 = null;
//        if (nodeBean != null) {
//            nodeBean1 = realm.copyFromRealm(nodeBean);
//        }
//        realm.close();
//        return nodeBean1;
        return  null;
    }

}
