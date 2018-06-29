package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.RedPacketLists;
import com.hz.zdjfu.application.data.bean.RedpacketBean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public interface RedpacketContract {

    //view 接口
    interface View extends BaseView<RedpacketContract.Presenter> {

        void initRefreshView();

        void initRecycleView();
        void completeRefresh();
        void completeLoadMore();
        void showRedPacketData(List<RedpacketBean> lists, boolean isrefresh);
        void setAllTotalNum(int num);
        void setEnableUsedNum(int num);
        void showCoinNum(String num);

    }

    //请求接口
    interface Presenter extends BasePresenter {

        //红包请求
        void redpacketHttpRequest(String type,boolean isrefresh);

    }

}
