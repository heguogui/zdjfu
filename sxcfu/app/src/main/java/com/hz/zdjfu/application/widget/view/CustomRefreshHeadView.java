package com.hz.zdjfu.application.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.callback.IHeaderCallBack;
import com.hz.zdjfu.application.R;

public class CustomRefreshHeadView extends LinearLayout implements IHeaderCallBack {

    private TextView mHintTextView;

    public CustomRefreshHeadView(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor("#f3f3f3"));
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public CustomRefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.refresh_head_layout, this);
        mHintTextView = (TextView) findViewById(R.id.gif_header_hint);
    }

    public void setRefreshTime(long lastRefreshTime) {
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void onStateNormal() {
        mHintTextView.setText(R.string.xrefreshview_header_hint_normal);
    }

    @Override
    public void onStateReady() {
        mHintTextView.setText(R.string.xrefreshview_header_hint_ready);
    }

    @Override
    public void onStateRefreshing() {
        mHintTextView.setText(R.string.xrefreshview_header_hint_refreshing);
    }

    @Override
    public void onStateFinish(boolean success) {
        mHintTextView.setText(success ? R.string.xrefreshview_header_hint_loaded : R.string.xrefreshview_header_hint_loaded_fail);
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {
        //
    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
