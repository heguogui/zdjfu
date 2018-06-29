package com.hz.zdjfu.application.modle.mine.personcenter.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *联系我们
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/21 0021
 */
public class ContactActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ContactActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contact;
    }

    @Override
    protected BaseFragment getFragment() {
        return ContactFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(R.string.contact_company_top_title);
        showBackBtn(true);

    }
}
