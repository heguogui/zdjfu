package com.hz.zdjfu.application.modle.opendeposit;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.ZTSHBean;
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

/**
 * [类功能说明]
 *2.0开通银行存管
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/1/17 0017.
 */

public class OpenDepositPresenter implements OpenDepositContract.Presenter{

    private Context mContext;
    private OpenDepositContract.View mView;

    public OpenDepositPresenter(Context context,OpenDepositContract.View view){
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
    public void getUserAuthNameCard(String real_name, String idcard_no) {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){

            String mSign = HttpsUtils.getHttpRequestSignNoIp(mBean.getUserPhone());
            if(TextUtils.isEmpty(mSign)){
                return;
            }

            DialogManager.showProgressDialog(mContext,"认证中...");
            RetrofitUtil.createZTHService().ztAuthNameAndBindCard(real_name,idcard_no).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
                @Override
                public void onSuccess(String result) {
                    DialogManager.hideProgressDialog();
                    if(mView!=null&&result!=null&&!TextUtils.isEmpty(result)){
                        mView.authNameResult(result);
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

    @Override
    public void isCheckUserAuthNameCardIdState() {

        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){

            String mIP = HttpsUtils.getMobileHostIP();
            if(TextUtils.isEmpty(mIP)){
                ToastUtils.show(mContext,"获取手机IP失败");
                return;
            }

            String mSign = HttpsUtils.getHttpRequestSign(mBean.getUserPhone(),Constants.ANDROID_SOURCE);
            if(TextUtils.isEmpty(mSign)){
                return;
            }

            RetrofitUtil.createZTHService().ztgetUserInform().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<ZTUserDetailBean>() {
                @Override
                public void onSuccess(ZTUserDetailBean result) {
                    if(result!=null){
                        mView.showAuthDetail(result);
                    }
                }

                @Override
                public void _onError(Throwable e) {
                    if(mView!=null){
                        mView.showErrorTips(e.getMessage()+"");
                    }
                }
            });



        }


    }


}
