package com.hz.zdjfu.application.modle.ztprogect.investamount;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.modle.discount.CheckCouponType;

/**
 * [类功能说明]
 *直投投资金额界面
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/4/27
 */
public interface InvestAmonutContract {


    //view 接口
    interface View extends BaseView<InvestAmonutContract.Presenter> {

        public void refresh();

        public void investState(String url);

        public  void showZTUserDetail(ZTUserDetailBean mZTUserDetailBean);

        public void showDiscountCoupon(DiscountBean bean);

        void showBalance(String investBalance);

        void goStartInvest();

        void showCouponInform(CheckCouponType mCheckCoupon);
    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 投资
         * @param productId
         * @param investAmt
         * @param couponId
         * @param giftId
         * @param coinStatus
         */
        void investHttpRequest(String productId, String investAmt, String couponId , String giftId, String coinStatus);

        /**
         * 账户余额
         * @param phone
         */
        void  userInform(String phone);

        /**
         * 优惠券
         * @param productId
         * @param investAmt
         */
        void investDiscount(String productId, String investAmt);



    }

}
