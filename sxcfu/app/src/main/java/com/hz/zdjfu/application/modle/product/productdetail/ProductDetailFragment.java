package com.hz.zdjfu.application.modle.product.productdetail;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.ProductDetail;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.invest.InvestRewordActivity;
import com.hz.zdjfu.application.modle.invest.invest.InvestActivity;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.mine.myinvest.MyInvestActivity;
import com.hz.zdjfu.application.modle.opendeposit.OpenDepositActivity;
import com.hz.zdjfu.application.modle.product.returnmoneyplay.ReturnMoneyPlayActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.ImageAlertDialog;
import com.hz.zdjfu.application.widget.dialog.OpenBankAccountDialog;
import com.hz.zdjfu.application.widget.pulltorefresh.PullToRefreshLayout;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.PullableScrollView;
import com.hz.zdjfu.application.widget.pulltorefresh.pullableview.ScrollViewListener;
import com.hz.zdjfu.application.widget.view.CountdownTimeTextView;
import com.hz.zdjfu.application.widget.view.WaveHelper;
import com.hz.zdjfu.application.widget.view.WaveViewNew;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 产品详情Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class ProductDetailFragment extends BaseFragment implements ProductDetailContract.View, TabHost.OnTabChangeListener, ScrollViewListener, PullToRefreshLayout.OnRefreshListener {

    private static final String TAG = ProductDetailFragment.class.getName();
    @BindView(R.id.productdetail_tilte_tv)
    TextView productdetailTilteTv;
    @BindView(R.id.productdetail_type_tv)
    TextView productdetailTypeTv;
    @BindView(R.id.productdetail_tilte_wv)
    WaveViewNew productdetailTilteWv;
    @BindView(R.id.productdetail_balance_tv)
    TextView productdetailBalanceTv;
    @BindView(R.id.productdetail_balance_title_tv)
    TextView productdetailBalanceTitleTv;
    @BindView(R.id.productdetail_intersst_tv)
    TextView productdetailIntersstTv;
    @BindView(R.id.productdetail_earning_day_tv)
    TextView productdetailEarningDayTv;
    @BindView(R.id.productdetail_all_amount_tv)
    TextView productdetailAllAmountTv;
    @BindView(R.id.productdetail_all_invest_tv)
    TextView productdetailAllInvestTv;
    @BindView(R.id.productdetail_tag_one_iv)
    ImageView productdetailTagOneIv;
    @BindView(R.id.productdetail_tag_two_iv)
    ImageView productdetailTagTwoIv;
    @BindView(R.id.productdetail_tag_three_iv)
    ImageView productdetailTagThreeIv;
    @BindView(R.id.productdetail_start_time_tv)
    TextView productdetailStartTimeTv;
    @BindView(R.id.productdetail_end_time_tv)
    TextView productdetailEndTimeTv;
    @BindView(R.id.productdetail_all_day_tv)
    TextView productdetailAllDayTv;
    @BindView(R.id.productdetail_refund_mode_tv)
    TextView productdetailRefundModeTv;
    @BindView(R.id.productdetail_refund_mode_iv)
    ImageView productdetailRefundModeIv;
    @BindView(R.id.productdetail_refund_mode_rl)
    RelativeLayout productdetailRefundModeRl;
    @BindView(R.id.productdetail_safe_guard_tv)
    TextView productdetailSafeGuardTv;
    @BindView(R.id.productdetail_safe_guard_iv)
    ImageView productdetailSafeGuardIv;
    @BindView(R.id.productdetail_safe_guard_go_iv)
    ImageView productdetailSafeGuardGoIv;
    @BindView(R.id.productdetail_safe_guard_rl)
    RelativeLayout productdetailSafeGuardRl;
    @BindView(R.id.imageView39)
    ImageView imageView39;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.product_head_layout)
    LinearLayout productHeadLayout;
    @BindView(R.id.product_pullable_scrollView)
    PullableScrollView productPullableScrollView;
    @BindView(R.id.product_refresh_view)
    PullToRefreshLayout productRefreshView;
    @BindView(R.id.productdetail_detail_title_tv)
    TextView productdetailDetailTitleTv;
    @BindView(R.id.productdetail_audit_tv)
    TextView productdetailAuditTv;
    @BindView(R.id.productdetail_contract_tv)
    TextView productdetailContractTv;
    @BindView(R.id.productdetail_more_detail_tr)
    TableRow productdetailMoreDetailTr;
    @BindView(R.id.fram_cursors)
    ImageView framCursors;
    @BindView(R.id.productdetail_line_tr)
    TableRow productdetailLineTr;
    @BindView(R.id.productdetail_more_detail_ll)
    LinearLayout productdetailMoreDetailLl;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    @BindView(R.id.btn_go_touzi)
    TextView btnGoTouzi;
    @BindView(R.id.productdetail_wv_rl)
    RelativeLayout productdetailWvRl;
    @BindView(R.id.productdetail_downtime_tv)
    CountdownTimeTextView productdetailDowntimeTv;
    @BindView(R.id.productdetail_downtime_ll)
    LinearLayout productdetailDowntimeLl;

    //动画效果
    private Animation mShowAction, mShowButtomAction, mHiddenAction, mHiddenActionHead;
    private UserDetailBean mUserDetail;
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    private ProductDetailContract.Presenter mPresenter;
    private String mProduct_id;
    private WaveHelper mWaveHelper;
    private ImageAlertDialog mDialog;
    //定义数组来存放Fragment界面
    private Class fragmentArray[];
    private LayoutInflater layoutInflater;
    private String mTextviewArray[] = {"","",""};
    //private String mTextviewArray[] = {"","",""};
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    // true代表是，已上拉查看项目介绍
    private boolean onLoadMoreCount = false;
    private int onLoadMoreRunCont = 0;

    private ProductInformBean mBean;
    private FragmentContractDetail mContractFragment;
    private FragmentDetail mDetailFragment;
    private FragmentRiskAudit mRiskAuditFragment;
    private ProductDetail bean;
    private OpenBankAccountDialog dialog;
    private String[] tabTexts = new String[] { "项目详情", "风控审核", "合同材料"};
    boolean isUsed =true;
    private UserBean mUserBean;
    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void initViewData() {

    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_productdetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new ProductDetailPresenter(mActivity, this);
        initViewData(view);
        InitImageView(view);
        initView();
    }

    @Override
    public void initView() {
        changeTitleColor(true,false,false);
    }

    @Override
    public void showProductDetail(ProductInformBean mProductInformBean) {

        if (mProductInformBean == null) {
            return;
        }

        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
            mPresenter.userDetailHttpRequest(mUserBean.getUserPhone());
        }


        this.mBean =mProductInformBean;

        this.bean= mProductInformBean.getProduct();

        if(bean!=null){
            if(!TextUtils.isEmpty(bean.getProduct_code())){
                if(((ProductDetailActivity)mActivity).mTitle!=null){
                    ((ProductDetailActivity)mActivity).mTitle.setText(bean.getProduct_code()+"");
                }
            }

            //圆球
            productdetailTilteWv.setBorder(2, Color.parseColor("#fa4740"));
            productdetailTilteWv.setWaterLevelRatio(0);
            productdetailTilteWv.setAmplitudeRatio(0.08F / 4);
            mWaveHelper = new WaveHelper(productdetailTilteWv);

            //名字
            productdetailTilteTv.setText(bean.getProduct_name()+"");
            //剩余可投金额
            productdetailBalanceTv.setText(UiUtils.formatMoneyToBigDecimal(bean.getBalance()+"",mActivity.getResources().getString(R.string.defalut_money_separator))+"");
            //年化率
            if(!TextUtils.isEmpty(bean.getPlatform_interest())){
                if (Double.parseDouble(bean.getPlatform_interest()) == 0.00) {
                    productdetailIntersstTv.setText(AmountUtils.getCommaFormat(bean.getIncome()+"")+ "%");
                } else {
                    double subIncome = UiUtils.sub(String.valueOf(bean.getIncome()),String.valueOf(bean.getPlatform_interest()));
                    productdetailIntersstTv.setText(AmountUtils.getCommaFormat(subIncome+"")+ "%+" + AmountUtils.getCommaFormat(bean.getPlatform_interest()+"")+"%");
                }

            }else{
                productdetailIntersstTv.setText(bean.getIncome()+"%");
            }

            //收益天数
            productdetailEarningDayTv.setText(bean.getIncome_days()+ "天");

            //项目金额
            productdetailAllAmountTv.setText(UiUtils.formatMoneyToBigDecimal(bean.getMoney()+"",mActivity.getResources().getString(R.string.defalut_money_separator))+"元");

            //目前投资人数
            productdetailAllInvestTv.setText(bean.getBuyer_count() + "笔投资>");

            //开始计息

            if(bean.getStart_dates().contains(" ")){
                String[] start_time =bean.getStart_dates().split(" ");
                productdetailStartTimeTv.setText(start_time[0]+ "");
            }else{
                productdetailStartTimeTv.setText(bean.getStart_dates()+ "");
            }

            if(bean.getEnd_date().contains(" ")){
                String[] end_time =bean.getEnd_date().split(" ");
                productdetailEndTimeTv.setText(end_time[0]+ "");
            }else{
                productdetailEndTimeTv.setText(bean.getEnd_date()+ "");
            }

            //下方按钮显示情况
            checkBtnState();

            //还款方式
            if(!TextUtils.isEmpty(bean.getReturn_method())){
                if(bean.getReturn_method().equals("1")){
                    productdetailRefundModeTv.setText("按日计息，到期一次性还本付息");
                }else{
                    productdetailRefundModeTv.setText("按日计息，按月付息，到期还本");
                }
            }


            //总天数
            if(!TextUtils.isEmpty(bean.getIncome_days())){
                productdetailAllDayTv.setText(bean.getIncome_days()+"天");
            }

            //设置滚动的水波进度
            String ratio = AmountUtils.div(bean.getBalance()+"",bean.getMoney()+"",2);//计算比例  总金额除以剩余可投
            try {
                int speed =0;
                if(!TextUtils.isEmpty(ratio)){
                    String content =String.valueOf(Double.parseDouble(ratio)*100);
                    if(!TextUtils.isEmpty(content)&&content.contains(".")){
                        speed =100-Integer.parseInt(content.split("\\.")[0]);
                    }else{
                        speed =100-Integer.parseInt(content);
                    }
                    if (speed <= 5) {
                        speed = 5;
                    }
                }else{
                    speed =100;
                }
                setSumProgress(speed);
            }catch (Exception e){

            }
        }

        //新手
        if(Integer.parseInt(bean.getIs_fresh())==1){
            productdetailTypeTv.setVisibility(View.VISIBLE);
            productdetailTypeTv.setText("新手");
        }else{
            productdetailTypeTv.setVisibility(View.GONE);
        }


        //设置下面内容
        showFragmentData();

    }

    @Override
    public void showUserDetail(UserDetailBean bean) {
        this.mUserDetail = bean;
        checkBtnState();

    }

    @Override
    public void bottomState() {
        if(bean!=null){
            int mState =Integer.parseInt(bean.getStatus());
            if (mState == 4) {//投资中
                if (Double.parseDouble(bean.getBalance()) > 100 || Double.parseDouble(bean.getBalance())== 100) {//有余额则可投
                    btnGoTouzi.setText("100元起投");
                } else if (Double.parseDouble(bean.getBalance()) > 0 && Double.parseDouble(bean.getBalance()) < 100) {
                    btnGoTouzi.setText("立即投资");
                }
                btnGoTouzi.setEnabled(true);
            } else if (mState == 5||mState== 7) {//履约中
                productdetailDowntimeLl.setVisibility(View.GONE);
                btnGoTouzi.setVisibility(View.VISIBLE);
                btnGoTouzi.setText("履约中");
                btnGoTouzi.setEnabled(false);
                btnGoTouzi.setBackgroundResource(R.color.gray13);
            } else if(mState == 6){
                productdetailDowntimeLl.setVisibility(View.GONE);
                btnGoTouzi.setVisibility(View.VISIBLE);
                btnGoTouzi.setText("已回款");
                btnGoTouzi.setEnabled(false);
                btnGoTouzi.setBackgroundResource(R.color.gray13);
            } else{
                btnGoTouzi.setText("100元起投");
                btnGoTouzi.setEnabled(true);
            }
        }

    }

    private void checkBtnState() {
        //底部显示
        UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
        if (mUserBean != null && mUserBean.isLogin()) {//登录了


            if(mUserDetail != null){

                if (mUserDetail.getStatus() == 3) {//认证完成

                    if(bean==null){
                        return;
                    }

                    if (Integer.parseInt(bean.getStatus()) == 31) {//预募集
                        long ms = Long.parseLong(bean.getWill_issue_time());//毫秒数
                        if (ms > 0) { //  不是读取缓存数据时，开始预募集倒计时
                            if (!productdetailDowntimeTv.isRun()) {
                                productdetailDowntimeTv.setTimes(ms,btnGoTouzi,null,productdetailDowntimeLl,null,"");
                                productdetailDowntimeTv.run();
                            }
                            productdetailDowntimeLl.setEnabled(false);
                            productdetailDowntimeLl.setVisibility(View.VISIBLE);
                            btnGoTouzi.setVisibility(View.GONE);
                            btnGoTouzi.setEnabled(false);
                        }else {
                            productdetailDowntimeLl.setEnabled(true);
                            productdetailDowntimeLl.setVisibility(View.GONE);
                            btnGoTouzi.setVisibility(View.VISIBLE);
                            btnGoTouzi.setEnabled(true);
                        }

                    } else if (Integer.parseInt(bean.getStatus()) == 4) {//投资中
                        if (Double.parseDouble(bean.getBalance()) > 100 || Double.parseDouble(bean.getBalance()) == 100) {//有余额则可投
                            btnGoTouzi.setText("100元起投");
                        } else if (Double.parseDouble(bean.getBalance()) > 0 && Double.parseDouble(bean.getBalance()) < 100) {
                            btnGoTouzi.setText("立即投资");
                        }
                        btnGoTouzi.setEnabled(true);

                    } else if (Integer.parseInt(bean.getStatus()) == 5||Integer.parseInt(bean.getStatus()) == 7) {//履约中

                        productdetailDowntimeLl.setVisibility(View.GONE);
                        btnGoTouzi.setVisibility(View.VISIBLE);
                        btnGoTouzi.setText("履约中");
                        btnGoTouzi.setEnabled(false);
                        btnGoTouzi.setBackgroundResource(R.color.gray13);

                    } else {
                        btnGoTouzi.setEnabled(false);
                    }

                } else if(mUserDetail != null && mUserDetail.getStatus() == 4){//认证了 但解绑了银行卡
                    btnGoTouzi.setText("绑定银行卡");
                    btnGoTouzi.setEnabled(true);
                    btnGoTouzi.setBackgroundResource(R.color.blue5);
                }else {
                    btnGoTouzi.setText("开通银行存管");
                    btnGoTouzi.setEnabled(true);
                    btnGoTouzi.setBackgroundResource(R.color.blue5);
                }

            }else{
                bottomState();
            }

        } else {
            bottomState();
        }
    }

    @Override
    public void showSettingPayPwdH5(String url) {

        if(!TextUtils.isEmpty(url)){
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="PRODUCTDETAIL";
            mIntent.putExtra("WebView_URL",url);
            mActivity.startActivity(mIntent);
        }

    }

    @Override
    public void isCheckUnPayOrder(boolean state) {

        if(state){
           mDialog =new ImageAlertDialog(mActivity, "提示", "您有未支付订单,是否先完成未支付订单?",0, "查看订单", "取消", new ImageAlertDialog.ConfirmListener() {
                @Override
                public void callback() {
                   startActivity(MyInvestActivity.makeIntent(mActivity,null));
                    mDialog.dismiss();
                    mDialog=null;
                }
            }, new ImageAlertDialog.CancelListener() {
                @Override
                public void callback() {
                    mDialog.dismiss();
                    mDialog=null;
                }
            },false);
            mDialog.show();

        }else{
            if(mBean==null){
                return;
            }
            Bundle bundle =new Bundle();
            bundle.putSerializable("PRODUCTBEAN",mBean.getProduct());
            startActivity(InvestActivity.makeIntent(mActivity,bundle));
        }

    }


    @Override
    public void showFragmentData() {

        if(mBean!=null){


            FragmentManager mFragmentManager= mActivity.getSupportFragmentManager();
            if(mFragmentManager!=null){
                if(mDetailFragment==null){
                    mDetailFragment= (FragmentDetail)mFragmentManager.findFragmentByTag("0");
                }
            }


            //详情
            if(mDetailFragment!=null){
                mDetailFragment.showDetailData(mBean);
            }

//            mFragmentManager.beginTransaction().replace(R.id.realtabcontent, mDetailFragment).commit();

            ((ProductDetailActivity)mActivity).mProductInformBean =mBean;

        }

    }

    @Override
    public void changeTitleColor(boolean one, boolean two, boolean three) {

        if(one){
            productdetailDetailTitleTv.setTextColor(getResources().getColor(R.color.blue5));
        }else {
            productdetailDetailTitleTv.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(two){
            productdetailAuditTv.setTextColor(getResources().getColor(R.color.blue5));
        }else {
            productdetailAuditTv.setTextColor(getResources().getColor(R.color.colorBack3));
        }

        if(three){
            productdetailContractTv.setTextColor(getResources().getColor(R.color.blue5));
        }else {
            productdetailContractTv.setTextColor(getResources().getColor(R.color.colorBack3));
        }

    }

    @Override
    public void authNameResult(String url) {

        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL",url);
        mIntent.putExtra("WebView_PARENT","PRODUCTDETAIL");
        mActivity.startActivity(mIntent);
    }


    public void initViewData(View view) {

        Intent mIntent =mActivity.getIntent();
        if(mIntent!=null){
            Bundle mBundle =mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                 mProduct_id =mBundle.getString("PRODUCTID");
            }
        }

        mUserBean = UserInfoManager.getInstance().getLocationUserData();
        initAnimations_Two();

        productPullableScrollView.setScrollViewListener(this);
        productPullableScrollView.setVerticalScrollBarEnabled(false);


        //	pullToRefreshLayout.setOnTouchListener(this);
        productRefreshView.setOnRefreshListener(this);
        productRefreshView.setIsListOrHomeRefresh(true, false); // 是一级入口刷新,并有头部文字说明
        productRefreshView.setOnLoadMoreTime(10); // 上拉恢复状态的间隔时间




        fragmentArray = new Class[]{FragmentDetail.class,FragmentRiskAudit.class,FragmentContractDetail.class};

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);
        mTabHost.setup(mActivity,mActivity.getSupportFragmentManager(),R.id.realtabcontent);
        // mTabHost.getTabWidget().setOrientation(TabWidget.HORIZONTAL);
        // tab间分割线
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

        mTabHost.setBackgroundColor(Color.TRANSPARENT);
        mTabHost.setOnTabChangedListener(this);
        mTabHost.setVisibility(View.GONE);

        layoutInflater =LayoutInflater.from(mActivity);
        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec =null;
            tabSpec = mTabHost.newTabSpec(i+"").setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(android.R.color.transparent);
        }

        productPullableScrollView.setScrollViewListener(this);
        productPullableScrollView.setVerticalScrollBarEnabled(false);



    }




    private void InitImageView(View view) {
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.a).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        framCursors.setImageMatrix(matrix);// 设置动画初始位置
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.view_tabhost_item, null);
        TextView textView = (TextView) view.findViewById(R.id.view_tabhost_tv);
        textView.setText(mTextviewArray[index]+"");
        return view;
    }


    @OnClick({R.id.productdetail_all_invest_tv, R.id.btn_go_touzi,R.id.productdetail_detail_title_tv,R.id.productdetail_audit_tv,R.id.productdetail_contract_tv,R.id.productdetail_safe_guard_rl,R.id.productdetail_refund_mode_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.productdetail_all_invest_tv:
                Bundle bundle = new Bundle();
                bundle.putString("PRODUCTID",mProduct_id);
                startActivity(InvestRewordActivity.makeIntent(mActivity,bundle));

                break;
            case R.id.btn_go_touzi:

                if(StringUtils.isFastClick()){
                    return;
                }

                //1.判断是否登录 2.判断是否开通认证 3.投资
                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY =TAG;
                UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
                if(mUserBean==null||!mUserBean.isLogin()){//未登录
                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY =TAG;
                    startActivity(LoginActivity.makeIntent(mActivity,null));
                }else {

                    //检查是否认证
                    if(mUserDetail==null){
                        return;
                    }
                    if(mUserDetail!=null&&(mUserDetail.getStatus()==0||mUserDetail.getStatus()==1|mUserDetail.getStatus()==2)){
                        checkIsOpenBankAccount();
                    }else if(mUserDetail.getStatus()==4){//解绑过卡
                       ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="INVESTDETAIL";
                        if(mPresenter!=null){
                            mPresenter.addBankCard();
                        }
                       // startActivity(AddBankCardActivity.makeIntent(mActivity,null));
                    }else if(mUserDetail.getStatus()==3){
                        if(mBean==null){
                            return;
                        }
                        Bundle mbundle =new Bundle();
                        mbundle.putSerializable("PRODUCTBEAN",mBean.getProduct());
                        startActivity(InvestActivity.makeIntent(mActivity,mbundle));
                    }
                }

                break;

            case R.id.productdetail_detail_title_tv:
                changeTitleColor(true,false,false);
                new MyOnClickListener(0).onClick(view);
                break;
            case R.id.productdetail_audit_tv:
                changeTitleColor(false,true,false);
                new MyOnClickListener(1).onClick(view);

                break;
            case R.id.productdetail_contract_tv:
                changeTitleColor(false,false,true);
                new MyOnClickListener(2).onClick(view);

                break;
            case R.id.productdetail_safe_guard_rl://安全保障

                Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_HOME_SAFE_GUARD);
                mIntent.putExtra("WebView_TITLE","安全保障");
                mActivity.startActivity(mIntent);

                break;
            case R.id.productdetail_refund_mode_rl:
                if(mBean!=null){
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("PRODUCTINFORM",mBean);
                    startActivity(ReturnMoneyPlayActivity.makeIntent(mActivity,mBundle));
                }
                break;

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(mProduct_id!=null&&!TextUtils.isEmpty(mProduct_id)){
            if(mPresenter!=null){
                mPresenter.productDetailHttpRequest(mProduct_id);
            }
        }
    }


    @Override
    public void onTabChanged(String tabId) {
        int position = Integer.parseInt(tabId);
        int one = offset * 2 + bmpW;
        Animation animation = new TranslateAnimation(one * currIndex, one* position, 0, 0);
        currIndex = position;
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        framCursors.startAnimation(animation);

    }


    // 显示头部产品信息
    public void toHeadInfo() {
        if (productHeadLayout.getVisibility() == View.GONE &&
                mTabHost.getVisibility() == View.VISIBLE) {
            try {
                // Thread.sleep(1);
                productHeadLayout.clearAnimation();
                productHeadLayout.startAnimation(mShowAction);
                productHeadLayout.setVisibility(View.VISIBLE);

                productdetailMoreDetailLl.clearAnimation();
                productdetailMoreDetailLl.startAnimation(mShowButtomAction);
                mTabHost.clearAnimation();
                mTabHost.startAnimation(mShowButtomAction);

                productRefreshView.refreshFinish(PullToRefreshLayout.SUCCEED);
                productPullableScrollView.scrollTo(0, 0);

            } catch (Exception e) {
            }
        }
    }



    @Override
    public void onScrollChanged(PullableScrollView scrollView, int x, int y, int oldx, int oldy) {


        if (currIndex == 0) {
            productPullableScrollView.scrollTo(0, 0);
        }

    }


    /**
     * 控制页面上下滑动的动画
     */
    private void initAnimations_Two() {
        mShowAction = AnimationUtils.loadAnimation(mActivity, R.anim.push_up_in);
        mShowAction.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                productRefreshView.setOnRefreshTime(800);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mShowButtomAction = AnimationUtils.loadAnimation(mActivity, R.anim.push_up_in_buttom);
        mShowButtomAction.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                productdetailMoreDetailLl.setVisibility(View.GONE);
                mTabHost.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mHiddenAction = AnimationUtils.loadAnimation(mActivity, R.anim.push_up_out);
        mHiddenActionHead = AnimationUtils.loadAnimation(mActivity, R.anim.push_up_out_head);
        mHiddenActionHead.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Log.e("onAnimationEnd","onAnimationEnd");
                productHeadLayout.setVisibility(View.GONE);
                // ps.scrollTo(0, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        // 设置动画插值器 被用来修饰动画效果,定义动画的变化率.开始快，结束时慢
        // mHiddenActionHead.setInterpolator(new DecelerateInterpolator());
    }


    // 翻页 更新list数据
    private Handler handerhome = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mPresenter!=null){
                if(!TextUtils.isEmpty(mProduct_id)){
                    mPresenter.productDetailHttpRequest(mProduct_id);
                }
            }
        }
    };


    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        handerhome.sendEmptyMessage(0);

        // 下拉刷新操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0,5000);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        onLoadMoreRunCont  = onLoadMoreRunCont + 1;
        if (onLoadMoreRunCont == 1) {

            onLoadMoreCount = true;
            if (mTabHost.getVisibility() == View.GONE &&
                    productdetailMoreDetailLl.getVisibility() == View.GONE) {

                pullToRefreshLayout.setOnRefreshTime(10);
                productHeadLayout.clearAnimation();
                productHeadLayout.startAnimation(mHiddenActionHead);

                productdetailMoreDetailLl.clearAnimation();
                productdetailMoreDetailLl.setVisibility(View.VISIBLE);
                productdetailMoreDetailLl.startAnimation(mHiddenAction);

                mTabHost.clearAnimation();
                mTabHost.setVisibility(View.VISIBLE);
                mTabHost.startAnimation(mHiddenAction);


                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

                return;
            }
        } else {
            onLoadMoreRunCont = 0;
        }
    }


    // tab切换时，点击调用的事件
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mTabHost.setCurrentTab(index);
        }
    };


    // 设置产品募集的进度
    public void setSumProgress(final int sumProgress) {
        try {
            float level = sumProgress / 100.0f;
            // 水位从0到设置的水位高度
            mWaveHelper.setWaterLevel(level);
            // mWaveHelper.cancel();
            mWaveHelper.start();
        } catch (Exception e) {
        }
    }


    public void checkIsOpenBankAccount() {

        if(mUserDetail!=null){
            if(mUserDetail.getStatus()==0||mUserDetail.getStatus()==1||mUserDetail.getStatus()==2){
                showOpenBankAccountDialog();
            }else if(mUserDetail.getStatus()==4){
                ToastUtils.show(mActivity,"请先绑定有效银行卡");
            }
        }

    }


    public void showOpenBankAccountDialog() {

        if(dialog==null){
            dialog = new OpenBankAccountDialog(mActivity, new OpenBankAccountDialog.OpenBankAccountCancleListener() {
                @Override
                public void callback() {
                    dialog.dismiss();
                    dialog =null;
                }
            }, new OpenBankAccountDialog.OpenBankAccountSureListener() {
                @Override
                public void callback() {

                    ZDJFUApplication.getInstance().MCURRENT_ACTIVITY="PRODUCTDETAIL";
                    startActivity(OpenDepositActivity.makeIntent(mActivity,null));
                    dialog.dismiss();
                    dialog =null;
                }
            });
            dialog.show();
        }


    }

//    public void showWXShareAndroid() {
//        WXWebpageObject webpage = new WXWebpageObject();
//        WXMediaMessage msg = new WXMediaMessage(webpage);
//        final SendMessageToWX.Req req = new SendMessageToWX.Req();
//        // productCodeTitle incomeDays yearIncome
//        // 标题：正道金服4001期，12.8%年化收益   描述：收益天数24天，更低风险，更高收益
//        webpage.webpageUrl = URLController.WEIXING_URL + "/detail.html?productId=" + mBean.getProduct().getId();
//        msg.title = mBean.getProduct().getProduct_code() + "，" + mBean.getProduct().getIncome() + "%年化收益";
//        msg.description = "收益天数" + mBean.getProduct().getIncomeDays() + "天，更低风险，更高收益";
//        req.transaction = buildTransaction("SXCFU_PRODUCT");
//        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_share);
//        msg.thumbData = UiUtils.bmpToByteArray(thumb, true);
//        req.message = msg;
//        thumb.recycle();
//
//        ImageView imgWeixinFriand = (ImageView) shareDialog.findViewById(R.id.img_weixin_friand);
//        imgWeixinFriand.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 分享给微信朋友
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                WXapi.sendReq(req);
//                shareDialog.dismiss();
//            }
//        });
//        ImageView imgWeixinFans = (ImageView) shareDialog.findViewById(R.id.img_weixin_fans);
//        imgWeixinFans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 发送到朋友圈 微信4.2以上支持
//                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                WXapi.sendReq(req);
//                shareDialog.dismiss();
//            }
//        });
//        LinearLayout btnCancel = (LinearLayout) shareDialog.findViewById(R.id.layout_cancel);
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shareDialog.dismiss();
//            }
//        });
//        shareDialog.show();
//    }
//
//
//    /**
//     * -
//     * 构建一个唯一标志
//     *
//     * @param type
//     * @return
//     * @author YOLANDA
//     */
//    private static String buildTransaction(String type) {
//        return (type == null) ? String.valueOf(System.currentTimeMillis()) : (type + System.currentTimeMillis());
//    }


}
