package com.hz.zdjfu.application.modle.mine.personcenter.updatenick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *设置昵称
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/22 0022
 */
public class UpdateNickActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UpdateNickActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_updatenick;
    }

    @Override
    protected BaseFragment getFragment() {
        return UpdateNickFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.update_nick_top_titlle));
        showBackBtn(true);
    }
}
