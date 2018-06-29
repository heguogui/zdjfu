package com.hz.zdjfu.application.modle.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.andview.refreshview.XRefreshView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.config.URLController;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.data.bean.BannerRecordLists;
import com.hz.zdjfu.application.data.bean.BannerRecordsBean;
import com.hz.zdjfu.application.data.bean.HomeDataBean;
import com.hz.zdjfu.application.data.bean.MainPartyBean;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.data.bean.PublicNoticeRecordsBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.modle.party.announcementlist.AnnouncementListActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.CardScaleHelperUtil;
import com.hz.zdjfu.application.utils.FileUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.RecyclerScaleUtils;
import com.hz.zdjfu.application.utils.ScreenUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UpdateAppUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;
import com.hz.zdjfu.application.widget.dialog.MainPartyDialog;
import com.hz.zdjfu.application.widget.view.BannerImageHolderView;
import com.hz.zdjfu.application.widget.view.CustomRefreshFooterView;
import com.hz.zdjfu.application.widget.view.CustomRefreshHeadView;
import com.hz.zdjfu.application.widget.view.MarqueeView;
import com.hz.zdjfu.application.widget.view.SpeedRecyclerView;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;



/**
 * [类功能说明]
 * 首页界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, AdapterView.OnItemClickListener {

    private static final String TAG = HomeFragment.class.getName();
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.viewflipper_advertisement)
    MarqueeView viewflipperAdvertisement;
    @BindView(R.id.text_news_more)
    TextView textNewsMore;
    @BindView(R.id.look_complan_new)
    RelativeLayout lookComplanNew;
    @BindView(R.id.index_invite)
    LinearLayout indexInvite;
    @BindView(R.id.index_sign)
    LinearLayout indexSign;
    @BindView(R.id.index_info)
    LinearLayout indexInfo;
    @BindView(R.id.home_partys_view)
    LinearLayout homePartysView;
    @BindView(R.id.home_product_item_rv)
    SpeedRecyclerView homeProductItemRv;
    @BindView(R.id.btn_go_touzi)
    Button btnGoTouzi;
    @BindView(R.id.xrefreshview_view)
    XRefreshView mRefreshView;
    @BindView(R.id.home_product_title_tv)
    TextView homeProductTitleTv;
    @BindView(R.id.home_product_tag_iv)
    TextView homeProductTagIv;
    @BindView(R.id.home_empty_iv)
    ImageView homeEmptyIv;

    private HomeRecyclerAdaper mHomeAdapter;
    private boolean isFirstRun = true;//是否是第一次
    private  List<PublicNoticeRecordsBean> noticeLists;
    private HomeContract.Presenter mPresenter;
    private BannerImageHolderViewCBViewHolderCreator bannerImageHolderViewCBViewHolderCreator;
    private HomeAdapter mAdapter;
    private boolean isNoticationReFresh =false;//无网状态下 当有网时刷新页面 从新获取数据
    private CardScaleHelperUtil mMySnapHelperProduct;
    private LinearSnapHelper mLinearSnapHelper;
    private List<ProductBean> productRecords;
    private ProductBean mProductBean;
    private List<ProductBean> mLists;
    private RecyclerScaleUtils utils;
    private List<BannerRecordsBean> mBannerRecordlists;
    public String mUrl="";

    public static final int PERMISSION_UNKOWN_APP_SOURCES = 10012;
    public static HomeFragment mHomeFragment =null;
    public AppVersionBean mVersionBean;


    private String DUI_BA_URL =null;

    public static HomeFragment newInstance() {
        mHomeFragment = new HomeFragment();
        return mHomeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        //建立P
        new HomePresenter(getActivity(), this);
        isFirstRun = true;
        initViewData();

        //数据请求
        mPresenter.homeDataHttpRequest();
        //激活
        mPresenter.avtiveUser();
        //活动
        mPresenter.partyRequest();


    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            //请求产品
            mPresenter.homeDataHttpRequest();
        }
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstRun) {

        }
        isFirstRun = false;
    }



    @Override
    public void showErrorTips(String msg) {
        stopViewRefresh();
        if(!TextUtils.isEmpty(msg)){
            if(msg.equals("网络不给力,请检查网络连接")){
                isNoticationReFresh =true;
            }else {
                isNoticationReFresh =false;
            }
            ToastUtils.show(mActivity,msg);
        }

    }

    @Override
    public void initViewData() {

        initBannerView();
        initRecycleView();
        initReFreshView();
        initvMarqueeView();
        homeProductTagIv.setVisibility(View.GONE);
        if(mPresenter!=null){
            mPresenter.startCheckVersion();
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
            this.mPresenter =presenter;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void initBannerView() {

        //创建轮播子View
        bannerImageHolderViewCBViewHolderCreator = new BannerImageHolderViewCBViewHolderCreator();

        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .setPageIndicator(new int[]{R.drawable.gray_radius100,R.drawable.white_radius100})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if(mBannerRecordlists!=null&&mBannerRecordlists.size()>0){
                            BannerRecordsBean bean=  mBannerRecordlists.get(position);
                            goBannerDetail(bean);
                        }
                    }
                });

    }

    @Override
    public void initReFreshView() {


        mRefreshView.setAutoRefresh(false);
        mRefreshView.setPullLoadEnable(true);
        mRefreshView.setAutoLoadMore(false);
        mRefreshView.setMoveForHorizontal(true);
        mRefreshView.setCustomFooterView(new CustomRefreshFooterView(mActivity));
        mRefreshView.enableReleaseToLoadMore(false);
        mRefreshView.enableRecyclerViewPullUp(true);
        mRefreshView.enablePullUpWhenLoadCompleted(true);
        mRefreshView.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onLoadMore(boolean isSilence) {
               //下载更多
                mRefreshView.stopLoadMore();
            }

            @Override
            public void onRefresh(boolean isPullDown) {
               //下拉刷新
                mPresenter.homeDataHttpRequest();

            }


        });
    }

    @Override
    public void initRecycleView() {
        LinearLayoutManager mLinearLayoutManager =new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        homeProductItemRv.setLayoutManager(mLinearLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dimen_10_dip);
        homeProductItemRv.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mLists = new ArrayList<>();
        mHomeAdapter = new HomeRecyclerAdaper(mLists,mActivity);
        homeProductItemRv.setAdapter(mHomeAdapter);
        utils = new RecyclerScaleUtils();
        utils.attachToRecyclerView(homeProductItemRv, ScreenUtils.dip2px(mActivity,30f));

        homeProductItemRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            //获取当前滑动的pos
                if(utils!=null){
                    View mView=  utils.getCenterView();
                    if(mView!=null){
                        int mPos = (int) mView.getTag();
                        changeProductTitle(mPos);
                    }
                }
            }
        });
    }

    @Override
    public void initvMarqueeView() {

        //点击后跳转
        viewflipperAdvertisement.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {

                    if(noticeLists!=null&&noticeLists.size()>0){
                        PublicNoticeRecordsBean mBean = noticeLists.get(position);
                        if(mBean!=null){

                            String url =null;
                            if(!TextUtils.isEmpty(URLController.URL_ZZ)){
                                if(URLController.URL_ZZ.contains("pctest")){
                                    url=URLController.URL_ZZ+"/appStatic/re_detail/"+mBean.getId()+".action";
                                }else{
                                    url=URLController.URL_ZZ+"/zdjf/appStatic/re_detail/"+mBean.getId()+".action";
                                }
                            }else{
                                return;
                            }
                            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                            mIntent.putExtra("WebView_URL", url);
                            mIntent.putExtra("WebView_TITLE",mBean.getTitle()+"");
                            mActivity.startActivity(mIntent);
                        }

                    }

            }
        });

        //监听滚动
        viewflipperAdvertisement.setOnDisplayChagnedListener(new MarqueeView.OnDisplayChagnedListener() {
            @Override
            public void OnDisplayChildChanging(ViewFlipper view, int index) {

                List<PublicNoticeRecordsBean> mLists = (List<PublicNoticeRecordsBean>) view.getTag();
                if (mLists!=null&&index == mLists.size() - 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                          //  getPointRush();
                        }
                    }, 1000);
                }
            }
        });

    }


    @Override
    public void showProdectRecordEmptyData(Boolean isflag) {



    }



    /**
     * 广告跳转
     * @param bean
     */
    @Override
    public void goBannerDetail(BannerRecordsBean bean) {

        if(bean==null){
            return;
        }
        if(!TextUtils.isEmpty(bean.getHref_url())){
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            if(!(bean.getHref_url().contains("new_year")||bean.getHref_url().contains("annualBonus")||bean.getHref_url().contains("276"))){
                UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
                if(mUserBean!=null&&mUserBean.isLogin()){
                    mUrl =bean.getHref_url()+"?phone="+mUserBean.getUserPhone()+"&reg_source="+Constants.ANDROID_SOURCE;
                }else{
                    mUrl=bean.getHref_url()+"?reg_source="+Constants.ANDROID_SOURCE;
                }
            }else{
                mUrl =bean.getHref_url();
            }

            mIntent.putExtra("WebView_URL",mUrl);
            mIntent.putExtra("WebView_TITLE", bean.getTitle());
            mActivity.startActivity(mIntent);
        }else {
            ToastUtils.show(mActivity,"无效路径");
        }

    }


    @Override
    public void startIndexBannerTurning() {
        convenientBanner.stopTurning();
        indexLoopHandler.sendEmptyMessageDelayed(0,1000);
    }


    @Override
    public void stopViewRefresh() {

        if(mRefreshView!=null){
            mRefreshView.stopRefresh();
        }

    }

    @Override
    public void stopFlipping() {

        if (viewflipperAdvertisement == null) return;

        if (viewflipperAdvertisement.isFlipping()) {
            viewflipperAdvertisement.stopFlipping();
        }
    }

    @Override
    public void changeProductTitle(int position) {
        if(productRecords==null){
            return;
        }
        this.mProductBean  = productRecords.get(position);
        if(mProductBean!=null&&!TextUtils.isEmpty(mProductBean.getProduct_code())){
            if(homeProductTitleTv!=null){
                homeProductTitleTv.setText(mProductBean.getProduct_code());
            }
        }
        //类型
        if(mProductBean!=null&&homeProductTagIv!=null){
            if(mProductBean.getIs_fresh()==1){
                homeProductTagIv.setVisibility(View.VISIBLE);
                homeProductTagIv.setText("新手");
            }else{
                homeProductTagIv.setVisibility(View.GONE);
            }


            if(btnGoTouzi!=null&&mProductBean.getPay_min()>0){
                btnGoTouzi.setText(mProductBean.getPay_min()+"元起投");
            }

        }

    }

    @Override
    public void showHomeData(HomeDataBean bean) {

            stopViewRefresh();

            //广告 为空则默认一张
            if(bean.getAdvertiseList()==null||bean.getAdvertiseList().size()==0){
                List<BannerRecordsBean> mLists = new ArrayList<>();
                BannerRecordsBean mBean =new BannerRecordsBean();
                mBean.setId(Constants.BANNER_ID);
                mLists.add(mBean);
                bean.setAdvertiseList(mLists);
            }

            if (convenientBanner == null) return;

            mBannerRecordlists =bean.getAdvertiseList();
            if (isNoticationReFresh||convenientBanner.getViewPager() == null || convenientBanner.getViewPager().getAdapter() == null) {
                convenientBanner.setPages(bannerImageHolderViewCBViewHolderCreator,bean.getAdvertiseList());
                isNoticationReFresh =false;
            } else {
                convenientBanner.notifyDataSetChanged();
            }
            if (bean.getAdvertiseList().size() == 1) {
                convenientBanner.stopTurning();
            } else {
                startIndexBannerTurning();
            }


            //公告栏
            if(bean.getNoticeList()!=null&&bean.getNoticeList().size()>0){

                stopFlipping();
                if (viewflipperAdvertisement == null) return;

                this.noticeLists =bean.getNoticeList();
                viewflipperAdvertisement.startWithList(bean.getNoticeList());

            }

        //产品
        if(bean!=null&&bean.getProductList()!=null&&bean.getProductList().size()>0){
            homeEmptyIv.setVisibility(View.GONE);
            homeProductItemRv.setVisibility(View.VISIBLE);
            if(bean.getProductList().size()>3){
                mLists = new ArrayList<>();
                mLists.addAll(bean.getProductList().subList(0,3));
                productRecords =mLists;
                mHomeAdapter.setData(mLists);
                mHomeAdapter.notifyDataSetChanged();
              //  mAdapter.setNewData(mlists);
            }else{
                productRecords =bean.getProductList();
              //  mAdapter.setNewData(bean.getProductList());
                mHomeAdapter.setData(bean.getProductList());
                mHomeAdapter.notifyDataSetChanged();
            }
        }else{
            homeEmptyIv.setVisibility(View.VISIBLE);
            homeProductItemRv.setVisibility(View.GONE);
            btnGoTouzi.setBackgroundResource(R.color.gray13);
        }

            changeProductTitle(0);

    }

    @Override
    public void showBannerdData(BannerRecordLists mlists) {

        //当无网络时候 默认数据
        if (convenientBanner == null) return;


        if (isNoticationReFresh||convenientBanner.getViewPager() == null || convenientBanner.getViewPager().getAdapter() == null) {
            convenientBanner.setPages(bannerImageHolderViewCBViewHolderCreator,mlists.getRecords());
            isNoticationReFresh =false;
        } else {
            convenientBanner.notifyDataSetChanged();
        }
        if (mlists.getRecords().size() == 1) {
            convenientBanner.stopTurning();
        } else {
            startIndexBannerTurning();
        }

    }

    @Override
    public void showVersionDetail(AppVersionBean bean) {

        new RxPermissions(mActivity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(granted -> {
                    if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                        if (bean == null) {
                            return;
                        }
                        if (mActivity == null) {
                            return;
                        }
                        String VersionTitle =null;
                        String mContent =null;
                        try{
                            String releaseContent =bean.getRelease_content();
                            if(!TextUtils.isEmpty(releaseContent)){
                                String[] mTitle =releaseContent.split(" ");
                                if(mTitle!=null&&mTitle.length>0){
                                    VersionTitle =mTitle[0];
                                    if(mTitle.length>1){
                                        int size =VersionTitle.length();
                                        mContent=releaseContent.substring(size+1);
                                    }else{
                                        mContent=null;
                                    }
                                }
                            }
                        }catch (Exception e){

                        }
                        if(!TextUtils.isEmpty(mContent)){
                            mContent =mContent.replaceAll(" ", "\\\n");
                        }
                        this.mVersionBean =bean;
                        try{
                            if(bean.getIs_force()==1){
                                UpdateAppUtils.getInstance().updateApp(bean, mActivity,mContent,VersionTitle,bean.getRelease_version(),bean.getSub_version(),true,TAG);
                            }else{
                                UpdateAppUtils.getInstance().updateApp(bean, mActivity,mContent,VersionTitle,bean.getRelease_version(),bean.getSub_version(),false,TAG);
                            }
                        }catch (Exception e){
                        }
                    }else{
                        DialogManager.showErrorDialog(
                                mActivity,
                                "获取权限失败",
                                "不授予权限,将无法更新，需更新则退出重启再试!",
                                sweetAlertDialog -> {
                                    sweetAlertDialog.dismiss();
                                }
                        );
                    }
                });



    }

    @Override
    public void showPartyData(MainPartyBean bean) {

        if(bean==null){
            return;
        }
        if(TextUtils.isEmpty(bean.getHrefUrl())||TextUtils.isEmpty(bean.getImageUrl())){
            return;
        }
        String oldUrl = PreferencesUtils.getString(mActivity, Constants.MAIN_PARTY_URL);
        String mState = PreferencesUtils.getString(mActivity, Constants.MAIN_PARTY_STATE);
        if (TextUtils.isEmpty(oldUrl)) {
            showPartyDialog(bean);
        } else {
            if(oldUrl.equals(bean.getImageUrl())){
                if(mState.equals("false")){
                    showPartyDialog(bean);
                }
            }else{
                showPartyDialog(bean);
            }
        }
    }

    @Override
    public void showPartyDialog(MainPartyBean bean) {
        MainPartyDialog mDialog = new MainPartyDialog(mActivity, bean,new MainPartyDialog.PartyDialogListener() {
            @Override
            public void callback(MainPartyBean mBean) {
                Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL", mBean.getHrefUrl()+"");
                mIntent.putExtra("WebView_TITLE",mBean.getTitle()+"");
                mActivity.startActivity(mIntent);
            }
        });
        mDialog.show();

        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PreferencesUtils.putString(mActivity, Constants.MAIN_PARTY_STATE,"true");
                PreferencesUtils.putString(mActivity, Constants.MAIN_PARTY_URL,bean.getImageUrl());
            }
        });

        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PreferencesUtils.putString(mActivity, Constants.MAIN_PARTY_STATE,"true");
                PreferencesUtils.putString(mActivity, Constants.MAIN_PARTY_URL,bean.getImageUrl());
            }
        });


    }

    @Override
    public void showDuiBaUrl(String url) {

        Intent  mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL", url);
        mIntent.putExtra("WebView_TITLE","兑吧");
        mActivity.startActivity(mIntent);

    }


    private Handler indexLoopHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(convenientBanner!=null){
                convenientBanner.startTurning(5000);
            }
        }
    };


    @OnClick({R.id.text_news_more, R.id.index_invite, R.id.index_sign, R.id.index_info, R.id.btn_go_touzi,R.id.index_splend_party})
    public void onViewClicked(View view) {

        Intent mIntent =null;
        switch (view.getId()) {
            case R.id.text_news_more://通知
                startActivity(AnnouncementListActivity.makeIntent(mActivity,null));
                break;
            case R.id.index_invite://每日签到

                UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();
                if(mUserBean!=null&&mUserBean.isLogin()){
                    //兑吧
                    if(mPresenter!=null){
                        mPresenter.duiba();
                    }
                }else{
                    Bundle mBundle = new Bundle();
                    mBundle.putString("FROMPARENT","HOME");
                    startActivity(LoginActivity.makeIntent(mActivity,mBundle));
                }
                break;
            case R.id.index_sign://安全保障
                mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_HOME_SAFE_GUARD);
                mIntent.putExtra("WebView_TITLE",getString(R.string.home_tag_safeguard_title));
                mActivity.startActivity(mIntent);
                break;
            case R.id.index_info://平台数据
                mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_HOME_PLATFORM_DATA);
                mIntent.putExtra("WebView_TITLE",getString(R.string.home_tag_platformdata_title));
                mActivity.startActivity(mIntent);
                break;
            case R.id.index_splend_party://精彩活动
                ToastUtils.show(mActivity,"正在开发中，敬请期待");
//                mIntent = new Intent(mActivity, WebViewActivity.class);
//                mIntent.putExtra("WebView_URL",H5Urls.H5_URL_PARTY);
//                mActivity.startActivity(mIntent);
                break;
            case R.id.btn_go_touzi://

                if(mProductBean!=null){
                    Bundle mBundle = new Bundle();
                    if(mProductBean.getProduct_type()==1){
                        mBundle.putString("PRODUCTID",mProductBean.getId()+"");
                        startActivity(ProductDetailActivity.makeIntent(mActivity,mBundle));
                    }else if(mProductBean.getProduct_type()==2){//直投
                        mBundle.putString("PRODUCTID",mProductBean.getId()+"");
                        startActivity(ZTProductDetailActivity.makeIntent(mActivity,mBundle));
                    }
                }
                break;
        }
    }



    /*包装BannerImageHolderView*/
    private class BannerImageHolderViewCBViewHolderCreator implements CBViewHolderCreator<BannerImageHolderView> {
        @Override
        public BannerImageHolderView createHolder() {
            return new BannerImageHolderView(HomeFragment.this);
        }
    }


    private class  SpaceItemDecoration extends  RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if(parent.getChildPosition(view) != 0)
                outRect.left =space;

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10011:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!AppUtils.installApp(mActivity, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
                        ToastUtils.show(mActivity,"安装包未找到,请退出重启再试");
                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, mActivity, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
                    }
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, PERMISSION_UNKOWN_APP_SOURCES);
                }
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PERMISSION_UNKOWN_APP_SOURCES:
                UpdateAppUtils.getInstance().checkIsAndroidApk(mActivity,mVersionBean);
                break;
            default:
                break;
        }
    }




}
