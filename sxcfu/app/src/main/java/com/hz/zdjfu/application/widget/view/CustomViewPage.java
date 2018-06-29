package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/6 0006
 */
public class CustomViewPage extends ViewPager{
    public CustomViewPage(Context context) {
        super(context);
    }

    public CustomViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
