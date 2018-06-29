package com.hz.zdjfu.application.modle.login.regist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.modle.MainActivity;

/**
 * [类功能说明]
 * 注册页面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RegistActivity extends ToolbarActivity{

    private static final String TAG =RegistActivity.class.getName();
    private String mForm =null;
    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegistActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_regist;
    }

    @Override
    protected BaseFragment getFragment() {
        return RegistFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        showBackBtn(true);
        setTitle(getString(R.string.regist_title));

        Intent mIntent =this.getIntent();
        if(mIntent!=null){
            Bundle mBundle = mIntent.getBundleExtra(BUNDLE);
            if(mBundle!=null){
                mForm =mBundle.getString("FROMPARENT");
            }
        }
    }




    @Override
    public void onBackPressed() {
        if(!TextUtils.isEmpty(mForm)&&mForm.equals("WELCOME")){
            startActivity(MainActivity.makeIntent(this,null));
            this.finish();
        }

        super.onBackPressed();
    }
}
