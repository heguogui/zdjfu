package com.hz.zdjfu.application.modle.financial;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.ProductBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.ZTProductBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
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
 * 债转理财
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class ZTFinancialFragment extends BaseFragment implements ZTFinancialContract.View {

    private static final String TAG = ZTFinancialFragment.class.getName();
    @BindView(R.id.ztfinancial_recycleview)
    RecyclerView ztfinancialRecycleview;
    @BindView(R.id.ztfinancial_refreshview)
    XRefreshView ztfinancialRefreshview;

    private ZTFinancialContract.Presenter mPresenter;
    private ZTFinancialAdapter mAdapter;
    private List<ZTProductBean> mList;
    private UserBean mBean;
    private int mTotalCount;

    public static ZTFinancialFragment newInstance() {
        return new ZTFinancialFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zt_financial;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new ZTFinancialPresenter(this, getActivity());
        mBean = UserInfoManager.getInstance().getUserBean();
        initRefreshView();
        initRecycleView();

    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        if (!hidden) {
//            //请求产品
//            mPresenter.financialZTRequest("",true);
//        }
//        super.onHiddenChanged(hidden);
//
//    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.financialZTRequest("",true);
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
    public void setPresenter(ZTFinancialContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initRecycleView() {

        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ztfinancialRecycleview.setLayoutManager(manager);
        mAdapter = new ZTFinancialAdapter(mActivity, mList);
        ztfinancialRecycleview.setAdapter(mAdapter);


    }

    @Override
    public void initRefreshView() {

        ztfinancialRefreshview.setAutoLoadMore(false);
        ztfinancialRefreshview.setPinnedTime(1000);
        ztfinancialRefreshview.setMoveForHorizontal(true);
        ztfinancialRefreshview.setPullLoadEnable(true);
        ztfinancialRefreshview.setPullLoadEnable(true);
        ztfinancialRefreshview.setPullRefreshEnable(true);
        ztfinancialRefreshview.enableRecyclerViewPullUp(true);
        ztfinancialRefreshview.enablePullUpWhenLoadCompleted(true);
        ztfinancialRefreshview.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        ztfinancialRefreshview.setCustomFooterView(new CustomRefreshFooterView(mActivity));
        ztfinancialRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                mPresenter.financialZTRequest("",true);
            }

            @Override
            public void onLoadMore(boolean isSilence) {

                if(mList!=null&&mList.size()<mTotalCount&&mTotalCount!=0){
                    mPresenter.financialZTRequest("",false);
                }else{
                    ToastUtils.show(mActivity,"没有更多数据");
                    completeLoadMore();
                }

            }
        });

    }

    @Override
    public void showZTFinancialData(List<ZTProductBean> list, boolean isrefresh) {
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

        if(mList.size()==mTotalCount){
            ZTProductBean bean =new ZTProductBean();
            bean.setId(-1);
            mList.add(bean);
        }
//        if(mList.size()==10){
//            ZTProductBean bean =new ZTProductBean();
//            bean.setId(-1);
//            mList.add(bean);
//        }

        if (mAdapter == null) {
            mAdapter = new ZTFinancialAdapter(mActivity, mList);
            ztfinancialRecycleview.setAdapter(mAdapter);
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
        if (ztfinancialRefreshview != null) {
            ztfinancialRefreshview.stopRefresh();
        }
    }

    @Override
    public void completeLoadMore() {
        if (ztfinancialRefreshview != null) {
            ztfinancialRefreshview.stopLoadMore();
        }
    }

    @Override
    public void refresh() {
        if(mPresenter!=null){
            mPresenter.financialZTRequest("",true);
        }
    }

    @Override
    public void setTotalCount(int num) {
        this.mTotalCount =num;
    }


}
