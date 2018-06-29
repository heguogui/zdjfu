package com.hz.zdjfu.application.base;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.EnvironmentSwitch;
import com.hz.zdjfu.application.config.URLController;
import com.hz.zdjfu.application.exception.CustomCrashHandler;
import com.hz.zdjfu.application.http.DiskCacheUtil;
import com.hz.zdjfu.application.http.OkHttpUtil;
import com.hz.zdjfu.application.modle.getturepwd.LockPatternUtils;
import com.hz.zdjfu.application.utils.FileUtils;
import com.hz.zdjfu.application.utils.LogUtils;
import com.hz.zdjfu.application.utils.UpdateAppUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;
import java.io.InputStream;


/**
 * [类功能说明]
 *应用上下文 需在要manifest文件中注册
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ZDJFUApplication extends MultiDexApplication{

    public static ZDJFUApplication instance = null;

    /**
     * 是否打印log（debug 状态打开打印 release状态关闭打印）
     */
    public boolean print = true;
    public File DataCache;

    public String MPRODUCTID=null;
    public String MCURRENT_ACTIVITY =null;
    public String BALANCE_NUM=null;
    public static ZDJFUApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;
        initAssistData();
    }

    /**
     * 初始化辅助数据
     */
    private void initAssistData() {

        /* 初始化全局异常捕获信息 */
        CustomCrashHandler customCrashHandler = CustomCrashHandler.getInstance();
        customCrashHandler.init(this);

        //debug 环境下
        /* 初始化接口环境 */
        String hzkjURL = EnvironmentSwitch.HZKJ_URL_DEBUG_VALUE;
        /* 初始化数据库版本 */
        int hzkjDBVer = EnvironmentSwitch.DB_DEBUG_VER;

        /*初始化接口环境地址*/
        URLController.initEnv(this,hzkjURL);
        //缓存
        DiskCacheUtil.createInstance(getApplicationContext());
        //初始化文件
        FileUtils.createInstance(getApplicationContext());
        //初始化图案密码
        LockPatternUtils.createInstance(getApplicationContext());
        //初始化第三方库的设置
        initThirdPartyConfig();


    }

    /**
     * 初始化第三方库的设置
     */
    private void initThirdPartyConfig() {

        LogUtils.init("ZDJFULogger");
 //        初始化glide
        Glide.get(this)
                .register(
                        GlideUrl.class,
                        InputStream.class,
                        new OkHttpUrlLoader.Factory(OkHttpUtil.createFileClient())
                );

        // 版本更新
        UpdateAppUtils.getInstance().updateInit();

        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID,false);


    }


    /**
     * 清除来源Activity
     * @param content
     * @return
     */
    public void clearParent(String content){
        if(!TextUtils.isEmpty(content)&&!TextUtils.isEmpty(MCURRENT_ACTIVITY)){
            if(content.equals(MCURRENT_ACTIVITY)){
                this.MCURRENT_ACTIVITY =null;
            }
        }
    }

    public void setParent(String content){
        if(!TextUtils.isEmpty(content)){
            this.MCURRENT_ACTIVITY =content;
        }
    };


    public boolean isCheckParent(String content){
        if(!TextUtils.isEmpty(content)&&!TextUtils.isEmpty(MCURRENT_ACTIVITY)){
            if(content.equals(MCURRENT_ACTIVITY)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
