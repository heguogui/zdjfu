package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.UiUtils;

/**
 * [类功能说明]
 *银行卡管理适配器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/25 0025
 */
public class BankCardAdapter extends BaseQuickAdapter<BankCardBean,BaseViewHolder>{


    private Context mContext;
    public BankCardAdapter(Context context) {
        super(R.layout.view_bankcard_layout);
        this.mContext =context;
        UiUtils.CreaterBankCardIcon();
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCardBean item) {

        if(item==null){
            return;
        }

        try{
            if(!TextUtils.isEmpty(item.getBank_alias())){
                String[] icon = UiUtils.getBankCardIcon(item.getBank_alias());
                ImageView mIcon =helper.getView(R.id.img_bank_icon);
                if(TextUtils.equals("0",icon[1])) {//没有
                    mIcon.setBackgroundResource(R.drawable.ic_launcher);
                }else{
                    mIcon.setBackgroundResource(Integer.parseInt(icon[1]));
                }

                helper.setText(R.id.text_bank_name,icon[2]+"");
                helper.setBackgroundRes(R.id.layout_my_bank_manage,Integer.parseInt(icon[0]));
                helper.setText(R.id.text_bank_codenumb, StringUtils.desenCardIdNumber(item.getBank_no()));
                TextView bindedTv =helper.getView(R.id.view_binded_tv);
                bindedTv.setTextColor(mContext.getResources().getColor(Integer.parseInt(icon[3])));
            }
        }
        catch (Exception e){

        }


    }
}
