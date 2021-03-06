package com.hz.zdjfu.application.modle.financial;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.ProductBean;


import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/4/4 0004.
 */

public interface ZZFinalcialContract {

    interface View extends BaseView<Presenter> {
        void initRecycleView();
        void initRefreshView();
        //显示数据
        void showZZFinancialData(List<ProductBean> mList, boolean isrefresh);
        //无数据
        void showDateEmptyView(boolean state);

        void completeRefresh();
        void completeLoadMore();
         void refresh();
    }


    interface Presenter extends BasePresenter {

        /**
         * 获取直投产品数据
         * @param isrefresh 是否刷新获取数据
         */
        void financialZZRequest(boolean isrefresh);

    }

}
