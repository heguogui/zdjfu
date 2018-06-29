package com.hz.zdjfu.application.modle.rechangecenter.rechangedetail;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.RechangeDetailBean;
import com.hz.zdjfu.application.data.bean.RechangeDetailList;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface RechangeDetailContract {


    //view 接口
    interface View extends BaseView<RechangeDetailContract.Presenter> {

        //初始View
        void initViewData();

        void initRecycleView();

        void initRefreshView();

        //保存数据
        void showRechangeDetailData(List<RechangeDetailBean> mlists, boolean isRefresh);
        //停止刷新更多
        void completeRefreshAndLoad();

    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 兑换请求数据
         * @param phone
         */
        void rechangeDetailHttpRequest(String phone, boolean isRefresh);


    }

}
