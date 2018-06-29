package com.hz.zdjfu.application.modle.mine.transationdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *我的资产交易明细Activity
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class TransationdetailActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, TransationdetailActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_transationdetail;
    }

    @Override
    protected BaseFragment getFragment() {
        return TransationDetailFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.transationdetail_top_title));
        showBackBtn(true);
    }
}
