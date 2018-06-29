package com.hz.zdjfu.application.modle.ztprogect.invest;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.base.ZTInvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordRankList;
import com.hz.zdjfu.application.data.bean.ZTRankList;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface ZTInvestRewordContract {


    //view 接口
    interface View extends BaseView<ZTInvestRewordContract.Presenter> {

        //显示数据
        void showInvestRewordDetailData(ZTInvestRewordLists mlists, boolean isRefresh);

        //停止刷新更多
        void completeRefreshAndLoad();

        //排行
        void showInvestRewordRank(ZTRankList mlist);


    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 投资记录请求数据
         * @param productid
         * @param isRefresh
         */
        void investRewordHttpRequest(String productid, boolean isRefresh);


        /**
         * 投资记录排行
         * @param productid
         */
        void investRewordRankHttpRequest(String productid);

    }

}
