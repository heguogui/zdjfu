package com.hz.zdjfu.application.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.URLController;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.modle.home.HomeFragment;
import com.hz.zdjfu.application.widget.dialog.UpdateDialog;
import com.hz.zdjfu.application.widget.dialog.UpdateDialogDownload;

import org.lzh.framework.updatepluginlib.UpdateBuilder;
import org.lzh.framework.updatepluginlib.UpdateConfig;
import org.lzh.framework.updatepluginlib.callback.UpdateDownloadCB;
import org.lzh.framework.updatepluginlib.creator.DialogCreator;
import org.lzh.framework.updatepluginlib.creator.InstallCreator;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.model.UpdateParser;
import org.lzh.framework.updatepluginlib.strategy.UpdateStrategy;

import java.io.File;

/**
 * [类功能说明]
 *版本更新
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class UpdateAppUtils {

    private NotificationManager manager;
    private Notification notif;
    private int NOTIFCATION_ID = 10;
    private int downloadCount = 0;
    private UpdateDialog updateDialog = null;
    private UpdateDialogDownload updateDialogDownload = null;
    private boolean isError = false;
    private  NotificationCompat.Builder mBuilder;
    public static UpdateAppUtils getInstance() {
        return new UpdateAppUtils();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        mBuilder.setContentText(String.format(ZDJFUApplication.getInstance().getApplicationContext().getString(R.string.app_update_download_progress), msg.obj));
                        mBuilder.setProgress(100,(int) msg.obj, false);
                        notif =mBuilder.build();
                    }else{
                        notif.contentView.setTextViewText(R.id.content_view_text1, String.format(ZDJFUApplication.getInstance().getApplicationContext().getString(R.string.app_update_download_progress), msg.obj));
                        notif.contentView.setProgressBar(R.id.content_view_progress, 100, (int) msg.obj, false);
                    }
                    manager.notify(NOTIFCATION_ID, notif);
                    break;
                case 1:
                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, ZDJFUApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS);
                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, ZDJFUApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                    // 下载完成  notification 取消
                    NotificationManager manger = (NotificationManager) ZDJFUApplication.getInstance().getApplicationContext().getSystemService(ZDJFUApplication.getInstance().getApplicationContext().NOTIFICATION_SERVICE);
                    manger.cancel(NOTIFCATION_ID);
                    downloadCount = 0;
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 对Config进行配置
     */
    public void updateInit() {

        String url =null;
        if(!TextUtils.isEmpty(URLController.URL_ZZ)){
            if(URLController.URL_ZZ.contains("pctest")){
                url=URLController.URL_ZZ+"/m/appRelease/version.json";
            }else{
                url=URLController.URL_ZZ+"/zdjfu/m/appRelease/version.json";
            }
        }else{
            return;
        }

        UpdateConfig.getConfig()
                // 必填：需尽早进行Application初始化操作。
                .init(ZDJFUApplication.getInstance().getApplicationContext())
                .url(url)
                // 自定义版本检查
                .updateChecker(update -> {
                    // 在此对应用版本进行比对检测。返回true说明该update版本需要被更新。false不需要更新
                    return true;
                })
                // 自定义更新策略，默认WIFI下自动下载更新
                .strategy(new UpdateStrategy() {
                    @Override
                    public boolean isShowUpdateDialog(Update update) {
                        // 是否在检查到有新版本更新时展示Dialog。
                        return true;
                    }

                    @Override
                    public boolean isAutoInstall() {
                        // 下载完成后，是否自动更新。若为false。则创建Dialog显示
                        return false;
                    }

                    @Override
                    public boolean isShowDownloadDialog() {
                        // 在APK下载时。是否显示下载进度的Dialog
                        return false;
                    }
                });
    }

    /**
     * app版本更新
     *
     * @param result            服务端返回的数据
     * @param context           context
     * @param content           提示更新时dialog显示的内容
     * @param content2          下载完成dialog显示的内容
     * @param isMandatoryUpdate 是否强制版本升级
     * @param tag                 用于判断是哪个页面
     */
    public void updateApp(AppVersionBean result, Context context, String content, String content2, String versionCode,String codename,boolean isMandatoryUpdate, String tag) {
        if (context == null) {
            return;
        }
        String isDownload = PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);

        // 已下载
        if (isDownload.equals(Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS)) {
            install(content2, isMandatoryUpdate, context, content, codename, result);
            return;
        }


        UpdateBuilder.create()
                .jsonParser(updateParser(result))
                // 自定义检查出更新后显示的Dialog
                .updateDialogCreator(new DialogCreator() {
                    @Override
                    public Dialog create(Update update, Activity context) {
                        updateDialog = new UpdateDialog(context, () -> {
                            updateDialog.dismiss();
                            if (NetworkUtils.isWifiConnected(context)) {
                                downloadAPK(result, false, context);
                            }
//							}
                        }, () -> {
                            if (!isMandatoryUpdate) {
                                updateDialog.dismiss();
                            }
                            if (!NetworkUtils.isConnected(context)) {
                                ToastUtils.show(context, "网络不给力，请检查网络连接");
                                return;
                            }
                            if (PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING).equals(Constants.UPDATE_KEY_EXTRAS_IS_LOWDING)) {
                                ToastUtils.show(context, "正在下载，请稍后~");
                                return;
                            } else if (isError) {
                                errorDownloadAPK(result, content, content2, codename, isMandatoryUpdate, context);
                                return;
                            }
                            if(update==null){
                                ToastUtils.show(context, "下载失败!");
                                return;
                            }
                            // 立即更新
                            sendDownloadRequest(update);
                        },
                                content2, content,codename,isMandatoryUpdate);
                        updateDialog.show();
                        return updateDialog;
                    }
                })
                // 自定义下载完成后。显示的Dialog,
                .installDialogCreator(installCreator(context, result, content, content2, codename, isMandatoryUpdate))
                // 新版本APK下载时的回调
                .downloadCB(updateDownloadCB(context))
                // 自定义下载文件缓存,默认参考
                .fileCreator(versionName -> {
                    // versionName 为解析的Update实例中的update_url数据。在这里可自定义下载文件缓存路径及文件名。放置于File中
                    return new File(FileUtils.APK_INSTALL_PATH + "zdjfu.apk");
                })
                .check();
    }

    /**
     * 版本已下载， 是否安装
     */
    private void install(String content2, boolean isMandatoryUpdate, Context context, String content, String versionCode, AppVersionBean result) {

        updateDialogDownload = new UpdateDialogDownload(context,
                () -> updateDialogDownload.dismiss(),
                () -> {
                    if (!isMandatoryUpdate)
                        updateDialogDownload.dismiss();
                    if (PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING).equals(Constants.UPDATE_KEY_EXTRAS_IS_LOWDING)) {
                        ToastUtils.show(context, "正在下载，请稍后~");
                        return;
                    }
                    // 立即安装
//                    if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
//                        ToastUtils.show(context, R.string.app_update_download_not_apk);
//                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
//                        downloadAPK(result, true, context);
//                    }

                    checkIsAndroidApk(context,result);

                }, content2, content, versionCode, isMandatoryUpdate);
        updateDialogDownload.show();
    }

    /**
     * 自动下载
     */
    public void downloadAPK(AppVersionBean result, boolean isdown, Context context) {
        UpdateBuilder.create()
                .jsonParser(updateParser(result))
                .fileCreator(versionName -> {
                    // versionName 为解析的Update实例中的update_url数据。在这里可自定义下载文件缓存路径及文件名。放置于File中
                    return new File(FileUtils.APK_INSTALL_PATH + "zdjfu.apk");
                })
                .updateDialogCreator(new DialogCreator() {
                    @Override
                    public Dialog create(Update update, Activity context) {

                        if(update==null){
                            ToastUtils.show(context, "下载失败!");
                            return null;
                        }
                        sendDownloadRequest(update);
                        return null;
                    }
                })
                .installDialogCreator(new InstallCreator() {
                    @Override
                    public Dialog create(Update update, String path, Activity activity) {
                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS);
                        return null;
                    }
                })
                .downloadCB(new UpdateDownloadCB() {
                    // 下载开始
                    @Override
                    public void onUpdateStart() {
                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING);
                    }

                    // 下载完成
                    @Override
                    public void onUpdateComplete(File file) {
                        if (isdown) {
                            checkIsAndroidApk(context,result);
//                            if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
//                                ToastUtils.show(context, R.string.app_update_download_not_apk);
//                                PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
//                                downloadAPK(result, true, context);
//                            }
                        }
                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, ZDJFUApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                    }

                    // 下载进度
                    @Override
                    public void onUpdateProgress(long current, long total) {
                    }

                    // 下载apk错误
                    @Override
                    public void onUpdateError(int code, String errorMsg) {
                        ToastUtils.show(context, R.string.app_update_download_error_tip);
                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, ZDJFUApplication.getInstance().getApplicationContext(), Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                    }
                })
                .check();
    }

    private void errorDownloadAPK(AppVersionBean result, String content, String content2, String versionCode, boolean isMandatoryUpdate, Context context) {
        UpdateBuilder.create()
                .jsonParser(updateParser(result))
                .fileCreator(versionName -> {
                    // versionName 为解析的Update实例中的update_url数据。在这里可自定义下载文件缓存路径及文件名。放置于File中
                    return new File(FileUtils.APK_INSTALL_PATH + "zdjfu.apk");
                })
                .updateDialogCreator(new DialogCreator() {
                    @Override
                    public Dialog create(Update update, Activity context) {
                        if(update==null){
                            ToastUtils.show(context, "下载失败!");
                            return null;
                        }
                        sendDownloadRequest(update);
                        return null;
                    }
                })
                .installDialogCreator(installCreator(context, result, content, content2, versionCode, isMandatoryUpdate))
                .downloadCB(updateDownloadCB(context))
                .check();
    }

    /**
     * 自定义下载完成后。显示的Dialog
     */
    private InstallCreator installCreator(Context context, AppVersionBean result, String content, String content2, String versionCode, boolean isMandatoryUpdate) {
        return new InstallCreator() {
            @Override
            public Dialog create(Update update, String path, Activity activity) {
                PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS);

                updateDialogDownload = new UpdateDialogDownload(context, () -> {
                    updateDialogDownload.dismiss();
                    // 取消更新
                    sendUserCancel();
                }, () -> {
                    if (!isMandatoryUpdate)
                        updateDialogDownload.dismiss();
                    if (PreferencesUtils.getString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING).equals(Constants.UPDATE_KEY_EXTRAS_IS_LOWDING)) {
                        ToastUtils.show(context, "正在下载，请稍后~");
                        return;
                    }
                    // 立即安装
//                    if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
//                        ToastUtils.show(context, R.string.app_update_download_not_apk);
//                        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
//                        downloadAPK(result, true, context);
//                    }
                    checkIsAndroidApk(context,result);
                }, content2, content, versionCode, isMandatoryUpdate);
                updateDialogDownload.show();
                return updateDialogDownload;
            }
        };
    }

    private UpdateParser updateParser(AppVersionBean result) {
        return new UpdateParser() {
            @Override
            public Update parse(String response) {
                /*
                * 此处模拟一个Update对象，传入接口返回的原始数据进去保存。
                * 若用户需要自定义的时候。对于有额外参数。可从中获取并定制。
                */
                Update update = new Update(response);
                // 此apk包的下载地址  例:http://apk.hiapk.com/web/api.do?qt=8051&id=721
                Log.i("UpdateAppUtils","URL="+result.getDown_url());
                update.setUpdateUrl(result.getDown_url());
                return update;
            }
        };
    }

    /**
     * 下载apk时的回调
     */
    private UpdateDownloadCB updateDownloadCB(Context context) {

        try{
            return new UpdateDownloadCB() {
                // 下载开始
                @Override
                public void onUpdateStart() {
                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING);
                    ToastUtils.show(context, R.string.app_update_downloading);
                    downloadCount = 0;
                    manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


                    //8.0的通知
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("10","zdjfu",NotificationManager.IMPORTANCE_HIGH);
                        manager.createNotificationChannel(channel);
                    }

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        mBuilder = new NotificationCompat.Builder(context);
                        mBuilder.setAutoCancel(true)//通知设置不会自动显示
                                .setShowWhen(true)//显示时间
                                .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                                .setContentTitle(context.getResources().getText(R.string.app_update_downloading))
                                .setProgress(100,0,false);
                        notif = mBuilder.build();
                    }else{
                        notif = new Notification();
                        notif.icon = R.mipmap.ic_launcher;
                        notif.tickerText = context.getResources().getText(R.string.app_update_downloading);
                        //通知栏显示所用到的布局文件
                        notif.contentView = new RemoteViews(context.getPackageName(), R.layout.view_progress_notif);
                    }

                    manager.notify(NOTIFCATION_ID, notif);
                }

                // 下载完成
                @Override
                public void onUpdateComplete(File file) {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }

                // 下载进度
                @Override
                public void onUpdateProgress(long current, long total) {
                    if (current == total) {
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = 100;
                        handler.sendMessage(msg);
                    }

                    int tmp = (int) (current * 100 / total);
                    // 百分比增加 5 通知一次
                    if (downloadCount == 0 || tmp - 5 == downloadCount) {
                        downloadCount += 5;
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = tmp;
                        handler.sendMessage(msg);
                    }
                }

                // 下载apk错误
                @Override
                public void onUpdateError(int code, String errorMsg) {
                    ToastUtils.show(context, R.string.app_update_download_error_tip);
                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                }
            };
        }catch (Exception e){
            return new UpdateDownloadCB(){

                @Override
                public void onUpdateStart() {
                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING);
                    ToastUtils.show(context, R.string.app_update_downloading);
                }

                @Override
                public void onUpdateComplete(File file) {

                }

                @Override
                public void onUpdateProgress(long current, long total) {

                }

                @Override
                public void onUpdateError(int code, String errorMsg) {
                    ToastUtils.show(context, R.string.app_update_download_error_tip);
                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
                }
            };
        }

    }


    public void checkIsAndroidApk(Context context,AppVersionBean result) {

        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = context.getPackageManager().canRequestPackageInstalls();
//            if (b) {
//                if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
//                    ToastUtils.show(context, R.string.app_update_download_not_apk);
//                    PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
//                    downloadAPK(result, true, context);
//                }
//            } else {
//                //请求安装未知应用来源的权限
//                ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, 10011);
//            }
            if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
                //ToastUtils.show(context, R.string.app_update_download_not_apk);
                PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
                downloadAPK(result, true, context);
            }

        } else {
            if (!AppUtils.installApp(context, FileUtils.APK_INSTALL_PATH + "zdjfu.apk")) {
               // ToastUtils.show(context, R.string.app_update_download_not_apk);
                PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, context, Constants.UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS, Constants.DEFAULT_DID);
                downloadAPK(result, true, context);
            }
        }

    }


}
