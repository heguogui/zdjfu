package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BankCardBean;

/**
 * [类功能说明]
 *银行卡管理契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface BankCardContract {



    //view 接口
    interface View extends BaseView<BankCardContract.Presenter> {

        //显示银行卡
        void showBankCardNotication(BankCardBean mlist);
        //账户余额是否大于0弹框
        void isShowBanlanceNoEmptyDialog(boolean isShow);

        void refreshData();

        //认证返回的结果
        void authNameResult(String url);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //所有银行卡请求
        void allBankCardHttpRequest(String phone);

        //检查账户余额
        void isCheckAccountBanlance(String phone);

        /**
         * 实名认证
         */
        void addBankCard();
    }

}
