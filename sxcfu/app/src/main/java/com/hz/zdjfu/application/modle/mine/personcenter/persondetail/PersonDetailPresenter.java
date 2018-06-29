package com.hz.zdjfu.application.modle.mine.personcenter.persondetail;

import android.content.Context;

/**
 * [类功能说明]
 * 个人资料接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class PersonDetailPresenter implements PersonDetailContract.Presenter{


    private static final String TAG =PersonDetailPresenter.class.getName();
    private PersonDetailContract.View mView;
    private Context mContext;
    public PersonDetailPresenter(Context context, PersonDetailContract.View view){
        this.mContext =context;
        this.mView =view;
        this.mView.setPresenter(this);
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
    public void pullHeadPicture(String url) {

        mView.updateHeadPictureSucccess("");

    }
}
