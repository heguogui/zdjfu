package com.hz.zdjfu.application.modle.mine.returnedmoneycalendar;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.BackMoneyCalendarLists;
import com.hz.zdjfu.application.data.bean.BackMoneyLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RMCalendarPresenter implements RMCalendarContract.Presenter{

    private static final String TAG =RMCalendarPresenter.class.getName();
    private RMCalendarContract.View mView;
    private Context mContext;

    public RMCalendarPresenter(Context context, RMCalendarContract.View view){
        this.mContext =context;
        this.mView =view;
        mView.setPresenter(this);
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
    public void rmcalendarHttpRequest(String phone, String month) {

        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(month)){
            return;
        }

        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTHService().ztBackCalendar(month).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<BackMoneyLists>() {
            @Override
            public void onSuccess(BackMoneyLists result) {
                DialogManager.hideProgressDialog();
                if(mView!=null&&result!=null){
                    mView.saveRMCalendarData(month,result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                if(mView!=null){
                    mView.showErrorTips(e.getMessage()+"");
                }
            }
        });





    }


}
