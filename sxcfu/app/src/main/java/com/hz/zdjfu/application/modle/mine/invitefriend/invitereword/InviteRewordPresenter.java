package com.hz.zdjfu.application.modle.mine.invitefriend.invitereword;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.InviteRewordLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 * 邀请记录接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class InviteRewordPresenter implements InviteRewordContract.Presenter{

    private static final String TAG =InviteRewordPresenter.class.getName();
    private InviteRewordContract.View mView;
    private Context mContext;
    private int mPage =0;

    public InviteRewordPresenter(Context context, InviteRewordContract.View view){
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
    public void allInviteReWordHttpRequest(String phone,boolean refresh) {

        if(refresh){
            mPage =1;
        }

        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.REQUESTSOURCE_ANDROID);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().inviteFriends(phone,mIP,Constants.REQUESTSOURCE_ANDROID,mSign,mPage+"").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<InviteRewordLists>() {
            @Override
            public void onSuccess(InviteRewordLists result) {
                mView.showInviteRewordData(result,refresh);
                mPage++;
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });














    }
}
