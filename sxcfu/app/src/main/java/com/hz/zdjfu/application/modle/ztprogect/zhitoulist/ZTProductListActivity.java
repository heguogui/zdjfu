package com.hz.zdjfu.application.modle.ztprogect.zhitoulist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;


/**
 * [类功能说明]
 *直投履约中
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2018/4/26 0026.
 */

public class ZTProductListActivity extends ToolbarActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ZTProductListActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        return ZTProductListFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        showBackBtn(true);
        setTitle("直投");
    }

}
