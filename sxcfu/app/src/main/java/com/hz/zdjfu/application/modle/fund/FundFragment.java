package com.hz.zdjfu.application.modle.fund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;
import com.hz.zdjfu.application.widget.webview.WebViewFragment;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class FundFragment extends WebViewFragment {


    public static FundFragment newInstance() {

        return new FundFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        baseUrl =H5Urls.H5_URL_HOME_FUND;
        super.initView(view, savedInstanceState);
    }

    public void setOnRightViewListener(View view) {

        Intent  mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_HOME_SERVICE_HELP);
        mIntent.putExtra("WebView_TITLE",getString(R.string.fund_servicehelp_title));
        mActivity.startActivity(mIntent);

    }


}
