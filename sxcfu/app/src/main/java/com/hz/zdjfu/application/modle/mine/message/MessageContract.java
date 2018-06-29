package com.hz.zdjfu.application.modle.mine.message;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.MessageBean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface MessageContract {


    //view 接口
    interface View extends BaseView<MessageContract.Presenter> {

        //初始View
        void initViewData();
        //保存数据
        void showMessageData(List<MessageBean> mlists,boolean isRefresh);
        //跳转
        void skipActivity(Class<?> mClass);
        //停止刷新更多
        void completeRefreshAndLoad();

        void readAllMessageState(boolean state);

        void readAllMessage();

        void refreshMessage();


    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 月份回款请求数据
         * @param phone
         */
        void messageHttpRequest(String phone,boolean isRefresh);

        //标记已读
        void readAllMessage(String phone);




    }

}
