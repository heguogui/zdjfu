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

public abstract class HttpResponseSubscriber<T> extends Subscriber<BaseResponse<T>> {


    private static  final  String TAG =HttpResponseSubscriber.class.getName();

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    public static final String RESULT_HEAD = "=";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
    //    LogUtils.e(e.getMessage());
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
            e = new Throwable("请求异常!");
        }
        if(!NetworkUtils.isConnected(null)){
            e = new Throwable("网络错误,请重试");
        }
        _onError(e);

    }

    @Override
    public void onNext(BaseResponse response) {

        if (response == null) {
            _onError(new Throwable("http请求无返回结果"));
            return;
        }

        if (response.code.equals(Constants.RESPONSE_CODE_100)) {//请求成功
            if(response.returnCase!=null&&response.returnCase.equals("true")){
                Log.i(TAG,"请求结果:"+response.data.toString());
                onSuccess((T)response.data);
            }else{
                if(response.data.toString().contains("dataList")){
                    _onError(new Throwable((String)controlResponse(response.data.toString())));
                }else{
                    _onError(new Throwable((String) response.data));
                }
            }
        } else if(response.code.equals(Constants.RESPONSE_CODE_102)){//请求超时
            _onError(new Throwable((String) response.data));
        }else if(response.code.equals(Constants.RESPONSE_CODE_002)){//其他设备登录

        }else if(response.code.equals(Constants.RESPONSE_CODE_003)){

        }else if(response.code.equals(Constants.RESPONSE_CODE_015)){

        }else if(response.code.equals(Constants.RESPONSE_CODE_101)){
            if(response.data.toString().contains("dataList")){
                _onError(new Throwable((String)controlResponse(response.data.toString())));
            }else{
               Log.i(TAG,TAG+"=:"+response.data.toString());
                _onError(new Throwable((String) response.data));
            }
        }else {
            _onError(new Throwable((String) response.data));
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

//    private String controlResponse(BaseResponse originalResponse){
//        // 拦截返回值并修改为resultStatus为code
//        String originalBodyStr = null;
//        StringBuffer originalSF =null;
//        try {
//            originalBodyStr = originalResponse.data.toString();
//            if (TextUtils.isEmpty(originalBodyStr)) {
//                Log.i(TAG+"返回数据:","结果为空");
//                return originalBodyStr;
//            }
//            originalSF = new StringBuffer();
//            String[] lastString =originalBodyStr.split(RESULT_HEAD);
//            originalSF.append(lastString[0]+lastString[1].substring(1));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return originalSF.toString();
//    }

    private String controlResponse(String originalResponse){
        // 拦截返回值并修改为resultStatus为code

        //此处特别处理 由于后台返回投标记录都为空时 标注了false 上线不想麻烦后台 直接这里处理
        if(originalResponse.contains("InvestRewordRank")){
            return "暂无排行及投标记录";
        }

        String originalBodyStr = null;
        StringBuffer originalSF =null;
        try {
            originalBodyStr = originalResponse;
            if (TextUtils.isEmpty(originalBodyStr)) {
                Log.i(TAG+"返回数据:","结果为空");
                return originalBodyStr;
            }
            originalSF = new StringBuffer();
            String[] lastString =originalBodyStr.split(RESULT_HEAD);
            originalSF.append(lastString[1].substring(1,lastString[1].length()-2));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return originalSF.toString();
    }

}
