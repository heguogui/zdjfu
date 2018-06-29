package com.hz.zdjfu.application.modle.ztprogect;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;

/**
 * [类功能说明]
 *产品详情契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface ZTProductDetailContract {



    //view 接口
    interface View extends BaseView<ZTProductDetailContract.Presenter> {
        //初始View
        void initView();
        void showProductDetail(ZTProductDetailBean bean);

        //用户信息
        void showUserDetail(ZTUserDetailBean bean);

        //设置交易密码连接
        void showSettingPayPwdH5(String url);


        void  showFragmentData();

        void changeTitleColor(boolean one, boolean two, boolean three);

        //认证返回的结果
        void addBankCardResult(String url);


        void bottomState();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //产品详情
        void productDetailHttpRequest(String productid);

        //用户信息
        void userDetailHttpRequest(String phone);

        //设置交易密码
        void settingPaySetting(String phone);

        //添加银行卡
        void addBankCard();

    }

}
