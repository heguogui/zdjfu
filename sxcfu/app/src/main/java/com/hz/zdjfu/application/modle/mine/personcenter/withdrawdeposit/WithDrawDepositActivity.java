package com.hz.zdjfu.application.modle.mine.personcenter.withdrawdeposit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *银行卡管理
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/22 0022
 */
public class WithDrawDepositActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WithDrawDepositActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_withdrawdeposit;
    }

    @Override
    protected BaseFragment getFragment() {
        return WithDrawDepositFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.withdraw_deposit_top_title));
        showBackBtn(true);
//        showRightIv(true);
//        setRightIv(R.mipmap.help);

    }

    @Override
    protected void onRightIvClick(View view) {
//        Intent mIntent = new Intent(this, WebViewActivity.class);
//        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_WITHDRAWDEPOSIT_HELP);
//        startActivity(mIntent);

    }
}
