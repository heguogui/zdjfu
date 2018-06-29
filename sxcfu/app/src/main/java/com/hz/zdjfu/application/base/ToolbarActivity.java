package com.hz.zdjfu.application.base;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;


/**
 * [类功能说明]
 *有toolbar Activity基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public abstract class ToolbarActivity extends BaseActivity implements View.OnClickListener {

    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mRightIv; // 右图片
    private TextView mRightTv; // 右标题
    private View mLine;

    @Override
    protected void init() {
        super.init();
        initToolBar();
    }

    /**
     * 初始化工具栏
     * 包括标题和返回键
     */
    private void initToolBar() {
        mLine = (View)findViewById(R.id.toobar_line);
        mTitle = (TextView) findViewById(R.id.toolbar_title);
        mRightIv = (ImageView) findViewById(R.id.right_iv);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null || mAppBarLayout == null) {
            throw new IllegalStateException(
                    "The subclass of ToolbarActivity must contain a toolbar.");
        }
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 设置是否显示标题
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 设置返回键监听事件
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        showBackBtn(false);
        showRightIv(false);
        showRightTv(false);
        showTitle(mTitle);
        setListener();
    }


    /**
     * 设置标题文字
     *
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitle.setText(title);
        super.setTitle(title);
    }

    /**
     * 设置标题栏监听
     */
    private void setListener() {
        mRightIv.setOnClickListener(this);
        mRightTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_iv: {
                onRightIvClick(view);
            }
            break;
            case R.id.right_tv: {
                onRightTvClick((TextView) view);
            }
            break;
        }
    }

    /**
     * 右标题点击
     */
    protected void onRightTvClick(TextView view) {
    }

    /**
     * 线条是否显示
     * @param state
     */
    public  void isShowLine(boolean state){
        if(!state){
            mLine.setVisibility(View.GONE);
        }
    }


    protected void showTitle(TextView view){

    }

    /**
     * 右图片点击
     *
     * @param view
     */
    protected void onRightIvClick(View view) {

    }

    /**
     * 设置标题颜色
     *
     * @param textColor
     */
    @Override
    public void setTitleColor(int textColor) {
        mTitle.setTextColor(textColor);
        super.setTitleColor(textColor);
    }

    /**
     * 显示返回键
     *
     * @param show true 显示, false 不显示
     */
    protected void showBackBtn(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        // 设置是否显示返回键
        actionBar.setDisplayHomeAsUpEnabled(show);
    }


    /**
     * 显示工具栏
     *
     * @param show true 显示, false 不显示
     */
    protected void showToolbar(boolean show) {
        if (show) {
            mAppBarLayout.setVisibility(View.VISIBLE);
            return;
        }
        mAppBarLayout.setVisibility(View.GONE);
    }

    /**
     * 设置右侧图片
     *
     * @param resId
     */
    public void setRightIv(int resId) {
        mRightIv.setImageResource(resId);
    }

    /**
     * 设置右侧图片
     *
     * @param resId 右图图标
     * @param tag 放置标号
     */
    public void setRightIv(int resId, String tag) {
        mRightIv.setImageResource(resId);
        mRightIv.setTag(tag);
    }

    /**
     * 设置右侧图片显示或隐藏
     *
     * @param show
     */
    public void showRightIv(boolean show) {
        if (show) {
            mRightIv.setVisibility(View.VISIBLE);
        } else {
            mRightIv.setVisibility(View.GONE);
        }
    }

    public void setRightTv(String str, String tag) {
        mRightTv.setText(str);
        mRightTv.setTag(tag);
    }

    public void setRightTv(String str) {
        mRightTv.setText(str);
    }

    public void showRightTv(boolean show) {
        if (show) {
            mRightTv.setVisibility(View.VISIBLE);
        } else {
            mRightTv.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题背景颜色
     * @param textColor
     */
    public void setToolBarBackGroundColor(int textColor) {
        mToolbar.setBackgroundResource(textColor);
    }

    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setRightTVState(boolean state){
        if(mRightTv!=null){
            mRightTv.setEnabled(state);
        }
    }

    public void setRightTvColor(int color){
        if(mRightTv!=null){
            mRightTv.setTextColor(color);
        }
    }

}
