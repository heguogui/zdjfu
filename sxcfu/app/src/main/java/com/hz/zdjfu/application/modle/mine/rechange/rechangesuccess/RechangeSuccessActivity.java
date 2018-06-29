package com.hz.zdjfu.application.modle.mine.rechange.rechangesuccess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.data.even.InvestEven;
import com.hz.zdjfu.application.modle.invest.invest.InvestActivity;

import org.greenrobot.eventbus.EventBus;


/**
 * [类功能说明]
 *其他充值帮助
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/27 0027.
 */

public class RechangeSuccessActivity extends ToolbarActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RechangeSuccessActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_rechangesuccess;
    }

    @Override
    protected BaseFragment getFragment() {
        return RechangeSuccessFragment.newInstance();
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("充值");
        showBackBtn(true);
    }


    @Override
    public void onBackPressed() {
        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("INVESTFRAGMENT")){
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY =null;
            startActivity(InvestActivity.makeIntent(this,null));
            EventBus.getDefault().post(new InvestEven("RECHANGESUCCESS"));
        }
    }
}
