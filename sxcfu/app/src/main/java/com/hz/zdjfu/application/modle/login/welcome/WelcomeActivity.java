package com.hz.zdjfu.application.modle.login.welcome;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseActivity;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.utils.PreferencesUtils;

/**
 * [类功能说明]
 *欢迎界面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/25 0025
 */
public class WelcomeActivity extends BaseActivity{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected BaseFragment getFragment() {
        return WelcomeFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        PreferencesUtils.putBoolean(Constants.WELCOME_PREFERENCE, this, Constants.SP_KEY_WELCOME_FLAG, true);
    }
}
