package com.hz.zdjfu.application.modle.party.partycenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *活动中心Activity
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/17 0017.
 */

public class PartyCenterActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PartyCenterActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }




    @Override
    protected int getContentViewId() {
        return R.layout.activity_partycenter;
    }

    @Override
    protected BaseFragment getFragment() {
        return PartyCenterFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle(R.string.partycenter_top_title);
        showBackBtn(true);
    }
}
