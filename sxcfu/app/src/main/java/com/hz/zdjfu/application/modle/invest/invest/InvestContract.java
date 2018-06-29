package com.hz.zdjfu.application.modle.invest.invest;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.DiscountBean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface InvestContract {


    //view 接口
    interface View extends BaseView<InvestContract.Presenter> {

        public void refresh();

        public void showToken(String token);

        public void investState(String url);

        public  void accountBalance(String accountbalance);

        public void showDiscountCoupon(DiscountBean bean);

        public void showCanInvestBalance(String investBalance,boolean state);
    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         *投资请求数据
         * @param phone 用户手机号
         * @param amount 投资金额
         * @param goods_id 投标ID
         * @param gift_money 优惠金额
         * @param trade_no Token
         * @param user_coupon_id 加息券ID
         * @param coin 正经值的数量
         * @param user_gift_id 红包ID
         */
        void investHttpRequest(String phone ,String amount,String goods_id,String gift_money,String trade_no,String user_coupon_id,String coin,String user_gift_id);

        /**
         * Token请求数据
         * @param phone
         */
        void tokenHttpRequest(String phone);

        /**
         * 账户余额
         * @param phone
         */
        void  accountBalance(String phone);

        void investDiscount(String phone,String income_days,String amount,String product_id);

        void canInvestBalance(String goods_id,boolean state);

    }

}
