package com.hz.zdjfu.application.modle.opendeposit;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;

/**
 * [类功能说明]
 *2.0开通存管
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/1/17 0017.
 */

public interface OpenDepositContract {


    //view 接口
    interface View extends BaseView<OpenDepositContract.Presenter> {

        void leftBtnOnClickListener();
        void showBackNoticationDialog();

        void changeBtnState();

        //认证返回的结果
        void authNameResult(String url);

        void showAuthDetail(ZTUserDetailBean mZTUserDetailBean);

    }


    //请求接口
    interface Presenter extends BasePresenter {


        /**
         * 实名认证
         * @param real_name
         * @param idcard_no
         */
        void getUserAuthNameCard(String real_name,String idcard_no);

        /**
         * 检查用户是否认证
         */
        void isCheckUserAuthNameCardIdState();


    }



}
