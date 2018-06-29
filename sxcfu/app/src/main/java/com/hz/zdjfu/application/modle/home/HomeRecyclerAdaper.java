package com.hz.zdjfu.application.modle.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.RecyclerScaleUtils;
import com.hz.zdjfu.application.utils.ScreenUtils;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import java.util.Date;
import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/5 0005.
 */

public class HomeRecyclerAdaper extends RecyclerView.Adapter<HomeRecyclerAdaper.MyViewHolder>{


    private  List<ProductBean> mList;
    private  Context mContext;

    public HomeRecyclerAdaper(List<ProductBean> list, Context context){
        this.mContext = context;
        this.mList = list;
    }

    public void setData(List<ProductBean> list){
        if(list!=null){
            mList =list;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_product_item_layout,parent,false);
        //这里设置view的宽度
        RecyclerScaleUtils.onCreateViewHolder(parent,view, ScreenUtils.dip2px(parent.getContext(),30f));
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //设置第一个与最后item对称显示
        RecyclerScaleUtils.onBindViewHolder(holder.itemView,position, getItemCount(), ScreenUtils.dip2px(holder.itemView.getContext(),30f));
        ProductBean item = mList.get(position);

        //剩余可投
        holder.mBalance.setText(UiUtils.formatMoneyToBigDecimal(item.getBalance()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"");

        //收益天数
        if(item.getProduct_type()==1){
            holder.mEarning.setText(TimeUtils.differentDays(new Date(TimeUtils.getCurrentTimeInLong()),new Date(item.getWill_end_date()))+"");
        }else if(item.getProduct_type()==2){//直投
            holder.mEarning.setText(item.getReturn_days()+"");
        }


        //年化率  平台大于0 则年化率减去平台贴息部分 否者直接显示年化率
        try {

            if (item.getPlatform_interest()==0.0) {
                holder.mInterest.setText(UiUtils.formatMoneyToBigDecimal(item.getIncome()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"");
                holder.mInterestll.setVisibility(View.GONE);
            } else {
                double subIncome = UiUtils.sub(String.valueOf(item.getIncome()),String.valueOf(item.getPlatform_interest()));
                holder.mInterest.setText(UiUtils.formatMoneyToBigDecimal(subIncome+"",mContext.getResources().getString(R.string.defalut_money_separator))+"");
                holder.maddInterest.setText(UiUtils.formatMoneyToBigDecimal(item.getPlatform_interest()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"");
                holder.mInterestll.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){

        }


        holder.mRelativeLayout.setTag(position);

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                ProductBean mProductBean =mList.get(pos);
                if(mProductBean!=null){
                    if(mProductBean!=null){
                        Bundle mBundle = new Bundle();
                        if(mProductBean.getProduct_type()==1){
                            mBundle.putString("PRODUCTID",mProductBean.getId()+"");
                            mContext.startActivity(ProductDetailActivity.makeIntent(mContext,mBundle));
                        }else if(mProductBean.getProduct_type()==2){//直投
                            mBundle.putString("PRODUCTID",mProductBean.getId()+"");
                            mContext.startActivity(ZTProductDetailActivity.makeIntent(mContext,mBundle));
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout mRelativeLayout;
        private TextView mBalance;
        private TextView mEarning;
        private TextView mInterest;
        private TextView maddInterest;
        private LinearLayout mInterestll;
        public MyViewHolder(View itemView) {
            super(itemView);
            mRelativeLayout = itemView.findViewById(R.id.home_item_product_rl);
            mBalance =itemView.findViewById(R.id.home_item_remain_tv);
            mEarning =itemView.findViewById(R.id.home_item_interestday_tv);
            mInterest =itemView.findViewById(R.id.home_item_interest_tv);
            maddInterest=itemView.findViewById(R.id.home_item_add_interest_tv);
            mInterestll =itemView.findViewById(R.id.home_item_platfrom_interest_ll);
        }
    }


}
