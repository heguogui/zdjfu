package com.hz.zdjfu.application.modle.mine.message;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MessageLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class MessagePresenter implements MessageContract.Presenter{

    private static final String TAG =MessagePresenter.class.getName();
    private MessageContract.View mView;
    private Context mContext;
    private int mPage =1;


    public MessagePresenter(Context context, MessageContract.View view){
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
    public void messageHttpRequest(String phone ,boolean reFresh) {


        if(reFresh){
            mPage =1;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,Constants.REQUESTSOURCE_ANDROID);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().messageLists(phone,mIP,Constants.REQUESTSOURCE_ANDROID,mSign,mPage+"").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<MessageLists>() {
            @Override
            public void onSuccess(MessageLists result) {

                if(result!=null){
                    mView.showMessageData(result.getDataList(),reFresh);
                    mPage++;
                }

            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void readAllMessage(String phone) {

       if(TextUtils.isEmpty(phone)){
          return;
       }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone,Constants.REQUESTSOURCE_ANDROID);
        if(TextUtils.isEmpty(mSign)){
            return;
        }

        RetrofitUtil.createService().messageReaded(phone,mIP,Constants.REQUESTSOURCE_ANDROID,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                mView.readAllMessageState(true);
            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });



    }
}
