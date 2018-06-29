package com.hz.zdjfu.application.modle.mine.message;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.MessageBean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class MessageAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder> {

    private Context mContext;
    public MessageAdapter(Context context) {
        super(R.layout.view_message_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

        if(item==null){
            return;
        }

        helper.setText(R.id.message_time_tv,item.getCreate_time()+"");
        helper.setText(R.id.message_title_tv,item.getTitle()+"");
        ImageView stateIv =helper.getView(R.id.message_state_tv);
        if(!TextUtils.isEmpty(item.getIs_read())){
            if(item.getIs_read().equals("1")){
                stateIv.setVisibility(View.VISIBLE);
                stateIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.red_radius));
            }else{
                stateIv.setVisibility(View.GONE);
            }
        }else{
            stateIv.setVisibility(View.GONE);
        }



    }
}
