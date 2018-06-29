package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.content.Context;

/**
 * [类功能说明]
 *认证开通上海银行账户接口请求
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class OpenSHBankAccountPresenter implements OpenSHBankAccountContract.Presenter{

    private Context mContext;
    private OpenSHBankAccountContract.View mView;

    public OpenSHBankAccountPresenter(Context context, OpenSHBankAccountContract.View view){
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
    public void bindNameCertificationHttpRequest(String phone) {

    }
}
