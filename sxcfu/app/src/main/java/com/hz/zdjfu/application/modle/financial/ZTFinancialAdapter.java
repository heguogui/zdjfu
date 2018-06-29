package com.hz.zdjfu.application.modle.financial;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.data.bean.ZTProductBean;
import com.hz.zdjfu.application.http.cache.Utils;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.zhitoulist.ZTProductListActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.ScreenUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.view.CircleProgressView;
import com.hz.zdjfu.application.widget.view.FinancialCDTimeView;

import java.security.acl.Group;
import java.util.List;

/**
 * [类功能说明]
 * 理财适配器
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class ZTFinancialAdapter extends RecyclerView.Adapter<ZTFinancialAdapter.ItemViewHolder> {

    private static  final String TAG =ZTFinancialAdapter.class.getName();
    private static final int VIEW_TYPE = -1;

    private Context mContext;
    private List<ZTProductBean> mLists;
    private boolean isRefersh = true;
    public ZTFinancialAdapter(Context context, List<ZTProductBean> lists) {
        this.mLists =lists;
        this.mContext = context;
    }


    public void setIsRefersh(boolean isRefersh) {
        this.isRefersh = isRefersh;
    }

    public void setData(List<ZTProductBean> lists){
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

        view = inflater.inflate(R.layout.view_zt_financial_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        if (mLists != null && mLists.size() > 0) {
            ZTProductBean mBean = mLists.get(position);
            holder.bindData(mBean,position);
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


        private RelativeLayout financial_item_main;
        private RelativeLayout financial_item_earnings_fillrates_rl;
        private RelativeLayout financial_item_bg_rl;
        private ImageView financial_item_isfresh_iv;
        private TextView financial_item_title;
        private TextView financial_item_one_lable;
        private TextView financial_item_two_lable;
        private RelativeLayout financial_item_line_rl;
        private TextView financial_data;
        private TextView financial_fillrates_dw;
        private TextView financial_fillrates;
        private TextView financial_earning_dw;
        private TextView financial_earning;
        private FinancialCDTimeView financial_item_down_time;
        private Button financial_item_statu_btn;
        private TextView financial_amount;
        private RelativeLayout financial_item_earnings_rl;
        private TextView financial_item_look_lvyue_tv;
        private RelativeLayout financial_item_bottom_rl;


        public ItemViewHolder(View itemView) {
            super(itemView);

            financial_item_bg_rl =itemView.findViewById(R.id.financial_item_bg_rl);
            financial_item_isfresh_iv=itemView.findViewById(R.id.financial_item_isfresh_iv);
            financial_item_title =itemView.findViewById(R.id.financial_item_title);
            financial_item_one_lable =itemView.findViewById(R.id.financial_item_one_lable);
            financial_item_two_lable =itemView.findViewById(R.id.financial_item_two_lable);
            financial_item_line_rl =itemView.findViewById(R.id.financial_item_line_rl);
            financial_fillrates_dw =itemView.findViewById(R.id.financial_item_earnings_fillrates_dw);
            financial_fillrates =itemView.findViewById(R.id.financial_item_earnings_fillrates);
            financial_earning_dw =itemView.findViewById(R.id.financial_item_earnings_dw);
            financial_earning =itemView.findViewById(R.id.financial_item_earnings);
            financial_data =itemView.findViewById(R.id.financial_item_data);
            financial_item_statu_btn=itemView.findViewById(R.id.financial_item_statu_btn);
            financial_item_down_time =itemView.findViewById(R.id.financial_item_down_time);
            financial_amount=itemView.findViewById(R.id.financial_item_amount_tv);
            financial_item_main =itemView.findViewById(R.id.financial_item_main);
            financial_item_earnings_fillrates_rl =itemView.findViewById(R.id.financial_item_earnings_fillrates_rl);
            financial_item_earnings_rl =itemView.findViewById(R.id.financial_item_earnings_rl);
            financial_item_look_lvyue_tv=itemView.findViewById(R.id.financial_item_look_lvyue_tv);
            financial_item_bottom_rl=itemView.findViewById(R.id.financial_item_bottom_rl);
        }


        public void bindData(ZTProductBean item,int pos) {

            if (item == null) {
                return;
            }

            //名字
            financial_item_title.setText(item.getProductCode()+ "");

            //年化率  平台大于0 则年化率减去平台贴息部分 否者直接显示年化率
            if (item.getPlatformInterest()==0.0) {
                financial_earning.setText(AmountUtils.getCommaFormat(item.getIncome()+"")+"");
                financial_item_earnings_fillrates_rl.setVisibility(View.GONE);
            } else {
                double subIncome = UiUtils.sub(String.valueOf(item.getIncome()),String.valueOf(item.getPlatformInterest()));
                financial_earning.setText(AmountUtils.getCommaFormat(subIncome+"")+"");
                financial_fillrates.setText("+"+AmountUtils.getCommaFormat(item.getPlatformInterest()+"")+"");
                financial_item_earnings_fillrates_rl.setVisibility(View.VISIBLE);
            }

            //活动标签
            if(!TextUtils.isEmpty(item.getLabel())){
                if(item.getLabel().contains("，")){
                    financial_item_one_lable.setVisibility(View.VISIBLE);
                    financial_item_two_lable.setVisibility(View.VISIBLE);
                    try{
                        String[]  labels =item.getLabel().split("\\，");
                        if(labels.length==2){
                            financial_item_one_lable.setText(labels[0]);
                            financial_item_two_lable.setText(labels[1]);
                        }else{
                            financial_item_one_lable.setVisibility(View.GONE);
                            financial_item_two_lable.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                    }
                }else{
                    financial_item_one_lable.setVisibility(View.VISIBLE);
                    financial_item_two_lable.setVisibility(View.GONE);
                    financial_item_one_lable.setText(item.getLabel());
                }
            }else {
                financial_item_one_lable.setVisibility(View.GONE);
                financial_item_two_lable.setVisibility(View.GONE);
            }



            int mHeight = ScreenUtils.getScreenHeight(mContext);

            //新手
            if(item.getIsFresh()==1){
                financial_item_isfresh_iv.setVisibility(View.VISIBLE);
                financial_item_line_rl.setVisibility(View.VISIBLE);
                ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,mHeight/4);  //item的宽高
                mp.setMargins(mHeight/50, 0, mHeight/50, mHeight/50);//分别是margin_top那四个属性
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mp);
                financial_item_bg_rl.setLayoutParams(lp);

                ViewGroup.MarginLayoutParams mc = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);  //item的宽高
                mc.setMargins(mHeight/50, mHeight/8, 0, 0);//分别是margin_top那四个属性
                RelativeLayout.LayoutParams lc = new RelativeLayout.LayoutParams(mc);
                financial_item_earnings_rl.setLayoutParams(lc);
                financial_item_main.setBackgroundColor(mContext.getResources().getColor(R.color.gray10));
                financial_item_bg_rl.setBackgroundResource(R.drawable.stroke_gray_solid_white_radius5);

                //年化利率大小
                financial_earning.setTextSize(33);
                financial_data.setTextSize(20);

            }else{

                financial_item_isfresh_iv.setVisibility(View.GONE);
                financial_item_line_rl.setVisibility(View.GONE);
                ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,mHeight/5);  //item的宽高
                mp.setMargins(mHeight/50, 0, 0, 0);//分别是margin_top那四个属性
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mp);
                financial_item_bg_rl.setLayoutParams(lp);
                financial_item_bg_rl.setBackgroundColor(mContext.getResources().getColor(R.color.white0));
                ViewGroup.MarginLayoutParams mc = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);  //item的宽高
                mc.setMargins(mHeight/50, mHeight/11, mHeight/50, 0);//分别是margin_top那四个属性
                RelativeLayout.LayoutParams lc = new RelativeLayout.LayoutParams(mc);
                financial_item_earnings_rl.setLayoutParams(lc);
                financial_item_main.setBackgroundColor(mContext.getResources().getColor(R.color.white0));
                financial_earning.setTextSize(28);
                financial_data.setTextSize(18);

//                ViewGroup.MarginLayoutParams mb = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);  //item的宽高
//                mb.setMargins(mHeight/50, mHeight/50, mHeight/50, 0);//分别是margin_top那四个属性
//                RelativeLayout.LayoutParams lb = new RelativeLayout.LayoutParams(mb);
//                financial_item_bottom_rl.setLayoutParams(lb);
            }

            //天数
            financial_data.setText(item.getReturnDays() + "天");

            //financial_amount_title 1.编辑中 11.审核中 2.发标中 3.待发布 31.预募集 4.投资中 5.履约中 6.已还款 7.满标 8.流标',
            if(item.getStatus()==31){//募集中
                financial_item_statu_btn.setVisibility(View.GONE);
                financial_item_down_time.setVisibility(View.VISIBLE);
                long ms = item.getCountDown();//毫秒数

                financial_item_down_time.setTag(pos);
                if (ms > 0) { //  不是读取缓存数据时，开始预募集倒计时
                    financial_item_down_time.setVisibility(View.VISIBLE);
                    if (!financial_item_down_time.isRun()) {
                        financial_item_down_time.setTimes(ms,mLists,ZTFinancialAdapter.this,financial_item_down_time);
                        financial_item_down_time.run();
                    }
                } else {
                    financial_item_down_time.setVisibility(View.GONE);
                }

                financial_amount.setText("总额"+UiUtils.formatMoneyToBigDecimal(item.getDebtMoney()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"元");

            }else if(item.getStatus()==4){//投资中
                financial_item_down_time.setVisibility(View.GONE);
                financial_item_statu_btn.setVisibility(View.VISIBLE);

                financial_amount.setText("剩余"+UiUtils.formatMoneyToBigDecimal(item.getBalance()+"",mContext.getResources().getString(R.string.defalut_money_separator))+"元");

            }

            financial_item_main.setTag(item);
            financial_item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ZTProductBean mBean= (ZTProductBean) view.getTag();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mBean.getId()+"");
                    mContext.startActivity(ZTProductDetailActivity.makeIntent(mContext,mBundle));
                }
            });

            financial_item_statu_btn.setTag(item);
            financial_item_statu_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ZTProductBean mBean= (ZTProductBean) v.getTag();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mBean.getId()+"");
                    mContext.startActivity(ZTProductDetailActivity.makeIntent(mContext,mBundle));
                }
            });


            //查看履约中的产品
            if(item.getId()==-1){
                financial_item_look_lvyue_tv.setVisibility(View.VISIBLE);
                financial_item_main.setBackgroundColor(mContext.getResources().getColor(R.color.gray10));
                financial_item_bg_rl.setVisibility(View.GONE);
            }else{
                financial_item_look_lvyue_tv.setVisibility(View.GONE);
                financial_item_bg_rl.setVisibility(View.VISIBLE);
            }


            financial_item_look_lvyue_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   mContext.startActivity(ZTProductListActivity.makeIntent(mContext,null));
                }
            });



        }


    }




}
