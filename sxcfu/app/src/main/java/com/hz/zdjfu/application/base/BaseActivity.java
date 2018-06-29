package com.hz.zdjfu.application.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.hz.zdjfu.application.utils.ActivityManagerUtil;
import com.hz.zdjfu.application.utils.ActivityUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;
import com.umeng.analytics.MobclickAgent;


/**
 * [类功能说明]
 *无toolbar Activity基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 页面间传值key常量
     */
    public static final String BUNDLE = "bundle";

    /**
     * 布局文件Id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 获取fragment
     *
     * @return
     */
    protected abstract BaseFragment getFragment();

    /**
     * 布局中Fragment的ID
     *
     * @return
     */
    protected abstract int getFragmentContentId();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // initStatusBar();
        //设置刘海屏幕
        ActivityManagerUtil.getActivityManager().add(this);
        setContentView(getContentViewId());
        // 处理传过来的intent
        handleIntent(getIntent());
        // 将fragment添加到activity
        addFragmentToActivity();

        // 后续初始化操作
        init();
    }

    private void addFragmentToActivity() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(getFragmentContentId());
        if (fragment == null) {
            // create the fragment
            fragment = getFragment();
            if (fragment != null) {
                ActivityUtils.addFragmenttoActivity(
                        getSupportFragmentManager(),
                        fragment,
                        getFragmentContentId()
                );
            }


        }
    }


    protected void setLiuHai(){
//        WindowManager.LayoutParams lp =this.getWindow().getAttributes();
//        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
//        getWindow().setAttributes(lp);
//
//
//        DisplayCutout displayCutout = null;
//        if (Build.VERSION.SDK_INT >= 28) {
//            displayCutout = new View(this).getRootWindowInsets().getDisplayCutout();
//            Util.logv("Util", "SafeInsetBottom:" + displayCutout.getSafeInsetBottom());
//            Util.logv("Util", "SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
//            Util.logv("Util", "SafeInsetRight:" + displayCutout.getSafeInsetRight());
//            Util.logv("Util", "SafeInsetTop:" + displayCutout.getSafeInsetTop());
//        }
    }


    /**
     * 获取Intent
     *
     * @param intent
     */
    protected void handleIntent(Intent intent) {
    }

    /**
     * 加载完fragment之后进行一些初始化操作
     */
    protected void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**友盟页面统计*/
        MobclickAgent.onResume(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        /**友盟页面统计*/
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogManager.hideProgressDialog();
        ActivityManagerUtil.getActivityManager().removeActivity(this);
    }

    /**
     * 初始化系统状态栏
     * 在4.4以上版本是否设置透明状态栏
     */
    private void initStatusBar() {
        if (!setTranslucentStatusBar()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 设置透明状态栏
     *
     * @return true 设置, false 不设置, 默认为false
     */
    protected boolean setTranslucentStatusBar() {
        return false;
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//   private void setToobBarBackGroud(){
//       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//           View decorView = getWindow().getDecorView();
//           int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                   | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//           decorView.setSystemUiVisibility(option);
//           getWindow().setStatusBarColor(Color.TRANSPARENT);
//       }
//   }

}
