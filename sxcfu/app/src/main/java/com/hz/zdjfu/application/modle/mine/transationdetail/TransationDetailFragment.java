package com.hz.zdjfu.application.modle.mine.transationdetail;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.TransationDetail;
import com.hz.zdjfu.application.data.bean.TransationDetailBean;
import com.hz.zdjfu.application.data.bean.TransationDetailList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.pulltorefresh.PullToRefreshLayout;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.EmptyLayout;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 * 交易明细界面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class TransationDetailFragment extends BaseFragment implements TransationdetailContract.View {

    private static final String TAG = TransationDetailFragment.class.getName();
    @BindView(R.id.transationdetail_radiob_tab_all)
    RadioButton transationdetailRadiobTabAll;
    @BindView(R.id.transationdetail_rechange_rb)
    RadioButton transationdetailRechangeRb;
    @BindView(R.id.transationdetail_withdrawdeposit_rb)
    RadioButton transationdetailWithdrawdepositRb;
    @BindView(R.id.transationdetail_invest_rb)
    RadioButton transationdetailInvestRb;
    @BindView(R.id.transationdetail_backmoney_rb)
    RadioButton transationdetailBackmoneyRb;
    @BindView(R.id.cursor)
    ImageView cursor;
    @BindView(R.id.tableRow2)
    TableRow tableRow2;
    @BindView(R.id.transationdetail_vPager)
    ViewPager transationdetailVPager;






    private List<View> listViews;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View allView,rechangeView,withdrawdepositView,investView,backmoneyView;
    private LayoutInflater mInflater;
    private PullableListView dataAllRefreshList,rechangeRefreshList,withdrawdepositRefreshList,investRefreshList,backmoneyRefreshList;
    private List<TransationDetail> dataAllData,rechangeListData,withdrawdepositListData,investListData,backmoneyListData;
    private TransationDetailAdapter dataAllAdapter,rechangeAdapter,withdrawdepositAdapter,investAdapter,backmoneyAdapter;
    private int currType;// 请求类型
    private int currPage ; // 当前页
    private int pageTotal = 0; // 总页数
    private UserBean mBean;
    private PullToRefreshLayout allRefreshView,rechangeRefreshView,withdrawdepositRefreshView,investRefreshView,backmoneyRefreshView;
    private EmptyLayout allEmptyLayout,rechangeEmptyLayout,withdrawdepositEmptyLayout,investEmptyLayout,backmoneyEmptyLayout;
    private List<TransationDetail> mTransationLists;


    private TransationdetailContract.Presenter mPresenter;
    private List<TransationDetailBean> mTransationDetailLists;
    public static TransationDetailFragment newInstance() {
        return new TransationDetailFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_transationdetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new TransationdetailPresenter(mActivity, this);
        initView();
        initViewData();
        initRadioButton();
        initViewPageData();
        initImageView();

    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }

    }

    @Override
    public void initViewData() {
        mTransationDetailLists = new ArrayList<>();
        mInflater = mActivity.getLayoutInflater();
        mBean = UserInfoManager.getInstance().getUserBean();
      //  mTransationLists = new ArrayList<>();
        //默认全部
        currType = 0;
        currPage = 1;
        hander.sendEmptyMessage(0);


    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(TransationdetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initView() {






    }

    @Override
    public void initViewPageData() {

        dataAllData = new ArrayList<>();
        rechangeListData =new ArrayList<>();
        withdrawdepositListData = new ArrayList<>();
        investListData = new ArrayList<>();
        backmoneyListData = new ArrayList<>();

        // 全部 布局加载 viewPage
        allView = mInflater.inflate(R.layout.view_refresh_list_layout,null);
        allRefreshView = ((PullToRefreshLayout) allView.findViewById(R.id.refresh_view));
        allRefreshView.setOnRefreshListener(new AllFefreshListener());
        allRefreshView.setIsListOrHomeRefresh(false,false);

        dataAllRefreshList = (PullableListView) allView.findViewById(android.R.id.list);
        allEmptyLayout= new EmptyLayout(mActivity, dataAllRefreshList);
        dataAllAdapter = new TransationDetailAdapter(mActivity,dataAllData,dataAllRefreshList);
        dataAllRefreshList.setAdapter(dataAllAdapter);
        dataAllRefreshList.setOnItemClickListener(new MyOnItemClickListener());

        // 充值
        rechangeView = mInflater.inflate(R.layout.view_refresh_list_layout, null);
        rechangeRefreshView = ((PullToRefreshLayout) rechangeView.findViewById(R.id.refresh_view));
        rechangeRefreshView.setOnRefreshListener(new AllFefreshListener());
        rechangeRefreshView.setIsListOrHomeRefresh(false,false);

        rechangeRefreshList = (PullableListView) rechangeRefreshView.findViewById(android.R.id.list);
        rechangeEmptyLayout= new EmptyLayout(mActivity, rechangeRefreshList);
        rechangeAdapter= new TransationDetailAdapter(mActivity,rechangeListData,rechangeRefreshList);
        rechangeRefreshList.setAdapter(rechangeAdapter);
        rechangeRefreshList.setOnItemClickListener(new MyOnItemClickListener());

        // 提现
        withdrawdepositView = mInflater.inflate(R.layout.view_refresh_list_layout, null);
        withdrawdepositRefreshView = ((PullToRefreshLayout) withdrawdepositView.findViewById(R.id.refresh_view));
        withdrawdepositRefreshView.setOnRefreshListener(new AllFefreshListener());
        withdrawdepositRefreshView.setIsListOrHomeRefresh(false,false);

        withdrawdepositRefreshList = (PullableListView) withdrawdepositRefreshView.findViewById(android.R.id.list);
        withdrawdepositEmptyLayout = new EmptyLayout(mActivity, withdrawdepositRefreshList);
        withdrawdepositAdapter = new TransationDetailAdapter(mActivity,withdrawdepositListData,withdrawdepositRefreshList);
        withdrawdepositRefreshList.setAdapter(withdrawdepositAdapter);
        withdrawdepositRefreshList.setOnItemClickListener(new MyOnItemClickListener());



        // 投资
        investView = mInflater.inflate(R.layout.view_refresh_list_layout, null);
        investRefreshView = ((PullToRefreshLayout) investView.findViewById(R.id.refresh_view));
        investRefreshView.setOnRefreshListener(new AllFefreshListener());
        investRefreshView.setIsListOrHomeRefresh(false,false);

        investRefreshList = (PullableListView) investRefreshView.findViewById(android.R.id.list);
        investEmptyLayout = new EmptyLayout(mActivity,investRefreshList);
        investAdapter = new TransationDetailAdapter(mActivity,investListData,investRefreshList);
        investRefreshList.setAdapter(investAdapter);
        investRefreshList.setOnItemClickListener(new MyOnItemClickListener());


        // 回款
        backmoneyView = mInflater.inflate(R.layout.view_refresh_list_layout, null);
        backmoneyRefreshView = ((PullToRefreshLayout) backmoneyView.findViewById(R.id.refresh_view));
        backmoneyRefreshView.setOnRefreshListener(new AllFefreshListener());
        backmoneyRefreshView.setIsListOrHomeRefresh(false,false);

        backmoneyRefreshList = (PullableListView) backmoneyRefreshView.findViewById(android.R.id.list);
        backmoneyEmptyLayout = new EmptyLayout(mActivity,backmoneyRefreshList);
        backmoneyAdapter = new TransationDetailAdapter(mActivity,backmoneyListData,backmoneyRefreshList);
        backmoneyRefreshList.setAdapter(backmoneyAdapter);
        backmoneyRefreshList.setOnItemClickListener(new MyOnItemClickListener());



        listViews = new ArrayList<View>();
        listViews.add(allView);
        listViews.add(rechangeView);
        listViews.add(withdrawdepositView);
        listViews.add(investView);
        listViews.add(backmoneyView);

        transationdetailVPager.setAdapter(new MyPagerAdapter(listViews));
        transationdetailVPager.setCurrentItem(0);
        transationdetailVPager.setOnPageChangeListener(new MyOnPageChangeListener());
        transationdetailVPager.setOffscreenPageLimit(3);


        changeTextColor(true,false,false,false,false);
    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;
        public MyOnClickListener(int i) {
            index = i;
        }
        public void onClick(View v) {
            transationdetailVPager.setCurrentItem(index);
        }
    };


    @Override
    public void initRadioButton() {

        transationdetailRadiobTabAll.setOnClickListener(new MyOnClickListener(0));
        transationdetailRechangeRb.setOnClickListener(new MyOnClickListener(1));
        transationdetailWithdrawdepositRb.setOnClickListener(new MyOnClickListener(2));
        transationdetailInvestRb.setOnClickListener(new MyOnClickListener(3));
        transationdetailBackmoneyRb.setOnClickListener(new MyOnClickListener(4));

    }

    @Override
    public void initImageView() {
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.a).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW /5 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }


    @Override
    public void setEmptyData() {

        //全部为空
        if(dataAllData.size() == 0 && 0 == currType){
            allEmptyLayout.showEmpty();
        }
        //充值为空
        if(rechangeListData.size() == 0 && 11 == currType){
            rechangeEmptyLayout.showEmpty();
        }
        //提现为空
        if(withdrawdepositListData.size() == 0 && 21 == currType){
            withdrawdepositEmptyLayout.showEmpty();
        }

        //投资为空
        if(investListData.size() == 0 && 22 == currType){
            investEmptyLayout.showEmpty();
        }
        //回款为空
        if(backmoneyListData.size() == 0 && 12 == currType){
            backmoneyEmptyLayout.showEmpty();
        }


    }

    @Override
    public void getData(int type, int page) {

        //全部
        if( 0 == currType){
            allEmptyLayout.showLoading();
        }

        //充值
        if( 11 == currType){
            rechangeEmptyLayout.showLoading();
        }

        //提现中
        if( 21 == currType){
            withdrawdepositEmptyLayout.showLoading();
        }

        //投资中
        if( 22 == currType){
            investEmptyLayout.showLoading();
        }

        //回款中
        if( 12 == currType){
            backmoneyEmptyLayout.showLoading();
        }


        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
            mPresenter.transationDetailHttpRequest(mBean.getUserPhone(),currType,currPage); // 默认加载 "0": 全部 "4"：履约中  "2"：已回款
        }


    }

    @Override
    public void showTransationDetailListsData(List<TransationDetailBean> mLists) {

        if(mLists!=null&&mLists.size()>0){

            if(currPage == 1){ // 下拉刷新，始终加载第一页

                if(mTransationLists!=null){
                    mTransationLists.clear();
                }

                if(mTransationDetailLists!=null&&mTransationDetailLists.size()>0){
                    mTransationDetailLists.clear();
                }

                // 原有的数据清空
                if(0 == currType){// 默认查询全部
                    dataAllData.clear();
                }else if(11 == currType){
                    rechangeListData.clear();
                }else if(21 == currType){
                    withdrawdepositListData.clear();
                } else if(22 == currType){
                    investListData.clear();
                }else if(12 == currType){
                    backmoneyListData.clear();
                }
            }
            //处理时间显示数据
            changeData(mLists);

            if(0 == currType){//默认查询全部
                if(dataAllData!=null&&dataAllData.size()>0){
                    dataAllData.clear();
                }
                dataAllData.addAll(mTransationLists);
                dataAllAdapter.notifyDataSetChanged();
            }else if(11 == currType){
                if(rechangeListData!=null&&rechangeListData.size()>0){
                    rechangeListData.clear();
                }
                rechangeListData.addAll(mTransationLists);
                rechangeAdapter.notifyDataSetChanged();
            } else if(21 == currType){
                if(withdrawdepositListData!=null&&withdrawdepositListData.size()>0){
                    withdrawdepositListData.clear();
                }
                withdrawdepositListData.addAll(mTransationLists);
                withdrawdepositAdapter.notifyDataSetChanged();
            }else if(22 == currType){
                if(investListData!=null&&investListData.size()>0){
                    investListData.clear();
                }
                investListData.addAll(mTransationLists);
                investAdapter.notifyDataSetChanged();
            }else if(12 == currType){
                if(backmoneyListData!=null&&backmoneyListData.size()>0){
                    backmoneyListData.clear();
                }
                backmoneyListData.addAll(mTransationLists);
                backmoneyAdapter.notifyDataSetChanged();
            }

        }else{
            setEmptyData();
        }


    }

    @Override
    public void changeTextColor(boolean one, boolean two, boolean three, boolean four, boolean five) {


        if(one){
            transationdetailRadiobTabAll.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            transationdetailRadiobTabAll.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(two){
            transationdetailRechangeRb.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            transationdetailRechangeRb.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(three){
            transationdetailWithdrawdepositRb.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            transationdetailWithdrawdepositRb.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(four){
            transationdetailInvestRb.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            transationdetailInvestRb.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(five){
            transationdetailBackmoneyRb.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            transationdetailBackmoneyRb.setTextColor(getResources().getColor(R.color.colorBack3));
        }


    }

    @Override
    public void changeData(List<TransationDetailBean> lists) {

        if(lists!=null){
            mTransationDetailLists.addAll(lists);
        }
         String temp = "";
        mTransationLists = new ArrayList<>();
        for(int i =0; i< mTransationDetailLists.size();i++){

            TransationDetailBean miBean =mTransationDetailLists.get(i);
//            String miMonth= TimeUtils.getYMtime(miBean.getCreate_time());

            String miMonth= TimeUtils.getYMStr(miBean.getTime());
            TransationDetail bean = new TransationDetail();
            bean.setAmount(miBean.getActionName());
            bean.setCreate_time(miBean.getTime());
            bean.setOperate_type(miBean.getOperateType()+"");
            bean.setStatus(miBean.getStatus()+"");
            bean.setSummary(miBean.getStatusName());
            bean.setmMonth(miMonth);
            bean.setAction(miBean.getAction()+"");
            if(!temp.equals(miMonth)){
                temp =miMonth;
            }else{
                bean.setmMonth("");
            }
            mTransationLists.add(bean);
        }

    }



    private class AllFefreshListener implements PullToRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            currPage = 1;
            hander.sendEmptyMessage(0);

            // 下拉刷新操作
            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, Constants.UP_DOWN_REFRESH_TIME);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
//            if(pageTotal != currPage){
//                currPage ++;
//                hander.sendEmptyMessage(0);
//
//                new Handler()
//                {
//                    @Override
//                    public void handleMessage(Message msg)
//                    {
//                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//                    }
//                }.sendEmptyMessageDelayed(0, Constants.UP_DOWN_REFRESH_TIME);
//
//            }else{
//                // 没有更多数据了
//                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.ALL_NODATA);
//            }

            currPage ++;
            hander.sendEmptyMessage(0);

            new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, Constants.UP_DOWN_REFRESH_TIME);

        }
    }




    // 产品介绍和投资记录的tab选项卡适配器
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;
        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }
        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }



    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageSelected(int position) {
            int one = offset * 2 + bmpW;
            Animation animation = new TranslateAnimation(one * currIndex, one* position, 0, 0);
            currIndex = position;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);

            switch (position) {
                case 0:
                    changeTextColor(true,false,false,false,false);
                    currType = 0;
                    currPage = 1;
                    dataAllData.clear();
                    if(mTransationLists!=null){
                        mTransationLists.clear();
                    }
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    dataAllRefreshList.setSelection(1);
                    transationdetailRadiobTabAll.setChecked(true);
                    break;
                case 1:
                    changeTextColor(false,true,false,false,false);
                    transationdetailRechangeRb.setChecked(true);
                    rechangeListData.clear();
                    if(mTransationLists!=null){
                        mTransationLists.clear();
                    }
                    currType = 11;
                    currPage = 1;
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    rechangeRefreshList.setSelection(1);//刷新后，直接定位到第一条
                    break;
                case 2:
                    changeTextColor(false,false,true,false,false);
                    transationdetailWithdrawdepositRb.setChecked(true);
                    withdrawdepositListData.clear();
                    if(mTransationLists!=null){
                        mTransationLists.clear();
                    }
                    currType = 21;
                    currPage = 1;
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    withdrawdepositRefreshList.setSelection(1);//刷新后，直接定位到第一条
                    break;
                case 3:
                    changeTextColor(false,false,false,true,false);
                    transationdetailInvestRb.setChecked(true);
                    currType = 22;
                    currPage = 1;
                    investListData.clear();
                    if(mTransationLists!=null){
                        mTransationLists.clear();
                    }
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    investRefreshList.setSelection(1);//刷新后，直接定位到第一条
                    break;

                case 4:
                    changeTextColor(false,false,false,false,true);
                    transationdetailBackmoneyRb.setChecked(true);
                    currType = 12;
                    currPage = 1;
                    backmoneyListData.clear();
                    if(mTransationLists!=null){
                        mTransationLists.clear();
                    }
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    backmoneyRefreshList.setSelection(1);//刷新后，直接定位到第一条
                    break;
            }


        }

        //        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        //        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }


    // 更新 数据
    private Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
                mPresenter.transationDetailHttpRequest(mBean.getUserPhone(),currType,currPage); // 默认加载 "0": 全部 "4"：履约中  "2"：已回款
            }

        }
    };



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActivity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }


}
