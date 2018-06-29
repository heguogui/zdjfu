/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.utils;

import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.orhanobut.logger.Logger;

/**
 * [类功能说明]
 * 打印日志工具
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public final class LogUtils {

    /**
     * 设置TAG
     * 仅在Application中设置一次
     *
     * @param tag
     */
    public static void init(String tag) {
        Logger.init(tag);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.log(priority, tag, message, throwable);
        }
    }

    public static void d(String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.d(message, args);
        }
    }

    public static void d(Object object) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.d(object);
        }
    }

    public static void e(String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.e(message, args);
        }
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.e(throwable, message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.i(message, args);
        }
    }

    public static void v(String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.v(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.w(message, args);
        }
    }

    public static void wtf(String message, Object... args) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.wtf(message, args);
        }
    }

    public static void json(String message) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.json(message);
        }
    }

    public static void xml(String message) {
        if (ZDJFUApplication.getInstance().print) {
            Logger.xml(message);
        }
    }
}
