package com.hz.zdjfu.application.modle.mine.returnedmoneycalendar;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.BackMoneyBean;
import com.hz.zdjfu.application.utils.UiUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class RMCalendarAdapter extends BaseQuickAdapter<BackMoneyBean,BaseViewHolder>{

    private Context mContext;
    public RMCalendarAdapter(Context context) {
        super(R.layout.view_rmcalendar_backmoney_detail_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BackMoneyBean item) {


        if(item!=null){
            helper.setText(R.id.rmcalendar_backmoney_detail_name,item.getProductName()+"");
            helper.setText(R.id.rmcalendar_backmoney_detail_money, UiUtils.formatMoneyToBigDecimal(item.getReturnTotal()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"元");
            helper.setText(R.id.rmcalendar_backmoney_detail_time,item.getBuyTime()+"");
            helper.setText(R.id.rmcalendar_backmoney_detail_interst,"(本"+UiUtils.formatMoneyToBigDecimal(item.getReturnAmt()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"+息"+UiUtils.formatMoneyToBigDecimal(item.getReturnIncome()+"",mContext.getResources().getString(R.string.defalut_money_separator))+")");

        }

    }
}
