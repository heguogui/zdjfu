package com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.InvestDetailBean;
import com.hz.zdjfu.application.data.bean.ZTBuyProductDetailBean;

/**
 * [类功能说明]
 *直投购买的项目详情
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/5/19 0019
 */
public interface ZTBuyProductDetailContract {


    //view 接口
    interface View extends BaseView<ZTBuyProductDetailContract.Presenter> {

        //显示数据
        void showBuyProductDetailData(ZTBuyProductDetailBean ztBuyProductDetailBean);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 投资详情请求数据
         * @param buy_id 购买的产品id
         */
        void buyProductDetailHttpRequest(String buy_id);


    }

}
