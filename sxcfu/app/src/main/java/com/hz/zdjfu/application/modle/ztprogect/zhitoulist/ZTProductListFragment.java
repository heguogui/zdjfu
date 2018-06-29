package com.hz.zdjfu.application.modle.ztprogect.zhitoulist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.ZTProductBean;
import com.hz.zdjfu.application.modle.financial.ZTFinancialAdapter;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.CustomRefreshFooterView;
import com.hz.zdjfu.application.widget.view.CustomRefreshHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 * 直投履约中列表
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ZTProductListFragment extends BaseFragment implements ZTProductListContract.View {

    private static final String TAG = ZTProductListFragment.class.getName();
    @BindView(R.id.ztproduct_recycleview)
    RecyclerView ztproductRecycleview;
    @BindView(R.id.ztproduct_refreshview)
    XRefreshView ztproductRefreshview;

    private ZTProductListContract.Presenter mPresenter;
    private ZTProductListAdapter mAdapter;
    private List<ZTProductBean> mList;

    public static ZTProductListFragment newInstance() {
        return new ZTProductListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zt_productlist;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new ZTProductPresenter(this, getActivity());
        initRefreshView();
        initRecycleView();

    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.ztProductListRequest(true);
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
    public void setPresenter(ZTProductListContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initRecycleView() {

        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ztproductRecycleview.setLayoutManager(manager);
        mAdapter = new ZTProductListAdapter(mActivity, mList);
        ztproductRecycleview.setAdapter(mAdapter);


    }

    @Override
    public void initRefreshView() {

        ztproductRefreshview.setAutoLoadMore(false);
        ztproductRefreshview.setPinnedTime(1000);
        ztproductRefreshview.setMoveForHorizontal(true);
        ztproductRefreshview.setPullLoadEnable(true);
        ztproductRefreshview.setPullLoadEnable(true);
        ztproductRefreshview.setPullRefreshEnable(true);
        ztproductRefreshview.enableRecyclerViewPullUp(true);
        ztproductRefreshview.enablePullUpWhenLoadCompleted(true);
        ztproductRefreshview.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        ztproductRefreshview.setCustomFooterView(new CustomRefreshFooterView(mActivity));
        ztproductRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                mPresenter.ztProductListRequest(true);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                mPresenter.ztProductListRequest(false);
            }
        });

    }

    @Override
    public void showProductListData(List<ZTProductBean> list, boolean isrefresh) {
        if (list == null) {
            return;
        }

        if (isrefresh) {
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
            mAdapter = new ZTProductListAdapter(mActivity, mList);
            ztproductRecycleview.setAdapter(mAdapter);
        } else {
            mAdapter.setData(mList);
            mAdapter.notifyDataSetChanged();
        }
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
    public void completeRefresh() {
        if (ztproductRefreshview != null) {
            ztproductRefreshview.stopRefresh();
        }
    }

    @Override
    public void completeLoadMore() {
        if (ztproductRefreshview != null) {
            ztproductRefreshview.stopLoadMore();
        }
    }

    @Override
    public void refresh() {
        if(mPresenter!=null){
            mPresenter.ztProductListRequest(true);
        }
    }


}
