package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.util.SparseArray;

/**
 * [类功能说明]
 *FragmentFactory 工厂
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/12 0012.
 */

public class FragmentFactory {

    public static final int FRAGMENT_NAMEAUTHENTICATION = 0x00;//实名认证
    public static final int FRAGMENT_BINDBANKCARD = 0x01;//绑定银行卡

    /*collection to manage fragment*/
    static SparseArray<OpenSHBankBaseFragment> mFragments = new SparseArray<OpenSHBankBaseFragment>();
    /**
     * the method to create fragment by flag(IDENTITY_AUTHENTICATION.......)
     *
     * @param flag
     * @return
     */
    public static OpenSHBankBaseFragment createFragment(int flag) {
        /*first get fragment*/
        OpenSHBankBaseFragment fragment = mFragments.get(flag);
        if (fragment == null) {
            switch (flag) {
                case FRAGMENT_NAMEAUTHENTICATION:
                    fragment = new NameAuthenticationFragment();
                    break;
                case FRAGMENT_BINDBANKCARD:
                    fragment = new BindBankCardFragment();
                    break;
                default:
                    break;
            }
            mFragments.put(flag, fragment);
        }
        return fragment;
    }

    /**
     * the method to clear SparseArray
     */
    public static void clearAll() {
        if (mFragments != null)
            mFragments.clear();
    }

    /**
     * remove object by key
     *
     * @param key
     */
    public static void remove(int key) {
        if (mFragments != null)
            mFragments.remove(key);
    }

}
