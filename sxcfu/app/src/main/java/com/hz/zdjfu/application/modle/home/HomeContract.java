package com.hz.zdjfu.application.modle.home;

import com.hz.zdjfu.application.base.BasePresenter;
import com.hz.zdjfu.application.base.BaseView;
import com.hz.zdjfu.application.data.bean.AppVersionBean;
import com.hz.zdjfu.application.data.bean.BannerRecordLists;
import com.hz.zdjfu.application.data.bean.BannerRecordsBean;
import com.hz.zdjfu.application.data.bean.HomeDataBean;
import com.hz.zdjfu.application.data.bean.MainPartyBean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 00
 */

public interface HomeContract {


        //view 接口
        interface View extends BaseView<Presenter> {

                //初始化广告View
                void initBannerView();
                //初始化刷新View
                void initReFreshView();
                //初始化RecycleView
                void initRecycleView();
                //初始化幻灯片
                void initvMarqueeView();
                //产品为空
                void showProdectRecordEmptyData(Boolean isflag);
                //点击广告跳转
                void goBannerDetail(BannerRecordsBean bean);
                //开始循环首页
                void  startIndexBannerTurning();
                //停止刷新
                void  stopViewRefresh();
                //停止滚动
                void stopFlipping();

                void changeProductTitle(int position);

                void showHomeData(HomeDataBean bean);

                void showBannerdData(BannerRecordLists mlists);

                void showVersionDetail(AppVersionBean bean);

                void showPartyData(MainPartyBean bean);

                void showPartyDialog(MainPartyBean bean);

                void showDuiBaUrl(String url);
        }

        //请求接口
        interface Presenter extends BasePresenter {

                void homeDataHttpRequest();
                //版本检测
                void startCheckVersion();

                void avtiveUser();

                void partyRequest();

                //兑吧
                void duiba();

        }



}
