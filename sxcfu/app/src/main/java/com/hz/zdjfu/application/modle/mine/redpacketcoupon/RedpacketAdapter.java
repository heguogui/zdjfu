package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.RedpacketBean;


import java.util.List;

/**
 * [类功能说明]
 *红包适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RedpacketAdapter extends RecyclerView.Adapter<RedpacketAdapter.ItemViewHolder>{


    private static final int VIEW_TYPE = -1;

    private Context mContext;
    private List<RedpacketBean> mLists;
    public RedpacketAdapter(Context context, List<RedpacketBean> lists) {
        this.mLists =lists;
        this.mContext = context;
    }

    public void setData(List<RedpacketBean> lists){
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

        view = inflater.inflate(R.layout.view_redpacketcoupon_item_layout, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        if (mLists != null && mLists.size() > 0) {
            RedpacketBean mBean = mLists.get(position);
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

        private TextView redpacketcouponDiscountsNumberTv;
        private TextView redpacketcouponTagTv;
        private TextView redpacketcouponNameTv;
        private TextView redpacketcouponDayTv;
        private TextView redpacketcouponTimeTv;
        private ImageView redpacketcouponStartuserIv;
        private ImageView redpacketcouponStateTv;


        private TextView redpacketDanWei;
        private RelativeLayout redpacketcouponDiscountsRl;



        public ItemViewHolder(View itemView) {
            super(itemView);
            redpacketcouponDiscountsNumberTv =itemView.findViewById(R.id.redpacketcoupon_discounts_number_tv);
            redpacketcouponTagTv =itemView.findViewById(R.id.redpacketcoupon_tag_tv);
            redpacketcouponNameTv =itemView.findViewById(R.id.redpacketcoupon_name_tv);
            redpacketcouponDayTv =itemView.findViewById(R.id.redpacketcoupon_day_tv);
            redpacketcouponTimeTv =itemView.findViewById(R.id.redpacketcoupon_time_tv);
            redpacketcouponStateTv =itemView.findViewById(R.id.redpacketcoupon_state_tv);
            redpacketcouponStartuserIv =itemView.findViewById(R.id.zt_coupon_out_time_iv);
            redpacketDanWei =itemView.findViewById(R.id.redpacketcoupon_discounts_danwei_tv);
            redpacketcouponDiscountsRl =itemView.findViewById(R.id.redpacketcoupon_discounts_rl);
        }


        public void bindData(RedpacketBean mBean) {
            if (mBean == null) {
                return;
            }

            //使用限制
            if(!TextUtils.isEmpty(mBean.getUseType())){
                if(mBean.getUseType().equals(Constants.TYPE_REDPACKET_COUPON_ALL_STATE)){
                    redpacketcouponTagTv.setText("全部");
                }else if(mBean.getUseType().equals(Constants.TYPE_REDPACKET_COUPON_NEW_STATE)){
                    redpacketcouponTagTv.setText("新手标");
                }else if(mBean.getUseType().equals(Constants.TYPE_REDPACKET_COUPON_OLD_STATE)){
                    redpacketcouponTagTv.setText("非新手标");
                }else{
                    redpacketcouponTagTv.setText("");
                }
            }else{
                redpacketcouponTagTv.setText("");
            }

            //红包金额
            if(!TextUtils.isEmpty(mBean.getAmount())){
                if(mBean.getAmount().contains(".")){
                    String[] mContent =mBean.getAmount().split("\\.");
                    redpacketcouponDiscountsNumberTv.setText(mContent[0]+"");
                }else{
                    redpacketcouponDiscountsNumberTv.setText(mBean.getAmount()+"");
                }
            }else{
                redpacketcouponDiscountsNumberTv.setText("0");
            }

            //限制
            redpacketcouponDayTv.setText("投资≥"+mBean.getMaxAmount()+"元, "+"收益天数≥"+mBean.getMaxDays()+"天");

            //有效时间
            String mStartTime =mBean.getStartDate();
            String mEndTime =mBean.getEndDate();
//            if(!TextUtils.isEmpty(mBean.getStart_date())){
//                if(mBean.getStart_date().contains(" ")){
//                    String[] start_time =mBean.getStart_date().split(" ");
//                    if(start_time.length>1){
//                        mStartTime=start_time[0];
//                    }
//                }else{
//                    mStartTime=mBean.getStart_date();
//                }
//            }
//
//            if(!TextUtils.isEmpty(mBean.getEnd_date())){
//                if(mBean.getEnd_date().contains(" ")){
//                    String[] end_time =mBean.getEnd_date().split(" ");
//                    if(end_time.length>1){
//                        mEndTime=end_time[0];
//                    }
//                }else{
//                    mEndTime=mBean.getEnd_date();
//                }
//            }

            redpacketcouponTimeTv.setText(mStartTime+ "至" + mEndTime);

            //状态
            if(!TextUtils.isEmpty(mBean.getStatus())){
                if(mBean.getStatus().equals("2")){
                    redpacketcouponStateTv.setVisibility(View.VISIBLE);
                    redpacketcouponStateTv.setImageResource(R.mipmap.used);
                    changeColor(false);
                }else if(mBean.getStatus().equals("3")){
                    redpacketcouponStateTv.setVisibility(View.VISIBLE);
                    redpacketcouponStateTv.setImageResource(R.mipmap.out_time);
                    changeColor(false);
                }else{
                    changeColor(true);
                    redpacketcouponStateTv.setVisibility(View.GONE);
                }
            }else{
                redpacketcouponStateTv.setVisibility(View.GONE);
            }


            //是否立即过期
            if(TextUtils.isEmpty(mBean.getWillOut())){
                redpacketcouponStartuserIv.setVisibility(View.GONE);
            }else{
                if(mBean.getWillOut().equals("false")){
                    redpacketcouponStartuserIv.setVisibility(View.GONE);
                }else{
                    redpacketcouponStartuserIv.setVisibility(View.VISIBLE);
                }
            }

            //标题
            if(!TextUtils.isEmpty(mBean.getGiftName())){
                redpacketcouponNameTv.setText(mBean.getGiftName()+"");
            }

        }


        public void changeColor(boolean state){
            if(state){
                redpacketDanWei.setTextColor(mContext.getResources().getColor(R.color.red4));
                redpacketcouponDiscountsRl.setBackgroundResource(R.mipmap.redpacket_coupon_red_bg);
                redpacketcouponDiscountsNumberTv.setTextColor(mContext.getResources().getColor(R.color.red4));
                redpacketcouponTagTv.setTextColor(mContext.getResources().getColor(R.color.red4));
            }else{
                redpacketDanWei.setTextColor(mContext.getResources().getColor(R.color.gray13));
                redpacketcouponDiscountsRl.setBackgroundResource(R.mipmap.redpacket_coupon_gray_bg);
                redpacketcouponDiscountsNumberTv.setTextColor(mContext.getResources().getColor(R.color.gray13));
                redpacketcouponTagTv.setTextColor(mContext.getResources().getColor(R.color.gray2));
            }
        }

    }




}
