package com.hz.zdjfu.application.modle.mine.myasset;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.AccountBalanceBean;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.MyAssetsBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.transationdetail.TransationdetailActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 我的资产界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class MyAssetFragment extends BaseFragment implements MyAssetContract.View {

    private static final String TAG = MyAssetFragment.class.getName();
    @BindView(R.id.myasset_all_asset_tv)
    TextView myassetAllAssetTv;
    @BindView(R.id.myasset_piechart)
    PieChart myassetPiechart;
    @BindView(R.id.myasset_transaction_detail_tv)
    TextView myassetTransactionDetailTv;
    @BindView(R.id.myasset_all_invest_tv)
    TextView myassetAllInvestTv;
    @BindView(R.id.myasset_all_invest_ll)
    LinearLayout myassetAllInvestLl;
    @BindView(R.id.myasset_all_earning_tv)
    TextView myassetAllEarningTv;
    @BindView(R.id.myasset_all_earning_ll)
    LinearLayout myassetAllEarningLl;
    @BindView(R.id.myasset_all_rechange_tv)
    TextView myassetAllRechangeTv;
    @BindView(R.id.myasset_all_rechange_ll)
    LinearLayout myassetAllRechangeLl;
    @BindView(R.id.myasset_all_withdraw_dispost_tv)
    TextView myassetAllWithdrawDispostTv;
    @BindView(R.id.myasset_all_withdraw_dispost_ll)
    LinearLayout myassetAllWithdrawDispostLl;

    @BindView(R.id.myasset_registtime_totalday)
    TextView myassetRegisttimeTotalday;
    @BindView(R.id.myasset_registtime_one)
    TextView myassetRegisttimeOne;
    @BindView(R.id.myasset_registtime_two)
    TextView myassetRegisttimeTwo;
    @BindView(R.id.myasset_registtime_three)
    TextView myassetRegisttimeThree;
    @BindView(R.id.myasset_registtime_day)
    TextView myassetRegisttimeDay;
    @BindView(R.id.myasset_all_earning_title_tv)
    TextView myassetAllEarningTitle_tv;
    @BindView(R.id.myasset_piechart_noempty_ll)
    LinearLayout myassetPiechartNoemptyLl;
    @BindView(R.id.myasset_piechart_empty_ll)
    RelativeLayout myassetPiechartEmptyLl;
    @BindView(R.id.myasset_piechart_empty)
    PieChart myassetPiechartEmpty;
    @BindView(R.id.myasset_all_asset_ll)
    LinearLayout myassetAllAssetLl;
    @BindView(R.id.myasset_all_accountbalance_ll)
    RelativeLayout myassetAllAccountbalanceLl;
    @BindView(R.id.myasset_all_pend_withdraw_ll)
    RelativeLayout myassetAllPendWithdrawLl;
    @BindView(R.id.myasset_all_pend_return_ll)
    RelativeLayout myassetAllPend_returnLl;
    @BindView(R.id.myasset_all_accountbalance_tv)
    TextView myassetAllAccountbalanceTv;
    @BindView(R.id.myasset_all_pend_withdraw_tv)
    TextView myassetAllPendWithdrawTv;
    @BindView(R.id.myasset_all_pend_return_tv)
    TextView myassetAllPendReturnTv;


    private MyAssetContract.Presenter mPresenter;
    private MyAssetsBean mBean;


    public static MyAssetFragment newInstance() {
        return new MyAssetFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myasset;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new MyAssetPresenter(mActivity, this);
        initPieChartView();
        initViewData();

    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }

    }

    @Override
    public void initViewData() {


        float mAllInvest = 0.0f;
        float mAllEarning = 0.0f;
        float mAllRechangeTv = 0.0f;
        float mAllWithdrawDispostTv = 0.0f;
        myassetAllInvestTv.setText(UiUtils.formatMoneyToBigDecimal(mAllInvest + "", getResources().getString(R.string.defalut_money_separator)));
        myassetAllEarningTv.setText(UiUtils.formatMoneyToBigDecimal(mAllEarning + "", getResources().getString(R.string.defalut_money_separator)));
        myassetAllRechangeTv.setText(UiUtils.formatMoneyToBigDecimal(mAllRechangeTv + "", getResources().getString(R.string.defalut_money_separator)));
        myassetAllWithdrawDispostTv.setText(UiUtils.formatMoneyToBigDecimal(mAllWithdrawDispostTv + "", getResources().getString(R.string.defalut_money_separator)));

        if(mPresenter!=null){
            mPresenter.allAssetHttpRequest();
        }

        initPiechartView();
        initPieChartView();


    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(MyAssetContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initPieChartView() {

        myassetPiechart.setHoleRadius(60f);
        Description mDescription = new Description();
        mDescription.setText("");
        myassetPiechart.setDescription(mDescription);
        myassetPiechart.setDrawCenterText(true);
        myassetPiechart.setDrawHoleEnabled(true);
        myassetPiechart.setRotationAngle(90);
        myassetPiechart.setRotationEnabled(false);
        myassetPiechart.setUsePercentValues(false);
        myassetPiechart.setDrawEntryLabels(false);
        Legend mLegend = myassetPiechart.getLegend();
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        mLegend.setOrientation(Legend.LegendOrientation.VERTICAL);
        mLegend.setDrawInside(false);
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(0f);
        mLegend.setYOffset(1f);
        mLegend.setForm(Legend.LegendForm.CIRCLE);
        LegendEntry[] mLegendEntrys = new LegendEntry[1];
        LegendEntry m0LegendEntry = new LegendEntry();
        mLegendEntrys[0] = m0LegendEntry;
        LegendEntry m1LegendEntry = new LegendEntry();
        m1LegendEntry.label = "";
        mLegend.setCustom(mLegendEntrys);
        mLegend.setEnabled(true);
        myassetPiechart.animateXY(1000, 1000);  //设置动画

    }

    @Override
    public void showPieData(MyAssetsBean myAssetsBean) {


        this.mBean =myAssetsBean;

        if(mBean==null){
            return;
        }


        //总资产
        if(!TextUtils.isEmpty(mBean.getTotalAssets())){
            myassetAllAssetTv.setText("¥"+UiUtils.formatMoneyToBigDecimal(mBean.getTotalAssets() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //累计投资
        if(!TextUtils.isEmpty(mBean.getTotalInvest())){
            myassetAllInvestTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getTotalInvest() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //累计收益
        if(!TextUtils.isEmpty(mBean.getTotalIncome())){
            myassetAllEarningTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getTotalIncome() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //累计充值
        if(!TextUtils.isEmpty(mBean.getTotalRecharge())){
            myassetAllRechangeTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getTotalRecharge() + "", getResources().getString(R.string.defalut_money_separator)));
        }


        //累计提现
        if(!TextUtils.isEmpty(mBean.getTotalWithdraw())){
            myassetAllWithdrawDispostTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getTotalWithdraw() + "", getResources().getString(R.string.defalut_money_separator)));
        }


        //加入的天数
        try{
            UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
            if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getCreateTime())){
                String mTime =mUserBean.getCreateTime();
                if(mTime.length()<11){
                    long mtime= Long.parseLong(mUserBean.getCreateTime())*1000L;
                    myassetRegisttimeTotalday.setText(TimeUtils.getYMDtime(mtime)+"注册,已加入正道金服");
                }else{
                    myassetRegisttimeTotalday.setText(TimeUtils.getYMDtime(Long.parseLong(mUserBean.getCreateTime()))+"注册,已加入正道金服");
                }
            }
        }catch (Exception e){

        }


        try{

            if(!TextUtils.isEmpty(mBean.getRegisterDays())){
                if(mBean.getRegisterDays().length()==1){
                    myassetRegisttimeOne.setText("0");
                    myassetRegisttimeTwo.setText("0");
                    myassetRegisttimeThree.setText(mBean.getRegisterDays().substring(0,1));
                }else if(mBean.getRegisterDays().length()==2){
                    myassetRegisttimeOne.setText("0");
                    myassetRegisttimeTwo.setText(mBean.getRegisterDays().substring(0,1));
                    myassetRegisttimeThree.setText(mBean.getRegisterDays().substring(1,2));
                }else{
                    myassetRegisttimeOne.setText(mBean.getRegisterDays().substring(0,1));
                    myassetRegisttimeTwo.setText(mBean.getRegisterDays().substring(1,2));
                    myassetRegisttimeThree.setText(mBean.getRegisterDays().substring(2,3));
                }
            }

        }catch (Exception e){

        }

        //饼图

        List<PieEntry> yValues = new ArrayList<PieEntry>();  //yVals用来表示封装每个饼块的实际数据

        float totalMoney = 0.0f;
        //账户可用金额
        float accountBalance =0.0f;
        if(!TextUtils.isEmpty(mBean.getBalance())){
            accountBalance = Float.parseFloat(mBean.getBalance());
        }

        //冻结金额
        float mLocked_money =0.0f;
        if(!TextUtils.isEmpty(mBean.getFreezeAmount())){
            mLocked_money = Float.parseFloat(mBean.getFreezeAmount());
        }

        //待收金额
        float mPend_amount =0.0f;
        if(!TextUtils.isEmpty(mBean.getPendAmount())){
            mPend_amount = Float.parseFloat(mBean.getPendAmount());
        }


        String totalAmount ="0.00";
        //账户可用金额
        if(accountBalance>0){
            totalAmount= AmountUtils.add(totalAmount,mBean.getBalance());
            myassetAllAccountbalanceLl.setVisibility(View.VISIBLE);
            myassetAllAccountbalanceTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getBalance() + "", getResources().getString(R.string.defalut_money_separator)));
        }
        //冻结金额
        if(mLocked_money>0){
            totalAmount= AmountUtils.add(totalAmount,mBean.getFreezeAmount());
            myassetAllPendWithdrawLl.setVisibility(View.VISIBLE);
            myassetAllPendWithdrawTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getFreezeAmount() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        //待收金额
        if(mPend_amount>0){
            totalAmount= AmountUtils.add(totalAmount,mBean.getPendAmount());
            myassetAllPend_returnLl.setVisibility(View.VISIBLE);
            myassetAllPendReturnTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getPendAmount() + "", getResources().getString(R.string.defalut_money_separator)));
        }

        totalMoney =Float.parseFloat(totalAmount);


        // 饼图数据
        /**
         * 将一个饼形图分成三部分，
         * 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1;
        float quarterly2;
        float quarterly3;

        if (accountBalance == 0) {
            quarterly1 = 0;
        } else {
            quarterly1 = accountBalance / totalMoney;
        }
        if (mLocked_money == 0) {
            quarterly2 = 0;
        } else {
            quarterly2 = mLocked_money / totalMoney;
        }

        if (mPend_amount == 0) {
            quarterly3 = 0;
        } else {
            quarterly3 = mPend_amount / totalMoney;
        }


        // 没有收益时，默认灰色}
        if (quarterly2 == 0
                && quarterly1 == 0
                && quarterly3 == 0
                ) {
            myassetPiechartNoemptyLl.setVisibility(View.GONE);
            myassetPiechartEmptyLl.setVisibility(View.VISIBLE);
            myassetAllAssetLl.setVisibility(View.GONE);
        }else{
            myassetPiechartNoemptyLl.setVisibility(View.VISIBLE);
            myassetAllAssetLl.setVisibility(View.VISIBLE);
            myassetPiechartEmptyLl.setVisibility(View.GONE);
        }

        yValues.add(new PieEntry(quarterly1, 0));
        yValues.add(new PieEntry(quarterly2, 1));
        yValues.add(new PieEntry(quarterly3, 2));


        //y轴的集合 /*显示在比例图上*/
        PieDataSet pieDataSet = new PieDataSet(yValues, "");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        pieDataSet.setDrawValues(false);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        colors.add(getResources().getColor(R.color.blue9)); // 账户可用金额
        colors.add(getResources().getColor(R.color.blue5)); // 冻结金额
        colors.add(getResources().getColor(R.color.red5)); // 待收金额
        pieDataSet.setColors(colors);

        PieData data = new PieData();
        data.setDataSet(pieDataSet);
        myassetPiechart.setData(data);
    }

    @Override
    public void initPiechartView() {

        List<PieEntry> yValues = new ArrayList<PieEntry>();
        float quarterly1 = 100;
        yValues.add(new PieEntry(quarterly1, 0));
        PieDataSet pieDataSet = new PieDataSet(yValues, "");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        pieDataSet.setDrawValues(false);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        pieDataSet.setColors(getResources().getColor(R.color.total_assets_0));
        PieData data = new PieData();
        data.setDataSet(pieDataSet);
        myassetPiechartEmpty.setHoleRadius(60f);
        Description mDescription = new Description();
        mDescription.setText("");
        myassetPiechartEmpty.setDescription(mDescription);
        myassetPiechartEmpty.setDrawCenterText(true);
        myassetPiechartEmpty.setDrawHoleEnabled(true);
        myassetPiechartEmpty.setRotationAngle(90);
        myassetPiechartEmpty.setRotationEnabled(false);
        myassetPiechartEmpty.setUsePercentValues(false);
        myassetPiechartEmpty.setData(data);
        myassetPiechartEmpty.setDrawEntryLabels(false);
        Legend mLegend = myassetPiechartEmpty.getLegend();
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        mLegend.setOrientation(Legend.LegendOrientation.VERTICAL);
        mLegend.setDrawInside(false);
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(0f);
        mLegend.setYOffset(1f);
        mLegend.setForm(Legend.LegendForm.CIRCLE);
        LegendEntry[] mLegendEntrys = new LegendEntry[1];
        LegendEntry m0LegendEntry = new LegendEntry();
        mLegendEntrys[0] = m0LegendEntry;
        LegendEntry m1LegendEntry = new LegendEntry();
        m1LegendEntry.label = "";
        mLegend.setCustom(mLegendEntrys);
        mLegend.setEnabled(true);
        myassetPiechartEmpty.animateXY(1000, 1000);  //设置动画


    }


    @OnClick({R.id.myasset_all_invest_ll, R.id.myasset_all_earning_ll, R.id.myasset_all_rechange_ll, R.id.myasset_all_withdraw_dispost_ll, R.id.myasset_transaction_detail_tv, R.id.myasset_all_earning_discounts_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myasset_all_invest_ll:

                break;
            case R.id.myasset_all_earning_ll:

                break;
            case R.id.myasset_all_rechange_ll:

                break;
            case R.id.myasset_all_withdraw_dispost_ll:

                break;
            case R.id.myasset_transaction_detail_tv:
                startActivity(TransationdetailActivity.makeIntent(mActivity, null));
                break;
            case R.id.myasset_all_earning_discounts_ll:
                if(myassetAllEarningTitle_tv.getText().equals(getResources().getString(R.string.myasset_all_earning))){
                    myassetAllEarningTitle_tv.setText(getResources().getString(R.string.myasset_all_usered_coupon)+"");
                    //累计优惠 ？
                    if(mBean!=null&&!TextUtils.isEmpty(mBean.getTotalCoupon())){
                        myassetAllEarningTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getTotalCoupon() + "", getResources().getString(R.string.defalut_money_separator)));
                    }

                }else {
                    myassetAllEarningTitle_tv.setText(getResources().getString(R.string.myasset_all_earning)+"");
                    //累计收益
                    if(mBean!=null&&!TextUtils.isEmpty(mBean.getTotalIncome())){
                        myassetAllEarningTv.setText(UiUtils.formatMoneyToBigDecimal(mBean.getTotalIncome() + "", getResources().getString(R.string.defalut_money_separator)));
                    }
                }
                break;
        }
    }


}
