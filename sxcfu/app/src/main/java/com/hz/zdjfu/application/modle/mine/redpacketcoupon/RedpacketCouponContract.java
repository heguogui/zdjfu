package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;

/**
 * [类功能说明]
 *红包卡券契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public interface RedpacketCouponContract {

    //view 接口
    interface View extends BaseView<RedpacketCouponContract.Presenter> {


        void initViewPager();
        void initImageView();
        void showAllZJZNum(String num);

    }


    //请求接口
    interface Presenter extends BasePresenter {



    }

}
