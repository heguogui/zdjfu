package com.hz.zdjfu.application.modle.discount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *优惠券
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/28 0028.
 */

public class DiscountActivity extends ToolbarActivity{

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DiscountActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    private DiscountFragment mDiscountFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_discount;
    }

    @Override
    protected BaseFragment getFragment() {
        mDiscountFragment =DiscountFragment.newInstance();
        return mDiscountFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void init() {
        super.init();
        setTitle(getString(R.string.discount_top_title));
        showBackBtn(true);
    }


    public void refreshLeftTitle(int num){
        if(mDiscountFragment!=null){
            mDiscountFragment.showUsedTitleNum(num);
        }
    }

    public void refreshRight(int num){
        if(mDiscountFragment!=null){
            mDiscountFragment.showUnUsedTitleNum(num);
        }
    }

}
