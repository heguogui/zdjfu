package com.hz.zdjfu.application.modle.ztprogect.invest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.InvestRewordBean;
import com.hz.zdjfu.application.data.bean.ZTInvestRewordBean;
import com.hz.zdjfu.application.modle.invest.InvestRewordFragment;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/16 0016
 */
public class ZTInvestRewordAdapter extends RecyclerView.Adapter<ZTInvestRewordAdapter.Item1ViewHolder>{

    public static final int IS_HEAD =1; //头部
    public static final int IS_REWORD=2;//记录


    private List<ZTInvestRewordBean> mInvestRewordLists;
    private Context context;
    private boolean isMore =false;

    public ZTInvestRewordAdapter(Context context, List<ZTInvestRewordBean> lists) {
        this.mInvestRewordLists = lists;
        this.context = context;
    }

    /**
     * 设置全局数据
     * @param list
     */
    public void setProductRewordLists(List<ZTInvestRewordBean> list ,boolean state) {
        mInvestRewordLists =list;
    }


    public  void  noMoreData(boolean ismore){
        this.isMore =ismore;
    }


    @Override
    public Item1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        Item1ViewHolder holder;
        //对不同的flag创建不同的Holder
//        if (viewType == IS_HEAD) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.investreword_item_head, parent,false);
//            holder = new Item1ViewHolder(view,mOnHeadViewInitListener,IS_HEAD, parent.getContext());
//            return holder;
//        } else{
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.investreword_item_layout, parent,false);
//            holder = new Item1ViewHolder(view,IS_REWORD, parent.getContext());
//            return holder;
//        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.investreword_item_layout, parent,false);
        holder = new Item1ViewHolder(view,parent.getContext());
        return holder;

    }

    @Override
    public void onBindViewHolder(Item1ViewHolder holder, int position) {
        ZTInvestRewordBean mBean=getItem(position);
        if(mBean!=null){
            //投资时间
            if(!TextUtils.isEmpty(mBean.getInvestTime())){
                holder.mdate.setText(mBean.getInvestTime()+"");
            }
            //投资金额
            if(!TextUtils.isEmpty(mBean.getInvestAmt())){
                holder.mMonsy.setText(UiUtils.formatMoneyToBigDecimal(mBean.getInvestAmt()+ "", context.getResources().getString(R.string.defalut_money_separator))+"元");
            }
            //投资人手机号
            if(!TextUtils.isEmpty(mBean.getUserName())){
                holder.mPhone.setText(mBean.getUserName()+"");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mInvestRewordLists == null ? 0 : mInvestRewordLists.size();
    }


    //根据Id 返回不同类型
    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    public static class Item1ViewHolder extends RecyclerView.ViewHolder {

        private ZTInvestRewordFragment.OnHeadViewInitListener monHeadViewInitListener;
        private Context context;
        private TextView mPhone;
        private TextView mMonsy;
        private TextView mdate;
        private LinearLayout noMore;

        //推荐导游信息绑定及监听
        public Item1ViewHolder(View itemView,Context context) {
            super(itemView);
            this.context = context;
            findViews(itemView);
        }

        public void findViews(View itemView) {

            mPhone = (TextView) itemView.findViewById(R.id.investreword_item_phone);
            mMonsy =(TextView) itemView.findViewById(R.id.investreword_item_amount);
            mdate =(TextView) itemView.findViewById(R.id.investreword_item_time);
            noMore = (LinearLayout) itemView.findViewById(R.id.layout_no_more);

        }


    }

    public ZTInvestRewordBean getItem(int position) {

        try {
            return mInvestRewordLists.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
