package com.hz.zdjfu.application.modle.invest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *投资记录
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class InvestRewordActivity extends ToolbarActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InvestRewordActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_investreword;
    }

    @Override
    protected BaseFragment getFragment() {
        return InvestRewordFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle("投资记录");
        showBackBtn(true);

    }
}
