package com.hz.zdjfu.application.modle.mine.transationdetail;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.TransationDetailBean;
import com.hz.zdjfu.application.data.bean.TransationDetailList;

import java.util.List;

/**
 * [类功能说明]
 *交易明细契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface TransationdetailContract {



    //view 接口
    interface View extends BaseView<TransationdetailContract.Presenter> {

        //初始View
        void initView();
        void initViewPageData();
        void initRadioButton();
        void initImageView();
        void setEmptyData();
        void getData(int type,int page);

        void showTransationDetailListsData(List<TransationDetailBean> mLists);

        void changeTextColor(boolean one,boolean two,boolean three,boolean four,boolean five);

        void changeData(List<TransationDetailBean> mLists);



    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 数据请求
         * @param type 类型
         * @param page 页数
         */
        void transationDetailHttpRequest(String phone,int type,int page);

    }

}
