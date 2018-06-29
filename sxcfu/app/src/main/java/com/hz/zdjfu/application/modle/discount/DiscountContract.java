package com.hz.zdjfu.application.modle.discount;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.DiscountBean;

/**
 * [类功能说明]
 *红包卡券契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public interface DiscountContract {

    //view 接口
    interface View extends BaseView<DiscountContract.Presenter> {

        void initViewPager();
        void initRecycleView();
        void showAllDiscountCoupon(DiscountBean mbean);


    }


    //请求接口
    interface Presenter extends BasePresenter {

        //所有数量请求
        void allCountsHttpRequest(String phone, String income_days, String amount,String product_id);

        //所有数量请求
        void allRightHttpRequest(String phone, String income_days, String amount,String product_id);

    }

}
