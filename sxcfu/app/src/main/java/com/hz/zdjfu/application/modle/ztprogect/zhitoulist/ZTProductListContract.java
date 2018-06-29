package com.hz.zdjfu.application.modle.ztprogect.zhitoulist;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.ZTProductBean;

import java.util.List;

/**
 * [类功能说明]
 *直投接口
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/4/26 0026.
 */

public interface ZTProductListContract {

    interface View extends BaseView<ZTProductListContract.Presenter> {
        void initRecycleView();
        void initRefreshView();
        //显示数据
        void showProductListData(List<ZTProductBean> mList, boolean isRefresh);
        void completeRefresh();
        void completeLoadMore();
        void refresh();
    }


    interface Presenter extends BasePresenter {

        /**
         * 获取直投产品数据
         * @param isRefresh 是否刷新获取数据
         */
        void ztProductListRequest(boolean isRefresh);
    }

}
