package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *认证开通上海银行账户activity
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class OpenSHBankAccountActivity extends ToolbarActivity{

    public OpenSHBankAccountFragment openSHBankAccountFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OpenSHBankAccountActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_openshbankaccount;
    }

    @Override
    protected BaseFragment getFragment() {
        openSHBankAccountFragment =OpenSHBankAccountFragment.newIntance();
        return openSHBankAccountFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.namecertification_top_title));
        showBackBtn(true);
        showRightIv(false);
//        setRightIv(R.mipmap.help);

    }

    @Override
    protected void onRightIvClick(View view) {
//        Intent mIntent = new Intent(this, WebViewActivity.class);
//        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_OPEN_BANK_ACCOUNT_HELP);
//        mIntent.putExtra("WebView_TITLE",getString(R.string.namecertification_top_title));
//        startActivity(mIntent);
    }

    @Override
    public void onBackPressed() {
        if(openSHBankAccountFragment!=null){
            openSHBankAccountFragment.leftBtnOnClickListener();
        }
    }
}
