package com.hz.zdjfu.application.modle.party.noobcontest;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.NoobContestBean;

/**
 * [类功能说明]
 *往前活动适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/17 0017.
 */

public class NoobContestAdapter extends BaseQuickAdapter<NoobContestBean, BaseViewHolder> {

    private Context mContext;
    public NoobContestAdapter(Context context) {
        super(R.layout.view_noobcontest_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NoobContestBean item) {

        if(item==null)
            return;

        TextView mMore =helper.getView(R.id.noobcontest_item_more);
        int posiition =helper.getLayoutPosition() -getHeaderLayoutCount();
        if(posiition==getData().size()-1){
            mMore.setVisibility(View.VISIBLE);
        }else{
            mMore.setVisibility(View.GONE);
        }

        helper.setText(R.id.noobcontest_item_name,item.getmName());
        helper.setText(R.id.noobcontest_item_content,item.getmContent());
        Button mState =helper.getView(R.id.noobcontest_item_state);
        if(item.ismState()){
            mState.setText(mContext.getString(R.string.noobcontest_state_uncomplete));
            mState.setBackground(mContext.getResources().getDrawable(R.drawable.gold_radius5));
            mState.setTextColor(mContext.getResources().getColor(R.color.white0));
        }else{
            mState.setText(mContext.getString(R.string.noobcontest_state_complete));
            mState.setBackground(mContext.getResources().getDrawable(R.drawable.gray_radius5));
            mState.setTextColor(mContext.getResources().getColor(R.color.white0));
        }

        helper.addOnClickListener(R.id.noobcontest_item_state);

    }

    @Override
    public NoobContestBean getItem(int position) {
        return super.getItem(position);
    }


}
