package com.hz.zdjfu.application.modle.performancelist;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/4/2 0002.
 */

public class PerformanceActivity extends ToolbarActivity{
    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return PerformanceFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        showToolbar(false);
    }
}
