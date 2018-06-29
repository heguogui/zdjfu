/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.utils.image.imagezoom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseActivity;
import com.hz.zdjfu.application.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * [图片展示activity]
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */
public class ImageZoomActivity extends BaseActivity {
    // 图片路径list
    public static String IMAGE_URL_LIST = "image_url_list";
    // 用户点击的第几张，0开始算
    public static String IMAGE_PAGE = "image_page";

    private int mPage = 0;
    private List<String> mImageUrlList = new ArrayList<>();

    private ImageViewPagerAdapter adapter;

    private HackyViewPager pager;
    private Xcircleindicator mXcircleindicator;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ImageZoomActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_image_zoom;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        Bundle bundleExtra = intent.getBundleExtra(BUNDLE);
        if (bundleExtra == null) {
            return;
        }
        //  接收数据。。。。
        mPage = Integer.parseInt(bundleExtra.getString(IMAGE_PAGE));
        mImageUrlList = bundleExtra.getStringArrayList(IMAGE_URL_LIST);
    }

    @Override
    protected void init() {
        super.init();
        pager = (HackyViewPager) findViewById(R.id.pager_image_activity);
        mXcircleindicator = (Xcircleindicator) findViewById(R.id.Xcircleindicator_image_activity);
        adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), mImageUrlList);
        pager.setAdapter(adapter);
        if (mPage < mImageUrlList.size() & mImageUrlList.size() > 0) {
            pager.setCurrentItem(mPage);//跳转到用户点击的图片
        }
        //设置总共的页数
        mXcircleindicator.initData(mImageUrlList.size(), 0);
        //设置当前的页面
        mXcircleindicator.setCurrentPage(mPage);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                mXcircleindicator.setCurrentPage(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}
