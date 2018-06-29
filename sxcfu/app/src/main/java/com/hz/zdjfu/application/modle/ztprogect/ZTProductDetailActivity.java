package com.hz.zdjfu.application.modle.ztprogect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailFragment;
import com.hz.zdjfu.application.share.ShareDialog;
import com.hz.zdjfu.application.utils.ToastUtils;


/**
 * [类功能说明]
 *  产品详情
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/4 0004.
 */

public class ZTProductDetailActivity extends ToolbarActivity{


    public ZTProductDetailFragment mProductDetailFragment;

    public ZTProductDetailBean mZTProductDetailBean;

    public TextView mTitle;
    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ZTProductDetailActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        mProductDetailFragment =ZTProductDetailFragment.newInstance();
        return mProductDetailFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        showToolbar(true);
        showRightIv(false);
        showBackBtn(true);
        setRightIv(R.mipmap.share);
        isShowLine(false);
    }


    @Override
    protected void showTitle(TextView view) {
        this.mTitle =view;
    }

    @Override
    protected void onRightIvClick(View view) {
        ShareDialog dialog = new ShareDialog(ZTProductDetailActivity.this,ZTProductDetailActivity.this);
        dialog.show();
    }


}
