package com.hz.zdjfu.application.modle.financial;

import android.content.Context;

import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.FinancialMarqueeBean;
import com.hz.zdjfu.application.data.bean.FinancialNoticationList;
import com.hz.zdjfu.application.data.bean.FinancialRecordLists;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;

import java.util.List;


/**
 * [类功能说明]
 *理财 请求接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class FinancialPresenter implements FinancialContract.Presenter{


    private FinancialContract.View mView;
    private int currPage =0;
    private String httpRequstTag = "financeList";
    private Context mContext;
    private boolean isInitData;

    public FinancialPresenter(FinancialContract.View view,Context context){
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
    public void financialMarquee() {


            RetrofitUtil.createZTService().ztRoundFind().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<List<FinancialMarqueeBean>>() {
                @Override
                public void onSuccess(List<FinancialMarqueeBean> result) {

                    if(result!=null){
                        mView.showFinancialMarquee(result);
                    }
                }

                @Override
                public void _onError(Throwable e) {

                    mView.showErrorTips(e.getMessage());
                }
            });







    }
}
