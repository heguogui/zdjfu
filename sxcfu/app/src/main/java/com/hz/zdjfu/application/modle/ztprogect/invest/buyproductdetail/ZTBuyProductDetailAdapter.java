package com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.InvestReturnDayBean;
import com.hz.zdjfu.application.data.bean.ZTRefundLists;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.UiUtils;


/**
 * [类功能说明]
 *直投购买产品详情适配器
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/5/30 0030.
 */

public class ZTBuyProductDetailAdapter extends BaseQuickAdapter<ZTRefundLists,BaseViewHolder> {
    private  Context mContext;
    public ZTBuyProductDetailAdapter(Context context) {
        super(R.layout.view_invest_returnway_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ZTRefundLists item) {

        if(item==null){
            return;
        }

        helper.setText(R.id.view_item_return_num,item.getSeqNo()+"");
        helper.setText(R.id.view_item_return_time, item.getReturnDate()+"");

        if(item.getAmount()==0){
            helper.setText(R.id.view_item_return_amount,"利息:"+ UiUtils.formatMoneyToBigDecimal(item.getIncome()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"元");
        }else{
            helper.setText(R.id.view_item_return_amount,"利息:"+ UiUtils.formatMoneyToBigDecimal(item.getIncome()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"元"+"\n"+"本金:"+UiUtils.formatMoneyToBigDecimal(item.getAmount()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"元");
        }

        if(item.getStatus()==-1){
            helper.setText(R.id.view_item_return_state,"待回款");
        }else if(item.getStatus()==1){
            helper.setText(R.id.view_item_return_state,"已回款");
            TextView mText =helper.getView(R.id.view_item_return_state);
            mText.setTextColor(mContext.getResources().getColor(R.color.red4));
        }else if(item.getStatus()==-2){
            helper.setText(R.id.view_item_return_state,"支付失败");
        }else if(item.getStatus()==-3){
            helper.setText(R.id.view_item_return_state,"作废");
        }

    }
}
