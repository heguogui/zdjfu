package com.hz.zdjfu.application.http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * [类功能说明]
 *转换线程工具
 * @author Administrator
 * @version v 1.0.0 2017/5/16 9:16 HaoZhuoKeJi
 * @email heguogui2013@163.com
 */

public class TransformUtils {

    /**
     * 默认计划,发布在io线程,订阅在ui线程
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * IO计划,发布和订阅都在IO线程
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> allIoSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}
