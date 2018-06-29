package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * [类功能说明]
 * @author HeGuoGui
 * @version v 1.0.0 2017/6/5 11:57
 * @email heguogui2013@163.com
 */

public class MyGridView extends GridView{
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
