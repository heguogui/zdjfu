package com.hz.zdjfu.application.modle.ztprogect.zhitoulist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ZTProductBean;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.UiUtils;


import java.util.List;

/**
 * [类功能说明]
 * 直投履约中适配器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class ZTProductListAdapter extends RecyclerView.Adapter<ZTProductListAdapter.ItemViewHolder> {

    private static  final String TAG =ZTProductListAdapter.class.getName();
    private static final int VIEW_TYPE = -1;

    private Context mContext;
    private List<ZTProductBean> mLists;

    public ZTProductListAdapter(Context context, List<ZTProductBean> lists) {
        this.mLists =lists;
        this.mContext = context;
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
            view = inflater.inflate(R.layout.view_empty_lvyue_data, parent, false);
            return new ItemViewHolder(view);
        }

        view = inflater.inflate(R.layout.view_zt_product_list_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        if (mLists != null && mLists.size() > 0) {
            ZTProductBean mBean = mLists.get(position);
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


        private RelativeLayout productlist_item_main;
        private RelativeLayout productlist_item_earnings_fillrates_rl;
        private ImageView productlist_item_isfresh_iv;
        private TextView productlist_item_title;
        private TextView productlist_item_one_lable;
        private TextView productlist_item_two_lable;
        private TextView productlist_data;
        private TextView productlist_fillrates;
        private TextView productlist_earning;
        private Button productlist_item_statu_btn;



        public ItemViewHolder(View itemView) {
            super(itemView);

            productlist_item_isfresh_iv=itemView.findViewById(R.id.productlist_item_isfresh_iv);
            productlist_item_title =itemView.findViewById(R.id.productlist_item_title);
            productlist_item_one_lable =itemView.findViewById(R.id.productlist_item_one_lable);
            productlist_item_two_lable =itemView.findViewById(R.id.productlist_item_two_lable);
            productlist_fillrates =itemView.findViewById(R.id.productlist_item_earnings_fillrates);
            productlist_earning =itemView.findViewById(R.id.productlist_item_earnings);
            productlist_data =itemView.findViewById(R.id.productlist_item_data);
            productlist_item_statu_btn=itemView.findViewById(R.id.productlist_item_statu_btn);
            productlist_item_main =itemView.findViewById(R.id.productlist_item_main);
            productlist_item_earnings_fillrates_rl =itemView.findViewById(R.id.productlist_item_earnings_fillrates_rl);

        }


        public void bindData(ZTProductBean item) {

            if (item == null) {
                return;
            }

            //名字
            productlist_item_title.setText(item.getProductCode()+ "");

            //年化率  平台大于0 则年化率减去平台贴息部分 否者直接显示年化率
            if (item.getPlatformInterest()==0.0) {
                productlist_earning.setText(AmountUtils.getCommaFormat(item.getIncome()+"")+"");
                productlist_item_earnings_fillrates_rl.setVisibility(View.GONE);
            } else {
                double subIncome = UiUtils.sub(String.valueOf(item.getIncome()),String.valueOf(item.getPlatformInterest()));
                productlist_earning.setText(AmountUtils.getCommaFormat(subIncome+"")+"");
                productlist_fillrates.setText("+"+AmountUtils.getCommaFormat(item.getPlatformInterest()+"")+"");
                productlist_item_earnings_fillrates_rl.setVisibility(View.VISIBLE);
            }

            //活动标签
            if(TextUtils.isEmpty(item.getLabel())){

            }else {

            }

            //活动标签
            if(!TextUtils.isEmpty(item.getLabel())){
                if(item.getLabel().contains("，")){
                    productlist_item_one_lable.setVisibility(View.VISIBLE);
                    productlist_item_two_lable.setVisibility(View.VISIBLE);
                    try{
                        String[]  labels =item.getLabel().split("\\，");
                        if(labels.length==2){
                            productlist_item_one_lable.setText(labels[0]);
                            productlist_item_two_lable.setText(labels[1]);
                        }else{
                            productlist_item_one_lable.setVisibility(View.GONE);
                            productlist_item_two_lable.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                    }
                }else{
                    productlist_item_one_lable.setVisibility(View.VISIBLE);
                    productlist_item_two_lable.setVisibility(View.GONE);
                    productlist_item_one_lable.setText(item.getLabel());
                }
            }else {
                productlist_item_one_lable.setVisibility(View.GONE);
                productlist_item_two_lable.setVisibility(View.GONE);
            }



            //新手
            if(item.getIsFresh()==1){
                productlist_item_isfresh_iv.setVisibility(View.VISIBLE);
            }else{
                productlist_item_isfresh_iv.setVisibility(View.GONE);
            }

            //天数
            productlist_data.setText(item.getReturnDays() + "天");

            //1.编辑中 11.审核中 2.发标中 3.待发布 31.预募集 4.投资中 5.履约中 6.已还款 7.满标 8.流标',
            if(item.getStatus()==5||item.getStatus()==7||item.getStatus()==71){//履约中
                productlist_item_statu_btn.setVisibility(View.VISIBLE);
                productlist_item_statu_btn.setText("履约中");
            }else if(item.getStatus()==6){//已还款
                productlist_item_statu_btn.setVisibility(View.VISIBLE);
                productlist_item_statu_btn.setText("已回款");
            }else{
                productlist_item_statu_btn.setVisibility(View.GONE);
            }

            productlist_item_main.setTag(item);
            productlist_item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ZTProductBean mBean= (ZTProductBean) view.getTag();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mBean.getId()+"");
                    mContext.startActivity(ZTProductDetailActivity.makeIntent(mContext,mBundle));
                }
            });

        }


    }




}
