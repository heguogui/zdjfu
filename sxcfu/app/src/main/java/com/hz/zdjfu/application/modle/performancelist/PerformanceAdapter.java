package com.hz.zdjfu.application.modle.performancelist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.view.CircleProgressView;
import com.hz.zdjfu.application.widget.view.FinancialCDTimeView;

import java.util.List;

/**
 * [类功能说明]
 * 履约中适配器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class PerformanceAdapter extends RecyclerView.Adapter<PerformanceAdapter.ItemViewHolder> {

    private static  final String TAG =PerformanceAdapter.class.getName();
    private static final int VIEW_TYPE = -1;

    private Context mContext;
    private List<ProductBean> mLists;
    private boolean isRefersh = true;
    public PerformanceAdapter(Context context, List<ProductBean> lists) {
        this.mLists =lists;
        this.mContext = context;
    }


    public void setIsRefersh(boolean isRefersh) {
        this.isRefersh = isRefersh;
    }

    public void setData(List<ProductBean> lists){
        this.mLists =lists;
    }

    @Override
    public int getItemCount() {
        return mLists.size() > 0 ?mLists.size() : 1;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (VIEW_TYPE == viewType) {
            view = inflater.inflate(R.layout.view_empty_data, parent, false);
            return new ItemViewHolder(view);
        }

        view = inflater.inflate(R.layout.view_performance_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        if (mLists != null && mLists.size() > 0) {
            ProductBean mBean = mLists.get(position);
            holder.bindData(mBean);
            holder.itemView.setTag(mBean);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mLists.size() <= 0) {
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }




    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView performance_title;
        private TextView performance_data;
        private TextView performance_fillrates;
        private TextView performance_earning;
        private ImageView performance_image;
        private TextView performance_amount;
        private TextView performance_amount_title;
        private ImageView performance_item_other_iv;
        private TextView performance_item_type;
        private LinearLayout performance_item_main;//点击查看
        private RelativeLayout performance_item_earnings_fillrates_rl;
        private TextView performance_is_frish;

        public ItemViewHolder(View itemView) {
            super(itemView);

            performance_title =itemView.findViewById(R.id.performance_item_title);
            performance_data =itemView.findViewById(R.id.performance_item_data);
            performance_fillrates =itemView.findViewById(R.id.performance_item_earnings_fillrates);
            performance_earning =itemView.findViewById(R.id.performance_item_earnings);
            performance_image =itemView.findViewById(R.id.performance_item_head);
            performance_amount=itemView.findViewById(R.id.performance_item_amount_tv);
            performance_amount_title =itemView.findViewById(R.id.performance_item_amount_title_tv);
            performance_item_other_iv =itemView.findViewById(R.id.performance_item_other_iv);
            performance_item_type =itemView.findViewById(R.id.performance_item_type);
            performance_item_main =itemView.findViewById(R.id.performance_item_main);
            performance_item_earnings_fillrates_rl =itemView.findViewById(R.id.performance_item_earnings_fillrates_rl);
            performance_is_frish =itemView.findViewById(R.id.performance_item_isfrish);
        }


        public void bindData(ProductBean item) {

            if (item == null) {
                return;
            }

            //获取头像
            if (TextUtils.isEmpty(item.getPhoto())) {
                ImageLoader.getInstance().displayCircleImage(mContext, R.mipmap.ic_list_defualt_bg, performance_image);
            } else {
                if(TextUtils.isEmpty(UiUtils.URLEncoderFileImage(item.getPhoto()))){
                    ImageLoader.getInstance().displayCircleImage(mContext, R.mipmap.ic_list_defualt_bg, performance_image);
                }else{
                    ImageLoader.getInstance().displayCircleImage(mContext,UiUtils.URLEncoderFileImage(item.getPhoto()),performance_image);
                }
            }

            //名字
            performance_title.setText(item.getProduct_code() + "");

            //年化率  平台大于0 则年化率减去平台贴息部分 否者直接显示年化率
            if (item.getPlatform_interest()==0.0) {
                performance_earning.setText(item.getIncome()+"");
                performance_item_earnings_fillrates_rl.setVisibility(View.GONE);
            } else {
                double subIncome = UiUtils.sub(String.valueOf(item.getIncome()),String.valueOf(item.getPlatform_interest()));
                performance_earning.setText(subIncome+"");
                performance_fillrates.setText("+"+item.getPlatform_interest());
                performance_item_earnings_fillrates_rl.setVisibility(View.VISIBLE);
            }


            //活动标签
            if(TextUtils.isEmpty(item.getLabel())){
                performance_item_type.setVisibility(View.GONE);
            }else {
                performance_item_type.setText(item.getLabel());
                performance_item_type.setVisibility(View.VISIBLE);
            }

            //新手
            if(item.getIs_fresh()==1){
                performance_is_frish.setText("新手");
                performance_is_frish.setVisibility(View.VISIBLE);
            }else{
                performance_is_frish.setVisibility(View.GONE);
            }

            //天数
            performance_data.setText(item.getIncomeDays() + "");

            //financial_amount_title 1.编辑中 11.审核中 2.发标中 3.待发布 31.预募集 4.投资中 5.履约中 6.已还款 7.满标 8.流标',
            if(item.getStatus()==5||item.getStatus()==7){//履约中
                performance_item_other_iv.setBackgroundResource(R.mipmap.lvyuezhong);
                performance_amount_title.setText("投资人数");
                performance_amount.setText(item.getBuyer_count()+"人");
                SpannableString sp = new SpannableString(performance_amount.getText().toString());
                //笔数颜色
                int mLength =performance_amount.getText().toString().length();
                sp.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.gray1)),mLength-1,mLength, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                performance_amount.setText(sp);
            }

            performance_item_main.setTag(item);
            performance_item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductBean mBean= (ProductBean) view.getTag();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mBean.getId()+"");
                    mContext.startActivity(ProductDetailActivity.makeIntent(mContext,mBundle));
                }
            });


        }


    }




}
