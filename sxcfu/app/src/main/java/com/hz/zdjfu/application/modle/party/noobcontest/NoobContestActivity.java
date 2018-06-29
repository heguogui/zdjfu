package com.hz.zdjfu.application.modle.party.noobcontest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/18 0018.
 */

public class NoobContestActivity extends ToolbarActivity {

    private static final String TAG =NoobContestActivity.class.getName();

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NoobContestActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    private NoobContestFragment mNoobContestFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_noobcontest;
    }

    @Override
    protected BaseFragment getFragment() {
        mNoobContestFragment= NoobContestFragment.newInstance();
        return mNoobContestFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle(R.string.noobcontest_top_title);
        showBackBtn(true);
        showRightIv(true);
        setRightIv(R.mipmap.back);
    }


    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);
        mNoobContestFragment.onClickTopRightView();
    }
}
