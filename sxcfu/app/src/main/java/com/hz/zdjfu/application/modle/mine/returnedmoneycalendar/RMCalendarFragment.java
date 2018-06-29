package com.hz.zdjfu.application.modle.mine.returnedmoneycalendar;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.BackMoneyCalendarLists;
import com.hz.zdjfu.application.data.bean.BackMoneyLists;
import com.hz.zdjfu.application.data.bean.BackMoneyMonthBean;
import com.hz.zdjfu.application.data.bean.DayDataBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.utils.TimeAllUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.view.CustomCalendar;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class RMCalendarFragment extends BaseFragment implements RMCalendarContract.View {

    private static final String TAG = RMCalendarFragment.class.getName();
    @BindView(R.id.rmcalendar_month_all_money_tv)
    TextView rmcalendarMonthAllMoneyTv;
    @BindView(R.id.rmcalendar_month_all_money_detail_tv)
    TextView rmcalendarMonthAllMoneyDetailTv;
    @BindView(R.id.rmcalendar_month_duein_money_tv)
    TextView rmcalendarMonthDueinMoneyTv;
    @BindView(R.id.rmcalendar_month_duein_money_detail_tv)
    TextView rmcalendarMonthDueinMoneyDetailTv;
    @BindView(R.id.rmcalendar_calendar_cv)
    CustomCalendar rmcalendarCalendarCv;
    @BindView(R.id.rmcalendar_more_earning)
    TextView rmcalendarMoreEarning;
    @BindView(R.id.rmcalendar_backmoney_detail_rv)
    RecyclerView rmcalendarBackmoneyDetailRv;
    @BindView(R.id.rmcalendar_time_tv)
    TextView rmcalendarTimeTv;
    @BindView(R.id.rmcalendar_backmoney_tv)
    TextView rmcalendarBackmoneyTv;
    @BindView(R.id.rmcalendar_empty_tv)
    LinearLayout rmcalendarEmptyTv;

    private RMCalendarAdapter mAdapter;
    private RMCalendarContract.Presenter mPresenter;
    private String mNowMonth =null;
    private UserBean mUserBean;
    private BackMoneyLists backMoneyCalendarLists;
    public static RMCalendarFragment newInstance() {
        return new RMCalendarFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(RMCalendarContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initViewData() {

        //默认当前
        final List<DayDataBean> list = new ArrayList<>();
        Long mtime =TimeAllUtils.getCurrentTimeInLong();
        String mYM =TimeAllUtils.getYMtimeStr(mtime);
        for(int i=0;i<31;i++){
            DayDataBean dayDataBean = new DayDataBean(i+1,0,0);
            list.add(dayDataBean);
        }
        rmcalendarCalendarCv.setRenwu(mYM, list);
        rmcalendarCalendarCv.setOnClickListener(new CustomCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {
                rmcalendarCalendarCv.monthChange(-1);
                mNowMonth =rmcalendarCalendarCv.getNowMonth();
                Log.i(TAG,"当前月份:"+mNowMonth);
                if(mPresenter != null){
                    if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())&&!TextUtils.isEmpty(mNowMonth)){
                        mPresenter.rmcalendarHttpRequest(mUserBean.getUserPhone(),mNowMonth);
                    }
                }

            }

            @Override
            public void onRightRowClick() {
                rmcalendarCalendarCv.monthChange(1);
                mNowMonth =rmcalendarCalendarCv.getNowMonth();
                Log.i(TAG,"当前月份:"+mNowMonth);
                if(mPresenter != null){
                    if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())&&!TextUtils.isEmpty(mNowMonth)){
                        mPresenter.rmcalendarHttpRequest(mUserBean.getUserPhone(),mNowMonth);
                    }
                }
            }

            @Override
            public void onTitleClick(String monthStr, Date month) {

            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {

            }

            @Override
            public void onDayClick(int day, String dayStr, DayDataBean finish) {
                if(!TextUtils.isEmpty(dayStr)&&backMoneyCalendarLists!=null){
                    String ymd =TimeAllUtils.getYMDStr(TimeAllUtils.getYMDayDate(dayStr));
                    showBottomData(ymd,backMoneyCalendarLists);
                }
            }
        });

    }

    @Override
    public void saveRMCalendarData(String month,BackMoneyLists mBackMoneyCalendarLists) {

        if(mBackMoneyCalendarLists==null){
            return;
        }
        this.backMoneyCalendarLists =mBackMoneyCalendarLists;

        //显示头部数据
        showTopData(backMoneyCalendarLists);

        //获取底部数据
        Long mtime =TimeAllUtils.getCurrentTimeInLong();
        String mYM =TimeAllUtils.getYMtime(mtime);
        if(mYM.equals(month)){//同月则显示当天 不是则显示其他月份的1号
            showBottomData(TimeAllUtils.getYMDtime(mtime),backMoneyCalendarLists);
        }else{
            StringBuffer buffer = new StringBuffer();
            buffer.append(month).append("-01");
            showBottomData(buffer.toString(),backMoneyCalendarLists);
        }

        //显示整个月的数据
        if(mBackMoneyCalendarLists.getRefundForDay()!=null){
            showCalendarData(month,backMoneyCalendarLists.getRefundForDay());
        }


    }



    @Override
    public void skipActivity(Class<?> mClass) {

    }

    @Override
    public void showTopData(BackMoneyLists mBackMoneyLists) {

        if(mBackMoneyLists!=null){

            if(!TextUtils.isEmpty(mBackMoneyLists.getTotalIncome())){
                rmcalendarMonthAllMoneyTv.setText(UiUtils.formatMoneyToBigDecimal(mBackMoneyLists.getTotalIncome(),mActivity.getResources().getString(R.string.defalut_money_separator))+"元");
            }

            if(!TextUtils.isEmpty(mBackMoneyLists.getAmt())&&!TextUtils.isEmpty(mBackMoneyLists.getInterest())){
                rmcalendarMonthAllMoneyDetailTv.setText("（本"+UiUtils.formatMoneyToBigDecimal(mBackMoneyLists.getAmt(),mActivity.getResources().getString(R.string.defalut_money_separator))+"+息"+UiUtils.formatMoneyToBigDecimal(mBackMoneyLists.getInterest(),mActivity.getResources().getString(R.string.defalut_money_separator))+")");
            }

            if(!TextUtils.isEmpty(mBackMoneyLists.getWillIncome())){
                rmcalendarMonthDueinMoneyTv.setText(UiUtils.formatMoneyToBigDecimal(mBackMoneyLists.getWillIncome(),mActivity.getResources().getString(R.string.defalut_money_separator))+"元");
            }

            if(!TextUtils.isEmpty(mBackMoneyLists.getWillAmt())&&!TextUtils.isEmpty(mBackMoneyLists.getWillInterest())){
                rmcalendarMonthDueinMoneyDetailTv.setText("（本"+UiUtils.formatMoneyToBigDecimal(mBackMoneyLists.getWillAmt(),mActivity.getResources().getString(R.string.defalut_money_separator))+"+息"+UiUtils.formatMoneyToBigDecimal(mBackMoneyLists.getWillInterest(),mActivity.getResources().getString(R.string.defalut_money_separator))+")");
            }

        }

    }

    @Override
    public void showBottomData(String day,BackMoneyLists mBackMoneyLists) {

        try {
            if(mBackMoneyLists!=null&&mBackMoneyLists.getRefundForDay()!=null&&mBackMoneyLists.getRefundForDay().size()>0){

                for(int i=0;i<mBackMoneyLists.getRefundForDay().size();i++){
                    if(mBackMoneyLists.getRefundForDay().get(i).getRefundDate().equals(day)){
                        BackMoneyMonthBean mBean=mBackMoneyLists.getRefundForDay().get(i);
                        if(mBean!=null){

                            //显示所有金额
                            Double total_amount =0.00;
                            if(mBean.getRefundForDayDtoList()!=null&&mBean.getRefundForDayDtoList().size()>0){
                                //每月有多少笔 总金额
                                for(int j =0;j<mBean.getRefundForDayDtoList().size();j++){
                                    total_amount =total_amount+mBean.getRefundForDayDtoList().get(j).getReturnTotal();
                                }
                            }
                            if(Integer.parseInt(mBean.getReturnNum())>0){
                                if(mBean.getStatus().equals("-1")){
                                    rmcalendarBackmoneyTv.setText("今日应收"+mBean.getReturnNum()+"笔回款,共"+UiUtils.formatMoneyToBigDecimal(total_amount+"",mActivity.getResources().getString(R.string.defalut_money_separator))+"元");
                                }else if(mBean.getStatus().equals("1")){
                                    rmcalendarBackmoneyTv.setText("今日已收"+mBean.getReturnNum()+"笔回款,共"+UiUtils.formatMoneyToBigDecimal(total_amount+"",mActivity.getResources().getString(R.string.defalut_money_separator))+"元");
                                }

                                String content =rmcalendarBackmoneyTv.getText().toString();
                                if(!TextUtils.isEmpty(content)){
                                    SpannableString sp = new SpannableString(content);
                                    //笔数颜色
                                    sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red4)),4,mBean.getReturnNum().length()+4,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                    //总共资金颜色
                                    int totalLength =content.length();
                                    int amouintLength =String.valueOf(UiUtils.formatMoneyToBigDecimal(total_amount+"",mActivity.getResources().getString(R.string.defalut_money_separator))).length();
                                    sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red4)),totalLength-amouintLength-1,totalLength-1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                    rmcalendarBackmoneyTv.setText(sp);
                                }

                            }

                            //列表显示期数
                            if(mBean.getRefundForDayDtoList()!=null&&mBean.getRefundForDayDtoList().size()>0){
                                rmcalendarBackmoneyDetailRv.setVisibility(View.VISIBLE);
                                rmcalendarEmptyTv.setVisibility(View.GONE);
                                if(mAdapter==null){
                                    mAdapter =new RMCalendarAdapter(mActivity);
                                    rmcalendarBackmoneyDetailRv.setAdapter(mAdapter);
                                }
                                if(mAdapter!=null){
                                    mAdapter.setNewData(mBean.getRefundForDayDtoList());
                                }
                            }

                        }
                        break;
                    }else{
                        rmcalendarBackmoneyTv.setText("");
                        rmcalendarEmptyTv.setVisibility(View.VISIBLE);
                        rmcalendarBackmoneyDetailRv.setVisibility(View.GONE);
                    }
                }


            }else{
                rmcalendarBackmoneyTv.setText("");
                rmcalendarEmptyTv.setVisibility(View.VISIBLE);
                rmcalendarBackmoneyDetailRv.setVisibility(View.GONE);
            }

            rmcalendarTimeTv.setText(day+"");

        }catch (Exception e){
          throw   new IllegalStateException("RMCalendarFragment show bottomdata is error");
        }


    }

    @Override
    public void showCalendarData(String month,List<BackMoneyMonthBean> mlists) {

      //  try{
            List<DayDataBean> dayList = new ArrayList<>();
            if(mlists!=null&&mlists.size()>0){
                for(int i=0;i<31;i++) {
                    int day = i + 1;
                    DayDataBean mBean = new DayDataBean(day,0,0);
                    for (int j = 0; j < mlists.size(); j++) {
                        //判断是否这一天是否有款项
                        BackMoneyMonthBean mBackMoneyMonthBean = mlists.get(j);
                        String mDay =mBackMoneyMonthBean.getRefundDate().substring(mBackMoneyMonthBean.getRefundDate().length() - 2, mBackMoneyMonthBean.getRefundDate().length());
                        StringBuffer  lastDay =new StringBuffer();
                        if(day<10){
                            lastDay.append("0").append(day);
                        }else{
                            lastDay.append(day);
                        }
                        if (mDay.equals(lastDay.toString())) {
                            int mState =0;//状态
                            if(mBackMoneyMonthBean.getStatus().equals("-1")){
                                mState =1;
                            }else if(mBackMoneyMonthBean.getStatus().equals("1")){
                                mState =2;
                            }else{
                                mState =0;
                            }
                            mBean.setDay(day);
                            mBean.setState(mState);
                            mBean.setSize(Integer.parseInt(mBackMoneyMonthBean.getReturnNum()));
                            break;
                        }
                    }
                    //保存
                    dayList.add(mBean);
                }
            }else{
                for(int i=0;i<31;i++) {
                    int day = i + 1;
                    DayDataBean mBean =new DayDataBean(day,0,0);
                    dayList.add(mBean);
                }
            }

            if(!TextUtils.isEmpty(month)){
                String dateStr =TimeAllUtils.getYMStr(TimeAllUtils.getYMDate(month));
                rmcalendarCalendarCv.setRenwu(dateStr,dayList);
            }

//        }catch (Exception e){
//            throw   new IllegalStateException("RMCalendarFragment show showCalendarData is error");
//        }

    }

    @Override
    public void initRecycleView() {

        mAdapter = new RMCalendarAdapter(mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rmcalendarBackmoneyDetailRv.setLayoutManager(manager);
        rmcalendarBackmoneyDetailRv.setAdapter(mAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rmcalendar;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new RMCalendarPresenter(mActivity, this);
        initViewData();
        initRecycleView();
        mUserBean =UserInfoManager.getInstance().getLocationUserData();
        Long mtime = TimeAllUtils.getCurrentTimeInLong();
        String mYM =TimeAllUtils.getYMtime(mtime);
        if(mPresenter != null){
            if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())&&!TextUtils.isEmpty(mYM)){
                mPresenter.rmcalendarHttpRequest(mUserBean.getUserPhone(),mYM);
            }
        }

    }


    @OnClick(R.id.rmcalendar_more_earning)
    public void onViewClicked() {
        //跳转投资页面
        startActivity(MainActivity.makeIntent(mActivity,null));
        EventBus.getDefault().post(new MainFromTagEven("FINANCIAL_TAB"));
        mActivity.finish();
    }


}
