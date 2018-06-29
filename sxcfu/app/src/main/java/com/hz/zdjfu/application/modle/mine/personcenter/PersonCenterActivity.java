package com.hz.zdjfu.application.modle.mine.personcenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.setting.SettingActivity;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/21 0021
 */
public class PersonCenterActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PersonCenterActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_personcenter;
    }

    @Override
    protected BaseFragment getFragment() {
        return PersonCenterFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        showBackBtn(true);
        showRightIv(true);
        setRightIv(R.mipmap.setting);
        setTitle(getString(R.string.personcenter_top_title));
    }

    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);
        startActivity(SettingActivity.makeIntent(this,null));
    }
}
