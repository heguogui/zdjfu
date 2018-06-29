package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.util.AttributeSet;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

/**
 * [类功能说明]
 *刷新重定义
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class XXRefreshView extends XRefreshView {

    public XXRefreshView(Context context) {
        super(context);
        init(context);
    }

    public XXRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化 刷新布局
     * @param context
     */
    private void init(Context context) {
        setAutoRefresh(false);
        setPullLoadEnable(true);
        setAutoLoadMore(false);
        setMoveForHorizontal(true);
        setPullRefreshEnable(true);
        enableReleaseToLoadMore(false);
        enableRecyclerViewPullUp(true);
        enablePullUpWhenLoadCompleted(true);
        setCustomFooterView(new XRefreshViewFooter(context));
        setCustomHeaderView(new CustomRefreshHeadView(context));
    }

}
