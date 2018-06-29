package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager;

import android.content.Context;
import android.text.TextUtils;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.UserBean;
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
 * 银行卡管理网络请求接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class BankCardPresenter implements BankCardContract.Presenter{


    private static final String TAG =BankCardContract.class.getName();
    private BankCardContract.View mView;
    private Context mContext;
    public BankCardPresenter(Context context, BankCardContract.View view){
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
    public void allBankCardHttpRequest(String phone) {

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
//        RetrofitUtil.createService().quickPayBank(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<QuickBankCardList>() {
//            @Override
//            public void onSuccess(QuickBankCardList result) {
//                DialogManager.hideProgressDialog();
//                if(result!=null&&result.getDataList()!=null){
//                    mView.showBankCardNotication(result);
//                    mView.showDateEmptyView(false);
//                }else{
//                    mView.showDateEmptyView(true);
//                }
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                DialogManager.hideProgressDialog();
//                mView.showErrorTips(e.getMessage());
//            }
//        });

      Call<Object> call= RetrofitUtil.createService().quickPayBank(phone,mIP,Constants.ANDROID_SOURCE,mSign);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                DialogManager.hideProgressDialog();
                String result =response.body().toString();
                if(!TextUtils.isEmpty(result)){

                    try{
                        if(result.contains("true")){//成功
                            JSONObject object =new JSONObject(result);
                            JSONObject contentObject = object.getJSONObject("content");
                            BankCardBean  bean=   GsonUtils.GsonToBean(contentObject.getJSONObject("dataList").toString(),BankCardBean.class);
                            if(bean!=null){
                                mView.showBankCardNotication(bean);
                                mView.showDateEmptyView(false);
                            }
                        }else{//失败
                            mView.showDateEmptyView(true);
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
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


    @Override
    public void isCheckAccountBanlance(String phone) {


        if(TextUtils.isEmpty(phone)){
            return;
        }
        String mIP = HttpsUtils.getMobileHostIP();
        if(TextUtils.isEmpty(mIP)){
            ToastUtils.show(mContext,"获取手机IP失败");
            return;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        String mSign =HttpsUtils.getHttpRequestSign(phone,Constants.ANDROID_SOURCE);
        RetrofitUtil.createService().accountBalance(phone,mIP,Constants.ANDROID_SOURCE,mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AccountDataList>() {
            @Override
            public void onSuccess(AccountDataList result) {

                if(result!=null&&!TextUtils.isEmpty(result.getDataList().getBalance())){
                    DialogManager.hideProgressDialog();
                    if(Double.parseDouble(result.getDataList().getBalance())>0){
                        mView.isShowBanlanceNoEmptyDialog(true);
                    }else{
                        mView.isShowBanlanceNoEmptyDialog(false);
                    }
                }

            }
            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage()+"");
            }
        });

    }


    @Override
    public void addBankCard() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){

            String mSign = HttpsUtils.getHttpRequestSignNoIp(mBean.getUserPhone());
            if(TextUtils.isEmpty(mSign)){
                return;
            }

            DialogManager.showProgressDialog(mContext,"加载中...");

            RetrofitUtil.createZTHService().ztAddBankCard().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
                @Override
                public void onSuccess(String result) {
                    DialogManager.hideProgressDialog();
                    if(result!=null&&mView!=null){
                        mView.authNameResult(result);
                    }
                }

                @Override
                public void _onError(Throwable e) {
                    DialogManager.hideProgressDialog();
                    mView.showErrorTips(e.getMessage()+"");
                }
            });

        }


    }


}
