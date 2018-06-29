package com.hz.zdjfu.application.modle.mine.invitefriend.invitereword;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.InviteRewordLists;

/**
 * [类功能说明]
 *邀请契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface InviteRewordContract {


    //view 接口
    interface View extends BaseView<InviteRewordContract.Presenter> {

        //保存数据
        void showInviteRewordData(InviteRewordLists mlists, boolean isRefresh);
        //停止刷新更多
        void completeRefreshAndLoad();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        /**
         * 所有邀请记录请求数据
         * @param phone
         */
        void allInviteReWordHttpRequest(String phone,boolean isrefresh);


    }

}
