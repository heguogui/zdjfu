package com.hz.zdjfu.application.modle.mine.myinvest;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MyInvestBean;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.PullableListView;
import com.hz.zdjfu.application.widget.view.MyInvestmentSpeedView;

import java.util.List;

/**
 * [类功能说明]
 * 我的投资适配器
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public class MyInvestAdapter extends BaseAdapter {

    private List<MyInvestBean> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private int cpage,tpage;
    private PullableListView listView;
    public MyInvestAdapter(Context context, List<MyInvestBean> data,PullableListView investmentView){
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listView = investmentView;
        this.listView.setOnScrollListener(onScrollListener);
    }

    // 是否在滚动
    private  boolean scrollState=false;
    public void setScrollState(boolean scrollState) {
        this.scrollState = scrollState;
    }

    public void setPagesInfo(int cpage,int tpage){
        this.cpage = cpage;
        this.tpage = tpage;
    }

    // 是否已经回款
    private boolean isTimeedIsSpeed = false;
    // 已回款时，不加载列表进度条信息，始终是100%进度
    public void setIsTimeedSpeed(boolean isTimeedSpeed){
        this.isTimeedIsSpeed = isTimeedSpeed;
    }

    AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    //设置为正在滚动
                    setScrollState(true);
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    // 停止滚动 加载进度条
                    // Log.e("SCROLL_STATE_FLING","2");
                    //设置为停止滚动
                    setScrollState(false);
                    //当前屏幕中listview的子项的个数
                    int count = view.getChildCount();
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    setScrollState(true);
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }
    };

    /**
     * 组件集合，对应list.xml中的控件
     */
    private final class ViewHolder{
        private TextView  textTilte,textHideStatus,textHideDays,textHideSumDays;
        private TextView  textMoney;
        private TextView  textCapitalMoney;
        private LinearLayout layoutNoMoreData;
        private MyInvestmentSpeedView myInvestmentSpeedView; // 产品投资的实心进度圆形进度条
        private TextView createTime;
        private RelativeLayout viewSpeedLiubiaoRl;
        private TextView type_tv;

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
            convertView = layoutInflater.inflate(R.layout.view_myinvest_item_layout, null);
            // 投资资本
            holder.textTilte=(TextView)convertView.findViewById(R.id.text_investment_sub_tilte);
            // 预期收益
            holder.textMoney=(TextView)convertView.findViewById(R.id.text_expected_moneyssss);
            // 投资本金
            holder.textCapitalMoney=(TextView)convertView.findViewById(R.id.text_money);
            // 环形进度view
            holder.myInvestmentSpeedView=(MyInvestmentSpeedView)convertView.findViewById(R.id.view_speed_info);
            // 状态
            holder.textHideStatus=(TextView)convertView.findViewById(R.id.text_hide_status);

            // 天数
            holder.textHideDays=(TextView)convertView.findViewById(R.id.text_hide_days);
            holder.textHideSumDays=(TextView)convertView.findViewById(R.id.text_hide_sumDays);

            // 么有更多数据显示
            holder.layoutNoMoreData = (LinearLayout)convertView.findViewById(R.id.layout_no_more);

            //提交时间
            holder.createTime =(TextView)convertView.findViewById(R.id.text_investment_sub_time);

            //流标
            holder.viewSpeedLiubiaoRl =convertView.findViewById(R.id.view_speed_liubiao_rl);

            holder.type_tv =convertView.findViewById(R.id.list_investment_type);
            convertView.setTag(holder);

        }else{
            holder=(ViewHolder)convertView.getTag();

        }

        // 没有更多数据时，底部显示没有更多了.文字
        if(cpage == tpage && position == (getCount()-1)){
            holder.layoutNoMoreData.setVisibility(View.VISIBLE);
        }else{
            holder.layoutNoMoreData.setVisibility(View.GONE);
        }

        /*设置TextView显示的内容，即我们存放在动态数组中的数据*/

        MyInvestBean mBean =data.get(position);
        //名称
        holder.textTilte.setText(mBean.getProductCode()+"");
        //投资本金
        if(!TextUtils.isEmpty(mBean.getPayAmount())){
            holder.textCapitalMoney.setText(UiUtils.formatMoneyToBigDecimal(mBean.getPayAmount(),context.getResources().getString(R.string.defalut_money_separator)));
        }

        // 预期收益
        if(!TextUtils.isEmpty(mBean.getWillIncome())){
            holder.textMoney.setText(UiUtils.formatMoneyToBigDecimal(mBean.getWillIncome()+"", context.getResources().getString(R.string.defalut_money_separator)));
        }
        //购买时间
        if(!TextUtils.isEmpty(mBean.getPayTime())){
            holder.createTime.setText(mBean.getPayTime()+"购买");
        }
        String circlePercent =null;
        if(!TextUtils.isEmpty(mBean.getIncomeDays())){
            double incomedays =Integer.parseInt(mBean.getIncomeDays())+1;
            circlePercent =String.valueOf(incomedays);
        }

        String days=null;
        if(!TextUtils.isEmpty(mBean.getRestDays())){
            days=mBean.getRestDays();
        }

        if(mBean.getType().equals("1")){//债转blue_radius100
            holder.type_tv.setVisibility(View.VISIBLE);
            holder.type_tv.setTextColor(context.getResources().getColor(R.color.blue5));
            holder.type_tv.setBackgroundResource(R.drawable.blue_radius100);
            holder.type_tv.setText("债");
        }else if(mBean.getType().equals("2")){//直投
            holder.type_tv.setVisibility(View.VISIBLE);
            holder.type_tv.setTextColor(context.getResources().getColor(R.color.purple));
            holder.type_tv.setBackgroundResource(R.drawable.purple_radius100);
            holder.type_tv.setText("直");
        }else{
            holder.type_tv.setVisibility(View.GONE);
        }

        int mType =0;
        if(!TextUtils.isEmpty(mBean.getType())){
            mType =Integer.parseInt(mBean.getType());
        }

        try{

            String currentStatus = mBean.getStatus();
            if(!TextUtils.isEmpty(days)&&!TextUtils.isEmpty(currentStatus)&&!TextUtils.isEmpty(circlePercent)){

                holder.textHideDays.setTag(mBean.getRestDays()+1);
                holder.textHideStatus.setTag(mBean.getStatus()+"");
                holder.textHideSumDays.setTag(circlePercent);

                if(TextUtils.equals(Constants.PAY_STATUS_6,currentStatus)){//已回款
                    holder.myInvestmentSpeedView.setProgress(100,Constants.PAY_STATUS_6,"",mType);
                    holder.myInvestmentSpeedView.setVisibility(View.VISIBLE);
                    holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                }else if((TextUtils.equals(Constants.PAY_STATUS_5,currentStatus)||TextUtils.equals(Constants.PAY_STATUS_7,currentStatus)||TextUtils.equals(Constants.PAY_STATUS_71,currentStatus))){//履行中
                    holder.myInvestmentSpeedView.setVisibility(View.VISIBLE);
                    int count =Integer.parseInt(mBean.getIncomeDays())+1;
                    int ration =100- (int) (Double.parseDouble(AmountUtils.div(mBean.getRestDays()+"",count+"",3))*100);
                    new Thread(new ProgressRunable(holder.myInvestmentSpeedView,ration,0,days,currentStatus,mType)).start();
                    holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                }else if(TextUtils.equals(Constants.PAY_STATUS_4,currentStatus)){//锁定期

                    if(mBean.getType().equals("1")){//债转
                        holder.myInvestmentSpeedView.setVisibility(View.VISIBLE);
                        int count =Integer.parseInt(mBean.getIncomeDays())+1;
                        int ration =100- (int) (Double.parseDouble(AmountUtils.div(mBean.getRestDays()+"",count+"",3))*100);
                        new Thread(new ProgressRunable(holder.myInvestmentSpeedView,ration,0,days,currentStatus,mType)).start();
                        holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                    }else if(mBean.getType().equals("2")){//直投
                        holder.myInvestmentSpeedView.setProgress(100,Constants.PAY_STATUS_4,"",mType);
                        holder.myInvestmentSpeedView.setVisibility(View.VISIBLE);
                        holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                    }

                }else {
                    holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                    holder.myInvestmentSpeedView.setVisibility(View.GONE);
                }
            }else{

                if(TextUtils.equals(Constants.PAY_STATUS_4,currentStatus)){//锁定期
                    if(mBean.getType().equals("1")){//债转
                        holder.myInvestmentSpeedView.setVisibility(View.VISIBLE);
                        int count =Integer.parseInt(mBean.getIncomeDays())+1;
                        int ration =100- (int) (Double.parseDouble(AmountUtils.div(mBean.getRestDays()+"",count+"",3))*100);
                        new Thread(new ProgressRunable(holder.myInvestmentSpeedView,ration,0,days,currentStatus,mType)).start();
                        holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                    }else if(mBean.getType().equals("2")){//直投
                        holder.myInvestmentSpeedView.setProgress(100,Constants.PAY_STATUS_4,"",mType);
                        holder.myInvestmentSpeedView.setVisibility(View.VISIBLE);
                        holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                    }

                }else {
                    holder.viewSpeedLiubiaoRl.setVisibility(View.GONE);
                    holder.myInvestmentSpeedView.setVisibility(View.GONE);
                }

            }


        }catch (Exception e){

        }


        return convertView;
    }

    // 设置不同投资情况下的圆形进度条
    // 如：46天到期
    class ProgressRunable implements Runnable {

        MyInvestmentSpeedView myInvestmentSpeedView;
        int mTotalProgress,mCurrentProgress;
        String days;
        String state;
        int mtype;
        public ProgressRunable(MyInvestmentSpeedView myInvestmentSpeed,int mTotal,int mCurrent,String dayVal,String state,int type){
            myInvestmentSpeedView = myInvestmentSpeed;
            mTotalProgress = mTotal;
            mCurrentProgress = mCurrent;
            days = dayVal;
            this.state =state;
            this.mtype =type;
        }
        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress||mCurrentProgress==mTotalProgress) {
                mCurrentProgress += 1;
                myInvestmentSpeedView.setProgress(mCurrentProgress,state,days,mtype);
                try {
                    // 进度加载的速度
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
