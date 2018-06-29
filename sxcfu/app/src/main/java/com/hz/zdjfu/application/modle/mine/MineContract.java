package com.hz.zdjfu.application.modle.mine;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.MyPageBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;

/**
 * [类功能说明]
 *我的页面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public interface MineContract {



    //view 接口
    interface View extends BaseView<MineContract.Presenter> {
        //初始View
        void initView();
        void showUserData(ZTUserDetailBean mUserDetailBean);
        //结束刷新
        void completeRefresh(boolean  state);

        void checkIsLogin();
        //设置交易密码连接
        void showSettingPayPwdH5(String url);

        void showNameValideNoticationDialog();

        //刷新数据
        void refreshData(boolean state);

        void userDetail();

        void showMyIndextData(MyPageBean result);

        void showDuiBaUrl(String url);

    }


    //请求接口
    interface Presenter extends BasePresenter {

        //用户详情请求
        void userDetailHttpRequest(String phone);


        void myDataHttpRequest(String phone);

        //兑吧
        void duiba();


    }

}
