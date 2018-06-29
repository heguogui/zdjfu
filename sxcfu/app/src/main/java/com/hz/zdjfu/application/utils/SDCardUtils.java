package com.hz.zdjfu.application.utils;

import android.os.Environment;
import android.os.StatFs;

/**
 * [类功能说明]
 * SD卡工具类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class SDCardUtils {

    private static final long LOW_STORAGE_THRESHOLD = 1024 * 1024 * 6;//10M
    private static SDCardUtils util;

    private SDCardUtils() {

    }

    public static synchronized SDCardUtils getInstance() {
        if (util == null) {
            util = new SDCardUtils();
        }
        return util;
    }

    /**
     * 判断是否有sdcard
     *
     * @return
     */
    public static boolean hasSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }


    /**
     * 判断剩余空间
     *
     * @return
     */
    public static long getAvailableStorage() {

        String storageDirectory = null;
        storageDirectory = Environment.getExternalStorageDirectory().toString();
        try {
            StatFs stat = new StatFs(storageDirectory);
            return ((long) stat.getAvailableBlocks() * (long) stat.getBlockSize());
        } catch (RuntimeException ex) {
            return 0;
        }
    }

    /**
     * 判断空间是否有大于6M
     *
     * @return
     */
    public static boolean isAvailable() {

        if (getAvailableStorage() < LOW_STORAGE_THRESHOLD) {
            return false;
        }
        return true;
    }


}
