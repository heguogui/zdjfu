package com.hz.zdjfu.application.widget.webview;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/6/25 0025.
 */
public class SpecialWebViewActivity extends ToolbarActivity {

    private static final String TAG =SpecialWebViewActivity.class.getName();
    @Override
    protected int getContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected BaseFragment getFragment() {
        return SpecialWebViewFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        if(mToolbar!=null){
            mToolbar.setNavigationIcon(R.mipmap.back);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
