package com.hz.zdjfu.application.modle.ztprogect.investamount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.even.InvestEven;
import com.hz.zdjfu.application.modle.invest.invest.InvestFragment;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * [类功能说明]
 *直投投资
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class InvestAmountActivity extends ToolbarActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InvestAmountActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    private InvestAmountFragment mInvestAmountFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_invest;
    }

    @Override
    protected BaseFragment getFragment() {
        this.mInvestAmountFragment =InvestAmountFragment.newInstance();
        return mInvestAmountFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(InvestAmountActivity.this)) {
            EventBus.getDefault().register(InvestAmountActivity.this);
        }
    }

    @Override
    protected void init() {
        super.init();
        setTitle("投资");
        showBackBtn(true);
        showRightIv(true);
        setRightIv(R.mipmap.help);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(InvestAmountActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshFromOther(InvestEven mEven){

        if(mEven==null){
            return;
        }
        String mFromTag =mEven.getFromTag();
        if(!TextUtils.isEmpty(mFromTag)&&mFromTag.equals("ZTRECHANGESUCCESS")){
            mInvestAmountFragment.refresh();
        }
    }
    @Override
    protected void onRightIvClick(View view) {
        Intent mIntent = new Intent(this, WebViewActivity.class);
        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_INVEST_HELP);
        mIntent.putExtra("WebView_TITLE","投资帮助");
        startActivity(mIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
