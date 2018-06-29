package com.hz.zdjfu.application.modle.image;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseActivity;
import com.hz.zdjfu.application.base.BaseFragment;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/29 0029.
 */

public class LookImagesDisplayActivity extends BaseActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LookImagesDisplayActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_lookimagedisplay;
    }

    @Override
    protected BaseFragment getFragment() {
        return LookImagesDisplayFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

}
