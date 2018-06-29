package com.hz.zdjfu.application.modle.mine.rechange;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *充值页面
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/27 0027.
 */

public class RechangeActivity extends ToolbarActivity{



    private RechangeFragment rechangeFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RechangeActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_rechange;
    }

    @Override
    protected BaseFragment getFragment() {
        rechangeFragment =RechangeFragment.newInstance();
        return rechangeFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.rechange_top_title));
        showBackBtn(true);
//        setRightIv(R.mipmap.help);
//        showRightIv(false);
    }


    @Override
    protected void onRightIvClick(View view) {
      //  rechangeFragment.rightOnClickViewListener();
    }
}
