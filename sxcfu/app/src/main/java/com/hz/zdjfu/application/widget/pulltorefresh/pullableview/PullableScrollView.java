package com.hz.zdjfu.application.widget.pulltorefresh.pullableview;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;


/**
 * ScrollView的上，下拉刷新
 *
 * @author xiangwq
 */
public class PullableScrollView extends ScrollView implements Pullable {
    private static boolean isCanPullDown = true;//是否允许滑动

    private ScrollViewListener scrollViewListener;

    private int downY,mTouchSlop,downX;
    private GestureDetector mGestureDetector;

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @TargetApi(21)
    public PullableScrollView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean canPullDown() {
//        if (isCanPullDown) { //是否允许滑动
//            if (getScrollY() == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
            return true;
        else
            return false;
    }

    public static void setCanPullDown(boolean canPullDown) {
        isCanPullDown = canPullDown;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float mDownPosX =0.0f ;
        float mDownPosY =0.0f;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownPosX = ev.getX();
                mDownPosY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                int moveY = (int) ev.getY();
//                // 判断是否滑动，若滑动就拦截事件
//                if (moveY - downY>0&&Math.abs(moveY - downY)>100&&Math.abs(moveY - downY)> mTouchSlop) {
//                    return true;
//                }
                float x =ev.getX();
                float y =ev.getY();
                float deltaX = Math.abs(x - mDownPosX);
               float deltaY = Math.abs(y - mDownPosY);
                // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (deltaX > deltaY) {
                    return false;
                }
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev)&& mGestureDetector.onTouchEvent(ev);
    }



    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }

}
