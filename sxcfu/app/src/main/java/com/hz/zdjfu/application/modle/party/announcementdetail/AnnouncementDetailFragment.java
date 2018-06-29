package com.hz.zdjfu.application.modle.party.announcementdetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.AnnouncementBean;

import butterknife.BindView;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/28 0028.
 */

public class AnnouncementDetailFragment extends BaseFragment {


    @BindView(R.id.announcement_detail_tv)
    TextView announcementDetailTv;


    public static AnnouncementDetailFragment newInstance() {
        return new AnnouncementDetailFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_announcementdetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        Intent mIntent = mActivity.getIntent();
        if (mIntent != null) {
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            AnnouncementBean mBean = (AnnouncementBean) mBundle.getSerializable("ANNOUNCEMENTBEAN");
            if(mBean!=null&&!TextUtils.isEmpty(mBean.getContent())){
                announcementDetailTv.setText(Html.fromHtml(mBean.getContent()));
            }
        }

    }


}
