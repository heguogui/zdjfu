package com.hz.zdjfu.application.modle.party.announcementlist;

import android.content.Context;

import com.hz.zdjfu.application.data.bean.AnnouncementList;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class AnnouncementListPresenter implements AnnouncementListsContract.Presenter{

    private static final String TAG =AnnouncementListPresenter.class.getName();
    private AnnouncementListsContract.View mView;
    private Context mContext;
    private int mPage =1;


    public AnnouncementListPresenter(Context context, AnnouncementListsContract.View view){
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
    public void announcementListsHttpRequest(boolean isRefresh) {
        if(isRefresh){
            mPage =1;
        }
        RetrofitUtil.createService().announcementLists(mPage+"").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AnnouncementList>() {
            @Override
            public void onSuccess(AnnouncementList result) {

                if(result!=null){
                    mView.showMessageData(result,isRefresh);
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
    public void getAnnouncementH5Url(String mid) {



    }
}
