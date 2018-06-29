package com.hz.zdjfu.application.modle.mine.personcenter.forgetpsd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *忘记密码Activity
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/26 0026.
 */

public class ForgetPsdActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ForgetPsdActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_forgetpsd;
    }

    @Override
    protected BaseFragment getFragment() {
        return ForgetPsdFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.forgetpsd_top_title));
        showBackBtn(true);
    }
}
