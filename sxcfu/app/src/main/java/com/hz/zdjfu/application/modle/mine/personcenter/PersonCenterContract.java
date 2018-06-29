package com.hz.zdjfu.application.modle.mine.personcenter;

import android.os.Bundle;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;

/**
 * [类功能说明]
 *我的页面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface PersonCenterContract {



    //view 接口
    interface View extends BaseView<PersonCenterContract.Presenter> {
        //初始View
        void initView();
        //清除用户信息
        void clearUserData();
        //实名认证
        void nameCertification();
        //判断是否登录
        void isCheckLogin();
        //是否实名认证
        void bankCardManager();
        //用户信息
        void showUserData(ZTUserDetailBean result);

        //显示银行卡
        void showBankCardNotication(BankCardBean mlist);

        //设置交易密码连接
        void showSettingPayPwdH5(String url);

        void showOpenBankAccountDialog();
        //检查是否开通银行账户
        void checkIsOpenBankAccount();

        //当返回数据异常时 有些View不能点击
        void changeViewState(boolean state);

        //退出成功
        void logoutSuccess(boolean state);
    }


    //请求接口
    interface Presenter extends BasePresenter {

        //退出APP请求
        void exitAppHttpRequest();
        //用户详情请求
        void userDetailHttpRequest(String phone);
        //用户银行卡管理请求
        void userBankCard(String phone);


    }

}
