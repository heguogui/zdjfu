package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;

/**
 * [类功能说明]
 *认证开通上海银行账户契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public interface BindBankCardContract {

    //view 接口
    interface View extends BaseView<BindBankCardContract.Presenter> {
        //验证码异常
        void showCodeInvalid();
        //手机号异常
        void showPhoneNumInvalid();
        void triggerCountDown();
        void setCodeTips(String  phone);
        void setSendCodeBtnEnable(boolean flag);
        void focusOnCodeEdit();

        void showBindBankCardSuccess(String url);

        void showProviceLists(ProviceLists lists);

        void showCityLists(CityLists lists);

        void showBankCardTypeLists(BankTypeLists lists);





    }


    //请求接口
    interface Presenter extends BasePresenter {

        //绑定银行卡
        void bindBankCardHttpRequest(String phone,String bankcardnumber,String validcode,String otherphone,String cardattribute,String province,String city,String bank_alias);

        //发送验证码
        void sendCode(String phone,String bankno,String otherphone,String cardattribute,String province,String city,String bank_alias);

        //获取省份
        void getProviceHttpRequest(String phone);

        //获取市
        void getCityHttpRequest(String phone,String provice);

        //获取银行类型
        void getBankCardTypeHttpRequest(String phone);



    }

}
