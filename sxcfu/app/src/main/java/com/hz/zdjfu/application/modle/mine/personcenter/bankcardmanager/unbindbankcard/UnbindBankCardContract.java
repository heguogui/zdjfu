package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.unbindbankcard;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;

/**
 * [类功能说明]
 *解绑银行卡契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface UnbindBankCardContract {


    //view 接口
    interface View extends BaseView<UnbindBankCardContract.Presenter> {


        void onClickListener();

        void triggerCountDown();

        void setCodeTips(boolean unRegisted);

        //解绑成功
        void unbindBankCardSuccess(boolean state);

        /**
         * 设置发送验证码按钮可用状态
         * @param flag
         */
        void setSendCodeBtnEnable(boolean flag);

        void focusOnCodeEdit();

        //手机号异常
        void showPhoneNumInvalid();
        //验证码异常
        void showCodeInvalid();

        void showTicket(String ticket);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 获取验证码
         * @param otherphone 银行预留手机号
         */
        void getCodeHttpRequest(String otherphone);

        //解绑银行卡请求
        void unbindBankCardHttpRequest(String phone,String code);

    }

}
