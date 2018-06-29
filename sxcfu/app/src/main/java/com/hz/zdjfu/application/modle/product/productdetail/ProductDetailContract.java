package com.hz.zdjfu.application.modle.product.productdetail;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.UserDetailBean;

/**
 * [类功能说明]
 *产品详情契约
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface ProductDetailContract {



    //view 接口
    interface View extends BaseView<ProductDetailContract.Presenter> {
        //初始View
        void initView();
        void showProductDetail(ProductInformBean bean);
        //用户信息
        void showUserDetail(UserDetailBean bean);

        //设置交易密码连接
        void showSettingPayPwdH5(String url);

        void isCheckUnPayOrder(boolean state);

        void  showFragmentData();

        void changeTitleColor(boolean one,boolean two,boolean three);

        //认证返回的结果
        void authNameResult(String url);

        void bottomState();

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //产品详情
        void productDetailHttpRequest(String productid);

        //用户信息
        void userDetailHttpRequest(String phone);

        //检查是否有未支付订单
        void isCheckUnPayOrder(String  phone);

        /**
         * 实名认证
         */
        void addBankCard();

    }

}
