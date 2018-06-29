package com.hz.zdjfu.application.modle.party.announcementlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ToolbarActivity;

/**
 * [类功能说明]
 *消息
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class AnnouncementListActivity extends ToolbarActivity{


    private static final String TAG =AnnouncementListActivity.class.getName();

    public AnnouncementListFragment mMessageFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AnnouncementListActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected BaseFragment getFragment() {
        mMessageFragment =AnnouncementListFragment.newInstance();
        return mMessageFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("公告列表");
        showBackBtn(true);
    }


}
