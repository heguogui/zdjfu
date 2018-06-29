package com.hz.zdjfu.application.modle.mine.personcenter.setting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bumptech.glide.Glide;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.utils.AppUtils;
import com.hz.zdjfu.application.utils.DataCleanManager;
import com.hz.zdjfu.application.utils.FileUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UpdateAppUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/21 0021
 */
public class SettingFragment extends BaseFragment implements SettingContract.View {

    public static final String TAG = SettingFragment.class.getName();
    @BindView(R.id.setting_show_version_tv)
    TextView settingShowVersionTv;
    @BindView(R.id.setting_message_tb)
    ToggleButton settingMessageTb;
    @BindView(R.id.setting_cache_rl)
    RelativeLayout settingCacheRl;
    @BindView(R.id.setting_contact_rl)
    RelativeLayout settingContactRl;
    @BindView(R.id.setting_about_rl)
    RelativeLayout settingAboutRl;
    @BindView(R.id.setting_cache_size_tv)
    TextView  settingCacheSizeTv;


    public AppVersionBean mVersionBean;
    public static final int PERMISSION_UNKOWN_APP_SOURCES = 10012;

    private SettingContract.Presenter mPresenter;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
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

        new SettingPresenter(mActivity, this);
        showVersionDetail();
        showCacheData();
        showPushMsgState();

    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void clearCache() {
        DialogManager.showProgressDialog(mActivity, "清理中...");
        try{

            DataCleanManager.clearCacheDiskSelf();
        }catch (Exception e){

        }
        DialogManager.hideProgressDialog();
        showCacheData();

    }

    @Override
    public void showVersionDetail() {

        String mVersion = AppUtils.getAppInfo(mActivity).getVersionName()+"";
        settingShowVersionTv.setText("版本:"+mVersion+"");

    }

    @Override
    public void showCacheData() {

        try{

            File mDiskCache =Glide.getPhotoCacheDir(mActivity);
            //File zdjfuFile=new File("/sdcard/zdjfu");
            long mDirSize =DataCleanManager.getDirSize(mDiskCache);
           // long mzdjfuFileSize =DataCleanManager.getDirSize(zdjfuFile);
           // double size =mDirSize+mzdjfuFileSize;
            String mSize =DataCleanManager.getFormatSize(mDirSize);
            settingCacheSizeTv.setText(mSize+"");

        }catch (Exception e){

        }

    }

    @Override
    public void showPushMsgState() {
        if(PreferencesUtils.getBoolean(mActivity,Constants.USER_PUSHMSG_STATE_PREFERENCE,false)){
            settingMessageTb.setChecked(true);
        }else{
            settingMessageTb.setChecked(false);
        }

        settingMessageTb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if(state){
                    PreferencesUtils.putBoolean(mActivity,Constants.USER_PUSHMSG_STATE_PREFERENCE,true);
                }else{
                    PreferencesUtils.putBoolean(mActivity,Constants.USER_PUSHMSG_STATE_PREFERENCE,false);
                }
            }
        });

    }

    @Override
    public void showVersionState(AppVersionBean versionBean) {
//        if (versionBean == null) {
//            return;
//        }
//        if (mActivity == null) {
//            return;
//        }
//        String VersionTitle =null;
//        String mContent =null;
//        try{
//
//            String releaseContent =versionBean.getRelease_content();
//            if(!TextUtils.isEmpty(releaseContent)){
//                String[] mTitle =releaseContent.split(" ");
//                if(mTitle!=null&&mTitle.length>0){
//                    VersionTitle =mTitle[0];
//                    if(mTitle.length>1){
//                        int size =VersionTitle.length();
//                        mContent=releaseContent.substring(size+1);
//                    }else{
//                        mContent=null;
//                    }
//                }
//            }
//
//        }catch (Exception e){
//
//        }
//        if(!TextUtils.isEmpty(mContent)){
//            mContent =mContent.replaceAll(" ", "\\\n");
//        }
//        this.mVersionBean =versionBean;
//        try{
//            if(versionBean.getIs_force()==1){
//                UpdateAppUtils.getInstance().updateApp(versionBean, mActivity,mContent,VersionTitle,versionBean.getRelease_version(),versionBean.getSub_version(),true,TAG);
//            }else{
//                UpdateAppUtils.getInstance().updateApp(versionBean, mActivity,mContent,VersionTitle,versionBean.getRelease_version(),versionBean.getSub_version(),false,TAG);
//            }
//        }catch (Exception e){
//
//        }

        new RxPermissions(mActivity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(granted -> {
                    if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
                        if (versionBean == null) {
                            return;
                        }
                        if (mActivity == null) {
                            return;
                        }
                        String VersionTitle =null;
                        String mContent =null;
                        try{

                            String releaseContent =versionBean.getRelease_content();
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
                        this.mVersionBean =versionBean;
                        try{
                            if(versionBean.getIs_force()==1){
                                UpdateAppUtils.getInstance().updateApp(versionBean, mActivity,mContent,VersionTitle,versionBean.getRelease_version(),versionBean.getSub_version(),true,TAG);
                            }else{
                                UpdateAppUtils.getInstance().updateApp(versionBean, mActivity,mContent,VersionTitle,versionBean.getRelease_version(),versionBean.getSub_version(),false,TAG);
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
    public void showVersionMsg() {
        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, mActivity, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
        ToastUtils.show(mActivity,"当前已是最新版本");
    }

    @OnClick({R.id.setting_check_version_iv,R.id.setting_cache_rl, R.id.setting_contact_rl, R.id.setting_about_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_check_version_iv:
                if(mPresenter!=null){
                    mPresenter.checkVersionDetail();
                }
                break;
            case R.id.setting_cache_rl://清除缓存
                clearCache();
                break;
            case R.id.setting_contact_rl://联系我们
                startActivity(ContactActivity.makeIntent(mActivity,null));
                break;
            case R.id.setting_about_rl://关于我们
                Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_SETTING_ABOUT);
                mIntent.putExtra("WebView_TITLE","关于我们");
                mActivity.startActivity(mIntent);
                break;
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
