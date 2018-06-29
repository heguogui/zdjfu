package com.hz.zdjfu.application.modle.mine.personcenter.paypwdmanager;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.ResultDataList;


/**
 * [类功能说明]
 * 忘记密码契约
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/26 0026.
 */

public interface PayPwdManagerContract {


    //view 接口
    interface View extends BaseView<PayPwdManagerContract.Presenter> {

        void  showH5Url(ResultDataList list);

        void fundAttestationPhone(String url);

        void resetPayPwd(String url);


        void updataPayPwd(String url);


        void withoutCodePwd(String url);


    }


    //请求接口
    interface Presenter extends BasePresenter {
        //获取第三方支付密码管理接口
        void allPayPwdManagerHttpRequest(String phone);

        void fundAttestationPhone(String phone);


        void resetPayPwd(String phone);


        void updataPayPwd(String phone);


        void withoutCodePwd(String phone);

    }

}
