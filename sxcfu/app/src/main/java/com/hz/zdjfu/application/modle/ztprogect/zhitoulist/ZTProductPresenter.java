package com.hz.zdjfu.application.modle.ztprogect.zhitoulist;

import android.content.Context;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.FinancialBean;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.modle.financial.ZTFinancialContract;
import com.hz.zdjfu.application.widget.dialog.DialogManager;


/**
 * [类功能说明]
 *理财 请求接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class ZTProductPresenter implements ZTProductListContract.Presenter{


    private ZTProductListContract.View mView;
    private int currPage =0;
    private String httpRequstTag = "financeList";
    private Context mContext;
    private boolean isInitData;

    public ZTProductPresenter(ZTProductListContract.View view, Context context){
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
    public void ztProductListRequest(boolean isRefresh) {

        if(isRefresh){
            currPage =1;
        }
        DialogManager.showProgressDialog(mContext,"加载中...");
        RetrofitUtil.createZTService().ztFinancialList(Constants.FINANCIAL_TYPE_ZT,"","",String.valueOf(currPage),"10","1").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<FinancialBean>() {
            @Override
            public void onSuccess(FinancialBean result) {
                DialogManager.hideProgressDialog();
                if(result!=null){
                    mView.showProductListData(result.getPageList(),isRefresh);
                    currPage++;
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
