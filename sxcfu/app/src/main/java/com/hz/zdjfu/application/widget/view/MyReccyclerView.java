package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * [类功能说明]
 *
 * @author Administrator
 * @version v 1.0.0 2017/5/23 16:51 HaoZhuoKeJi
 * @email heguogui2013@163.com
 */

public class MyReccyclerView extends RecyclerView {
    public MyReccyclerView(Context context) {
        super(context);
    }

    public MyReccyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyReccyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
