/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.modle.login.welcome;


import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;

public interface WelcomeContract {

    interface View extends BaseView<Presenter> {

        void jumpRegistActivity();

        void jumpHomeActivity();

        void showViewPager(Integer[] pics);
    }

    interface Presenter extends BasePresenter {
        void loadViews();

        void initViewItem();
    }
}
