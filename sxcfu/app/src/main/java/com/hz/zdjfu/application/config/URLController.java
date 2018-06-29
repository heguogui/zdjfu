package com.hz.zdjfu.application.config;

import android.content.Context;

/**
 * [类功能说明]
 *URL 路径控制
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class URLController {

    /**
     * 开发
     */
    public static final String URL_DEV = "URL_DEV";

    /**
     * 测试
     */
    public static final String URL_TEST = "URL_TEST";

    /**
     * 线上
     */
    public static final String URL_PRODUCT = "URL_PRODUCT";


    /**
     * 直投接口服务URL地址
     */
    public static String URL_ZT = "";

    /**
     * 债转服务器URL地址
     */
    public static String URL_ZZ = "";


    /**
     * @param context
     * @param env
     */
    public static void initEnv(Context context, String env) {

        if (URL_DEV.equals(env)) {
            URL_ZZ ="http://47.96.154.104:8080";//测试
          //  URL_ZZ ="http://118.31.47.96:8082";//张
          //  URL_ZZ ="http://172.16.0.190:8080";//原
         //   URL_ZZ ="http://172.16.0.139:8080";//孙
         //   URL_ZZ ="http://47.96.154.104:8080";//张
          //  URL_ZZ ="http://192.168.0.141:8080";//王
          //  URL_ZZ ="http://192.168.0.141:8080";//陈

            //  URL_ZT ="http://118.31.47.96:8082";//张
            //  URL_ZT ="http://172.16.0.190:8080";//原
            //   URL_ZT ="http://172.16.0.139:8080";//孙
            URL_ZT ="http://47.96.154.104:19000";//张志
            //  URL_ZT ="http://172.16.0.161:19000";//王
            //  URL_ZT ="http://192.168.0.141:8080";//陈
            //URL_ZT ="http://172.16.0.161:19000";//全生



        } else if (URL_TEST.equals(env)) {
            URL_ZZ ="http://app1.zdjfu.com";//债转测试环境
            URL_ZT ="http://app1.zdjfu.com";//直投测试环境
        } else if (URL_PRODUCT.equals(env)) {
            URL_ZZ = "http://pctest.zdjfu.com";//债转线上环境
            URL_ZT ="http://web-api.zdjfu.com";//直投线上环境
        }
    }


}
