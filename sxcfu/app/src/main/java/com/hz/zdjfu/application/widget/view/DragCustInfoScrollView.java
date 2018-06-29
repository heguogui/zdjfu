package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * [类功能说明]
 *随着手指滑动
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class DragCustInfoScrollView extends ScrollView implements GestureDetector.OnGestureListener{

    private static final int TOUCH_IDLE = 0;
    private static final int TOUCH_INNER_CONSIME = 1; // touch事件由ScrollView内部消费
    private static final int TOUCH_DRAG_LAYOUT = 2; // touch事件由上层的DragLayout去消费
    private static final int FLING_MIN_DISTANCE = 200;
    private static final int FLING_MIN_VELOCITY = 0;

    boolean isAtBottom,isTop; // 按下的时候是否在底部
    private int mTouchSlop = 4; // 判定为滑动的阈值，单位是像素
    private int scrollMode;
    private float downY;
    private GestureDetector mGestureDetector;

    public DragCustInfoScrollView(Context arg0) {
        this(arg0, null);
        mGestureDetector = new GestureDetector(this);
    }

    public DragCustInfoScrollView(Context arg0, AttributeSet arg1) {
        this(arg0, arg1, 0);
        mGestureDetector = new GestureDetector(this);
    }

    public DragCustInfoScrollView(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        mGestureDetector = new GestureDetector(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getRawY();
            isAtBottom = isAtBottom();
            isTop = isTop();
            scrollMode = TOUCH_IDLE;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            isTop = isTop();

            if (scrollMode == TOUCH_IDLE) {
                float yOffset = downY - ev.getRawY();
                float yDistance = Math.abs(yOffset);
                // Log.e("yDistance",yOffset +":==:"+ yDistance);
                if (yDistance > mTouchSlop) {
                    if (yOffset > 0 && isAtBottom) {
                        scrollMode = TOUCH_DRAG_LAYOUT;
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    } else {
                        scrollMode = TOUCH_INNER_CONSIME;
                    }
                }else{
                    // 当项目详情，滑动到顶部时，下拉显示项目进度信息
                    if(yOffset < 0 && isTop){
                        scrollMode = TOUCH_DRAG_LAYOUT;
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    }else{
                        scrollMode = TOUCH_INNER_CONSIME;
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollMode == TOUCH_DRAG_LAYOUT) {
            return false;
        }
        mGestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }
    private boolean isAtBottom() {
        return getScrollY() + getMeasuredHeight() >= computeVerticalScrollRange() - 2;
    }
    private boolean isTop() {
        return getScrollY() == 0;
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

}
