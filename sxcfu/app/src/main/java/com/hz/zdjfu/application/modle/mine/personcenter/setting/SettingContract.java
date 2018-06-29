package com.hz.zdjfu.application.modle.mine.personcenter.setting;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.AppVersionBean;

/**
 * [类功能说明]
 *设置页面接口契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface SettingContract {



    //view 接口
    interface View extends BaseView<SettingContract.Presenter> {
        //清除缓存
        void clearCache();
        //获取当前版本信息
        void showVersionDetail();
        //缓存信息
        void showCacheData();
        //推送消息状态
        void showPushMsgState();
        //版本信息
        void showVersionState(AppVersionBean versionBean);

        //提示
        void showVersionMsg();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //用户详情请求
        void checkVersionDetail();

    }

}
