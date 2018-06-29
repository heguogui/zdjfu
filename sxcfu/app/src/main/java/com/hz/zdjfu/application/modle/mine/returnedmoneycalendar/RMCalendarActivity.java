package com.hz.zdjfu.application.modle.mine.returnedmoneycalendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *回款日历
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class RMCalendarActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RMCalendarActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_rmcalendar;
    }

    @Override
    protected BaseFragment getFragment() {
        return RMCalendarFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.rmcalendar_top_title));
        showBackBtn(true);
    }
}
