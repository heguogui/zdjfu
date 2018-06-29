package com.hz.zdjfu.application.modle.getturepwd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *  创建手势密码
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/27 0027.
 */

public class CreateGesturePwdActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CreateGesturePwdActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    private CreateGesturePwdFragment mFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_creategesturepwd;
    }

    @Override
    protected BaseFragment getFragment() {
        mFragment =CreateGesturePwdFragment.newInstance();
        Intent mIntent =getIntent();
        Bundle bundle =null;
        if(mIntent!=null){
            bundle =mIntent.getBundleExtra(BUNDLE);
        }
        if(bundle!=null){
            mFragment.setArguments(bundle);
        }
        return mFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        showToolbar(false);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mFragment!=null){
            mFragment.saveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
