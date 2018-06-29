package com.hz.zdjfu.application.modle.login;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.LoginBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.ResponseBodyUtil;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.json.GsonUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * [类功能说明]
 * 登录接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class LoginPresenter implements LoginContract.Presenter{


    private static final String TAG =LoginPresenter.class.getName();
    private LoginContract.View mView;
    private Context mContext;
    public LoginPresenter(Context context,LoginContract.View view){
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
    public void loginHttpRequest(String phone, String password) {

        DialogManager.showProgressDialog(mContext, "登录中...");
        RetrofitUtil.createZTService().ztlogin(phone,password).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<LoginBean>() {
            @Override
            public void onSuccess(LoginBean result) {
                DialogManager.hideProgressDialog();
                if(result!=null&&!TextUtils.isEmpty(result.getAccessToken())&&!TextUtils.isEmpty(result.getJwtToken())){
                    ToastUtils.show(mContext,"登录成功");
                    UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                    //不是上一个用户
                    if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())&&!mBean.getUserPhone().equals(phone)){
                        mBean= new UserBean();
                        mBean.setNameCertification(true);
                    }
                    mBean.setLogin(true);
                    mBean.setUserPhone(phone);
                    mBean.setUserPassord(password);
                    mBean.setAccessToken(result.getAccessToken());
                    mBean.setJwtToken(result.getJwtToken());
                    UserInfoManager.getInstance().saveCurrentUserInfo(mBean);
                    mView.loginSuccess(true);

                }else{
                    mView.loginThreeNumberError(true);
                }

            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });


    }




}
