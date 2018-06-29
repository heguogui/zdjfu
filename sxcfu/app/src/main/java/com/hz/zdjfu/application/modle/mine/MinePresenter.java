package com.hz.zdjfu.application.modle.mine;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.MyIndexDataBean;
import com.hz.zdjfu.application.data.bean.MyPageBean;
import com.hz.zdjfu.application.data.bean.UrlBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.ZTUnUserCouponBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;

/**
 * [类功能说明]
 * 登录接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class MinePresenter implements MineContract.Presenter{


    private static final String TAG =MinePresenter.class.getName();
    private MineContract.View mView;
    private Context mContext;
    public MinePresenter(Context context, MineContract.View view){
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
    public void userDetailHttpRequest(String phone) {


        if(TextUtils.isEmpty(phone)){
            return;
        }

        RetrofitUtil.createZTHService().ztUserInform(phone).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTUserDetailBean>(){

            @Override
            public void onSuccess(ZTUserDetailBean result) {

                if(result==null){
                    return;
                }

                UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                mBean.setCreateTime(result.getCreateTime()+"");
                UserInfoManager.getInstance().saveCurrentUserInfo(mBean);
                mView.showUserData(result);
            }

            @Override
            public void _onError(Throwable e) {

            }
        });


    }


    @Override
    public void myDataHttpRequest(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }
        RetrofitUtil.createZTHService().ztMyPage().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<MyPageBean>() {
            @Override
            public void onSuccess(MyPageBean result) {
                if((mView!=null&&result!=null)){
                    mView.showMyIndextData(result);
                }
            }

            @Override
            public void _onError(Throwable e) {

            }
        });

    }

    @Override
    public void duiba() {
        RetrofitUtil.createZTHService().duiba().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<UrlBean>() {
            @Override
            public void onSuccess(UrlBean result) {
                if(result!=null&&!TextUtils.isEmpty(result.getUrl())){
                    mView.showDuiBaUrl(result.getUrl());
                }
            }
            @Override
            public void _onError(Throwable e) {

            }
        });
    }
}
