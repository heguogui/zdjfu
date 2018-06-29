package com.hz.zdjfu.application.modle.opendeposit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

/**
 * [类功能说明]
 *2.0版本 开通银行账户
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/1/17 0017.
 */

public class OpenDepositActivity extends ToolbarActivity{


    public OpenDepositFragment mOpenDepositFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OpenDepositActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_opendeposit;
    }

    @Override
    protected BaseFragment getFragment() {
        mOpenDepositFragment =OpenDepositFragment.newInstance();
        return mOpenDepositFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    public void onBackPressed() {
        if(mOpenDepositFragment!=null){
            mOpenDepositFragment.leftBtnOnClickListener();
        }
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.namecertification_top_title));
        showBackBtn(true);
        showRightIv(true);
        setRightIv(R.mipmap.help);

    }


    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);
        Intent mIntent = new Intent(OpenDepositActivity.this, WebViewActivity.class);
        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_OPEN_BANK_ACCOUNT_HELP);
        OpenDepositActivity.this.startActivity(mIntent);
    }
}
