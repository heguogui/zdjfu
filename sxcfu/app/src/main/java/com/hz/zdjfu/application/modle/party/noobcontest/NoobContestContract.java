package com.hz.zdjfu.application.modle.party.noobcontest;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.NoobContestBean;

import java.util.List;

/**
 * [类功能说明]
 *活动中心契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface NoobContestContract {


    interface View extends BaseView<NoobContestContract.Presenter> {

        //初始化RecyclerView
        void initRecyclerView();

        void showNoobContestData(List<NoobContestBean> list);
        //完成结果
        void showCompleteResult(boolean state);
        //客服帮助
        void onClickTopRightView();
    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 新手赢奖请求数据
         */
        void noobContestHttpRequest();

        /**
         * 完成请求数据
         */
        void completeHttpRequest(String mid,String phone);



    }

}
