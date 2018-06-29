package com.hz.zdjfu.application.modle.financial;

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
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.view.CircleProgressView;
import com.hz.zdjfu.application.widget.view.FinancialCDTimeView;

import java.util.List;

/**
 * [类功能说明]
 * 理财适配器
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class FinancialAdapter extends RecyclerView.Adapter<FinancialAdapter.ItemViewHolder> {

    private static  final String TAG =FinancialAdapter.class.getName();
    private static final int VIEW_TYPE = -1;

    private Context mContext;
    private List<ProductBean> mLists;
    private boolean isRefersh = true;
    public FinancialAdapter(Context context, List<ProductBean> lists) {
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

        view = inflater.inflate(R.layout.view_financial_item, parent, false);

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

        private TextView financial_title;
        private TextView financial_data;
        private TextView financial_fillrates_dw;
        private TextView financial_fillrates;
        private TextView financial_earning_dw;
        private TextView financial_earning;
        private ImageView financial_image;
        private CircleProgressView financial_cp;
        private FinancialCDTimeView financial_time;
        private TextView financial_amount;
        private TextView financial_amount_title;//可投金额 显示
        private ImageView financial_item_other_iv;
        private TextView financial_item_type;
        private LinearLayout financial_item_main;
        private RelativeLayout financial_item_earnings_fillrates_rl;
        private TextView financial_is_frish;
        public ItemViewHolder(View itemView) {
            super(itemView);

            financial_title =itemView.findViewById(R.id.financial_item_title);
            financial_data =itemView.findViewById(R.id.financial_item_data);
            financial_fillrates_dw =itemView.findViewById(R.id.financial_item_earnings_fillrates_dw);
            financial_fillrates =itemView.findViewById(R.id.financial_item_earnings_fillrates);
            financial_earning_dw =itemView.findViewById(R.id.financial_item_earnings_dw);
            financial_earning =itemView.findViewById(R.id.financial_item_earnings);
            financial_image =itemView.findViewById(R.id.financial_item_head);
            financial_cp =itemView.findViewById(R.id.financial_item_circleprogressbar);
            financial_time =itemView.findViewById(R.id.financial_item_time);
            financial_amount=itemView.findViewById(R.id.financial_item_amount_tv);
            financial_amount_title =itemView.findViewById(R.id.financial_item_amount_title_tv);
            financial_item_other_iv =itemView.findViewById(R.id.financial_item_other_iv);
            financial_item_type =itemView.findViewById(R.id.financial_item_type);
            financial_item_main =itemView.findViewById(R.id.financial_item_main);
            financial_item_earnings_fillrates_rl =itemView.findViewById(R.id.financial_item_earnings_fillrates_rl);
            financial_is_frish =itemView.findViewById(R.id.financial_item_isfrish);
        }


        public void bindData(ProductBean item) {

            if (item == null) {
                return;
            }

            if (TextUtils.isEmpty(item.getPhoto())) {
                ImageLoader.getInstance().displayCircleImage(mContext, R.mipmap.ic_list_defualt_bg, financial_image);
            } else {
                if(TextUtils.isEmpty(UiUtils.URLEncoderFileImage(item.getPhoto()))){
                    ImageLoader.getInstance().displayCircleImage(mContext, R.mipmap.ic_list_defualt_bg, financial_image);
                }else{
                    ImageLoader.getInstance().displayCircleImage(mContext,UiUtils.URLEncoderFileImage(item.getPhoto()),financial_image);
                }
            }

            //名字
            financial_title.setText(item.getProduct_code() + "");

            //年化率  平台大于0 则年化率减去平台贴息部分 否者直接显示年化率
            if (item.getPlatform_interest()==0.0) {
                financial_earning.setText(item.getIncome()+"");
                financial_item_earnings_fillrates_rl.setVisibility(View.GONE);
            } else {
                double subIncome = UiUtils.sub(String.valueOf(item.getIncome()),String.valueOf(item.getPlatform_interest()));
                financial_earning.setText(subIncome+"");
                financial_fillrates.setText("+"+item.getPlatform_interest());
                financial_item_earnings_fillrates_rl.setVisibility(View.VISIBLE);
            }


            //活动标签
            if(TextUtils.isEmpty(item.getLabel())){
                financial_item_type.setVisibility(View.GONE);
            }else {
                financial_item_type.setText(item.getLabel());
                financial_item_type.setVisibility(View.VISIBLE);
            }

            if(item.getIs_fresh()==1){//新手
                financial_is_frish.setText("新手");
                financial_is_frish.setVisibility(View.VISIBLE);
            }else{
                financial_is_frish.setVisibility(View.GONE);
            }


            //天数
            financial_data.setText(item.getIncomeDays() + "");
//            financial_data.setText(TimeUtils.differentDays(new Date(TimeUtils.getCurrentTimeInLong()),new Date(item.getWill_end_date()))+ "");

            //financial_amount_title 1.编辑中 11.审核中 2.发标中 3.待发布 31.预募集 4.投资中 5.履约中 6.已还款 7.满标 8.流标',
            if(item.getStatus()==31){//募集中
//                financial_cp.setProgress(0);
//                financial_item_other_iv.setVisibility(View.GONE);
//                long ms = item.getWill_show_time();//毫秒数
//                financial_time.setTag(getLayoutPosition());
//                if (ms > 0 && !isRefersh) { //  不是读取缓存数据时，开始预募集倒计时
//                    financial_time.setVisibility(View.VISIBLE);
//                    if (!financial_time.isRun()) {
//                        financial_time.setTimes(ms,null,null,null,mLists,"");
//                        financial_time.run();
//                    }
//                } else {
//                    financial_time.setVisibility(View.GONE);
//                }
                financial_amount_title.setText("项目总额");
                financial_amount.setText("¥"+UiUtils.formatMoneyToBigDecimal(item.getMoney()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"");

            }else if(item.getStatus()==4){//投资中
                financial_time.setVisibility(View.GONE);
                financial_item_other_iv.setVisibility(View.GONE);

                try {
                    float ratio = AmountUtils.divFloat(item.getBalance()+"",item.getMoney()+"",4);//计算比例  总金额除以剩余可投
                    float mRatio =100-ratio*100;
                    financial_cp.setProgress(mRatio);
                    financial_cp.setVisibility(View.VISIBLE);

                }catch (Exception e){

                }


                financial_amount_title.setText("剩余可投");
                financial_amount.setText("¥"+UiUtils.formatMoneyToBigDecimal(item.getBalance()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"");

            }else if(item.getStatus()==5||item.getStatus()==7){//履约中
                financial_time.setVisibility(View.GONE);
                financial_cp.setVisibility(View.GONE);
                financial_item_other_iv.setVisibility(View.VISIBLE);
                financial_item_other_iv.setBackgroundResource(R.mipmap.lvyuezhong);

                financial_amount_title.setText("投资人数");
                financial_amount.setText(item.getBuyer_count()+"人");

                SpannableString sp = new SpannableString(financial_amount.getText().toString());
                //笔数颜色
                int mLength =financial_amount.getText().toString().length();
                sp.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.gray1)),mLength-1,mLength, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                financial_amount.setText(sp);

            }else{//其他

                financial_time.setVisibility(View.GONE);
                financial_cp.setVisibility(View.GONE);
                financial_item_other_iv.setVisibility(View.GONE);
                financial_amount_title.setVisibility(View.GONE);
                financial_amount.setVisibility(View.GONE);

            }

            financial_item_main.setTag(item);
            financial_item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductBean mBean= (ProductBean) view.getTag();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mBean.getId()+"");
 //                   mContext.startActivity(ProductDetailActivity.makeIntent(mContext,mBundle));
                    mContext.startActivity(ZTProductDetailActivity.makeIntent(mContext,mBundle));
                }
            });


        }


    }




}
