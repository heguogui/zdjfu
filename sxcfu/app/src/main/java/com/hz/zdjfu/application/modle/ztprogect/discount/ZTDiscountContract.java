package com.hz.zdjfu.application.modle.ztprogect.discount;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.DiscountZJZBean;
import com.hz.zdjfu.application.data.bean.ZTUnUserCouponBean;
import com.hz.zdjfu.application.data.bean.ZTUserCouponBean;

import java.util.List;

/**
 * [类功能说明]
 *直投红包卡券契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public interface ZTDiscountContract {

    //view 接口
    interface View extends BaseView<ZTDiscountContract.Presenter> {

        void initViewPager();
        void initRecycleView();
        //显示可用优惠券
        void showLeftCoupon(ZTUserCouponBean mbean);
        //显示不可用优惠券
        void showRightCoupon(ZTUnUserCouponBean mbean);
        //封装可用优惠券
        DiscountBean packageLeftCoupon(ZTUserCouponBean mbean);
        //封装不可用优惠券
        DiscountBean packageRightCoupon(ZTUnUserCouponBean mbean);
        //封装推荐优惠券
        List<DiscountZJZBean> packageRecommendCoupon(List<DiscountZJZBean> mlist);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //所有数量请求
        void allLeftHttpRequest(String product_id,String amount);

        //所有数量请求
        void allRightHttpRequest(String product_id,String amount);

    }

}
