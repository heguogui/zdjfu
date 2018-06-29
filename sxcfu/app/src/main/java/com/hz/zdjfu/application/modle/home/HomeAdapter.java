package com.hz.zdjfu.application.modle.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.view.CountdownTimeTextView;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/18 0018.
 */

public class HomeAdapter extends BaseQuickAdapter<ProductBean,BaseViewHolder>{

    private Context mContext;

    public HomeAdapter(Context context) {
        super(R.layout.view_home_product_item_layout);
        this.mContext =context;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean item) {

        if(item==null){
            return;
        }

        //剩余可投
        helper.setText(R.id.home_item_remain_tv,item.getBalance()+" ");

        //收益天数
//        helper.setText(R.id.home_item_interestday_tv,TimeUtils.differentDays(new Date(TimeUtils.getCurrentTimeInLong()),new Date(item.getWill_end_date()))+ "");

        helper.setText(R.id.home_item_interestday_tv,item.getIncomeDays()+"");

        //年化率  平台大于0 则年化率减去平台贴息部分 否者直接显示年化率
        if (item.getPlatform_interest()==0.0) {
            helper.setText(R.id.home_item_interest_tv,item.getIncome()+"");
            helper.setVisible(R.id.home_item_platfrom_interest_ll,false);
        } else {
            double subIncome = UiUtils.sub(String.valueOf(item.getIncome()),String.valueOf(item.getPlatform_interest()));
            helper.setText(R.id.home_item_interest_tv,subIncome+"");
            helper.setText(R.id.home_item_add_interest_tv,item.getPlatform_interest()+"");
            helper.setVisible(R.id.home_item_platfrom_interest_ll,true);
        }

        CountdownTimeTextView mTextView =helper.getView(R.id.home_item_downtime_tv);
        LinearLayout mLL =helper.getView(R.id.home_item_downtime_ll);
        //预发布
        if(item.getStatus()==31){
            mLL.setVisibility(View.VISIBLE);
            long ms = item.getWill_show_time();//毫秒数
            if (ms > 0) { //  不是读取缓存数据时，开始预募集倒计时
                if (!mTextView.isRun()) {
                    mTextView.setTimes(ms, null,null,mLL,null,"");
                    mTextView.run();
                }
            }

        }else{
            mLL.setVisibility(View.GONE);
        }

    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        return super.getItemView(layoutResId, parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        holder.getView(R.id.home_item_product_rl).setTag(position);

    }

}
