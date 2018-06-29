package com.hz.zdjfu.application.modle.mine.myinvest;

import android.content.Context;
import android.text.TextUtils;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MyInvestList;
import com.hz.zdjfu.application.data.bean.MyInvestLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;


/**
 * [类功能说明]
 *我的投资 请求接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class MyInvestPresenter implements MyInvestContract.Presenter{


    private MyInvestContract.View mView;
    private Context mContext;

    public MyInvestPresenter(MyInvestContract.View view, Context context){
        this.mView =view;
        mView.setPresenter(this);
        this.mContext =context;
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
    public void getMyInvestHttpRequest(String phone,int type,int page) {


        if(TextUtils.isEmpty(phone)){
            return;
        }

        DialogManager.showProgressDialog(mContext,"加载中...");

        RetrofitUtil.createZTHService().ztMyAllInvest(type+"",page+"",10+"").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<MyInvestList>() {
            @Override
            public void onSuccess(MyInvestList result) {
                DialogManager.hideProgressDialog();
                if(mView!=null&&result!=null){
                    mView.showMyInvestListsData(result);
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
