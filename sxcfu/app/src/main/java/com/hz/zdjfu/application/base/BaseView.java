package com.hz.zdjfu.application.base;

/**
 * [类功能说明]
 *MVP中view基类接口以P关联
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public interface BaseView<T> extends BaseContract{

    void setPresenter(T presenter);

}
