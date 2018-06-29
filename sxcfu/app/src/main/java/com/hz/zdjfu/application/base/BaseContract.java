package com.hz.zdjfu.application.base;
/**
 * [类功能说明]
 *MVP中V的基础接口
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public interface BaseContract {
    //显示异常信息
    void showErrorTips(String msg);
    //初始化View
    void initViewData();
    //数据为空是否显示
    void showDateEmptyView(boolean isRefresh);


}
