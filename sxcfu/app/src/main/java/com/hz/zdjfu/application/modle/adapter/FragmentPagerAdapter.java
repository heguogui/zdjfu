package com.hz.zdjfu.application.modle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * [类功能说明]
 *fragment 适配器
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/28 0028
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentsList;

    public FragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragmentsList = fragments;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentsList.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
