package com.hz.zdjfu.application.modle.mine.rechange.rechangehelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *其他充值帮助
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/27 0027.
 */

public class RechangeHelpActivity extends ToolbarActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RechangeHelpActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_rechangehelp;
    }

    @Override
    protected BaseFragment getFragment() {
        return RechangeHelpFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.rechange_bigamount_top_title));
        showBackBtn(true);
    }


}
