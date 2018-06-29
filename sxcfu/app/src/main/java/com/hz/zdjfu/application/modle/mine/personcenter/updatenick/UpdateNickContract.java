package com.hz.zdjfu.application.modle.mine.personcenter.updatenick;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;

/**
 * [类功能说明]
 *设置昵称接口契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface UpdateNickContract {



    //view 接口
    interface View extends BaseView<UpdateNickContract.Presenter> {

        void  showCurrentNick();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //修改昵称请求
        void updateNickpHttpRequest(String nick);

    }

}
