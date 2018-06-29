package com.hz.zdjfu.application.modle.product.returnmoneyplay;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ReturnPlayBean;
import com.hz.zdjfu.application.utils.TimeUtils;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/31 0031.
 */

public class ReturnMoneyPlayAdapter extends BaseQuickAdapter<ReturnPlayBean,BaseViewHolder>{

    private Context mContext;
    public ReturnMoneyPlayAdapter(Context context) {
        super(R.layout.view_returnmoneyplay_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReturnPlayBean item) {

        if(!TextUtils.isEmpty(item.getEnd_date())){

            if(helper.getLayoutPosition() == (getItemCount()-1)){
                helper.setText(R.id.text_data, TimeUtils.getYMDtime(Long.parseLong(item.getEnd_date()))+"利息+本金");
            }else{
                helper.setText(R.id.text_data, TimeUtils.getYMDtime(Long.parseLong(item.getEnd_date()))+"利息");
            }
        }
        helper.setText(R.id.text_index,item.getId()+"");
    }
}
