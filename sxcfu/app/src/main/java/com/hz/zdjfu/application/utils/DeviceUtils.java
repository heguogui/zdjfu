package com.hz.zdjfu.application.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;

/**
 * [类功能说明]
 *设备基本数据工具
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class DeviceUtils {


    private DeviceUtils() {
        throw new UnsupportedOperationException("you can't create DeviceUtils object");
    }

    /**
     * 获取设备MAC地址
     * 需添加权限<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     */
    public static String getMacAddress(Context context) {
        String macAddress;
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        macAddress = info.getMacAddress();
        if (null == macAddress) {
            return "";
        }
        macAddress = macAddress.replace(":", "");
        return macAddress;
    }

    /**
     * 获取设备厂商，如Xiaomi
     */
    public static String getManufacturer() {
        String MANUFACTURER = Build.MANUFACTURER;
        return MANUFACTURER;
    }

    /**
     * 获取设备型号，如MI2SC
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 获取设备SD卡是否可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取设备SD卡路径
     * 一般是/storage/emulated/0/
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getImsi(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }


    /**
     * 获取外接SD卡上context.getPackageName()下面的目录，如果不存在则创建
     * @param dir 外接SD卡上context.getPackageName()下面的目录名
     * @return
     */
    public static String getExternalStoragePath(String dir, Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath()
                    + File.separatorChar + context.getPackageName();
            File file = new File(path);
            if (!file.exists() && !file.mkdir()) {
                LogUtils.e("fail to create " + dir + " dir:" + path);
                return path;
            } else if (!file.isDirectory()) {
                LogUtils.e(dir + " dir exist,but not directory:" + path);
                return null;
            }

            path = path + File.separatorChar + dir;
            file = new File(path);
            if (!file.exists() && !file.mkdir()) {
                LogUtils.e("fail to create " + dir + " dir:" + path);
                return path;
            } else if (!file.isDirectory()) {
                LogUtils.e(dir + " dir exist,but not directory:" + path);
                return null;
            } else {
                return path;
            }
        }
        return null;
    }

}
