package com.hz.zdjfu.application.modle.party.announcementlist;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.AnnouncementList;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface AnnouncementListsContract {


    //view 接口
    interface View extends BaseView<AnnouncementListsContract.Presenter> {

        //初始View
        void initViewData();
        //保存数据
        void showMessageData(AnnouncementList mlists, boolean isRefresh);
        //跳转
        void skipActivity(Class<?> mClass);
        //停止刷新更多
        void completeRefreshAndLoad();

        void showH5Url(String url);
    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 月份回款请求数据
         */
        void announcementListsHttpRequest(boolean isRefresh);

        /**
         * 根据Id 获取公告路径
         * @param mid
         */
        void getAnnouncementH5Url(String mid);
    }

}
