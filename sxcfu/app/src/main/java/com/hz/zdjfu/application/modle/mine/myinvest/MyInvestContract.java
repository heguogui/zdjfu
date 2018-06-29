package com.hz.zdjfu.application.modle.mine.myinvest;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.MyInvestList;
import com.hz.zdjfu.application.data.bean.MyInvestLists;

/**
 * [类功能说明]
 *我的投资契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public interface MyInvestContract {

    interface View extends BaseView<MyInvestContract.Presenter> {

        void initData();
        void initViewPageData();
        void initRefreshView();
        void setEmptyData();
        void initRadioButton();
        void initImageView();
        //显示数据
        void showMyInvestListsData(MyInvestList mList);

        //无数据
        void showDateEmptyView(boolean state);

        void getData(int type,int page);

        void changeTextViewColor(boolean one,boolean two,boolean three);

    }

    interface Presenter extends BasePresenter {

        /**
         * 获取数据
         * @param type 类型
         * @param page 页数
         */
        void getMyInvestHttpRequest(String phone,int type,int page);


    }

}
