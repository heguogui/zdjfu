package com.hz.zdjfu.application.modle.rechangecenter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.RechangeCenterBean;

/**
 * [类功能说明]
 *兑换中心适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RechangeCenterAdapter extends BaseQuickAdapter<RechangeCenterBean,BaseViewHolder> {

    private Context mContext;
    public RechangeCenterAdapter(Context context) {
        super(R.layout.view_rechangecenter_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RechangeCenterBean mBean) {

        RelativeLayout redpacket_rl =helper.getView(R.id.layout_redpacket_rl);
        RelativeLayout withdrow_rl =helper.getView(R.id.layout_withdraw_rl);
        RelativeLayout addinterest_rl =helper.getView(R.id.layout_addinterest_rl);
        Button rechange_btn =helper.getView(R.id.layout_sumbit_btn);
        TextView withdrow_content =helper.getView(R.id.text_sxb_time_info);
        TextView addinterest_amount =helper.getView(R.id.layout_addinterest_amount_tv);
        TextView redpacket_amount =helper.getView(R.id.layout_redpacket_amount_tv);
        TextView text_money_txt =helper.getView(R.id.text_money_txt);

        // 商品类型： 1、红包券（优惠券） 2、加息券 3、提现券
        int goodsType =mBean.getGoodsType();
        // 兑换价格

//
//        // "投资金额≥1000.0元",
//        double minAmount =0.00;
//        if(!TextUtils.isEmpty(mBean.getInvestmentQuota())){
//            minAmount =  Double.parseDouble(mBean.getInvestmentQuota());
//        }


      // 投资收益天数限制（不包括提现券）


        // 面额（不包括提现券）
  //      double faceValue = mBean.getAmount();

        // 使用限制（不包括提现券）  "use":"限非新手标",
        int mType =  mBean.getUseType();

        // 有效时间（不包括提现券）
        int validDate = mBean.getEffectiveDay();


        if(goodsType==3){

            redpacket_rl.setVisibility(View.GONE);
            addinterest_rl.setVisibility(View.GONE);
            withdrow_content.setVisibility(View.VISIBLE);
            withdrow_rl.setVisibility(View.VISIBLE);
            withdrow_content.setText("免提现手续费\n"+"获取后"+validDate+"天有效");
            rechange_btn.setText(mBean.getCoinCost()+"正经值兑换");

        }else if(goodsType==2){

            redpacket_rl.setVisibility(View.GONE);
            withdrow_rl.setVisibility(View.GONE);

            addinterest_rl.setVisibility(View.VISIBLE);
            withdrow_content.setVisibility(View.VISIBLE);
            addinterest_amount.setText("+"+mBean.getAmount()+"%");
            String type =null;
            if(mType==1){
                type="全部";
            }else if(mType==2){
                type="新手标";
            }else if(mType==3){
                type="非新手标";
            }else{
                type="";
            }
            Log.i(TAG,"minDays="+mBean.getMinDay()+"type="+type+"validDate="+validDate);
            withdrow_content.setText("收益天数≥"+mBean.getMinDay()+"天\n"+type+"\n"+"获取后"+validDate+"天有效");

            rechange_btn.setText(mBean.getCoinCost()+"正经值兑换");

        }else if(goodsType==1){

            withdrow_content.setVisibility(View.GONE);
            addinterest_rl.setVisibility(View.GONE);

            withdrow_content.setVisibility(View.VISIBLE);
            redpacket_rl.setVisibility(View.VISIBLE);
            String type =null;
            if(mType==1){
                type="全部";
            }else if(mType==2){
                type="新手标";
            }else if(mType==3){
                type="非新手标";
            }else{
                type="";
            }
            text_money_txt.setText(Html.fromHtml("<font color='#ffffff'>元</font><font color='#ffea00'>红包</font>"));
            redpacket_amount.setText(mBean.getAmount()+"");
            withdrow_content.setText("投资金额≥"+mBean.getInvestmentQuota()+"元\n"+"收益天数≥"+mBean.getMinDay()+"天\n"+type+"\n"+"获取后"+validDate+"天有效");
            rechange_btn.setText(mBean.getCoinCost()+"正经值兑换");
        }
        helper.addOnClickListener(R.id.layout_sumbit_btn);
    }

}
