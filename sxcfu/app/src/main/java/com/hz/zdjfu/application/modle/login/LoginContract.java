package com.hz.zdjfu.application.modle.login;

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
public interface LoginContract {



    //view 接口
    interface View extends BaseView<LoginContract.Presenter> {

        //初始View
        void initView();
        void saveLoginData(UserBean userBean);
        //手机号异常
        void showPhoneNumInvalid();
        //密码异常
        void showPasswordNumInvalid();
        //跳转
        void skipActivity();
        //登录成功
        void loginSuccess(boolean state);

        void changeLineColor(boolean phone,boolean code,boolean password);
        //登录三次有误
        void loginThreeNumberError(boolean state);
    }


    //请求接口
    interface Presenter extends BasePresenter {

        //登录数据请求
        void loginHttpRequest(String phone,String password);

    }

}
