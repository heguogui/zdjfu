package com.hz.zdjfu.application.modle.mine.personcenter.withdrawdeposit;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.WithDrawBean;
import com.hz.zdjfu.application.data.bean.WithDrawCouponList;
import com.hz.zdjfu.application.data.bean.WithDrawFreeBean;

/**
 * [类功能说明]
 *提现契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface WithDrawDepositContract {



    //view 接口
    interface View extends BaseView<WithDrawDepositContract.Presenter> {
        //初始View
        void initView();
        //显示单笔要求
        void showBeyondDemand();

        void showBankCardDetail(WithDrawBean bean);

        //优惠券
        void showCoupon();

        //提现状态
        void showWhitDrawApplyState(String url);

        //显示用户提现券
        void showUserWithDrawCoupon(WithDrawCouponList result);

        //提现手续费
        void showWithdrawFree(WithDrawFreeBean withDrawFreeBean);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //提现请求
        void withDrawDepositHttpRequest(String phone,String amount,String couponid);
        //使用提现券
        void getUserCoupon(String phone);

        //用户银行卡
        void userBankCard(String phone);

        //提交申请
        void submitWithDrawApply(String phone,String amount,String couponid);

    }

}
