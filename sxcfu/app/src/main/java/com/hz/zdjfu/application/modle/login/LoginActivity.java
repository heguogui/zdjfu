package com.hz.zdjfu.application.modle.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.login.regist.RegistActivity;


/**
 * [类功能说明]
 *登录界面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class LoginActivity extends ToolbarActivity {

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected BaseFragment getFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(R.string.login_title);
        showBackBtn(true);
        showRightTv(true);
        setRightTv(getString(R.string.regist_title));
    }

    @Override
    protected void onRightTvClick(TextView view) {
        super.onRightTvClick(view);
        startActivity(RegistActivity.makeIntent(this,null));
    }


    @Override
    public void onBackPressed() {
        String mForm =null;
        Intent mIntent =getIntent();
        if(mIntent!=null){
            Bundle mBundle = mIntent.getBundleExtra(BUNDLE);
            if(mBundle!=null){
                mForm =mBundle.getString("FROMPARENT");//   mBundle.putString("FROMPARENT","UPDATEPWD");
            }
        }

        if(!TextUtils.isEmpty(mForm)&&(mForm.equals("UPDATEPWD")||mForm.equals("FORGETPWD"))){
            UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
            if(mUserBean!=null&&!mUserBean.isLogin()){
                startActivity(MainActivity.makeIntent(this,null));
            }
        }
        finish();
    }
}
