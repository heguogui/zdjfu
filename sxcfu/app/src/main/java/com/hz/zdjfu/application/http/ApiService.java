package com.hz.zdjfu.application.http;

import com.hz.zdjfu.application.base.ZTInvestRewordLists;
import com.hz.zdjfu.application.data.bean.AccountDataList;
import com.hz.zdjfu.application.data.bean.AddRateLists;
import com.hz.zdjfu.application.data.bean.AnnouncementList;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.data.bean.AuthNameCardIdBean;
import com.hz.zdjfu.application.data.bean.BackMoneyCalendarLists;
import com.hz.zdjfu.application.data.bean.BackMoneyLists;
import com.hz.zdjfu.application.data.bean.BankCardLists;
import com.hz.zdjfu.application.data.bean.BankLists;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.BannerRecordLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.CoinBalanceBean;
import com.hz.zdjfu.application.data.bean.DataStringList;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.FinancialBean;
import com.hz.zdjfu.application.data.bean.FinancialMarqueeBean;
import com.hz.zdjfu.application.data.bean.FinancialNoticationList;
import com.hz.zdjfu.application.data.bean.FinancialRecordLists;
import com.hz.zdjfu.application.data.bean.HomeDataBean;
import com.hz.zdjfu.application.data.bean.InvestDetailBean;
import com.hz.zdjfu.application.data.bean.InvestProjectDetailList;
import com.hz.zdjfu.application.data.bean.InvestResultBean;
import com.hz.zdjfu.application.data.bean.InvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordRankList;
import com.hz.zdjfu.application.data.bean.InviteRewordLists;
import com.hz.zdjfu.application.data.bean.LoginBean;
import com.hz.zdjfu.application.data.bean.LogoutBean;
import com.hz.zdjfu.application.data.bean.MainPartyBean;
import com.hz.zdjfu.application.data.bean.MessageBean;
import com.hz.zdjfu.application.data.bean.MessageLists;
import com.hz.zdjfu.application.data.bean.MyAssetsBean;
import com.hz.zdjfu.application.data.bean.MyIndexDataBean;
import com.hz.zdjfu.application.data.bean.MyInvestList;
import com.hz.zdjfu.application.data.bean.MyInvestLists;
import com.hz.zdjfu.application.data.bean.MyPageBean;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.ProductRecordLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.data.bean.PublicNoticeRecordLists;
import com.hz.zdjfu.application.data.bean.RechangeDetail;
import com.hz.zdjfu.application.data.bean.RechangeDetailList;
import com.hz.zdjfu.application.data.bean.RechangeZJZLists;
import com.hz.zdjfu.application.data.bean.RedPacketLists;
import com.hz.zdjfu.application.data.bean.RedpackAndCouponBean;
import com.hz.zdjfu.application.data.bean.SplashRecordLists;
import com.hz.zdjfu.application.data.bean.TokenBean;
import com.hz.zdjfu.application.data.bean.TransationDetailList;
import com.hz.zdjfu.application.data.bean.UrlBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.WithDrawBean;
import com.hz.zdjfu.application.data.bean.WithDrawCouponList;
import com.hz.zdjfu.application.data.bean.WithDrawFreeBean;
import com.hz.zdjfu.application.data.bean.ZTBuyProductDetailBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.data.bean.ZTRankList;
import com.hz.zdjfu.application.data.bean.ZTUnUserCouponBean;
import com.hz.zdjfu.application.data.bean.ZTUserCouponBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * [类功能说明]
 *  API接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public interface ApiService {


    public static final String GET_ZDJF="/";
//    public static final String GET_ZDJF="/zdjfu/";

    /**
     * 首页数据 1
     * @return
     */
    @GET(GET_ZDJF+"index.json")
    Observable<BaseResponse<HomeDataBean>> mainIndex();



    /**
     *  首页产品浏览数据
     * @return
     */
    @POST(GET_ZDJF+"mob/product/indexCenters?")
    Observable<BaseResponse<ProductRecordLists>> indexCenters();

    /**
     * 启动页面背景
     * @return
     */
    @POST(GET_ZDJF+"mob/common/bannerList?")
    Observable<BaseResponse<SplashRecordLists>> bannerList(@Query("position") String position, @Query("webSite") String webSite, @Query("currPage") String currPage, @Query("pageSize") String pageSize);

    /**
     * 首页广告
     * @return
     */
    @POST(GET_ZDJF+"mob/common/bannerList?")
    Observable<BaseResponse<BannerRecordLists>> bannerList(@Query("currPage") String currPage);


    /**
     * 首页公告
     * @return
     */
    @POST(GET_ZDJF+"mob/common/noticeList?")
    Observable<BaseResponse<PublicNoticeRecordLists>> noticeList(@Query("currPage") String currPage,@Query("pageSize") String pageSize,@Query("searchType") String type);


    /**
     * 理财产品接口
     * @param page 页数
     * @return
     */
    @GET(GET_ZDJF+"product.json")
    Observable<BaseResponse<FinancialRecordLists>> financiaProductLists(@Query("page") String page);

    /**
     * 理财直投和转债接口
     * @param currPage
     * @param productType
     * @param type
     * @return
     */
    @POST(GET_ZDJF+"web/product/list_v2?")
    Observable<BaseResponse<FinancialRecordLists>> list_v2(@Query("currPage") String currPage,@Query("productType") String productType,@Query("searchType") String type);


    /**
     * 银行卡管理
     * @return
     */
    @POST(GET_ZDJF+"mob/user/userBanks?")
    Observable<BaseResponse<BankCardLists>> userBanks();

    /**
     * 发送验证码
     * @param phone 手机号
     * @param type 类型：（1、注册 2、忘记密码）
     * @param ip 手机IP
     * @param sign MD5加密
     * @return
     */
    @GET(GET_ZDJF+"m/sms/send.json")
    Observable<BaseResponse<String>> send(@Query("phone") String phone,@Query("type") String type,@Query("ip") String ip,@Query("sign") String sign);


    /**
     * 检查用户手机是否注册
     * @param phone
     * @return
     */
    @GET(GET_ZDJF+"m/user/check.json?")
    Observable<BaseResponse<Boolean>> check(@Query("phone") String phone,@Query("ip") String ip,@Query("reg_source") String reg_source,@Query("sign") String sign);


    /**
     * 注册
     * @param phone 登录手机号
     * @param passwd 登录密码
     * @param verif 短信验证码
     * @param ip 手机Ip
     * @param reg_source 2-iOS 3-android  整数
     * @param sign MD5加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/register.json")
    Observable<BaseResponse<String>> register(@Query("phone") String phone,@Query("passwd") String passwd,@Query("verif") String verif,@Query("ip") String ip,@Query("reg_source") String reg_source,@Query("sign") String sign,@Query("inviter_phone") String inviter_phone,@Query("invite_code") String invite_code,@Query("invite_source") String invite_source);


    /**
     * 登录
     * @param phone 手机号码
     * @param ip 手机IP
     * @param passwd 登录密码
     * @param sign MD5加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/login.json")
    Observable<BaseResponse<String>> login(@Query("phone") String phone,@Query("ip") String ip,@Query("passwd") String passwd,@Query("sign") String sign,@Query("reg_source") String reg_source,@Query("is_new") String is_new);


    /**
     * 忘记密码
     * @param phone 手机号码
     * @param ip 手机IP
     * @param password 新密码
     * @param sign MD5加密
     * @param verif 短信验证码
     * @return
     */
    @POST(GET_ZDJF+"m/user/passwd.json")
    Observable<BaseResponse<String>> forgetPasswd(@Query("phone") String phone,@Query("ip") String ip,@Query("passwd") String password,@Query("sign") String sign,@Query("verif") String verif);


    /**
     * 加息券
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param currentPage 页数
     * @return
     */
    @GET(GET_ZDJF+"m/userCoupon/page.json")
    Observable<BaseResponse<AddRateLists>> addRestCoupon(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("currentPage") String currentPage);


    /**
     *  红包
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param currentPage 页数
     * @return
     */
    @GET(GET_ZDJF+"m/userGift/page.json")
    Observable<BaseResponse<RedPacketLists>> allRedPacket(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("currentPage") String currentPage);





    /**
     *  用户信息
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/user/details.json")
    Observable<BaseResponse<UserDetailBean>> userInform(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     *  用户信息
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */


    /**
     * 实名认证
     * @param phone 手机号码
     * @param idcard_no 身份证号码
     * @param real_name 真实姓名
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/audit.json")
    Observable<BaseResponse<String>> nameCertification(@Query("phone") String phone, @Query("idcard_no") String idcard_no, @Query("real_name") String real_name, @Query("sign") String sign);




    /**
     * 用户银行卡
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/userBank/page.json")
    Observable<BaseResponse<BankCardLists>> userBankCard(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 省份
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/util/province.json")
    Observable<BaseResponse<ProviceLists>> getProvince(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 城市
     * @param phone  手机号码
     * @param province 省份
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/util/city.json")
    Observable<BaseResponse<CityLists>> getCity(@Query("phone") String phone, @Query("province") String province, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 获取银行类型
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/userBank/bank/list.json")
    Observable<BaseResponse<BankTypeLists>> getBankType(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 绑定银行卡 76
     * @param phone 登录手机号
     * @param bank_no 银行卡号
     * @param ip 手机IP地址
     * @param valid_code 手机验证码
     * @param sign 签名
     * @param other_phone 银行预留手机号
     * @param card_attribute 个人还是企业  个人C 企业B
     * @param province 开户省份
     * @param city 开户城市
     * @return
     */
    @POST(GET_ZDJF+"m/userBank/bank/advance.json")
    Observable<BaseResponse<String>> bindBankCard(@Query("phone") String phone, @Query("ip") String ip, @Query("valid_code") String valid_code, @Query("sign") String sign, @Query("other_phone") String other_phone, @Query("card_attribute") String card_attribute, @Query("province") String province, @Query("city") String city, @Query("bank_no") String bank_no,@Query("bank_alias") String bank_alias,@Query("reg_source") String reg_source);


    /**
     * 绑定发送验证码 77
     * @param phone 登录手机号
     * @param bank_no 银行卡号
     * @param other_phone 预留手机号
     * @param card_attribute 个人还是企业  个人C 企业B
     * @param province 开户省份
     * @param city 开户城市
     * @param bank_alias 银行属性
     * @return
     */
    @POST(GET_ZDJF+"m/userBank/bind.json")
    Observable<BaseResponse<String>> bindBankCardSendCode(@Query("phone") String phone, @Query("bank_no") String bank_no, @Query("other_phone") String other_phone, @Query("card_attribute") String card_attribute,@Query("province") String province, @Query("city") String city,@Query("bank_alias") String bank_alias,@Query("sign") String sign,@Query("real_name") String real_name,@Query("idcard_no") String idcard_no);


    /**
     * 绑定发送验证码 77
     * @param phone 登录手机号
     * @param bank_no 银行卡号
     * @param other_phone 预留手机号
     * @param card_attribute 个人还是企业  个人C 企业B
     * @param province 开户省份
     * @param city 开户城市
     * @param bank_alias 银行属性
     * @return
     */
    @POST(GET_ZDJF+"m/userBank/bind.json")
    Observable<BaseResponse<String>> addBankCardSendCode(@Query("phone") String phone, @Query("bank_no") String bank_no, @Query("other_phone") String other_phone, @Query("card_attribute") String card_attribute,@Query("province") String province, @Query("city") String city,@Query("bank_alias") String bank_alias,@Query("sign") String sign);



    /**
     * 设置银行支付密码 返回第三方H5页面 60
     * @param phone 登录手机号
     * @param ip 手机IP地址
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/paypasswd.json")
    Observable<BaseResponse<DataStringList>> settingBankCardPayPwd(@Query("phone") String phone,@Query("reg_source") String reg_source,@Query("ip") String ip,@Query("sign") String sign);


    /**
     * 充值  20
     * @param phone 注册手机号
     * @param reg_source
     * @param amount 充值金额
     * @param summary 充值概要
     * @param pay_type 充值类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/recharge.json")
    Observable<BaseResponse<String>> rechange(@Query("phone") String phone, @Query("reg_source") String reg_source, @Query("amount") String amount, @Query("summary") String summary,@Query("pay_type") String pay_type, @Query("sign") String sign,@Query("recharge_type") String recharge_type);


    /**
     * 充值 页面详情
     * @param phone
     * @param ip
     * @param reg_source
     * @param sign
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/pre/recharge.json")
    Observable<BaseResponse<RechangeDetail>> rechangeDetail(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 充值短信确认 21
     * @param phone 银行卡预留手机号
     * @param ip
     * @param valid_code 验证码
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/recharge/advance.json")
    Observable<BaseResponse<String>> rechangeSMSsure(@Query("phone") String phone, @Query("ip") String ip, @Query("valid_code") String valid_code, @Query("sign") String sign);


    /**
     * 账户余额查询 19
     * @param phone
     * @param ip
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/balance/query.json")
    Observable<BaseResponse<AccountDataList>> accountBalance(@Query("phone") String phone, @Query("ip") String ip,@Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 所有银行列表
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/userBank/bank/list.json")
    Observable<BaseResponse<BankLists>> allBankLists(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 解绑银行卡验证码 17
     * @param phone 登录手机号
     * @param ip 手机IP
     * @param reg_source 请求手机类型
     * @param other_phone 预留手机号
     * @param sign MD5加密
     * @return
     */
    @POST(GET_ZDJF+"m/userBank/unbind.json")
    Observable<BaseResponse<String>> unBindBnakCard(@Query("phone") String phone,@Query("ip") String ip,@Query("reg_source") String reg_source,@Query("other_phone") String other_phone,@Query("sign") String sign);



    /**
     * 短信确认解绑银行卡 18
     * @param phone 登录手机号
     * @param ip 手机IP
     * @param valid_code 验证码
     * @param sign MD5加密
     * @return
     */
    @POST(GET_ZDJF+"m/userBank/unbind/advance.json")
    Observable<BaseResponse<String>> sureUnBindBnakCard(@Query("phone") String phone,@Query("ip") String ip,@Query("valid_code") String valid_code,@Query("sign") String sign);



    /**
     * 消息列表
     * @param phone 登录手机号
     * @param ip 手机IP
     * @param reg_source
     * @param sign MD5加密
     * @return
     */
    @GET(GET_ZDJF+"m/news/list.json")
    Observable<BaseResponse<MessageLists>> messageLists(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("currentPage") String currentPage);



    /**
     * 消息全部已读
     * @param phone 登录手机号
     * @param ip 手机IP
     * @param reg_source
     * @param sign MD5加密
     * @return
     */
    @POST(GET_ZDJF+"m/news/allread.json")
    Observable<BaseResponse<String>> messageReaded(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);




    /**
     * 消息详情
     * @param phone 登录手机号
     * @param ip 手机IP
     * @param reg_source
     * @param sign MD5加密
     * @param news_id 消息ID
     * @return
     */
    @GET(GET_ZDJF+"m/news/details.json")
    Observable<BaseResponse<MessageBean>> messageDetail(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("news_id") String news_id,@Query("is_read") String is_read);



    /**
     * 我的页面
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/combination/mypage.json")
    Observable<BaseResponse<MyIndexDataBean>> myIndexData(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 理财通知
     * @return
     */
    @GET(GET_ZDJF+"m/combination/roll/details.json")
    Observable<BaseResponse<FinancialNoticationList>> investNotication();


    /**
     * 找回认证手机号码
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/verify/mobile.json")
    Observable<BaseResponse<DataStringList>> fundAttestationPhone(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 重设支付密码
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/find/paypasswd.json")
    Observable<BaseResponse<DataStringList>> resetPayPwd(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 修改支付密码
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/modify /paypasswd.json")
    Observable<BaseResponse<DataStringList>> updataPayPwd(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 免密操作
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/nvestment/withhold/authority.json")
    Observable<BaseResponse<DataStringList>> withoutCodePwd(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


//    /**
//     * 快捷支付银行
//     * @param phone  手机号码
//     * @param ip 手机ip
//     * @param reg_source 请求手机类型
//     * @param sign 加密
//     * @return
//     */
//    @GET(GET_ZDJF+"m/userBank/quick/payment.json")
//    Observable<Object> quickPayBank(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 快捷支付银行
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/userBank/quick/payment.json")
    Call<Object>  quickPayBank(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 退出App
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/user/loginout.json")
    Observable<BaseResponse<String>> exitApp(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 交易明细
     * @param phone 手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param operate_type 搜索类别
     * @return
     */
    @GET(GET_ZDJF+"m/userFundStat/transaction/details.json")
    Observable<BaseResponse<TransationDetailList>> transactionDetail(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("operate_type") String operate_type, @Query("currentPage") String currentPage);



    /**
     * 产品详情
     * @param ip
     * @param reg_source
     * @param project_id 产品Id
     * @return
     */
    @GET(GET_ZDJF+"m/product/details.json")
    Observable<BaseResponse<ProductInformBean>> productDetail(@Query("ip") String ip, @Query("reg_source") String reg_source, @Query("product_id") String project_id);


    /**
     * 投资记录中的所有记录
     * @param product_id
     * @return
     */
    @POST(GET_ZDJF+"m/product/investment/records.json")
    Observable<BaseResponse<InvestRewordLists>> investRewordDetail(@Query("product_id") String product_id,@Query("currentPage") String currentPage);


    /**
     * 投资记录中的 排行
     * @param product_id
     * @return
     */
    @POST(GET_ZDJF+"m/product/investment/rank.json")
    Observable<BaseResponse<InvestRewordRankList>> investRewordRank(@Query("product_id") String product_id);



    /**
     * 用户提现前
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/pre/withdraw.json")
    Observable<BaseResponse<WithDrawBean>> withdrawBefore(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 用户提现券
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/userFundStat/user/withdraw.json")
    Observable<BaseResponse<WithDrawCouponList>> withdrawCoupon(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 用户提现手续费
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/withdraw/amount.json")
    Observable<BaseResponse<WithDrawFreeBean>> withdrawAmount(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("amount") String amount, @Query("withdraw_id") String withdraw_id);




    /**
     * 用户提现申请
     * @param phone 手机号码
     * @param reg_source 请求手机类型
     * @param amount 金额
     * @param summary 描述
     * @param pay_type 必须填1
     * @param sign 加密
     * @param payto_type GENERAL：普通  FAST: 快速
     * @param withdraw_id 是否用提现券
     * @return
     */
    @POST(GET_ZDJF+"m/userFundStat/withdraw.json")
    Observable<BaseResponse<String>> withdrawApply(@Query("phone") String phone,@Query("reg_source") String reg_source, @Query("amount") String amount, @Query("summary") String summary, @Query("pay_type") String pay_type, @Query("sign") String sign, @Query("payto_type") String payto_type, @Query("withdraw_id") String withdraw_id);



    /**
     * 我的投资
     * @param phone 手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param status 搜索类别
     * @return
     */
    @GET(GET_ZDJF+"m/product/mybuy.json")
    Observable<BaseResponse<MyInvestLists>> myAllInvest(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("status") String status, @Query("currentPage") String currentPage);




    /**
     *  正经值余额
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/coinStream/balance.json")
    Observable<BaseResponse<String>> coinStreamNumber(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);




    /**
     *  兑换
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/coinStream/exchange.json")
    Observable<BaseResponse<String>> rechangeCoinStream(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign,@Query("coin_good_id") String coin_good_id);


    /**
     *  正经值兑换明细
     * @param phone  手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param currentPage 页数
     * @return
     */
    @GET(GET_ZDJF+"m/coinStream/page.json")
    Observable<BaseResponse<RechangeDetailList>> coinStream(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("currentPage") String currentPage);


    /**
     * 投资的项目详情
     * @param phone 手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param project_id 产品id
     * @return
     */
    @POST(GET_ZDJF+"m/product/my/investment/details.json")
    Observable<BaseResponse<InvestDetailBean>> investProgectDetail(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("project_id") String project_id,@Query("buy_id") String buy_id);


    /**
     * 取消订单
     * @param buy_id 订单ID
     * @return
     */
    @POST(GET_ZDJF+"m/product/buy/cancel.json")
    Observable<BaseResponse<String>> cancleInvestOrder(@Query("buy_id") String buy_id);



    /**
     * 投资Token
     * @param phone 手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/investment/token.json")
    Observable<BaseResponse<String>> investToken(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 投资
     * @param phone 用户手机
     * @param reg_source 来源
     * @param amount 购买金额
     * @param summary 描述
     * @param pay_type 必须填写1
     * @param sign 加密
     * @param goods_id 标ID
     * @param gift_money 优惠金额
     * @param trade_no token
     * @param user_coupon_id 加息券id
     * @param coin 正经值ID
     * @param user_gift_id 红包Id
     * @return
     */
    @POST(GET_ZDJF+"m/investment/collect/trade.json")
    Observable<BaseResponse<String>> investStart(@Query("phone") String phone,@Query("reg_source") String reg_source,@Query("amount") String amount,@Query("summary") String summary,@Query("pay_type") String pay_type,@Query("sign") String sign,@Query("goods_id") String goods_id,@Query("gift_money") String gift_money,@Query("trade_no") String trade_no,@Query("user_coupon_id") String user_coupon_id,@Query("coin") String coin,@Query("user_gift_id") String user_gift_id);



    /**
     * 优惠券
     * @param phone 手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @POST(GET_ZDJF+"m/combination/coupons.json")
    Observable<BaseResponse<String>> coupons(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);



    /**
     * 是否含有未支付订单
     * @param phone 手机号码
     * @param ip 手机ip
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @return
     */
    @GET(GET_ZDJF+"m/combination/unpay.json")
    Observable<BaseResponse<InvestProjectDetailList>> unPayOrder(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);


    /**
     * 投标选择优惠券
     * @param phone 手机号码
     * @param income_days 标的总共天数
     * @param reg_source 请求手机类型
     * @param sign 加密
     * @param amount 投资金额
     * @return
     */
    @POST(GET_ZDJF+"m/investment/pro/collect/trade.json")
    Observable<BaseResponse<DiscountBean>> investDiscount(@Query("phone") String phone, @Query("income_days") String income_days, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("amount") String amount,@Query("product_id") String product_id);



    /**
     * 首页公告列表
     * @return
     */
    @POST(GET_ZDJF+"m/news/notice.json")
    Observable<BaseResponse<AnnouncementList>> announcementLists(@Query("currentPage") String currentPage);



    /**
     * 回款日历
     * @param phone
     * @param ip
     * @param reg_source
     * @param sign
     * @param time_month 月份
     * @return
     */
    @POST(GET_ZDJF+"m/combination/back/calendar.json")
    Observable<BaseResponse<BackMoneyCalendarLists>> backCalendar(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("time_month") String time_month);


    /**
     * 获取最新版本
     * @param ip
     * @param reg_source
     * @param sign
     * @return
     */
    @GET(GET_ZDJF+"m/appRelease/version.json")
    Observable<BaseResponse<AppVersionBean>> checkVersion(@Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign);

//    /**
//     * 获取最新版本
//     */
//    @GET(GET_ZDJF+"m/appRelease/version.json")
//    Observable<BaseResponse<BackMoneyCalendarLists>> checkVersion();


    /**
     * 邀请好友列表
     * @param phone
     * @param ip
     * @param reg_source
     * @param sign
     * @param currentPage
     * @return
     */
    @GET(GET_ZDJF+"m/user/invite/friends.json")
    Observable<BaseResponse<InviteRewordLists>> inviteFriends(@Query("phone") String phone, @Query("ip") String ip, @Query("reg_source") String reg_source, @Query("sign") String sign, @Query("currentPage") String currentPage);


    /**
     * 获取剩余可投金额
     * @param product_id
     * @return
     */
    @GET(GET_ZDJF+"m/product/invest.json")
    Observable<BaseResponse<String>> canInvestBalance(@Query("project_id") String product_id);


    /**
     * 分享成功通知后台计数
     * @return
     */
    @GET(GET_ZDJF+"m/sign/share.action")
    Observable<BaseResponse<String>> shareWeiXinState(@Query("phone") String phone,@Query("reg_source") String reg_source);

    /**
     * 邀请成功通知后台计数
     * @return
     */
    @GET(GET_ZDJF+"m/sign/sign/times.action")
    Observable<BaseResponse<String>> inviteWeiXinState(@Query("phone") String phone,@Query("reg_source") String reg_source);

    /**
     * 实名认证信息
     * @return
     */
    @GET(GET_ZDJF+"m/user/realNameInfo.json")
    Observable<BaseResponse<AuthNameCardIdBean>> authNameCardId(@Query("user_name") String phone, @Query("reg_source") String reg_source, @Query("ip") String ip, @Query("sign") String sign);


  /**
   * 实名认证信息
   * @return
   */
  @POST(GET_ZDJF+"m/userBank/bind.json")
  Observable<BaseResponse<String>> authNameAndBindCard(@Query("real_name") String real_name,@Query("idcard_no") String idcard_no, @Query("reg_source") String reg_source, @Query("phone") String phone, @Query("sign") String sign);


  //第三期

    /**
     * 登录
     * @param phone 手机号码
     * @param pwd 登录密码
     * @return
     */
    @POST("/user/login")
    Observable<ZTBaseResponse<LoginBean>> ztlogin(@Query("phone") String phone, @Query("pwd") String pwd);



    /**
     * 退出App
     * @return
     */
    @POST("/user/logout")
    Observable<ZTBaseResponse<String>> ztExitApp();


    /**
     *
     * @return
     */
    @POST("/product/list")
    Observable<ZTBaseResponse<FinancialBean>> ztFinancialList(@Query("productType") String productType, @Query("phone") String phone, @Query("status") String status, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize,@Query("findType") String findType);


    /**
     * 直投投资金额
     * @param productId
     * @param investAmt
     * @param couponId
     * @param giftId
     * @param coinStatus 是否用正经值 0不用 1用
     * @return
     */
    @POST("/product/buy")
    Observable<ZTBaseResponse<String>> ztInvestStart(@Query("productId") String productId, @Query("investAmt") String investAmt, @Query("couponId") String couponId, @Query("giftId") String giftId, @Query("coinStatus") String coinStatus);


    /**
     * 直投优惠券
     * @param productId 产品Id
     * @param investAmt 输入金额
     * @return
     */
    @POST("/product/getCoupon")
    Observable<ZTBaseResponse<DiscountBean>> ztInvestDiscount(@Query("productId") String productId, @Query("investAmt") String investAmt);


    /**
     * 理财轮播
     * @return
     */
    @POST("/product/roundFind")
    Observable<ZTBaseResponse<List<FinancialMarqueeBean>>> ztRoundFind();


    /**
     * 直投产品详情
     * @param productId 产品Id
     * @return
     */
    @POST("/product/detail")
    Observable<ZTBaseResponse<ZTProductDetailBean>> ztProductDetail(@Query("productId") String productId);



    /**
     *  用户信息
     * @param phone  手机号码
     * @return
     */
    @POST("/user/getUserInfo")
    Observable<ZTBaseResponse<ZTUserDetailBean>> ztUserInform(@Query("phone") String phone);


    /**
     * 直投投资记录中的所有记录
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @POST("/product/investlist")
    Observable<ZTBaseResponse<ZTInvestRewordLists>> ztInvestRewordDetail(@Query("productId") String productId, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);



    /**
     * 投资记录中的 排行
     * @param productId
     * @return
     */
    @POST("/product/topInvestlist")
    Observable<ZTBaseResponse<ZTRankList>> ztInvestRewordRank(@Query("productId") String productId);

    /**
     * 投资中可用优惠券
     * @param productId
     * @return
     */
    @POST("/product/getAbleCoupon")
    Observable<ZTBaseResponse<ZTUserCouponBean>> ztUserCoupon(@Query("productId") String productId, @Query("investAmt") String investAmt);

    /**
     * 投资中不可用优惠券
     * @param productId
     * @return
     */
    @POST("/product/getUnAbleCoupon")
    Observable<ZTBaseResponse<ZTUnUserCouponBean>> ztUnUserCoupon(@Query("productId") String productId, @Query("investAmt") String investAmt);


    /**
     * 我的页面
     * @return
     */
    @POST("/user/getMyPageInfo")
    Observable<ZTBaseResponse<MyPageBean>> ztMyPage();


    /**
     * 我的投资
     * @param status //（必须）1 全部  5 履约中  6 已回款
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return
     */
    @POST("/product/userBuyRec")
    Observable<ZTBaseResponse<MyInvestList>> ztMyAllInvest(@Query("status") String status, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);


    /**
     * 直投回款日历
     * @param qryDate
     * @return
     */
    @POST("/product/buyRecord")
    Observable<ZTBaseResponse<BackMoneyLists>> ztBackCalendar(@Query("qryDate") String qryDate);


    /**
     * 红包卡券
     * @param type
     * @param isPage
     * @param pageNum
     * @param pageSize
     * @return
     */
    @POST("/user/coupon")
    Observable<ZTBaseResponse<RedpackAndCouponBean>> ztRedPacketAndCoupon(@Query("type") String type, @Query("isPage") String isPage, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);


    /**
     * 直投投资的项目详情
     * @param buy_id 购买的产品ID
     * @return
     */
    @POST("/product/userBuyDetail")
    Observable<ZTBaseResponse<ZTBuyProductDetailBean>> ztBuyProductDetail( @Query("buyId") String buy_id);



    /**
     * 我的页面
     * @return
     */
    @POST("/user/userAssets")
    Observable<ZTBaseResponse<MyAssetsBean>> ztMyAsset();



    /**
     * 直投发送验证码
     * @param phone 手机号
     * @param codeType 类型：（1、注册 2、忘记密码）
     * @return
     */
    @POST("/sms/getCode")
    Observable<ZTBaseResponse<Object>> ztSend(@Query("phone") String phone,@Query("codeType") String codeType);


    /**
     * 直投注册
     * @param phone 手机号
     * @param smsCode 发送的验证码
     * @param pwd 密码
     * @param userType 用户类型   1：普通用户 2：出借人 3：直投借款人 4：担保人'
     * @param reqSource 注册来源: 1web 2iOS 3android 4weixin 5其他
     * @param inviteCode （可空）邀请码
     * @param inviterPhone （可空）邀请人手机号
     * @param inviteSource  推广渠道
     * @return
     */
    @POST("/user/register")
    Observable<ZTBaseResponse<TokenBean>> ztRegister(@Query("phone") String phone, @Query("smsCode") String smsCode, @Query("pwd") String pwd, @Query("userType") String userType, @Query("reqSource") String reqSource, @Query("inviteCode") String inviteCode, @Query("inviterPhone") String inviterPhone, @Query("inviteSource") String inviteSource);



    /**
     * 刷新Token
     */
    @POST("/user/login/fresh")
    Observable<ZTBaseResponse<TokenBean>> ztFreshToken();


    /**
     *  用户信息
     * @return
     */
    @POST("/user/getUserInfo")
    Observable<ZTBaseResponse<ZTUserDetailBean>> ztgetUserInform();



    /**
     * 实名认证信息
     * @return
     */
    @POST("/user/openAccount")
    Observable<ZTBaseResponse<String>> ztAuthNameAndBindCard(@Query("realName") String realName,@Query("idCardNo") String idcardNo);


    /**
     * 充值
     * @param source
     * @param amount
     * @return
     */
    @POST("/recharge/deposit")
    Observable<ZTBaseResponse<String>> ztRechange(@Query("source") String source, @Query("amount") String amount);


    /**
     * 用户提现
     * @param source
     * @param amount
     * @return
     */
    @POST("/recharge/withdraw")
    Observable<ZTBaseResponse<String>> ztWithdrawApply(@Query("source") String source, @Query("amount") String amount);



    /**
     * 交易明细
     * @return
     */
    @POST("/user/tradeDetail")
    Observable<ZTBaseResponse<TransationDetailList>> ztTransactionDetail(@Query("type") String type,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);


    /**
     *  兑换中心
     * @return
     */
    @POST("/user/coinGoods")
    Observable<ZTBaseResponse<RechangeZJZLists>> ztRechangeCenter();


    /**
     *  兑换
     * @return
     */
    @POST("/user/goodsExchange")
    Observable<ZTBaseResponse<CoinBalanceBean>> ztRechangeCoinStream(@Query("goodsId") String coin_good_id);


    /**
     * 实名认证信息
     * @return
     */
    @POST("/user/bindBankCard")
    Observable<ZTBaseResponse<String>> ztAddBankCard();


    /**
     *  正经值兑换明细
     * @return
     */
    @POST("/user/userCoinStream")
    Observable<ZTBaseResponse<RechangeDetailList>> ztCoinStreamList(@Query("pageNum") String pageNum,@Query("pageSize") String pageSize);


   /**
    * 解绑银行卡验证码 17
    * @return
    */
   @POST("/user/unbindSmsCode")
   Observable<ZTBaseResponse<String>> unBindBnakCard();


   /**
    * 短信确认解绑银行卡 18
    * @return
    */
   @POST("/user/unbindBankCard")
   Observable<ZTBaseResponse<String>> sureUnBindBnakCard(@Query("smsCode") String smsCode,@Query("ticket") String ticket);


   /**
    * 激活用户
    * @param idfa
    * @param channel
    * @param os
    * @return
    */
   @GET("/zshd/activation")
   Observable<ZTBaseResponse<String>> activeUser(@Query("idfa") String idfa,@Query("channel") String channel,@Query("os") String os);



   /**
    * 首页活动
    * @return
    */
   @GET("/common/getDefaultInformation")
   Observable<ZTBaseResponse<MainPartyBean>> mainPartyRequest();

    /**
     * 激活用户
     */
    @GET("/common/login")
    Observable<ZTBaseResponse<UrlBean>> duiba();


    /**
     * 激活用户
     */
    @GET("/duiba/sign")
    Observable<ZTBaseResponse<UrlBean>> qiandao();


    /**
     *  使用明细
     * @return
     */
    @GET("/duiba/select")
    Observable<ZTBaseResponse<RechangeDetailList>> ztSpecialList(@Query("pageNum") String pageNum,@Query("pageSize") String pageSize);


}
