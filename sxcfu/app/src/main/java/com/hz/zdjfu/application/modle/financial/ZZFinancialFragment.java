package com.hz.zdjfu.application.modle.financial;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
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

/**
 * [类功能说明]
 * 债转理财
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ZZFinancialFragment extends BaseFragment implements ZZFinalcialContract.View {

    private static final String TAG = ZZFinancialFragment.class.getName();

    @BindView(R.id.zz_financial_recycleview)
    RecyclerView zzFinancialRecycleview;
    @BindView(R.id.zz_financial_refreshview)
    XRefreshView zzFinancialRefreshview;
    private ZZFinalcialContract.Presenter mPresenter;
    private ZZFinancialAdapter mAdapter;
    private List<ProductBean> mList;

    public static ZZFinancialFragment newInstance() {
        return new ZZFinancialFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zz_financial;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new ZZFinancialPresenter(this, getActivity());
        initRefreshView();
        initRecycleView();
       
    }



    @Override
    public void onResume() {
        super.onResume();
        mPresenter.financialZZRequest(true);
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
    public void setPresenter(ZZFinalcialContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


   

    @Override
    public void initRecycleView() {

        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        zzFinancialRecycleview.setLayoutManager(manager);
        mAdapter = new ZZFinancialAdapter(mActivity, mList);
        zzFinancialRecycleview.setAdapter(mAdapter);


    }

    @Override
    public void initRefreshView() {

        zzFinancialRefreshview.setAutoLoadMore(false);
        zzFinancialRefreshview.setPinnedTime(1000);
        zzFinancialRefreshview.setMoveForHorizontal(true);
        zzFinancialRefreshview.setPullLoadEnable(true);
        zzFinancialRefreshview.setPullLoadEnable(true);
        zzFinancialRefreshview.setPullRefreshEnable(true);
        zzFinancialRefreshview.enableRecyclerViewPullUp(true);
        zzFinancialRefreshview.enablePullUpWhenLoadCompleted(true);
        zzFinancialRefreshview.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        zzFinancialRefreshview.setCustomFooterView(new CustomRefreshFooterView(mActivity));
        zzFinancialRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                mPresenter.financialZZRequest(true);

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                mPresenter.financialZZRequest(false);
            }
        });

    }

    @Override
    public void showZZFinancialData(List<ProductBean> list, boolean isrefresh) {

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
            mAdapter = new ZZFinancialAdapter(mActivity, mList);
            zzFinancialRecycleview.setAdapter(mAdapter);
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
        if (zzFinancialRefreshview != null) {
            zzFinancialRefreshview.stopRefresh();
        }
    }

    @Override
    public void completeLoadMore() {
        if (zzFinancialRefreshview != null) {
            zzFinancialRefreshview.stopLoadMore();
        }
    }

    @Override
    public void refresh() {
        if(mPresenter!=null){
            mPresenter.financialZZRequest(true);
        }
    }


}
