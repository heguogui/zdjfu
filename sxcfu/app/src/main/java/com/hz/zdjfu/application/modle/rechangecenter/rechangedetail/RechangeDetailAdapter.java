package com.hz.zdjfu.application.modle.rechangecenter.rechangedetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.RechangeDetailBean;
import com.hz.zdjfu.application.data.bean.RechangeDetailList;
import com.hz.zdjfu.application.utils.TimeUtils;

import java.util.List;

/**
 * [类功能说明]
 *正经值兑换详情适配器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class RechangeDetailAdapter extends RecyclerView.Adapter<RechangeDetailAdapter.ItemViewHolder> {

    private static final int VIEW_TYPE = -1;

    private Context mContext;
    private List<RechangeDetailBean> mLists;
    public RechangeDetailAdapter(Context context, List<RechangeDetailBean> lists) {
        this.mLists =lists;
        this.mContext = context;
    }

    public void setData(List<RechangeDetailBean> lists){
        this.mLists =lists;
    }

    @Override
    public int getItemCount() {
        return (mLists != null&&mLists.size() > 0) ?mLists.size() : 1;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (VIEW_TYPE == viewType) {
            view = inflater.inflate(R.layout.view_empty_data, parent, false);
            return new ItemViewHolder(view);
        }

        view = inflater.inflate(R.layout.view_rechangedetail_item_layout, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        if (mLists != null && mLists.size() > 0) {
            RechangeDetailBean mBean = mLists.get(position);
            holder.bindData(mBean);
            holder.itemView.setTag(mBean);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mLists!=null&&mLists.size() <= 0) {
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mNumberTv;
        private TextView mTitleTv;
        private TextView mTimeTv;


        public ItemViewHolder(View itemView) {
            super(itemView);
            mNumberTv =itemView.findViewById(R.id.rechangedetail_item_number);
            mTitleTv =itemView.findViewById(R.id.rechangedetail_item_title);
            mTimeTv =itemView.findViewById(R.id.rechangedetail_item_time);

        }


        public void bindData(RechangeDetailBean mBean) {

            if (mBean == null) {
                return;
            }


            if(!TextUtils.isEmpty(mBean.getNum())){
                mNumberTv.setText(mBean.getNum()+"");
            }else{
                mNumberTv.setText("");
            }
            if(!TextUtils.isEmpty(mBean.getRemark())){
                mTitleTv.setText(mBean.getRemark()+"");
            }else{
                mTitleTv.setText("");
            }


            if(!TextUtils.isEmpty(mBean.getTime())){
                mTimeTv.setText(mBean.getTime()+ "");
            }else{
                mTimeTv.setText("");
            }

        }


    }



}
