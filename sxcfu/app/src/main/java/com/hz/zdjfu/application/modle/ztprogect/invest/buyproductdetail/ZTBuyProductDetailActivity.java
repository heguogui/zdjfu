package com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailFragment;

/**
 * [类功能说明]
 *直投购买的投资详情
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/20 0020.
 */

public class ZTBuyProductDetailActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ZTBuyProductDetailActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_investdetail;
    }

    @Override
    protected BaseFragment getFragment() {
        return ZTBuyProductDetailFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("投资详情");
        showBackBtn(true);
    }
}
