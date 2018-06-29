package com.hz.zdjfu.application.modle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/24 0024.
 */

public class RecycleViewDialogAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    private Context mContext;

    public RecycleViewDialogAdapter(Context context) {
        super(R.layout.view_item_recycleview_dialog_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        if(item==null){
            return;
        }

        helper.setText(R.id.view_item_recycleview_tv,item);

    }
}
