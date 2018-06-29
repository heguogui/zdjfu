package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.unbindbankcard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/13 0013.
 */

public class UnbindBankCardActivity extends ToolbarActivity{

    private UnbindBankCardFragment mUnbindBankCardFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UnbindBankCardActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_unbindbankcard;
    }

    @Override
    protected BaseFragment getFragment() {
        return UnbindBankCardFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.bankcard_top_title));
        showBackBtn(true);
    }

}
