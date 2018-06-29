package com.hz.zdjfu.application.modle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * [类功能说明]
 *fragment 适配器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private String[] mCount;
    private List<Fragment> mLists;


    public MyFragmentPagerAdapter(FragmentManager fm ,List<Fragment> lists,String[] count) {
        super(fm);
        this.mCount =count;
        this.mLists =lists;
    }

    @Override
    public Fragment getItem(int position) {

        if(mLists==null){
            return null;
        }
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists==null?0:mLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCount[position];
    }
}
