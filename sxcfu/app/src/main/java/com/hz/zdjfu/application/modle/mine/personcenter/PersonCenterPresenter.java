package com.hz.zdjfu.application.modle.mine.personcenter;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.LogoutBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.json.GsonUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * [类功能说明]
 * 登录接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class PersonCenterPresenter implements PersonCenterContract.Presenter{


    private static final String TAG =PersonCenterPresenter.class.getName();
    private PersonCenterContract.View mView;
    private Context mContext;
    public PersonCenterPresenter(Context context, PersonCenterContract.View view){
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
    public void exitAppHttpRequest() {


        DialogManager.showProgressDialog(mContext,"退出中...");
        RetrofitUtil.createZTHService().ztExitApp().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
            @Override
            public void onSuccess(String result) {
                DialogManager.hideProgressDialog();
                mView.logoutSuccess(true);
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
            }
        });

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

                mView.showUserData(result);
            }

            @Override
            public void _onError(Throwable e) {

            }
        });

    }

    @Override
    public void userBankCard(String phone) {
        if(TextUtils.isEmpty(phone)){
            return;
        }

        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(phone, Constants.ANDROID_SOURCE);
        if(TextUtils.isEmpty(mSign)){
            return;
        }


        DialogManager.showProgressDialog(mContext,"加载中...");
        Call<Object> call= RetrofitUtil.createService().quickPayBank(phone,mIP,Constants.ANDROID_SOURCE,mSign);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                DialogManager.hideProgressDialog();
                if(response!=null){
                    String result =response.body().toString();
                    if(!TextUtils.isEmpty(result)){

                        try{
                            if(result.contains("true")){//成功
                                JSONObject object =new JSONObject(result);
                                JSONObject contentObject = object.getJSONObject("content");
                                BankCardBean  bean=   GsonUtils.GsonToBean(contentObject.getJSONObject("dataList").toString(),BankCardBean.class);
                                if(bean!=null&&mView!=null){
                                    mView.showBankCardNotication(bean);
                                }
                            }else{//失败
                                if(mView!=null){
                                    mView.showDateEmptyView(true);
                                }
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(t.getMessage());
            }
        });

    }


}
