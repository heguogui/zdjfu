package com.hz.zdjfu.application.modle.mine.invitefriend.invitereword;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.InviteRewordBean;

/**
 * [类功能说明]
 *邀请记录适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/13 0013.
 */

public class InviteRewordAdapter extends BaseQuickAdapter<InviteRewordBean,BaseViewHolder> {

    private Context mContext;
    public InviteRewordAdapter(Context context) {
        super(R.layout.view_invitereword_item_layout);
        this.mContext =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InviteRewordBean item) {
        try{
            if(!TextUtils.isEmpty(item.getUser_name())){
                helper.setText(R.id.invitereword_item_name,item.getUser_name().substring(0,3)+"****"+item.getUser_name().substring(item.getUser_name().length()-4,item.getUser_name().length()));
            }
        }catch (Exception e){

        }

        helper.setText(R.id.invitereword_item_time,item.getRegister_time()+"");
        helper.setText(R.id.invitereword_item_number,item.getCoin()+"");

    }
}
