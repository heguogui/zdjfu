package com.hz.zdjfu.application.widget.pulltorefresh.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;

/**
 * ScrollView的上，下拉刷新
 *
 *  @author xiangwq
 *
 */
public class HomePullableScrollView extends PullableScrollView
{

	private boolean canScroll;
	private GestureDetector mGestureDetector;

	public HomePullableScrollView(Context context)
	{
		super(context);
		// mGestureDetector = new GestureDetector(new YScrollDetector());
		canScroll = true;
	}

	public HomePullableScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		//mGestureDetector = new GestureDetector(new YScrollDetector());
		canScroll = true;
	}

	public HomePullableScrollView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// mGestureDetector = new GestureDetector(new YScrollDetector());
		canScroll = true;
	}

	/*@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(ev.getAction() == MotionEvent.ACTION_UP)
			canScroll = true;
		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if(canScroll)
				if (Math.abs(distanceY) >= Math.abs(distanceX))
					canScroll = true;
				else
					canScroll = false;
			return canScroll;
		}
	}*/

}
