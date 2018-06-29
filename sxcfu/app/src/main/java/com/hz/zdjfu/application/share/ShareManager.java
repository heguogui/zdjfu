package com.hz.zdjfu.application.share;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;

/**
 * [类功能说明]
 *  分享管理器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ShareManager {
    private static ShareManager mShareManager = null;
    private Platform mCurrentPlatform; //当前平台类型

    public static void init(Context context) {
        ShareSDK.initSDK(context);
    }

    private ShareManager() {
    }

    //线程安全的单例模式
    public static ShareManager getInstance() {
        if (mShareManager == null) {
            synchronized (ShareManager.class) {
                if (mShareManager == null) {
                    mShareManager = new ShareManager();
                }
            }
        }
        return mShareManager;
    }

    //分享数据入口
    public void shareData(ShareData data, PlatformActionListener listener) {
        switch (data.type) {
            case SinaWeibo:
                mCurrentPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                break;
            case QQ:
                mCurrentPlatform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case QZONE:
                mCurrentPlatform = ShareSDK.getPlatform(QZone.NAME);
                break;
        }
        mCurrentPlatform.setPlatformActionListener(listener);
        mCurrentPlatform.share(data.params);
    }

    //当前支持的平台类型
    public enum PlatformType {
        QQ, QZONE,SinaWeibo
    }

}
