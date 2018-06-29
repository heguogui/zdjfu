package com.hz.zdjfu.application.config;

/**
 * [类功能说明]
 *项目所有配置常量
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class Constants {



    public static final String MHTTP_KEY ="3QvlI7PArDBTa@4YC%efmFxU";
    /**
     * preference文件名
     */
    public static final String PREFERENCE_NAME = "zdjfu_data";
    //请求参数DeviceToken
    public static final String DEVICE_TOKEN_PREFERENCE = "device_token_preference";

    //请求参数AccessToken
    public static final String ACCESS_TOKEN_PREFERENCE = "access_token_preference";

    //请求参数jwtToken
    public static final String JWT_TOKEN_PREFERENCE = "jwt_token_preference";

    //用户手机号
    public static final String USER_PHONE_PREFERENCE = "user_phone_preference";
    //用户密码
    public static final String USER_PASSWORD_PREFERENCE = "user_password_preference";
    //用户头像
    public static final String USER_HEADPICTURE_PREFERENCE = "user_headpicture_preference";
    //用户昵称
    public static final String USER_NICK_PREFERENCE = "user_nick_preference";

    //用户token
    public static final String USER_ACCESS_TOKEN = "user_access_token";
    public static final String USER_JWT_TOKEN = "user_jwt_token";

    //活动
    public static final String MAIN_PARTY_URL ="main_party_url";
    public static final String MAIN_PARTY_STATE ="main_party_state";




    //用户推送消息开关
    public static final String USER_PUSHMSG_STATE_PREFERENCE = "user_pushmsg_state_preference";
    //用户手势密码开关
    public static final String USER_GESTURE_STATE_PREFERENCE = "user_gesture_state_preference";

    //用户是否登录
    public static final String USER_IS_LOGIN_PREFERENCE ="user_is_login_preference";

    //用户注册时间
    public static final String USER_CREATE_TIME_PREFERENCE = "user_create_time_preference";

    //用户是否弹出认证
    public static final String USER_NAME_VALID_PREFERENCE = "user_name_valid_preference";

   //update preference文件名
    public static final String UPDATE_PREFERENCE = "update_preference";

    public static final String UPDATE_KEY_EXTRAS_TIME_FIRST = "update_time_first";

    public static final String UPDATE_KEY_EXTRAS_TIME_SECOND = "update_time_second";

    public static final String UPDATE_KEY_EXTRAS_TIME_THIRD = "update_time_third";

    public static final String UPDATE_KEY_EXTRAS_DOWNLOAD_SUCCESS = "update_download_success";

    public static final String UPDATE_KEY_EXTRAS_IS_LOWDING = "update_is_loading";


    // 提现，最大金额限制
    public final static String MONEY_MAX = "10000000";
    // 充值，最大金额限制
    public final static String RECH_MONEY_MAX = "1000000";

    /**
     * 更新提示的时间间隔
     */
    public static final long UPDATE_TIP_TIME = 6 * 60 * 60 * 1000;

    //请求头部设备唯一标识did
    public static final String DID_PREFERENCE = "did_preference";
    //welcome preference文件名
    public static final String WELCOME_PREFERENCE = "welcome_preference";
    // 微信分享
    public final static String WX_SHARE_APP_ID = "wxa87777f587a63c69";

    //请求头部cookie
    public static final String COOKIE = "cookie";

    public static final String RESULT_OK ="ok";

    //以下五个是公共的请求参数
    public  static final String ACCESSTOKEN = "accessToken";
    public  static final String REQSOURCE = "reqSource";
    public  static final String APPVERSION = "appVersion";
    public  static final String JWTTOKEN = "jwtToken";


    //安卓端请求标识
    public static  String REQUESTSOURCE_ANDROID = "3";


    //请求头部设备唯一标识did
    public static final String DID = "did";
    //默认的did
    public static final String DEFAULT_DID = "-1";
    public static final String SP_KEY_DEVICE_ID = "deviceId";
    //用户为安卓设备
    public static final String USER_AGENT = "User-Agent";
    public static final String AGENT = "ANDROID";
    //欢迎界面的状态
    public static final String SP_KEY_WELCOME_FLAG = "welcome_flag";


    public final static String RESPONSE_CODE_000 = "000";// 请求成功
    public final static String RESPONSE_CODE_001 = "001";// 请求超时
    public final static String RESPONSE_CODE_002 = "002";// 其他设备登录
    public final static String RESPONSE_CODE_003 = "003";// 用户未登录
    public final static String RESPONSE_CODE_015 = "015";// 手机号不存在

    public final static String RESPONSE_CODE_100 = "100";// 请求成功
    public final static String RESPONSE_CODE_101 = "101";// 请求失败
    public final static String RESPONSE_CODE_102 = "102";// 请求失败
    public final static String RESPONSE_ZT_CODE_100 = "200";// 请求成功


    //客服电话
    public final static String PHONE_ONE_KF = "4006909898";
    public final static String PHONE_TWO_KF = "057156929127";
    public final static String CJRANDJCR = "JKRANDCJR=true";

    public static final String BANNER_ID = "0000";//默认banner的id


    // 产品状态
    public final static String PRODUCT_STATUS4 = "4";// 投资中
    public final static String PRODUCT_STATUS5 = "5";// 履约中
    public final static String PRODUCT_STATUS6 = "6";// 已还款
    public final static String PRODUCT_STATUS31 = "31";// 马上上线，募集中


    public final static String PRODUCT_STATUS0_STR = "立即投资";
    public final static String PRODUCT_STATUS4_STR = "投资中";// 投资中
    public final static String PRODUCT_STATUS5_STR = "履约中";// 履约中
    public final static String PRODUCT_STATUS6_STR = "已还款";// 已还款
    public final static int UP_DOWN_REFRESH_TIME = 500;



    public final static String OK = "1";// OK
    public final static String NO = "-1";// NO

    public final static String ANDROID_SOURCE ="3";//安卓手机来源

    // 最近投资列表的付款状态值
    public final static String PAY_STATUS_0 = "0"; // 多少天到期
//    public final static String PAY_STATUS_1 = "12"; // 回款中
//    public final static String PAY_STATUS_2 = "2"; // 已回款
//    public final static String PAY_STATUS_3 = "3"; // 待支付

    public final static String PAY_STATUS_4 = "4"; // 失败
    public final static String PAY_STATUS_6 = "6"; // 已回款
    public final static String PAY_STATUS_5 = "5"; // 履约中
    public final static String PAY_STATUS_7 = "7"; // 履约中
    public final static String PAY_STATUS_71 = "71"; // 履约中

    public final static String PAY_STATUS_99999 = "99999"; // 加载中

    //认证信息
    public final static int REAL_NAME_AUTH_OK =1;//认证成功
    public final static int REAL_NAME_AUTH_ING =2;//认证中
    public final static int REAL_NAME_AUTH_FAIL =-1;//认证失败

    public final static String OPEN_BANK_TYPE_RERSON ="C";
    public final static String OPEN_BANK_TYPE_COMPANY ="B";



    //交易明细
    //充值
    public final static String TYPE_OPERATE_RECHANGE ="11";
    //提现
    public final static String TYPE_OPERATE_WHITDRAW ="21";
    //投资
    public final static String TYPE_OPERATE_INVEST ="22";
    //回款
    public final static String TYPE_OPERATE_RETURN ="12";
    //收益
    public final static String TYPE_OPERATE_EARNING ="13";



    //红包卡券状态
    public final static String TYPE_REDPACKET_COUPON_ALL_STATE="1";
    public final static String TYPE_REDPACKET_COUPON_NEW_STATE="2";
    public final static String TYPE_REDPACKET_COUPON_OLD_STATE="3";


    //bugly APP_ID
    public final static String BUGLY_APP_ID="1f19e127e5";
    public final static String BUGLY_APP_KEY ="3a4c8ce8-40c4-46f4-8254-c469347af406";


    // 友盟SDK 别名type
    public final static String UMENG_ALIAS_TYPE = "ZDJFU";

    //
    public final static String FINANCIAL_TYPE_ZT="2";
    public final static String FINANCIAL_TYPE_ZZ="1";


}
