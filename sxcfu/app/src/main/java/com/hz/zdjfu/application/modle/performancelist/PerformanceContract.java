package com.hz.zdjfu.application.modle.performancelist;
import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.ProductBean;

import java.util.List;

/**
 * [类功能说明]
 *理财接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public interface PerformanceContract {



    interface View extends BaseView<Presenter> {


        void initData();
        void initRecycleView();
        void initRefreshView();
        void setEmptyData();
        //显示数据
        void showFinancialListsData(List<ProductBean> mList, boolean isrefresh);

        void onClickListener();

        //无数据
        void showDateEmptyView(boolean state);

        //网络异常
        void networkIsConnected();

        void completeRefresh();
        void completeLoadMore();



    }

    interface Presenter extends BasePresenter {

        /**
         * 获取履约列表
         * @param isrefresh 是否刷新获取数据
         */
        void getPerformanceListData(boolean isrefresh);


    }

}
