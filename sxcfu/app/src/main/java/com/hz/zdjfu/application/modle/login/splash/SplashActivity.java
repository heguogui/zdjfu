package com.hz.zdjfu.application.modle.login.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.data.bean.LoginBean;
import com.hz.zdjfu.application.data.bean.TokenBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.http.RetrofitUtil;
import com.hz.zdjfu.application.http.TransformUtils;
import com.hz.zdjfu.application.http.ZTHttpResponseSubscriber;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.login.welcome.WelcomeActivity;
import com.hz.zdjfu.application.utils.ActivityManagerUtil;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.DialogManager;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * [类功能说明]
 *启动页面
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/13 0013.
 */

public class SplashActivity extends Activity {

    private final  static String TAG = SplashActivity.class.getName();
//    @BindView(R.id.splash_background_iv)
//    ImageView splashBackgroundIv;
//    @BindView(R.id.skip_home_tv)
//    TextView skipHomeTv;

    private AlphaAnimation refreshingAnimation;
    private int recLen =2;// 统计广告时间
    private boolean mState =false;
    private  Thread myThread;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        ButterKnife.bind(this);
        ActivityManagerUtil.getActivityManager().add(this);
        initData();
    }

//
//    @OnClick(R.id.skip_home_tv)
//    public void onViewClicked() {
//        // 到时间，跳转到主页
//        mState =false;
//        if(myThread!=null&&!myThread.isInterrupted()){
//            myThread.interrupt();
//            myThread =null;
//        }
//        goMainHome();
//    }


//    public void initViewData() {
//        refreshingAnimation =(AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.launch_anim);
//        splashBackgroundIv.setAnimation(refreshingAnimation);
//        skipHomeTv.setText("跳过 " + recLen + "s");
//    }



//    //由于6.0 之后需要手动授予权限
//    private void getPersimmions() {
//        new RxPermissions(this)
//                .request(
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_PHONE_STATE
//                )
//                .compose(TransformUtils.defaultSchedulers())
//                .subscribe(granted -> {
//                    if (granted) { // 在android 6.0之前会默认返回true, 已经获取权限
//                        initData();
//                    } else { // 未获取权限
//                        DialogManager.showErrorDialog(
//                                SplashActivity.this,
//                                "获取权限失败",
//                                "请检查并开启应用所需权限",
//                                sweetAlertDialog -> {
//                                    sweetAlertDialog.dismiss();
//                                    finish();
//                                }
//                        );
//                    }
//                });
//
//    }

    private void initData() {
//        // 第一次打开app，进入欢迎页面
//        if (!PreferencesUtils.getBoolean(Constants.WELCOME_PREFERENCE,SplashActivity.this, Constants.SP_KEY_WELCOME_FLAG,false)) {
//            startActivity(new Intent(ZDJFUApplication.getInstance().getApplicationContext(),WelcomeActivity.class));
//            SplashActivity.this.finish();
//        } else {//获取背景活动图片
//            initViewData();
//            mState =true;
//            myThread = new Thread(new MyThread());
//            myThread.start();
//
//            //更新Token
//            RetrofitUtil.createZTHService().ztFreshToken().compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<TokenBean>() {
//                @Override
//                public void onSuccess(TokenBean result) {
//                    if(result!=null){
//                        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
//                        mBean.setJwtToken(result.getJwtToken());
//                        mBean.setAccessToken(result.getAccessToken());
//                        UserInfoManager.getInstance().saveCurrentUserInfo(mBean);
//                        Log.i(TAG,"更新Token成功");
//                    }
//                }
//
//                @Override
//                public void _onError(Throwable e) {
//
//                }
//            });
//
//        }


        // 第一次打开app，进入欢迎页面
        if (!PreferencesUtils.getBoolean(Constants.WELCOME_PREFERENCE,SplashActivity.this, Constants.SP_KEY_WELCOME_FLAG,false)) {
            startActivity(new Intent(ZDJFUApplication.getInstance().getApplicationContext(),WelcomeActivity.class));
            SplashActivity.this.finish();
        } else {//获取背景活动图片

            UserBean  mBean = UserInfoManager.getInstance().getLocationUserData();
            if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())&&!TextUtils.isEmpty(mBean.getUserPassord())){
                RetrofitUtil.createZTService().ztlogin(mBean.getUserPhone(),mBean.getUserPassord()).compose(TransformUtils.defaultSchedulers()).subscribe(new ZTHttpResponseSubscriber<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean result) {
                        if(result!=null&&!TextUtils.isEmpty(result.getAccessToken())&&!TextUtils.isEmpty(result.getJwtToken())){
                            UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                            mBean.setLogin(true);
                            mBean.setUserPhone(mBean.getUserPhone());
                            mBean.setUserPassord(mBean.getUserPassord());
                            mBean.setAccessToken(result.getAccessToken());
                            mBean.setJwtToken(result.getJwtToken());
                            UserInfoManager.getInstance().saveCurrentUserInfo(mBean);
                        }
                    }
                    @Override
                    public void _onError(Throwable e) {

                    }
                });
            }

            mState =true;
            myThread = new Thread(new MyThread());
            myThread.start();
        }


    }


    @Override
    protected void onDestroy() {
        DialogManager.hideProgressDialog();
        super.onDestroy();
        ActivityManagerUtil.getActivityManager().removeActivity(this);
    }


    /**
     * 进入主页面
     */
    private void goMainHome() {
        startActivity(MainActivity.makeIntent(SplashActivity.this,null));
        SplashActivity.this.finish();
    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                recLen--;
                if(recLen==1){
                    mState =false;
                    if(myThread!=null&&!myThread.isInterrupted()){
                        myThread.interrupt();
                        myThread =null;
                    }
                    goMainHome();
                    return;
                }
            }
        };
    };


    public class MyThread implements Runnable {
        @Override
        public void run() {
            while (mState){
                try{
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what =0;
                    handler.sendMessage(message);
                }catch (Exception e){

                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
