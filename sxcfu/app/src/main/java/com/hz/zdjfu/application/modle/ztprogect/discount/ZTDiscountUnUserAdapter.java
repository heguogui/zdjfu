package com.hz.zdjfu.application.modle.ztprogect.discount;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.DiscountZJZBean;
import com.hz.zdjfu.application.utils.AmountUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/23 0023.
 */

public class ZTDiscountUnUserAdapter extends BaseQuickAdapter<DiscountZJZBean,BaseViewHolder>{

    private Context mContext =null;

    public ZTDiscountUnUserAdapter(Context context) {
        super(R.layout.view_zt_discount_unuse_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscountZJZBean item) {


        if(item!=null){

            if(!TextUtils.isEmpty(item.getType())){
                if(item.getType().equals("1")){//红包
                    helper.setText(R.id.zt_discount_discounts_unuse_number_tv, AmountUtils.sqlitDoubleStr(item.getAmount())+"");
                    helper.getView(R.id.zt_discount_discounts_unuse_danwei_tv).setVisibility(View.VISIBLE);
                    //收益天数
                    if(!TextUtils.isEmpty(item.getInvest_dates())&&!TextUtils.isEmpty(item.getInvest_amount())){
                        helper.setText(R.id.zt_discount_unuse_day_tv,"投资≥"+item.getInvest_amount()+"元  "+"收益天数≥"+item.getInvest_dates()+"天");
                    }
                }else if(item.getType().equals("2")){

                    helper.getView(R.id.zt_discount_discounts_unuse_danwei_tv).setVisibility(View.GONE);
                    helper.setText(R.id.zt_discount_discounts_unuse_number_tv, AmountUtils.formatAmount(String.valueOf(item.getAmount()),2)+"%");
                   // helper.setText(R.id.discount_discounts_unuse_number_tv,item.getAmount()+"%");
                    //加息券没有投资限额
                    if(!TextUtils.isEmpty(item.getInvest_dates())){
                        helper.setText(R.id.zt_discount_unuse_day_tv,"收益天数≥"+item.getInvest_dates()+"天");
                    }
                }
            }

            //名称
            if(!TextUtils.isEmpty(item.getName())){
                helper.setText(R.id.zt_discount_unuse_name_tv,item.getName());
            }

            //是否是直投
            TextView type_tv =helper.getView(R.id.zt_discount_unuser_type_tv);
            if(!TextUtils.isEmpty(item.getStrAmount())){
                if(item.getStrAmount().equals("2")){
                    type_tv.setText("直投");
                    type_tv.setVisibility(View.VISIBLE);
                }else if(item.getStrAmount().equals("2")){
                    type_tv.setText("债转");
                    type_tv.setVisibility(View.VISIBLE);
                }else {
                    type_tv.setVisibility(View.GONE);
                }
            }else{
                type_tv.setVisibility(View.GONE);
            }

            //使用限制
            if(!TextUtils.isEmpty(item.getUse_type())){
                if(item.getUse_type().equals(Constants.TYPE_REDPACKET_COUPON_ALL_STATE)){
                    helper.setText(R.id.zt_discount_tag_unuse_tv,"全部");
                }else if(item.getUse_type().equals(Constants.TYPE_REDPACKET_COUPON_NEW_STATE)){
                    helper.setText(R.id.zt_discount_tag_unuse_tv,"新手标");
                }else if(item.getUse_type().equals(Constants.TYPE_REDPACKET_COUPON_OLD_STATE)){
                    helper.setText(R.id.zt_discount_tag_unuse_tv,"非新手标");
                }else{
                    helper.setText(R.id.zt_discount_tag_unuse_tv,"");
                }
            }

            //使用期限
            if(!TextUtils.isEmpty(item.getDates())){
                helper.setText(R.id.zt_discount_unuse_time_tv,item.getDates());
            }

            //是否过期
            if(!TextUtils.isEmpty(item.getOverdue())){
                helper.getView(R.id.zt_discount_out_time_iv).setVisibility(View.VISIBLE);
            }else{
                helper.getView(R.id.zt_discount_out_time_iv).setVisibility(View.GONE);
            }

        }

    }
}
