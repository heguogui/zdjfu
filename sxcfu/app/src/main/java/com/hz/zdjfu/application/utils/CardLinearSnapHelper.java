package com.hz.zdjfu.application.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/19 0019.
 */

public class CardLinearSnapHelper extends LinearSnapHelper {

    public boolean mNoNeedToScroll = false;

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        if (mNoNeedToScroll) {
            return new int[]{0, 0};
        } else {
            return super.calculateDistanceToFinalSnap(layoutManager, targetView);
        }
    }





}
