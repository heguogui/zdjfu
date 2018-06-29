package com.hz.zdjfu.application.utils;

import android.support.v7.widget.LinearSnapHelper;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/12/5 0005.
 */

public class CustomLinearSnapHelper extends LinearSnapHelper {


    public static boolean mStateIdle = false;

    @Override
    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        if(mStateIdle){
            return new int[2];
        }else{
            int[] ints = super.calculateScrollDistance(velocityX, velocityY);
            for (int i:ints){
            }
            return super.calculateScrollDistance(velocityX, velocityY);
        }
    }

}
