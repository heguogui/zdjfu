package com.hz.zdjfu.application.config;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/11 0011.
 */

public class H5Urls {


    public static final String GET_ZDJF_DEV="/";
//    public static final String GET_ZDJF_DEV="/zdjfu/";


    /**
     *  充值
     */
   //帮助
    public static final String H5_URL_RECHANGE_HELP ="https://hao.360.cn/";

    //其他充值方式
    public static final String H5_URL_RECHANGE_OTHER_WAY ="https://hao.360.cn/";


    /**
     * 提现
     */
    //帮助
    public static final String H5_URL_WITHDRAWDEPOSIT_HELP ="https://hao.360.cn/";


    //银行卡管理

    public static final String H5_URL_BANKCARD_MANAGER_HELP =URLController.URL_ZZ+GET_ZDJF_DEV+"static/zdjf_app/page/help/safe.html";


    //开通银行存管账户
    public static final String H5_URL_OPEN_BANK_ACCOUNT_HELP =URLController.URL_ZZ+GET_ZDJF_DEV+"static/zdjf_app/page/help/bank.html";


    //查看支持银行及限额
    public static final String H5_URL_BANKCARD_LIMIT__HELP ="https://hao.360.cn/";

    //产品详情 安全保障
    public static final String H5_URL_PRODUCT_DETAIL_SAFE_GUARD_HELP ="https://hao.360.cn/";


    //首页 精彩活动
    public static final String H5_URL_HOME_SPLEND_PARTY=URLController.URL_ZZ+GET_ZDJF_DEV+"appStatic/active.action";

    //首页 每日签到
    public static final String H5_URL_HOME_EVERY_DAY_SIGN =URLController.URL_ZZ+GET_ZDJF_DEV+"toSign.action";

    //首页 安全保障
    public static final String H5_URL_HOME_SAFE_GUARD =URLController.URL_ZZ+GET_ZDJF_DEV+"appStatic/safe.action";

    //首页 平台数据
    public static final String H5_URL_HOME_PLATFORM_DATA =URLController.URL_ZZ+GET_ZDJF_DEV+"appStatic/platform.action";

    // 发现
    public static final String H5_URL_HOME_FUND =URLController.URL_ZZ+GET_ZDJF_DEV+"appStatic/find.action";

    //发现 客服帮助
    public static final String H5_URL_HOME_SERVICE_HELP =URLController.URL_ZZ+GET_ZDJF_DEV+"appStatic/customer.action";

    //设置  关于我们
    public static final String H5_URL_SETTING_ABOUT =URLController.URL_ZZ+GET_ZDJF_DEV+"appStatic/about.action";

    //实名制 银行卡限额
    public static final String H5_URL_BANK_LIMIT_AMOUNT =URLController.URL_ZZ+GET_ZDJF_DEV+"static/zdjf_app/page/bank.html";

   //新手邀请
   public static final String H5_URL_HOME_NEW_FRISH_INVEST =URLController.URL_ZZ+GET_ZDJF_DEV+"newHand/award.action";


   //注册协议
   public static final String H5_URL_REGIST_AGREEMENT ="https://www.zdjfu.com/static/zdjf_app/page/login/agreement.html";

    //债转协议
    public static final String H5_URL_INVEST_AGREEMENT_HELP=URLController.URL_ZZ+GET_ZDJF_DEV+"product/agreement.action";

//    //借款协议
//    public static final String H5_URL_ZT_INVEST_AGREEMENT_HELP="http://47.96.154.104/front/agreement?";
//
//    //银行卡管理
//
//    public static final String H5_URL_INVEST_HELP ="http://47.96.154.104/front/explain";

    //借款协议
    public static final String H5_URL_ZT_INVEST_AGREEMENT_HELP=URLController.URL_ZT+GET_ZDJF_DEV+"front/agreement?";

    //银行卡管理
    public static final String H5_URL_INVEST_HELP =URLController.URL_ZT+GET_ZDJF_DEV+"front/explain";

    //精彩活动
    public static final String H5_URL_PARTY =URLController.URL_ZT.contains("web-api.zdjfu.com")?URLController.URL_ZT+GET_ZDJF_DEV+"front/active":"http://47.96.154.104/front/active";

}
