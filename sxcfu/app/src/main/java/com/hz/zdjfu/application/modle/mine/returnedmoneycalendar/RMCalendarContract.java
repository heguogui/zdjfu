package com.hz.zdjfu.application.modle.mine.returnedmoneycalendar;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.BackMoneyCalendarLists;
import com.hz.zdjfu.application.data.bean.BackMoneyLists;
import com.hz.zdjfu.application.data.bean.BackMoneyMonthBean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface RMCalendarContract {


    //view 接口
    interface View extends BaseView<RMCalendarContract.Presenter> {

        //初始View
        void initViewData();
        //保存数据
        void saveRMCalendarData(String month,BackMoneyLists mBackMoneyCalendarLists);
        //跳转
        void skipActivity(Class<?> mClass);
        //显示头部数据
        void showTopData(BackMoneyLists mBackMoneyLists);

        //显示底部数据
        void showBottomData(String day,BackMoneyLists mBackMoneyLists);

        //显示日历数据
        void showCalendarData(String month,List<BackMoneyMonthBean> mlists);

        void initRecycleView();


    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 月份回款请求数据
         * @param phone
         * @param month
         */
        void rmcalendarHttpRequest(String  phone,String month);


    }

}
