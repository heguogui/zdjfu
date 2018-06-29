/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.modle.login.welcome;


import com.hz.zdjfu.application.R;

public class WelcomePresenter implements WelcomeContract.Presenter {


    private final WelcomeContract.View mWelcomeView;

    WelcomePresenter(WelcomeContract.View welcomeView) {
        mWelcomeView = welcomeView;
        mWelcomeView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadViews() {

        //最后一个布局需要带button
        Integer[] pics = {R.layout.guide_view1,
                R.layout.guide_view2, R.layout.guide_view3,R.layout.guide_view4};
        mWelcomeView.showViewPager(pics);


    }

    @Override
    public void initViewItem() {

    }

}
