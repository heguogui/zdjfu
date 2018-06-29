package com.hz.zdjfu.application.modle.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.data.bean.BannerRecordLists;
import com.hz.zdjfu.application.data.bean.BannerRecordsBean;
import com.hz.zdjfu.application.data.bean.HomeDataBean;
import com.hz.zdjfu.application.data.bean.MainPartyBean;
import com.hz.zdjfu.application.data.bean.UrlBean;
import com.hz.zdjfu.application.http.HttpResponseSubscriber;
import com.hz.zdjfu.application.http.HttpsUtils;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * [类功能说明]
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class HomePresenter implements HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getName();
    private HomeContract.View mView;
    private Context mContext;

    public HomePresenter(Context context, HomeContract.View mView) {
        this.mContext = context;
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


//    @Override
//    public void bannersHttpRequest() {
//
//        RetrofitUtil.createService().bannerList("1").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<BannerRecordLists>() {
//            @Override
//            public void onSuccess(BannerRecordLists result) {
//
//                if(result!=null){
//                    mView.showBannerdData(result);
//                }else{
//                    mView.showBannerdData(setDefaultImage());
//                }
//
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                mView.showErrorTips(e.getMessage());
//                mView.showBannerdData(setDefaultImage());
//            }
//        });

//
//        BannerRecordLists mLists = new BannerRecordLists();
//        BannerRecordsBean mBean = new BannerRecordsBean();
//        mBean.setId("22");
//        List<BannerRecordsBean> lists = new ArrayList<BannerRecordsBean>();
//        lists.add(mBean);
//        BannerRecordsBean mBean1 = new BannerRecordsBean();
//        mBean1.setId("");
//        lists.add(mBean1);
//        BannerRecordsBean mBean2 = new BannerRecordsBean();
//        mBean2.setId("");
//        lists.add(mBean2);
//        mLists.setRecords(lists);
//        mView.showBannerdData(mLists);
//
//
//    }


    /**
     *  为空则默认一张
     * @return
     */
    private BannerRecordLists setDefaultImage() {
        BannerRecordLists mLists = new BannerRecordLists();
        BannerRecordsBean mBean = new BannerRecordsBean();
        mBean.setId(Constants.BANNER_ID);
        List<BannerRecordsBean> lists = new ArrayList<BannerRecordsBean>();
        lists.add(mBean);
        mLists.setRecords(lists);
        return mLists;
    }


    //   @Override
    //   public void publicNoticeHttpRequest() {
//
//        RetrofitUtil.createService().noticeList("1","3","1").compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<PublicNoticeRecordLists>() {
//            @Override
//            public void onSuccess(PublicNoticeRecordLists result) {
//                if(result!=null){
//                    mView.showPublicNotice(result);
//                }
//            }
//
//            @Override
//            public void _onError(Throwable e) {
//                mView.showErrorTips(e.getMessage());
//            }
//        });
//
//        PublicNoticeRecordLists result = new PublicNoticeRecordLists();
//        List<PublicNoticeRecordsBean> records = new ArrayList<>();
//        PublicNoticeRecordsBean mbean = new PublicNoticeRecordsBean();
//        mbean.setTitle("元宝0加入正道金服");
//        records.add(mbean);
//        PublicNoticeRecordsBean mbean1 = new PublicNoticeRecordsBean();
//        mbean1.setTitle("元宝1加入正道金服");
//        records.add(mbean1);
//        PublicNoticeRecordsBean mbean2 = new PublicNoticeRecordsBean();
//        mbean2.setTitle("元宝2加入正道金服");
//        records.add(mbean2);
//        PublicNoticeRecordsBean mbean3 = new PublicNoticeRecordsBean();
//        mbean3.setTitle("元宝3加入正道金服");
//        records.add(mbean3);
//        result.setRecords(records);
//        mView.showPublicNotice(result);
//
//    }

//    @Override
//    public void productListsHttpRequest() {
//        RetrofitUtil.createService().indexCenters()
//                .compose(TransformUtils.defaultSchedulers())
//                .subscribe(new HttpResponseSubscriber<ProductRecordLists>() {
//
//
//                    @Override
//                    public void onSuccess(ProductRecordLists result) {
//                        mView.showProductRecordLists(result);
//                    }
//
//                    @Override
//                    public void _onError(Throwable e) {
//                        mView.showErrorTips(e.getMessage());
//                        mView.showProductRecordLists(null);
//                    }
//                });


    //            ProductRecordLists mList = new ProductRecordLists();
//            List<ProductRecordsBean> mlist = new ArrayList<>();
//
//            ProductRecordsBean mbean = new ProductRecordsBean();
//            mbean.setStatus(4);
//            mbean.setProductName("正道金服000");
//            mbean.setProductCode("正道金服001");
//            mbean.setPlatformInterest("0.00");
//            mbean.setIncome("0.00");
//            mbean.setIncomeDays(0);
//            mbean.setIsFresh(-1);
//            mbean.setMoney("0.00");
//            mbean.setPhoto("");
//            mbean.setSpeed("0.85");
//            mbean.setPayMin("100");
//            mbean.setWillIssueTime("0");
//            mlist.add(mbean);
//
//        ProductRecordsBean mbean1 = new ProductRecordsBean();
//        mbean1.setStatus(4);
//        mbean1.setProductName("正道金服001");
//        mbean1.setProductCode("正道金服001");
//        mbean1.setPlatformInterest("0.00");
//        mbean1.setIncome("0.00");
//        mbean1.setIncomeDays(0);
//        mbean1.setIsFresh(-1);
//        mbean1.setMoney("0.00");
//        mbean1.setPhoto("");
//        mbean1.setSpeed("0.85");
//        mbean1.setPayMin("100");
//        mbean1.setWillIssueTime("0");
//        mlist.add(mbean1);
//
//
//        ProductRecordsBean mbean2 = new ProductRecordsBean();
//        mbean2.setStatus(4);
//        mbean2.setProductName("正道金服002");
//        mbean2.setProductCode("正道金服001");
//        mbean2.setPlatformInterest("0.00");
//        mbean2.setIncome("0.00");
//        mbean2.setIncomeDays(0);
//        mbean2.setIsFresh(-1);
//        mbean2.setMoney("0.00");
//        mbean2.setPhoto("");
//        mbean2.setSpeed("0.85");
//        mbean2.setPayMin("100");
//        mbean2.setWillIssueTime("0");
//        mlist.add(mbean2);
//
//        mList.setProductRecords(mlist);
//
//        mView.showProductRecordLists(mList);
//
//    }
//
    @Override
    public void homeDataHttpRequest() {

        DialogManager.showProgressDialog(mContext, "加载中...");
        RetrofitUtil.createService().mainIndex().compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<HomeDataBean>() {
            @Override
            public void onSuccess(HomeDataBean result) {
                DialogManager.hideProgressDialog();
                if (result != null) {
                    mView.showHomeData(result);
                }
            }

            @Override
            public void _onError(Throwable e) {
                DialogManager.hideProgressDialog();
                mView.showErrorTips(e.getMessage());
                mView.showBannerdData(setDefaultImage());
            }
        });


    }

    @Override
    public void startCheckVersion() {

        String mIP = HttpsUtils.getMobileHostIP();
        if (TextUtils.isEmpty(mIP)) {
            ToastUtils.show(mContext, "获取手机IP失败");
            return;
        }

        String mSign = HttpsUtils.getHttpRequestSign(Constants.ANDROID_SOURCE);
        if (TextUtils.isEmpty(mSign)) {
            return;
        }

        RetrofitUtil.createService().checkVersion(mIP, Constants.ANDROID_SOURCE, mSign).compose(TransformUtils.defaultSchedulers()).subscribe(new HttpResponseSubscriber<AppVersionBean>() {
            @Override
            public void onSuccess(AppVersionBean result) {

                if (result == null) {
                    return;
                }
                AppUtils.AppInfo appInfo = AppUtils.getAppInfo(ZDJFUApplication.getInstance().getApplicationContext());
                if (appInfo == null) {
                    return;
                }

                Log.i(TAG, "当前的版本" + appInfo.getVersionCode());

                if(result.getIs_release()==0){
                    return;
                }

                if (Integer.parseInt(result.getRelease_version()) <= appInfo.getVersionCode()) {
                    clearUpdatePreference();
                    return;
                }
                mView.showVersionDetail(result);

            }

            @Override
            public void _onError(Throwable e) {
                mView.showErrorTips(e.getMessage());
            }
        });


    }

    @Override
    public void avtiveUser() {

        try{

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {//6.0以上(包括6.0)
                new RxPermissions((Activity) mContext)
                        .request(Manifest.permission.READ_PHONE_STATE)
                        .compose(TransformUtils.defaultSchedulers())
                        .subscribe(granted -> {
                            if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                                TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                                String IMEI = null;
                                if (tm != null) {
                                    IMEI = tm.getDeviceId();
                                }
                                Log.i(TAG,"IMEI="+IMEI);
                                if(TextUtils.isEmpty(IMEI)){
                                    return;
                                }

                                String umeng_channel = AppUtils.metaData(mContext,"UMENG_CHANNEL");
                                if(TextUtils.isEmpty(umeng_channel)){
                                    return;
                                }

                                RetrofitUtil.createZTService().activeUser(IMEI,umeng_channel,"android").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        Log.i(TAG,"激活成功");
                                    }

                                    @Override
                                    public void _onError(Throwable e) {

                                    }
                                });

                            }
                        });
            }else{

                TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                String IMEI = null;
                if (tm != null) {
                    IMEI = tm.getDeviceId();
                }
                Log.i(TAG,"IMEI="+IMEI);
                if(TextUtils.isEmpty(IMEI)){
                    return;
                }

                String umeng_channel = AppUtils.metaData(mContext,"UMENG_CHANNEL");
                if(TextUtils.isEmpty(umeng_channel)){
                    return;
                }

                RetrofitUtil.createZTService().activeUser(IMEI,umeng_channel,"android").compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i(TAG,"激活成功");
                    }

                    @Override
                    public void _onError(Throwable e) {

                    }
                });

            }


        }catch (Exception e){

        }

    }

    @Override
    public void partyRequest() {

        RetrofitUtil.createZTService().mainPartyRequest().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<MainPartyBean>() {
            @Override
            public void onSuccess(MainPartyBean result) {
               if(result!=null){
                   mView.showPartyData(result);
               }
            }
            @Override
            public void _onError(Throwable e) {

            }
        });

    }

    @Override
    public void duiba() {

        RetrofitUtil.createZTHService().qiandao().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<UrlBean>() {
            @Override
            public void onSuccess(UrlBean result) {

              if(result!=null&&!TextUtils.isEmpty(result.getUrl())){
                  mView.showDuiBaUrl(result.getUrl());
              }
            }
            @Override
            public void _onError(Throwable e) {

            }
        });
    }


    public void clearUpdatePreference() {
        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, mContext, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
    }

}
