
package com.hz.zdjfu.application.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hz.zdjfu.application.modle.MainActivity;


/**
 * [类功能说明]
 *  Activity 工具类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ActivityUtils {

    /**
     * 将fragment添加到容器中,由fragmentManager执行
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmenttoActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId,
            String tag
    ) {
        if (fragmentManager == null || fragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
//        transaction.commit();
        transaction.commitAllowingStateLoss();
    }

    public static void addFragmenttoActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId
    ) {
        addFragmenttoActivity(
                fragmentManager,
                fragment,
                frameId,
                null
        );
    }


    /**
     * 替换fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void replaceFragment(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId
    ) {
        if (fragment == null || fragmentManager == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    /**
     * 切换首页选项卡的显示
     *
     * @param fragmentManager
     * @param fragmentTag
     */
    public static void switchFragmentInMainPage(
            @NonNull FragmentManager fragmentManager,
            @NonNull String fragmentTag
    ) {
        if (fragmentManager == null || fragmentTag == null) {
            return;
        }
        Fragment homeFragment = fragmentManager.findFragmentByTag(MainActivity.HOME_TAB);
        Fragment financialFragment = fragmentManager.findFragmentByTag(MainActivity.FINANCIAL_TAB);
        Fragment fundFragment = fragmentManager.findFragmentByTag(MainActivity.FUND_TAB);
        Fragment mineFragment = fragmentManager.findFragmentByTag(MainActivity.MINE_TAB);

        if (homeFragment == null || financialFragment == null || fundFragment == null || mineFragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction == null) {
            return;
        }
        switch (fragmentTag) {
            case MainActivity.HOME_TAB: {
                transaction.show(homeFragment);
                transaction.hide(financialFragment);
                transaction.hide(fundFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
            }
            break;
            case MainActivity.FINANCIAL_TAB: {
                transaction.hide(homeFragment);
                transaction.show(financialFragment);
                transaction.hide(fundFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
            }
            break;
            case MainActivity.FUND_TAB: {
                transaction.hide(homeFragment);
                transaction.hide(financialFragment);
                transaction.show(fundFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
            }
            break;
            case MainActivity.MINE_TAB: {
                transaction.hide(homeFragment);
                transaction.hide(financialFragment);
                transaction.hide(fundFragment);
                transaction.show(mineFragment);
                transaction.commitAllowingStateLoss();
            }
            break;
        }

    }

}
