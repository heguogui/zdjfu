package com.hz.zdjfu.application.modle.mine.myasset;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.MyAssetsBean;

/**
 * [类功能说明]
 *我的资产契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface MyAssetContract {



    //view 接口
    interface View extends BaseView<MyAssetContract.Presenter> {

        //初始View
        void initPieChartView();
        //分成几份
        void showPieData(MyAssetsBean result);
        void  initPiechartView();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //登录数据请求
        void allAssetHttpRequest();

    }

}
