package com.hz.zdjfu.application.modle.mine.personcenter.updatenick;

import android.content.Context;

/**
 * [类功能说明]
 * 登录接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class UpdateNickPresenter implements UpdateNickContract.Presenter{


    private static final String TAG =UpdateNickPresenter.class.getName();
    private UpdateNickContract.View mView;
    private Context mContext;
    public UpdateNickPresenter(Context context, UpdateNickContract.View view){
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
    public void updateNickpHttpRequest(String nick) {

    }
}
