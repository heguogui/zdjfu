package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.addbankcard;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;

/**
 * [类功能说明]
 *解绑银行卡契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface AddBankCardContract {


    //view 接口
    interface View extends BaseView<AddBankCardContract.Presenter> {


        void onClickListener();
        //添加成功
        void addBankCardSuccess(boolean state);

        void triggerCountDown();
        void setCodeTips(String otherphone);
        void setSendCodeBtnEnable(boolean flag);
        void focusOnCodeEdit();
        void showPhoneNumInvalid();
        void showCodeInvalid();
        void lookBankCardExplain();


        void showProviceLists(ProviceLists lists);

        void showCityLists(CityLists lists);

        void showBankCardTypeLists(BankTypeLists lists);

        void showBindBankCardSuccess(String url);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //获取验证码
        void getCodeHttpRequest(String phone,String bankno,String otherphone,String cardattribute,String province,String city,String bank_alias);

        //添加银行卡请求
        void addBankCardHttpRequest(String phone,String bankcardnumber,String validcode,String otherphone,String cardattribute,String province,String city,String bank_alias);

        //获取省份
        void getProviceHttpRequest(String phone);

        //获取市
        void getCityHttpRequest(String phone,String provice);

        //获取银行类型
        void getBankCardTypeHttpRequest(String phone);


    }

}
