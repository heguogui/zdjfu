package com.hz.zdjfu.application.config;

/**
 * [类功能说明]
 *开发环境选择
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public interface EnvironmentSwitch {

    //debug环境下
    
    /**
     * URLController.URL_DEV 开发
     * URLController.URL_TEST 测试
     * URLController.URL_PRODUCT 线上
     */

    String HZKJ_URL_DEBUG_VALUE = URLController.URL_PRODUCT;
    /**
     * db 版本号
     */
    int DB_DEBUG_VER = 1;


}
