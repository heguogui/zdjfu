package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.support.annotation.Px;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * [类功能说明]
 *
 * @author Administrator
 * @version v 1.0.0 2017/6/5 15:18
 * @email heguogui2013@163.com
 */

public class MyViewPager extends ViewPager{


    private boolean isCanScroll =false;//是否可以滑动

    public MyViewPager(Context context) {
        super(context);
    }


    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void  setIsCanScroll(boolean isCanScroll){
        this.isCanScroll =isCanScroll;
    }


    @Override
    public void scrollTo(@Px int x, @Px int y) {
        if (isCanScroll){
            super.scrollTo(x, y);
        }
    }



}
