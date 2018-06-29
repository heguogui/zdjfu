package com.hz.zdjfu.application.modle.login.regist;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.UserBean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface RegistContract {


    //view 接口
    interface View extends BaseView<RegistContract.Presenter> {

        //初始View
        void initView();
        //保存数据
        void saveRegistData(UserBean userBean);
        //手机号异常
        void showPhoneNumInvalid();
        //验证码异常
        void showCodeInvalid();
        //密码异常
        void showPasswordInvalid();

        //跳转
        void skipActivity();

        void showSmsCodeInvalid();

        void triggerCountDown();

        void setCodeTips(String  phone);

        void registSuccess(boolean state);

        /**
         * 设置发送验证码按钮可用状态
         * @param flag
         */
        void setSendCodeBtnEnable(boolean flag);

        void focusOnCodeEdit();

        void changeLineColor(boolean phone,boolean code,boolean password,boolean invite);

        void showInvestFriends(String phone);


    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 短信验证码请求
         * @param phoneNumber 电话号码
         */
        void codeHttpRequest(String phoneNumber);

        //验证码数据请求

        /**
         *注册请求
         * @param phoneNumber 电话号码
         * @param password 登录密码
         * @param code 短信验证码
         * @param inviter 邀请人
         */
        void registHttpRequest(String phoneNumber,String password,String code,String inviter);


        void noticationInvestFriends(String phone);


    }

}
