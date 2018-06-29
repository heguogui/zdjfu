package com.hz.zdjfu.application.modle.financial;

import android.content.Context;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.FinancialBean;
import com.hz.zdjfu.application.data.bean.FinancialNoticationList;
import com.hz.zdjfu.application.data.bean.FinancialRecordLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;


/**
 * [类功能说明]
 *理财 请求接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class ZTFinancialPresenter implements ZTFinancialContract.Presenter{


    private ZTFinancialContract.View mView;
    private int currPage =0;
    private String httpRequstTag = "financeList";
    private Context mContext;
    private boolean isInitData;

    public ZTFinancialPresenter(ZTFinancialContract.View view, Context context){
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
    public void financialZTRequest(String phone,boolean isrefresh) {

        if(isrefresh){
            currPage =1;
        }
        RetrofitUtil.createZTService().ztFinancialList(Constants.FINANCIAL_TYPE_ZT,phone,"",String.valueOf(currPage),"10","0").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<FinancialBean>() {
            @Override
            public void onSuccess(FinancialBean result) {
                if(result!=null){
                    mView.setTotalCount(result.getTotalCount());
                    mView.showZTFinancialData(result.getPageList(),isrefresh);
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
