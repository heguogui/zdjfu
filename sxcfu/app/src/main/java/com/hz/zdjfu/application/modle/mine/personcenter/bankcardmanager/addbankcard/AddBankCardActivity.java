package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.addbankcard;

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
 *添加银行卡管理
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/22 0022
 */
public class AddBankCardActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AddBankCardActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bankcard;
    }

    @Override
    protected BaseFragment getFragment() {
        return AddBankCardFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.addbankcard_top_title));
        showBackBtn(true);
        showRightIv(true);
        setRightIv(R.mipmap.help);
    }

    @Override
    protected void onRightIvClick(View view) {
        Intent mIntent = new Intent(this, WebViewActivity.class);
        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_BANKCARD_MANAGER_HELP);
        mIntent.putExtra("WebView_TITLE",getString(R.string.addbankcard_top_title));
        startActivity(mIntent);
    }
}
