package com.hz.zdjfu.application.modle.mine.rechange;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.RechangeDetail;

/**
 * [类功能说明]
 *我的资产契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface RechangeContract {



    //view 接口
    interface View extends BaseView<RechangeContract.Presenter> {

        void showRechangeDetail(RechangeDetail result);

        void rightOnClickViewListener();

        void validCodeSuccess(boolean state);

        void showAccountBalance(String balance);

        void triggerCountDown();

        void setBtnStatus(boolean state);

        void showSHrechangeUrl(String url);
      
    }


    //请求接口
    interface Presenter extends BasePresenter {

        //银行卡数据请求
        void rechangeDetailHttpRequest(String phone);

        void sureSMS(String phone,String valid_code);

        void rechange(String amount);

        void accountBalance(String phone);

    }

}
