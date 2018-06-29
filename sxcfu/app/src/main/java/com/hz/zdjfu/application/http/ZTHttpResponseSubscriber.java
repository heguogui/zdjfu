package com.hz.zdjfu.application.http;

import android.text.TextUtils;
import android.util.Log;

import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.utils.NetworkUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import retrofit2.HttpException;
import rx.Subscriber;

/**
 * [类功能说明]
 *接口调用返回结果统一处理类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public abstract class ZTHttpResponseSubscriber<T> extends Subscriber<ZTBaseResponse<T>> {


    private static  final  String TAG =ZTHttpResponseSubscriber.class.getName();

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int GATEWAY_OTHER = 1000;
    public static final String RESULT_HEAD = "=";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        // 在这里做全局的错误处理
        if (e instanceof ConnectException || e instanceof UnknownHostException ||
                e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException) {
            e = new Throwable("网络错误,请重试");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN: //权限错误，需要实现
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default://均视为网络错误
                    e = new Throwable("网络错误,请重试");
                    break;
            }
        }else{
            Log.i(TAG,TAG+"==:"+e.getMessage());
            if(!TextUtils.isEmpty(e.getMessage())){
                e = new Throwable(e.getMessage()+"");
            }else{
                e = new Throwable("请求异常!");
            }

        }
        if(!NetworkUtils.isConnected(null)){
            e = new Throwable("网络错误,请重试");
        }
        _onError(e);

    }

    @Override
    public void onNext(ZTBaseResponse response) {

        if (response == null) {
            _onError(new Throwable("http请求无返回结果"));
            return;
        }
        if (response.code.equals(Constants.RESPONSE_ZT_CODE_100)) {//请求成功
            if(response.success){
                Log.i(TAG,"请求结果:"+response.data.toString());
                onSuccess((T)response.data);
            }else{
                Log.i(TAG,"请求结果:"+response.msg.toString());
                _onError(new Throwable( response.msg));
            }
        }else{
            Log.i(TAG,"请求结果:"+response.msg.toString());
            _onError(new Throwable( response.msg));
        }
    }


    /**
     * 业务成功
     */
    public abstract void onSuccess(T result);

    /**
     * 请求失败,包括业务失败和网络失败
     *
     * @param e
     */
    public abstract void _onError(Throwable e);



}
