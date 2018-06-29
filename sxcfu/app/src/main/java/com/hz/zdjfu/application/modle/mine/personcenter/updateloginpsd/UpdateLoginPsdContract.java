package com.hz.zdjfu.application.modle.mine.personcenter.updateloginpsd;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;


/**
 * [类功能说明]
 * 修改登录密码契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/26 0026.
 */

public interface UpdateLoginPsdContract {


    //view 接口
    interface View extends BaseView<UpdateLoginPsdContract.Presenter> {

        void   checkStateEt();
        void  showDefaultPhone();
        //手机号异常
        void showPhoneNumInvalid();
        //验证码异常
        void showCodeInvalid();
        //密码异常
        void showPasswordInvalid();

        //图片验证码异常
        void showImgCodeInvalid();

        void showSmsCodeInvalid();

        void triggerCountDown();

        void setCodeTips(String  phone);

        /**
         * 设置发送验证码按钮可用状态
         * @param flag
         */
        void setSendCodeBtnEnable(boolean flag);

        void focusOnCodeEdit();

        void  changeLineColor(boolean phoneline,boolean codeline,boolean passwordline);

        void updataSuccess(boolean state);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //确认修改密码请求
        void sureUpdatePsdHttpRequest(String phone, String code, String newpassword);

        //获取验证码
        void sendCodeHttpRequest(String phone);

    }

}
