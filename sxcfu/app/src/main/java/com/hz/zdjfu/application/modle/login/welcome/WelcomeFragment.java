/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.hz.zdjfu.application.modle.login.welcome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.login.regist.RegistActivity;
import com.hz.zdjfu.application.widget.view.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;


/**
 * 欢迎界面
 */
public class WelcomeFragment extends BaseFragment implements WelcomeContract.View {

    private WelcomeContract.Presenter mPresenter;
    private GuideViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private CircleIndicator mIndicator;


    public static WelcomeFragment newInstance() {

        return new WelcomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_welcome;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        mViewPager = (ViewPager) view.findViewById(R.id.vp_guide);
        mAdapter = new GuideViewPagerAdapter(getActivity(), new Integer[0], mPagerItemListener);

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new PageChangeListener());
        mIndicator = (CircleIndicator) view.findViewById(R.id.ci_ad);
        mIndicator.setViewPager(mViewPager);

        new WelcomePresenter(this);

        mPresenter.loadViews();

    }

    @Override
    public void setPresenter(@NonNull WelcomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void jumpRegistActivity() {
        if(mActivity!=null){
            Bundle mBundle = new Bundle();
            mBundle.putString("FROMPARENT","WELCOME");
            startActivity(RegistActivity.makeIntent(mActivity, mBundle));
            mActivity.finish();
        }
    }

    @Override
    public void jumpHomeActivity() {
        startActivity(MainActivity.makeIntent(mActivity, null));
        mActivity.finish();
    }

    @Override
    public void showViewPager(Integer[] pics) {

        mAdapter.replaceData(pics);
        mIndicator.setViewPager(mViewPager);

    }

    @Override
    public void showErrorTips(String msg) {

    }

    @Override
    public void initViewData() {

    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {

    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
           //  setCurDot(position);
            if(mIndicator!=null){
                if(position>2){
                    mIndicator.setVisibility(View.GONE);
                }else{
                    mIndicator.setVisibility(View.VISIBLE);
                }
            }

        }



    }



    private void setCurDot(int position) {

    }


    private PagerItemListener mPagerItemListener = new PagerItemListener() {
        @Override
        public void onLeftBtnClick() {
            jumpHomeActivity();
        }

        @Override
        public void onRightBtnClick() {
            jumpRegistActivity();
        }
    };


    private class GuideViewPagerAdapter extends PagerAdapter {
        private List<View> mViews;
        private PagerItemListener mPagerItemListener;
        private Context mContext;

        public GuideViewPagerAdapter(Context context, Integer[] viewIDs, PagerItemListener pagerItemListener) {
            super();

            mPagerItemListener = pagerItemListener;
            mContext = context;
            initList(viewIDs);
        }

        @Override
        public int getCount() {
            if (mViews != null) {
                return mViews.size();
            }
            return 0;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mViews.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mViews.get(position), 0);
            return mViews.get(position);
        }

        public void replaceData(Integer[] viewIDs) {
            initList(viewIDs);
            notifyDataSetChanged();
        }

        public void initList(Integer[] viewIDs) {

            mViews = new ArrayList<View>();

            Observable.from(viewIDs).map(new Func1<Integer, View>() {
                @Override
                public View call(Integer integer) {
                    return LayoutInflater.from(mContext).inflate(integer, null);

                }
            }).subscribe(new Observer<View>() {
                @Override
                public void onCompleted() {
                    if (!mViews.isEmpty() && mViews.size() > 1) {

                        //最后一个布局需要带button
                        Button startBtn = (Button) mViews.get(mViews.size() - 1).findViewById(R.id.btn_enter);
                        startBtn.setTag("enter");
                        startBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mPagerItemListener.onLeftBtnClick();
                            }
                        });

                        Button startRegistBtn = (Button) mViews.get(mViews.size() - 1).findViewById(R.id.btn_enter_regist);
                        startRegistBtn.setTag("regist");
                        startRegistBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mPagerItemListener.onRightBtnClick();
                            }
                        });


                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(View view) {
                    mViews.add(view);
                }
            }).unsubscribe();


        }
    }

    public interface PagerItemListener {

        void onLeftBtnClick();
        void onRightBtnClick();

    }


}
