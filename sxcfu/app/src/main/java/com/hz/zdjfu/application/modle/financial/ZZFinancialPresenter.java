package com.hz.zdjfu.application.modle.financial;

import android.content.Context;

import com.hz.zdjfu.application.data.bean.FinancialNoticationList;
import com.hz.zdjfu.application.data.bean.FinancialRecordLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;


/**
 * [类功能说明]
 *理财 请求接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class ZZFinancialPresenter implements ZZFinalcialContract.Presenter{

    private ZZFinalcialContract.View mView;
    private int currPage =0;
    private Context mContext;

    public ZZFinancialPresenter(ZZFinalcialContract.View view, Context context){
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
    public void financialZZRequest(boolean isrefresh) {
        if(isrefresh){
            currPage =1;
        }
        RetrofitUtil.createService().financiaProductLists(String.valueOf(currPage)).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<FinancialRecordLists>() {
            @Override
            public void onSuccess(FinancialRecordLists result) {
                if(result!=null){
                    mView.showZZFinancialData(result.getDataList(),isrefresh);
                    currPage++;
                }

            }
            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });
    }
}
