package com.hz.zdjfu.application.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;


/**
 * [类功能说明]
 *  Fragment 基类
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    /**
     * butterknife 解绑对象
     */
    private Unbinder unbinder;

    /*解绑 rxjava Subscription  */
    protected Subscription subscription;

    /**
     * 获取fragment布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        // 设置butterknife
        unbinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind(); // 解除绑定
    }

    @Override
    public void onDestroy() {
        mActivity = null;
        super.onDestroy();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
