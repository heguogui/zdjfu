package com.hz.zdjfu.application.modle.mine.transationdetail;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.TransationDetail;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.PullableListView;


import java.util.List;

/**
 * [类功能说明]
 * 交易明细适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public class TransationDetailAdapter extends BaseAdapter {

    private List<TransationDetail> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private int cpage,tpage;
    private PullableListView listView;
    public TransationDetailAdapter(Context context, List<TransationDetail> data, PullableListView investmentView){
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listView = investmentView;
    }


    /**
     * 组件集合，对应list.xml中的控件
     */
    private final class ViewHolder{
        private TextView  monthTv;
        private TextView  nameTv;
        private TextView  stateTv;
        private TextView  timeTv;
        private ImageView typeTv;
        private TextView  numberTv;

    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if(null == convertView) {

            holder = new ViewHolder();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.view_transationdetail_item_layout, null);
            // 月份
            holder.monthTv=(TextView)convertView.findViewById(R.id.transationdetail_item_month_tv);
            //名字
            holder.nameTv=(TextView)convertView.findViewById(R.id.transationdetail_item_name_tv);
            // 状态
            holder.stateTv=(TextView)convertView.findViewById(R.id.transationdetail_item_state_tv);
            // 时间
            holder.timeTv=(TextView)convertView.findViewById(R.id.transationdetail_item_time_tv);
            // 类型
            holder.typeTv=(ImageView)convertView.findViewById(R.id.transationdetail_item_type_iv);
            //数据
            holder.numberTv=(TextView)convertView.findViewById(R.id.transationdetail_item_number_tv);

            convertView.setTag(holder);

        }else{
            holder=(ViewHolder)convertView.getTag();

        }

        TransationDetail mBean =data.get(position);

        if(mBean!=null){

            //判断类型
            if(!TextUtils.isEmpty(mBean.getOperate_type())){

                if(mBean.getOperate_type().equals(Constants.TYPE_OPERATE_RECHANGE)){//充值 1为成功 其他都为失败
                    holder.nameTv.setText("充值");
                    if(!TextUtils.isEmpty(mBean.getStatus())&&mBean.getStatus().equals("1")){//成功
                        holder.typeTv.setImageResource(R.mipmap.rechange_success_icon);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.red4));
                    }else {//-3审核不通过-5已撤销
                        holder.typeTv.setImageResource(R.mipmap.rechange_fail);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.gray2));
                    }
              //      holder.numberTv.setText("+"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));
                }else if(mBean.getOperate_type().equals(Constants.TYPE_OPERATE_WHITDRAW)){//提现

                    holder.nameTv.setText("提现");
                    if(!TextUtils.isEmpty(mBean.getStatus())&&mBean.getStatus().equals("1")){//成功
                        holder.typeTv.setImageResource(R.mipmap.whitdraw_success);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.green0));
                    }else {//-3审核不通过-5已撤销
                        holder.typeTv.setImageResource(R.mipmap.whitdraw_fail);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.gray2));
                    }
                //    holder.numberTv.setText("-"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));

                }else if(mBean.getOperate_type().equals(Constants.TYPE_OPERATE_INVEST)){//投资

                    holder.nameTv.setText("投资");
                    if(!TextUtils.isEmpty(mBean.getStatus())&&mBean.getStatus().equals("1")){//成功
                        holder.typeTv.setImageResource(R.mipmap.invest_success_iv);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.green0));
                    }else {//-3审核不通过-5已撤销
                        holder.typeTv.setImageResource(R.mipmap.invest_fail);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.gray2));
                    }
                 //   holder.numberTv.setText("-"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));
                }else if(mBean.getOperate_type().equals(Constants.TYPE_OPERATE_RETURN)){//回款

                    holder.nameTv.setText("回款");
                    if(!TextUtils.isEmpty(mBean.getStatus())&&mBean.getStatus().equals("1")){//成功
                        holder.typeTv.setImageResource(R.mipmap.return_success);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.red4));
                    }else{//-3审核不通过-5已撤销
                        holder.typeTv.setImageResource(R.mipmap.return_fail);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.gray2));
                    }
                //    holder.numberTv.setText("+"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));

                }else if(mBean.getOperate_type().equals(Constants.TYPE_OPERATE_EARNING)){//收益
                    holder.nameTv.setText("收益");
                    if(!TextUtils.isEmpty(mBean.getStatus())&&mBean.getStatus().equals("1")){//成功
                        holder.typeTv.setImageResource(R.mipmap.earning_success);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.red4));
                    }else{//-3审核不通过-5已撤销
                        holder.typeTv.setImageResource(R.mipmap.earning_fail);
                        holder.numberTv.setTextColor(context.getResources().getColor(R.color.gray2));
                    }
                //    holder.numberTv.setText("+"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));
                }


            }

            //描述
            if(mBean.getStatus().equals("1")){//成功
                if(!TextUtils.isEmpty(mBean.getSummary())){
                    holder.stateTv.setText(mBean.getSummary());
                }
            }else {
                if(!TextUtils.isEmpty(mBean.getSummary())){
                    holder.stateTv.setText(mBean.getSummary());
                }
            }

            //时间
//            holder.timeTv.setText(TimeUtils.getMDHMStr(mBean.getCreate_time()));
            holder.timeTv.setText(mBean.getCreate_time()+"");

            //加减
//            if(!TextUtils.isEmpty(mBean.getAction())){
//                if(mBean.getAction().equals("1")){
//                    holder.numberTv.setText("+"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));
//                }else if(mBean.getAction().equals("2")){
//                    holder.numberTv.setText("-"+UiUtils.formatMoneyToBigDecimal(mBean.getAmount() + "",context.getResources().getString(R.string.defalut_money_separator)));
//                }
//            }
            holder.numberTv.setText(mBean.getAmount()+"");

            //月份
            if(!TextUtils.isEmpty(mBean.getmMonth())){
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.monthTv.setText(mBean.getmMonth());
            }else{
                holder.monthTv.setVisibility(View.GONE);
            }

        }

        return convertView;
    }

}
