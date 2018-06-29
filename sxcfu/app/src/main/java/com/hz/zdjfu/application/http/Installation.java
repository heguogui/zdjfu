/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.http;

import android.text.TextUtils;


import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.utils.FileUtils;
import com.hz.zdjfu.application.utils.PreferencesUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * [类功能说明]
 *读取和存储设备唯一标识工具
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class Installation {

    private static final String INSTALLATION = ".deviceId";
    /**
     * 读取SD中存储的did
     *
     * @return
     * @throws IOException
     */
    public static String readInstallationFile() throws IOException {
        File installation = new File(FileUtils.DID_PATH, INSTALLATION);
        if (!installation.exists()) {
            return "";
        }
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    /**
     * 将did写入到SD中
     *
     * @param didStr
     * @throws IOException
     */
    public static void writeInstallationFile(String didStr) throws IOException {
        if (TextUtils.isEmpty(didStr) || didStr.equals(Constants.DEFAULT_DID) || didStr.equals(readInstallationFile())) {
            return;
        }
        File installation = new File(FileUtils.DID_PATH, INSTALLATION);
        FileOutputStream out = new FileOutputStream(installation);
        out.write(didStr.getBytes());
        out.close();
    }

    /**
     * 从SD卡和sharedpreference中读取did
     *
     * @return
     */
    public static String readDid() {
        String result = Constants.DEFAULT_DID;
        try {
            result = readInstallationFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(result) || result.equals(Constants.DEFAULT_DID)) {
            result = PreferencesUtils.getString(
                    Constants.DID_PREFERENCE,
                    ZDJFUApplication.getInstance().getApplicationContext(),
                    Constants.SP_KEY_DEVICE_ID,
                    Constants.DEFAULT_DID
            );
        }
        return result;
    }
}
