package com.hz.zdjfu.application.modle.party.oldparty;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.PartyBean;

import java.util.List;

/**
 * [类功能说明]
 *活动中心契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface OldPartyContract {


    interface View extends BaseView<OldPartyContract.Presenter> {

        //停止刷新更多
        void completeRefreshAndLoad();

        //初始化RecyclerView
        void initRecyclerView();

        //初始化刷新加载更多页面
        void initRefreshView();

        void showPartyViewData(List<PartyBean> list, boolean isRefresh);


    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 活动请求数据
         */
        void partysHttpRequest(boolean isrefresh);



    }

}
