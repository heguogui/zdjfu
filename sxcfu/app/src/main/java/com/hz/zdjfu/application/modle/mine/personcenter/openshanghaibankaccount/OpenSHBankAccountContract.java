package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.os.Bundle;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;

/**
 * [类功能说明]
 *认证开通上海银行账户契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public interface OpenSHBankAccountContract {

    //view 接口
    interface View extends BaseView<OpenSHBankAccountContract.Presenter> {

        void changeViewState(boolean mOne, boolean mTwo, boolean mThree);
        void switchFragment(int flag, Bundle bundle);
        void leftBtnOnClickListener();
        void showBackNoticationDialog();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //实名认证请求
        void bindNameCertificationHttpRequest(String phone);

    }

}
