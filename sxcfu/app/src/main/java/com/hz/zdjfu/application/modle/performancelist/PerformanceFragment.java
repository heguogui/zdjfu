package com.hz.zdjfu.application.modle.performancelist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.CustomRefreshFooterView;
import com.hz.zdjfu.application.widget.view.CustomRefreshHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * [类功能说明]
 * 履约中列表
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class PerformanceFragment extends BaseFragment implements PerformanceContract.View {

    private static final String TAG = PerformanceFragment.class.getName();
    @BindView(R.id.performance_zt_rb)
    RadioButton performanceZtRb;
    @BindView(R.id.performance_zz_rb)
    RadioButton performanceZzRb;
    @BindView(R.id.performance_rg)
    RadioGroup performanceRg;
    @BindView(R.id.performance_recycleview)
    RecyclerView performanceRecycleview;
    @BindView(R.id.performance_refreshview)
    XRefreshView performanceRefreshview;

    private boolean performanceZzRb_state =false;
    private boolean performanceZtRb_state =false;



    private PerformanceContract.Presenter mPresenter;
    private PerformanceAdapter mAdapter;
    private List<ProductBean> mList;

    public static PerformanceFragment newInstance() {
        return new PerformanceFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_performance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new PerformancePresenter(this, getActivity());
        initRefreshView();
        initRecycleView();
        onClickListener();
        initData();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            //请求产品
            mPresenter.getPerformanceListData(true);
        }
        super.onHiddenChanged(hidden);

    }

    @Override
    public void showErrorTips(String msg) {
        completeRefresh();
        completeLoadMore();
        if (TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, "" + msg);
        }
    }

    @Override
    public void initViewData() {

    }

    @Override
    public void setPresenter(PerformanceContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initData() {

    }

    @Override
    public void initRecycleView() {

        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        performanceRecycleview.setLayoutManager(manager);
        mAdapter = new PerformanceAdapter(mActivity, mList);
        performanceRecycleview.setAdapter(mAdapter);


    }

    @Override
    public void initRefreshView() {

        performanceRefreshview.setAutoLoadMore(false);
        performanceRefreshview.setPinnedTime(1000);
        performanceRefreshview.setMoveForHorizontal(true);
        performanceRefreshview.setPullLoadEnable(true);
        performanceRefreshview.setPullLoadEnable(true);
        performanceRefreshview.setPullRefreshEnable(true);
        performanceRefreshview.enableRecyclerViewPullUp(true);
        performanceRefreshview.enablePullUpWhenLoadCompleted(true);
        performanceRefreshview.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        performanceRefreshview.setCustomFooterView(new CustomRefreshFooterView(mActivity));
        performanceRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                mPresenter.getPerformanceListData(true);

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                mPresenter.getPerformanceListData(false);
            }
        });

    }

    @Override
    public void setEmptyData() {

    }


    @Override
    public void showFinancialListsData(List<ProductBean> list, boolean isRefer) {

        if (list == null) {
            return;
        }

        if (isRefer) {
            if (mList != null && mList.size() > 0) {
                mList.clear();
            }
            mList = list;
            completeRefresh();
        } else {
            mList.addAll(list);
            completeLoadMore();
        }

        if (mAdapter == null) {
            mAdapter = new PerformanceAdapter(mActivity, mList);
            performanceRecycleview.setAdapter(mAdapter);
        } else {
            mAdapter.setData(mList);
            mAdapter.notifyDataSetChanged();
        }


    }


    @Override
    public void onClickListener() {


        performanceZtRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                performanceZtRb_state =state;
                Log.i(TAG,"performanceZtRb_state="+performanceZtRb_state);
                if(state){

                }
            }
        });

        performanceZzRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                performanceZzRb_state =state;
                Log.i(TAG,"performanceZzRb_state="+performanceZzRb_state);
                if(state){

                }
            }
        });



    }

    @Override
    public void showDateEmptyView(boolean state) {
        completeRefresh();
        completeLoadMore();
        if (state) {
            ToastUtils.show(mActivity, "暂无数据");
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        } else {
            ToastUtils.show(mActivity, "没有了更多");
        }
    }

    @Override
    public void networkIsConnected() {

    }

    @Override
    public void completeRefresh() {
        if (performanceRefreshview != null) {
            performanceRefreshview.stopRefresh();
        }
    }

    @Override
    public void completeLoadMore() {
        if (performanceRefreshview != null) {
            performanceRefreshview.stopLoadMore();
        }
    }


  
}
