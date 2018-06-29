package com.hz.zdjfu.application.modle.rechangecenter;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.RechangeCenterBean;
import com.hz.zdjfu.application.data.bean.RechangeZJZLists;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public interface RechangeCenterContract {


    //view 接口
    interface View extends BaseView<RechangeCenterContract.Presenter> {

        void showData(RechangeZJZLists mlists);

        void  rechangeState(String balance);

        void isSureRechangeDialog(RechangeCenterBean num);

        void showsCoinStreamNumber(String num);

    }

    //请求接口
    interface Presenter extends BasePresenter {

        //兑换中心请求
        void rechangeCenterHttpRequest();

        //兑换请求
        void rechangeHttpRequest(String goodsid);


    }
}
