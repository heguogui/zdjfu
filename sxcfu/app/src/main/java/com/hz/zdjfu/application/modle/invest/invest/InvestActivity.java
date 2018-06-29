package com.hz.zdjfu.application.modle.invest.invest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;
import com.hz.zdjfu.application.data.even.InvestEven;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * [类功能说明]
 *投资
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class InvestActivity extends ToolbarActivity{


    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InvestActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    private InvestFragment mInvestFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_invest;
    }

    @Override
    protected BaseFragment getFragment() {
        this.mInvestFragment =InvestFragment.newInstance();
        return mInvestFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(InvestActivity.this)) {
            EventBus.getDefault().register(InvestActivity.this);
        }
    }

    @Override
    protected void init() {
        super.init();
        setTitle("投资");
        showBackBtn(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(InvestActivity.this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshFromOther(InvestEven mEven){

        if(mEven==null){
            return;
        }
        String mFromTag =mEven.getFromTag();
        if(!TextUtils.isEmpty(mFromTag)&&mFromTag.equals("RECHANGESUCCESS")){
            mInvestFragment.refresh();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
