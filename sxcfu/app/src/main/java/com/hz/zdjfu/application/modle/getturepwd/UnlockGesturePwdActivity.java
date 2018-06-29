package com.hz.zdjfu.application.modle.getturepwd;

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
 * @time 2017/9/28 0028.
 */

public class UnlockGesturePwdActivity extends ToolbarActivity{

    private static  String TAG = UnlockGesturePwdActivity.class.getName();

    private UnlockGesturePwdFragment mFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UnlockGesturePwdActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_unlockgesturepwd;
    }

    @Override
    protected BaseFragment getFragment() {

        mFragment = UnlockGesturePwdFragment.newInstance();
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
    public void onBackPressed() {
        finish();
    }


}
