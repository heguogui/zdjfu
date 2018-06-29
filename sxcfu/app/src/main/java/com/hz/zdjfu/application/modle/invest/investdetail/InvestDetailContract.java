package com.hz.zdjfu.application.modle.invest.investdetail;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.InvestDetailBean;

/**
 * [类功能说明]
 *项目详情
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface InvestDetailContract {


    //view 接口
    interface View extends BaseView<InvestDetailContract.Presenter> {

        //显示数据
        void showInvestDetailData(InvestDetailBean mInvestDetailBean);

        void cancleOrderState(boolean state);

        void resetPayOrder(boolean state);
    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 投资详情请求数据
         * @param productid
         */
        void investDetailHttpRequest(String phone,String productid,String buy_id);


        void resetPayOrder(String phone,String orderid);

        /**
         * 取消订单
         * @param orderId
         */
        void cancleInvestOrder(String orderId);


    }

}
