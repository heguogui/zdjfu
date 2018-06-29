package com.hz.zdjfu.application.modle.financial;
import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.FinancialMarqueeBean;
import com.hz.zdjfu.application.data.bean.ProductBean;

import java.util.List;

/**
 * [类功能说明]
 *理财接口平台
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public interface FinancialContract {



    interface View extends BaseView<Presenter> {


        void initData();

        void onClickListener();

        //初始化幻灯片
        void initvMarqueeView();
        //停止滚动
        void stopFlipping();

        void showFinancialMarquee(List<FinancialMarqueeBean> beans);

        void initViewPager();
    }

    interface Presenter extends BasePresenter {

        /**
         * 获取跑马灯的数据
         */
        void financialMarquee();

    }

}
