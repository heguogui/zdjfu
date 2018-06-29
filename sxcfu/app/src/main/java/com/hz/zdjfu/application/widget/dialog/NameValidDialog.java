package com.hz.zdjfu.application.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.utils.PreferencesUtils;
import com.hz.zdjfu.application.widget.view.NameValidBannerImageHolderView;

import java.util.ArrayList;
import java.util.List;

/**
 * [类功能说明]
 *认证提示弹框
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/23 0023.
 */

public class NameValidDialog extends Dialog implements View.OnClickListener{


    private ImageView mTvCancle;
    private ConvenientBanner banner;
    private Context mContext;
    private BannerImageHolderViewCBViewHolderCreator mBannerImageHolderViewCBViewHolderCreator;
    public NameValidDialog(Context context) {
        super(context, R.style.common_alert_dialog);
        this.mContext =context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_namevalid_notication_dialog);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        mTvCancle = (ImageView) findViewById(R.id.namevalid_dialog_cancle);
        mTvCancle.setOnClickListener(this);
        banner =findViewById(R.id.namevalid_convenientBanner);
        banner.setCanLoop(false);
        //创建轮播子View
        mBannerImageHolderViewCBViewHolderCreator = new BannerImageHolderViewCBViewHolderCreator();

//        banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
//                .setPointViewVisible(true)
//                .setPageIndicator(new int[]{R.drawable.gray_radius100,R.drawable.white_radius100})
//                .setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//
//                    }
//                });

        List<String> mLists = new ArrayList<>();
        String mOne ="ONE";
        String mTwo ="TWO";
        String mThree ="THREE";
        String mFour ="FOUR";
        mLists.add(mOne);
        mLists.add(mTwo);
        mLists.add(mThree);
        mLists.add(mFour);

        if (banner.getViewPager() == null || banner.getViewPager().getAdapter() == null) {
            banner.setPages(mBannerImageHolderViewCBViewHolderCreator,mLists);
        }
        banner.stopTurning();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.namevalid_dialog_cancle) {
            PreferencesUtils.putBoolean(mContext, Constants.USER_NAME_VALID_PREFERENCE,true);
           dismiss();
        }
    }

    /*包装BannerImageHolderView*/
    private class BannerImageHolderViewCBViewHolderCreator implements CBViewHolderCreator<NameValidBannerImageHolderView> {
        @Override
        public NameValidBannerImageHolderView createHolder() {
            return new NameValidBannerImageHolderView(mContext);
        }
    }



}
