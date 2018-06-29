package com.hz.zdjfu.application.http;

import android.content.Context;
import android.util.Log;

import com.hz.zdjfu.application.http.cache.DiskLruCacheHelper;

import java.io.IOException;

/**
 * [类功能说明]
 *获取网络请求数据缓存工具
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/24 0024
 */
public class DiskCacheUtil {


    private static  final  String TAG = DiskCacheUtil.class.getName();
    public Context mContext;
    private static DiskCacheUtil mInstance;
    public DiskLruCacheHelper diskLruCacheHelper;

    public DiskCacheUtil(Context context) {
        mContext = context;
        try {
            //创建DiskLruCacheHelper 对象
            diskLruCacheHelper = new DiskLruCacheHelper(mContext);
            if(diskLruCacheHelper!=null){
                Log.i(TAG,"diskLruCacheHelper create is successs");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建缓存
     * @param mcontext 上下文
     * @return
     */
    public static synchronized DiskCacheUtil createInstance(Context mcontext){
        if(mInstance==null){
            mInstance = new DiskCacheUtil(mcontext);
            return mInstance;
        }
        return  mInstance;
    }


    /**
     * 获取缓存工具类实例
     * @return
     */
    public static synchronized DiskCacheUtil getInstance() {
        if (mInstance == null)
            throw new IllegalStateException("DiskCacheUtil must be create by call createInstance(Context context)");
        return mInstance;
    }



}
