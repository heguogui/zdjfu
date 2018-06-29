package com.hz.zdjfu.application.manager;

import android.content.Context;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.utils.PreferencesUtils;

/**
 * [类功能说明]
 *用户信息管理器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class UserInfoManager {

    private static UserInfoManager instance;

    private UserBean userBean;
    private Context mContext;
    private UserInfoManager() {
        this.mContext =ZDJFUApplication.getInstance().getApplicationContext();
    }


    public static UserInfoManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new UserInfoManager();
                }
            }
        }
        return instance;
    }


    /**
     * 获取当前用户信息
     *
     * @return
     */
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * 设置当前用户信息
     * @param userBean
     */
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }



    /**
     * 清楚当前用户信息
     */
    public void clearUserInfo() {
        userBean = null;
    }


    /**
     * 保存当前用户信息到内存汇总
     * @param userBean
     */
    public void saveCurrentUserInfo(UserBean userBean) {
        //缓存保存
        setUserBean(userBean);
        //本地保存
        saveLocationUserInfo(userBean);
    }


    private void saveLocationUserInfo(UserBean userBean) {

        PreferencesUtils.putString(mContext,Constants.USER_PHONE_PREFERENCE,userBean.getUserPhone());
        PreferencesUtils.putString(mContext,Constants.USER_PASSWORD_PREFERENCE,userBean.getUserPassord());
        PreferencesUtils.putString(mContext,Constants.USER_HEADPICTURE_PREFERENCE,userBean.getUserHead());
        PreferencesUtils.putString(mContext,Constants.USER_NICK_PREFERENCE,userBean.getUserNick());
        PreferencesUtils.putBoolean(mContext,Constants.USER_IS_LOGIN_PREFERENCE,userBean.isLogin());
        PreferencesUtils.putString(mContext,Constants.USER_CREATE_TIME_PREFERENCE,userBean.getCreateTime());
        PreferencesUtils.putString(mContext,Constants.ACCESS_TOKEN_PREFERENCE,userBean.getAccessToken());
        PreferencesUtils.putString(mContext,Constants.JWT_TOKEN_PREFERENCE,userBean.getJwtToken());




    }


    /**
     * 获取本地的用户信息
     * @return
     */
    public UserBean getLocationUserData(){

        if(userBean==null){
            userBean = new UserBean();
            userBean.setUserPhone(PreferencesUtils.getString(mContext,Constants.USER_PHONE_PREFERENCE));
            userBean.setUserPassord(PreferencesUtils.getString(mContext,Constants.USER_PASSWORD_PREFERENCE));
            userBean.setUserHead(PreferencesUtils.getString(mContext,Constants.USER_HEADPICTURE_PREFERENCE));
            userBean.setUserNick(PreferencesUtils.getString(mContext,Constants.USER_NICK_PREFERENCE));
            userBean.setCreateTime(PreferencesUtils.getString(mContext,Constants.USER_CREATE_TIME_PREFERENCE));
            userBean.setPushMsgState(PreferencesUtils.getBoolean(mContext,Constants.USER_PUSHMSG_STATE_PREFERENCE,false));
            userBean.setGestureState(PreferencesUtils.getBoolean(mContext,Constants.USER_GESTURE_STATE_PREFERENCE,false));
            userBean.setLogin(PreferencesUtils.getBoolean(mContext,Constants.USER_IS_LOGIN_PREFERENCE,false));
            userBean.setAccessToken(PreferencesUtils.getString(mContext,Constants.ACCESS_TOKEN_PREFERENCE));
            userBean.setJwtToken(PreferencesUtils.getString(mContext,Constants.JWT_TOKEN_PREFERENCE));
        }

        return userBean;

    }


    /**
     * 清除当前用户数据<br/>
     * 排除welcome标识和did标识
     */
    public void clearCurrentUserData() {

        PreferencesUtils.clearAll(mContext);
        //请求信息
        PreferencesUtils.clearAll(Constants.ACCESS_TOKEN_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.DEVICE_TOKEN_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.JWT_TOKEN_PREFERENCE,mContext);
        //本地用户信息
        PreferencesUtils.clearAll(Constants.USER_PHONE_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_PASSWORD_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_HEADPICTURE_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_NICK_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_PUSHMSG_STATE_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_GESTURE_STATE_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_CREATE_TIME_PREFERENCE,mContext);
        PreferencesUtils.clearAll(Constants.USER_CREATE_TIME_PREFERENCE,mContext);



        //缓存信息
        clearUserInfo();
    }


}
