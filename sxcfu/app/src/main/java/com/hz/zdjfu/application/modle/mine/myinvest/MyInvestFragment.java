package com.hz.zdjfu.application.modle.mine.myinvest;

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
import android.widget.RadioGroup;
import android.widget.TableRow;

import com.facebook.stetho.common.StringUtil;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.MyInvestBean;
import com.hz.zdjfu.application.data.bean.MyInvestList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail.ZTBuyProductDetailActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.widget.pulltorefresh.PullToRefreshLayout;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.EmptyLayout;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.PullableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 * 我的投资
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/30 0030.
 */

public class MyInvestFragment extends BaseFragment implements MyInvestContract.View {
    private static final String TAG = MyInvestFragment.class.getName();
    @BindView(R.id.radiob_tab_all)
    RadioButton radiobTabAll;
    @BindView(R.id.radiob_tab_promiseIng)
    RadioButton radiobTabPromiseIng;
    @BindView(R.id.radiob_tab_repaymented)
    RadioButton radiobTabRepaymented;
    @BindView(R.id.radioButtons)
    RadioGroup radioButtons;
    @BindView(R.id.cursor)
    ImageView cursor;
    @BindView(R.id.tableRow2)
    TableRow tableRow2;
    @BindView(R.id.my_investment_vPager)
    ViewPager myInvestmentVPager;


    private List<View> listViews;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View allView,promiseIngView,repaymentedView;
    private LayoutInflater mInflater;
    private PullableListView dataAllRefreshList,promiseIngRefreshList,repaymentedRefreshList;
    private List<MyInvestBean> dataAllData,promiseIngListData,repaymentedListData;
    private MyInvestAdapter dataAllAdapter,promiseIngRefreshAdapter,repaymentedRefreshAdapter;
    private int currType;// 请求类型
    private int currPage ; // 当前页
    private int pageTotal = 0; // 总页数

    private PullToRefreshLayout allRefreshView,promiseIngRefreshView,repaymentedRefreshView;
    private EmptyLayout allEmptyLayout,promiseIngEmptyLayout,repaymentedEmptyLayout;

    private MyInvestContract.Presenter mPresenter;
    public static MyInvestFragment newInstance() {
        return new MyInvestFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myinvest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new MyInvestPresenter(this,mActivity);
        initRadioButton();
        initViewData();
        initViewPageData();
        initImageView();

    }

    @Override
    public void showErrorTips(String msg) {

    }

    @Override
    public void initViewData() {

        mInflater = mActivity.getLayoutInflater();
        //默认全部
        currType = 1;
        currPage = 1;
        hander.sendEmptyMessage(0);

        changeTextViewColor(true,false,false);
    }

    @Override
    public void setPresenter(MyInvestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewPageData() {

        dataAllData = new ArrayList<>();
        promiseIngListData = new ArrayList<>();
        repaymentedListData = new ArrayList<>();

        // 全部 布局加载 viewPage
        allView = mInflater.inflate(R.layout.view_myinvest_list_layout, null);
        allRefreshView = ((PullToRefreshLayout) allView.findViewById(R.id.myinvest_refresh_view));
        allRefreshView.setOnRefreshListener(new AllFefreshListener());
        allRefreshView.setIsListOrHomeRefresh(false,false);

        dataAllRefreshList = (PullableListView) allView.findViewById(android.R.id.list);
        allEmptyLayout= new EmptyLayout(mActivity, dataAllRefreshList);
        dataAllAdapter = new MyInvestAdapter(mActivity,dataAllData,dataAllRefreshList);
        dataAllRefreshList.setAdapter(dataAllAdapter);
        dataAllRefreshList.setOnItemClickListener(new MyOnItemClickListener());

        // 履约中
        promiseIngView = mInflater.inflate(R.layout.view_myinvest_list_layout, null);
        promiseIngRefreshView = ((PullToRefreshLayout) promiseIngView.findViewById(R.id.myinvest_refresh_view));
        promiseIngRefreshView.setOnRefreshListener(new AllFefreshListener());
        promiseIngRefreshView.setIsListOrHomeRefresh(false,false);


        promiseIngRefreshList = (PullableListView) promiseIngView.findViewById(android.R.id.list);
        promiseIngRefreshAdapter = new MyInvestAdapter(mActivity,promiseIngListData,promiseIngRefreshList);
        promiseIngRefreshList.setAdapter(promiseIngRefreshAdapter);
        promiseIngRefreshList.setOnItemClickListener(new MyOnItemClickListener());
        promiseIngEmptyLayout = new EmptyLayout(mActivity, promiseIngRefreshList);


        // 已还款
        repaymentedView = mInflater.inflate(R.layout.view_myinvest_list_layout, null);
        repaymentedRefreshView = ((PullToRefreshLayout) repaymentedView.findViewById(R.id.myinvest_refresh_view));
        repaymentedRefreshView.setOnRefreshListener(new AllFefreshListener());
        repaymentedRefreshView.setIsListOrHomeRefresh(false,false);

        repaymentedRefreshList = (PullableListView) repaymentedView.findViewById(android.R.id.list);
        repaymentedRefreshAdapter = new MyInvestAdapter(mActivity,repaymentedListData,repaymentedRefreshList);
        repaymentedRefreshList.setAdapter(repaymentedRefreshAdapter);
        repaymentedRefreshList.setOnItemClickListener(new MyOnItemClickListener());
        repaymentedEmptyLayout = new EmptyLayout(mActivity,repaymentedRefreshList);



        listViews = new ArrayList<View>();
        listViews.add(allView);
        listViews.add(promiseIngView);
        listViews.add(repaymentedView);

        myInvestmentVPager.setAdapter(new MyPagerAdapter(listViews));
        myInvestmentVPager.setCurrentItem(0);
        myInvestmentVPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    @Override
    public void initRefreshView() {

    }

    @Override
    public void setEmptyData() {

        //全部为空
        if(dataAllData.size() == 0 && 1 == currType){
            allEmptyLayout.showEmpty();
        }

        //履行为空
        if(promiseIngListData.size() == 0 && 5 == currType){
            promiseIngEmptyLayout.showEmpty();
        }

        //回款为空
        if(repaymentedListData.size() == 0 && 6 == currType){
            repaymentedEmptyLayout.showEmpty();
        }

    }

    @Override
    public void initRadioButton() {

        radiobTabAll.setOnClickListener(new MyOnClickListener(0));
        radiobTabPromiseIng.setOnClickListener(new MyOnClickListener(1));
        radiobTabRepaymented.setOnClickListener(new MyOnClickListener(2));
    }

    @Override
    public void initImageView() {
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.a).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW /3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }

    @Override
    public void showMyInvestListsData(MyInvestList listBean) {

        if(listBean!=null){

            pageTotal =Integer.parseInt(listBean.getPageNum());

            List<MyInvestBean> mList =listBean.getPageList();

            if(mList!=null&&mList.size()>0){

                if(currPage == 1){ // 下拉刷新，始终加载第一页
                    // 原有的数据清空
                    if(1== currType){// 默认查询全部
                        dataAllData.clear();
                    }else if(5 == currType){//履约中
                        promiseIngListData.clear();
                    }else if(6 == currType){//已回款
                        repaymentedListData.clear();
                    }
                }

                if(1 == currType){//默认查询全部
                    dataAllData.addAll(mList);

                }else if(5== currType){//履约中
                    promiseIngListData.addAll(mList);

                }else if(6 == currType){//已回款
                    repaymentedListData.addAll(mList);
                }

                if(1 == currType){//默认查询全部
                    dataAllAdapter.setPagesInfo(currPage,pageTotal);
                    dataAllAdapter.notifyDataSetChanged();
                }else if(5 == currType){//履约中
                    repaymentedRefreshAdapter.setIsTimeedSpeed(false);
                    promiseIngRefreshAdapter.setPagesInfo(currPage,pageTotal);
                    promiseIngRefreshAdapter.notifyDataSetChanged();
                }else if(6 == currType){//已回款
                    repaymentedRefreshAdapter.setIsTimeedSpeed(true);
                    repaymentedRefreshAdapter.setPagesInfo(currPage,pageTotal);
                    repaymentedRefreshAdapter.notifyDataSetChanged();
                }

            }else{
                setEmptyData();
            }




        }



    }

    @Override
    public void showDateEmptyView(boolean state) {

    }

    @Override
    public void getData(int type, int page) {
        //全部
        if( 1 == currType){
            allEmptyLayout.showLoading();
        }

        //履行中
        if( 5 == currType){
            promiseIngEmptyLayout.showLoading();
        }

        //已回款
        if( 6 == currType){
            repaymentedEmptyLayout.showLoading();
        }


        UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
        if(mUserBean!=null&& !TextUtils.isEmpty(mUserBean.getUserPhone())){
            mPresenter.getMyInvestHttpRequest(mUserBean.getUserPhone(),type,page);
        }

    }

    @Override
    public void changeTextViewColor(boolean one, boolean two, boolean three) {

        if(one){
            radiobTabAll.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            radiobTabAll.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(two){
            radiobTabPromiseIng.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            radiobTabPromiseIng.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(three){
            radiobTabRepaymented.setTextColor(getResources().getColor(R.color.blue5));
        }else{
            radiobTabRepaymented.setTextColor(getResources().getColor(R.color.colorBack3));
        }


    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;
        public MyOnClickListener(int i) {
            index = i;
        }
        public void onClick(View v) {
            myInvestmentVPager.setCurrentItem(index);
        }
    };


    // 查询
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
                    changeTextViewColor(true,false,false);
                    currType = 1;
                    currPage = 1;
                    dataAllData.clear();
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    dataAllRefreshList.setSelection(1);
                    radiobTabAll.setChecked(true);
                    break;
                case 1:
                    changeTextViewColor(false,true,false);
                    radiobTabPromiseIng.setChecked(true);
                    promiseIngListData.clear();
                    currType = 5;
                    currPage = 1;
                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    promiseIngRefreshList.setSelection(1);//刷新后，直接定位到第一条
                    break;
                case 2:
                    changeTextViewColor(false,false,true);
                    radiobTabRepaymented.setChecked(true);
                    currType = 6;
                    currPage = 1;
                    repaymentedListData.clear();

                    // 初始化数据
                    hander.sendEmptyMessage(0);
                    repaymentedRefreshList.setSelection(1);//刷新后，直接定位到第一条
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

    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //goBackMyWealthPage();
                mActivity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    // 更新 数据
    private Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
            if(mUserBean!=null&& !TextUtils.isEmpty(mUserBean.getUserPhone())){
                mPresenter.getMyInvestHttpRequest(mUserBean.getUserPhone(),currType,currPage); // 默认加载 "1": 全部 "5：履约中  "6"：已回款
            }

        }
    };


    public class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if(StringUtils.isFastClick()){
                return;
            }

            try{
                MyInvestBean myInvestBean =null;
                if(currType==1){
                    myInvestBean =dataAllData.get(position);
                }else if(currType==5){
                    myInvestBean =promiseIngListData.get(position);
                }else if(currType==6){
                    myInvestBean =repaymentedListData.get(position);
                }

                if(myInvestBean!=null&&!TextUtils.isEmpty(myInvestBean.getType())){
//                    if(myInvestBean.getType().equals("1")){
//                        Bundle mBundle = new Bundle();
//                        mBundle.putSerializable("PRODUCTBEAN",myInvestBean);
//                        startActivity(InvestDetailActivity.makeIntent(mActivity,mBundle));
//                    }else if(myInvestBean.getType().equals("2")){
//                        Bundle mBundle = new Bundle();
//                        mBundle.putSerializable("BUYPRODUCTBEAN",myInvestBean);
//                        startActivity(ZTBuyProductDetailActivity.makeIntent(mActivity,mBundle));
//                    }
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("BUYPRODUCTBEAN",myInvestBean);
                    startActivity(ZTBuyProductDetailActivity.makeIntent(mActivity,mBundle));
                }
            }catch (Exception e){

            }

        }
    }



}
