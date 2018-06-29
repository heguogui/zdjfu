package com.hz.zdjfu.application.modle.rechangecenter.rechangedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *正经值兑换详情
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class RechangeDetailActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RechangeDetailActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_rechangedetail;
    }

    @Override
    protected BaseFragment getFragment() {
        return RechangeDetailFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.rechangedetail_top_title));
        showBackBtn(true);
    }
}
