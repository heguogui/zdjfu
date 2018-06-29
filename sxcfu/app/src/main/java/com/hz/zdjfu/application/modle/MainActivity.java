package com.hz.zdjfu.application.modle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.financial.FinancialFragment;
import com.hz.zdjfu.application.modle.fund.FundFragment;
import com.hz.zdjfu.application.modle.home.HomeFragment;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.mine.MineFragment;
import com.hz.zdjfu.application.utils.ActivityManagerUtil;
import com.hz.zdjfu.application.utils.ActivityUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.webview.WebViewFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends ToolbarActivity {


    //正道选项卡
    public static final String HOME_TAB = "homeTab";
    //理财选项卡
    public static final String FINANCIAL_TAB = "financialTab";
    //发现选项卡
    public static final String FUND_TAB = "fundTab";
    //我的选项卡
    public static final String MINE_TAB = "mineTab";

    @BindView(R.id.tab_home)
    TextView homeTab;
    @BindView(R.id.tab_financial)
    TextView financialTab;
    @BindView(R.id.tab_fund)
    TextView fundTab;
    @BindView(R.id.tab_mine)
    TextView mineTab;


    public static final  int SEARCH_REQUEST_CODE=0x11;

    private Unbinder unbinder;
    private HomeFragment mHomeFragment;
    private FinancialFragment mFinancialFragment;
    private FundFragment mFundFragment;
    private MineFragment mMineFragment;

    private String fromTag;

    /**
     * 用于页面跳转传值
     *
     * @param context
     * @param bundle
     * @return
     */
    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);

    }


    private void initData(Bundle savedInstanceState) {
        initTab(savedInstanceState);
    }


    @Override
    protected void init() {
        super.init();
        //绑定
        unbinder = ButterKnife.bind(this);

        mHomeFragment = HomeFragment.newInstance();
        mFinancialFragment = FinancialFragment.newInstance();
        mFundFragment = FundFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(MainActivity.this)) {
            EventBus.getDefault().register(MainActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(MainActivity.this);
    }


    public void initTab(Bundle savedInstanceState) {

        if (savedInstanceState == null) { // 如果没有有缓存,添加新的fragment, 如果有缓存从缓存读取,此操作已经在switchFragmentInMainPage中处理
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (transaction == null) {
                return;
            }

            Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.HOME_TAB);
            Fragment financialFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.FINANCIAL_TAB);
            Fragment fundFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.FUND_TAB);
            Fragment mineFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.MINE_TAB);
            //添加首页
            if (homeFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mHomeFragment,
                        HOME_TAB
                );
            }
            //添加理财
            if (financialFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mFinancialFragment,
                        FINANCIAL_TAB
                ).hide(mFinancialFragment);
            }
            //添加发现
            if (fundFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mFundFragment,
                        FUND_TAB
                ).hide(mFundFragment);
            }

            //添加我的
            if (mineFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mMineFragment,
                        MINE_TAB
                ).hide(mMineFragment);
            }
            transaction.commitAllowingStateLoss();

            showToolbar(false);
            homeTab.setCompoundDrawables(
                    null,
                    handleDrawable(R.mipmap.tab_home_selected),
                    null,
                    null
            );
            homeTab.setTextColor(getResources().getColor(R.color.blue1));

            setTitle(getString(R.string.home_title));
        }


    }


    /**
     * 切换选项卡
     *
     * @param tab
     */
    public void changeTab(String tab) {
        //设置底部文字颜色
        homeTab.setTextColor(getResources().getColor(R.color.gray4));
        financialTab.setTextColor(getResources().getColor(R.color.gray4));
        fundTab.setTextColor(getResources().getColor(R.color.gray4));
        mineTab.setTextColor(getResources().getColor(R.color.gray4));
        //设置底部图片
        homeTab.setCompoundDrawables(
                null,
                handleDrawable(R.mipmap.tab_home_normal),
                null,
                null
        );
        financialTab.setCompoundDrawables(
                null,
                handleDrawable(R.mipmap.tab_finance_defualt),
                null,
                null
        );
        fundTab.setCompoundDrawables(
                null,
                handleDrawable(R.mipmap.tab_find_defualt),
                null,
                null
        );
        mineTab.setCompoundDrawables(
                null,
                handleDrawable(R.mipmap.tab_mywealth_defualt),
                null,
                null
        );

        ActivityUtils.switchFragmentInMainPage(
                getSupportFragmentManager(),
                tab
        );
        switch (tab) {
            case HOME_TAB: {
                showToolbar(false);
                homeTab.setCompoundDrawables(
                        null,
                        handleDrawable(R.mipmap.tab_home_selected),
                        null,
                        null
                );
                homeTab.setTextColor(getResources().getColor(R.color.blue1));
                setTitle(getString(R.string.home_title));
            }
            break;
            case FINANCIAL_TAB: {

                showToolbar(false);
                financialTab.setCompoundDrawables(
                        null,
                        handleDrawable(R.mipmap.tab_finance_selected),
                        null,
                        null
                );
                financialTab.setTextColor(getResources().getColor(R.color.blue1));
                setTitle(getString(R.string.financial_title));
                showRightIv(false);
                showRightTv(false);
            }
            break;
            case FUND_TAB: {
                showToolbar(true);
                fundTab.setCompoundDrawables(
                        null,
                        handleDrawable(R.mipmap.tab_find_selected),
                        null,
                        null
                );
                fundTab.setTextColor(getResources().getColor(R.color.blue1));
                setTitle(getString(R.string.fund_title));
                showRightTv(false);
               // setRightTv(getString(R.string.fund_servicehelp_title),FUND_TAB);

            }
            break;
            case MINE_TAB: {
                showToolbar(false);
                mineTab.setCompoundDrawables(
                        null,
                        handleDrawable(R.mipmap.tab_mywealth_selected),
                        null,
                        null
                );
                mineTab.setTextColor(getResources().getColor(R.color.blue1));
                setTitle(getString(R.string.mine_title));
                showRightIv(false);
            }
            break;
        }
    }


    /**
     * 设置过边界的drawable才会在textview中生效
     *
     * @param id
     * @return
     */
    private Drawable handleDrawable(@DrawableRes int id) {
        Drawable drawable = getResources().getDrawable(id);
        if (drawable == null) {
            return null;
        }
        drawable.setBounds(
                0,
                0,
                drawable.getMinimumWidth(),
                drawable.getMinimumHeight()
        );
        return drawable;
    }

    private boolean mIsExit; // 是否退出App

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mIsExit) {
                ActivityManagerUtil.getActivityManager().exit();
            } else {
                ToastUtils.show(this, "再按一次退出", Toast.LENGTH_SHORT);
                mIsExit = true;
                new Handler().postDelayed(() -> mIsExit = false, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.tab_home, R.id.tab_financial, R.id.tab_fund,R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                changeTab(HOME_TAB);
                break;
            case R.id.tab_financial:
                changeTab(FINANCIAL_TAB);
                break;
            case R.id.tab_fund:
                changeTab(FUND_TAB);
                break;
            case R.id.tab_mine:
                //判断是否登录
                UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
                if(mUserBean!=null&&!mUserBean.isLogin()){
                    Bundle mBundle  =new Bundle();
                    mBundle.putString("FROMPARENT","MINE");
                    startActivity(LoginActivity.makeIntent(this,mBundle));
                    return;
                }
                changeTab(MINE_TAB);
                break;

        }

    }


    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);
        String mTag = (String) view.getTag();
        if(!TextUtils.isEmpty(mTag)){

        }

    }

    @Override
    protected void onRightTvClick(TextView view) {
        super.onRightTvClick(view);
        String mTag = (String) view.getTag();
        if(!TextUtils.isEmpty(mTag)){
            if(mTag.equals(FUND_TAB)){//发现右图标
                mFundFragment.setOnRightViewListener(view);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeTabFromOther(MainFromTagEven mEven){

        if(mEven==null){
            return;
        }

        String mFromTag =mEven.getFromTag();

        if(!TextUtils.isEmpty(mFromTag)&&(mFromTag.equals("REDPACKFRAGMENT")||mFromTag.equals("RECHANGESUCCESSGOINVITE"))||mFromTag.equals("FINANCIAL_TAB")||mFromTag.equals("TOINVEST")){
            changeTab(FINANCIAL_TAB);
        }

        if(mFromTag.equals("LOGIN")||mFromTag.equals("REGIST")||mFromTag.equals("MINE")||mFromTag.equals("UNSETTINGPAYPWD")){
            changeTab(MINE_TAB);
        }

        if(mFromTag.equals("APPLYSUCCESS")||mFromTag.equals("RECHANGESUCCESS")||mFromTag.equals("SETTINGSUCCESS")||mFromTag.equals("RENZHENGBACK")){
            changeTab(MINE_TAB);
//            if(mMineFragment!=null){
//                mMineFragment.refreshData(true);
//            }
        }

        if(mFromTag.equals("SETTINGSUCCESS")||mFromTag.equals("RENZHENGBACK")||mFromTag.equals("WITHDRAWDEPOSIT")){
            changeTab(MINE_TAB);
        }

        if(mFromTag.equals("HOME")){
            changeTab(HOME_TAB);
        }


        if(mFromTag.equals("SHOWFUNDBACK")){//发现页面
            if(mFundFragment!=null){
                showBackBtn(true);
            }

        }







    }

    @Override
    public void onBackPressed() {

        if(mFundFragment!=null&&!mFundFragment.isHidden()){//发现页面显示了
            WebViewFragment mWebViewFragment =(WebViewFragment)mFundFragment;
            if (mWebViewFragment == null || mWebViewFragment.getWebView() == null) {
                return;
            }

            if (mWebViewFragment.getWebView().canGoBack()) {
                mWebViewFragment.getWebView().goBack();
                showBackBtn(false);
                return;
            }else{
                if(mFundFragment!=null){
                    showBackBtn(false);
                }
            }
        }
        super.onBackPressed();

    }
}
