package com.hz.zdjfu.application.modle.login.regist;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.TokenBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

/**
 * [类功能说明]
 * 注册接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RegistPresenter implements RegistContract.Presenter{

    private static final String TAG =RegistPresenter.class.getName();
    private RegistContract.View mView;
    private Context mContext;
    private int channel;
    public RegistPresenter(Context context, RegistContract.View view){
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
    public void codeHttpRequest(String phoneNumber) {

        mView.setSendCodeBtnEnable(false);

        RetrofitUtil.createZTService().ztSend(phoneNumber,"1").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<Object>() {
            @Override
            public void onSuccess(Object result) {
                if(mView!=null){
                    mView.setSendCodeBtnEnable(true);
                    mView.setCodeTips(phoneNumber);
                    mView.triggerCountDown();
                    mView.focusOnCodeEdit();
                }
            }

            @Override
            public void _onError(Throwable e) {
                if(mView!=null){
                    mView.setSendCodeBtnEnable(true);
                    if(e.getMessage().contains("mobile")){
                        mView.showErrorTips("手机号码有误");
                    }else{
                        mView.showErrorTips(e.getMessage());
                    }
                }
            }
        });


    }

    @Override
    public void registHttpRequest(String phoneNumber, String password, String code, String inviter) {


        String inviter_phone ="";
        String inviter_code ="";
        if(!TextUtils.isEmpty(inviter)){
            if(inviter.length()==11){//为手机号
                inviter_phone=inviter;
            }else{
                inviter_code=inviter;
            }
        }

        String umeng_channel = AppUtils.metaData(mContext,"UMENG_CHANNEL");
        final int registChannel = getRegistChannel(umeng_channel);

        DialogManager.showProgressDialog(mContext,"注册中...");
        RetrofitUtil.createZTService().ztRegister(phoneNumber,code,password,"1",Constants.ANDROID_SOURCE,inviter_code,inviter_phone,registChannel+"").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<TokenBean>() {
            @Override
            public void onSuccess(TokenBean result) {
                DialogManager.hideProgressDialog();
                if(result!=null){
                    ToastUtils.show(mContext,"注册成功");
                    UserBean mBean = new UserBean();
                    mBean.setLogin(true);
                    mBean.setUserPhone(phoneNumber);
                    mBean.setUserPassord(password);
                    mBean.setNameCertification(false);
                    mBean.setAccessToken(result.getAccessToken());
                    mBean.setJwtToken(result.getJwtToken());
                    UserInfoManager.getInstance().saveCurrentUserInfo(mBean);
                    PreferencesUtils.putBoolean(mContext, Constants.USER_NAME_VALID_PREFERENCE,false);
                    mView.registSuccess(true);
                }else{
                    ToastUtils.show(mContext,"注册失败");
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void noticationInvestFriends(String phone) {

        if(TextUtils.isEmpty(phone)){
            return;
        }

        RetrofitUtil.createService().inviteWeiXinState(phone,Constants.ANDROID_SOURCE).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG,"邀请好友计数成功!");
            }

            @Override
            public void _onError(Throwable e) {
                Log.i(TAG,"邀请好友计数失败!");
            }
        });

    }


    private int getRegistChannel(String registChannel) {
        switch (registChannel) {
            case "wandoujia":
                channel = 1;
                break;
            case "c360":
                channel = 2;
                break;
            case "tencentyyb":
                channel = 3;
                break;
            case "baidu":
                channel = 4;
                break;
            case "baidu91":
                channel = 5;
                break;
            case "baiduandroid":
                channel = 6;
                break;
            case "zdjfu":
                channel = 7;
                break;
            case "huawei":
                channel = 8;
                break;
            case "xiaomi":
                channel = 9;
                break;
            case "oppo":
                channel = 10;
                break;
            case "vivo":
                channel = 11;
                break;
            case "ppzhushou":
                channel = 12;
                break;
            case "meizu":
                channel = 13;
                break;
            case "lenovo":
                channel = 14;
                break;
            case "sougou":
                channel = 15;
                break;
            case "stk1":
                channel = 16;
                break;
            case "stk2":
                channel = 17;
                break;
            case "diank":
                channel = 18;
                break;
            case "diankai2":
                channel = 19;
                break;
            case "mmy_zd_1":
                channel = 20;
                break;
            case "quanm_zdjf":
                channel = 21;
                break;
            case "chenku":
                channel = 22;
                break;
            case "zhongyi_zd":
                channel = 23;
                break;
            case "baofeng":
                channel = 24;
                break;
            case "zdjfu360":
                channel = 25;
                break;
            case "toutiao":
                channel = 26;
                break;
            case "mshk":
                channel = 53;
                break;
            case "feipao":
                channel = 54;
                break;
            case "qianke":
                channel = 55;
                break;
            case "duobei":
                channel = 56;
                break;
            case "lebai":
                channel = 57;
                break;
            case "zhongyi":
                channel = 58;
                break;
            case "aifengwo":
                channel = 59;
                break;
            case "zhangzuanbao":
                channel = 60;
                break;
            case "szcb":
                channel = 61;
                break;
            case "tuia":
                channel = 62;
                break;
            default:
                channel = 0;
                break;

        }
        return channel;
    }


}
