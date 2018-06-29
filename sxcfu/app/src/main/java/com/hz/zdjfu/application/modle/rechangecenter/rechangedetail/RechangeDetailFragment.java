package com.hz.zdjfu.application.modle.rechangecenter.rechangedetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.RechangeDetailBean;
import com.hz.zdjfu.application.data.bean.RechangeDetailList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 * 正经值兑换详情Fragment
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class RechangeDetailFragment extends BaseFragment implements RechangeDetailContract.View {

    private static final String TAG = RechangeDetailFragment.class.getName();
    @BindView(R.id.rechangedetail_recycleview)
    RecyclerView rechangedetailRecycleview;
    @BindView(R.id.rechangedetail_refreshview)
    XRefreshView rechangedetailRefreshview;


    private RechangeDetailAdapter mAdapter;
    private RechangeDetailContract.Presenter mPresenter;
    private List<RechangeDetailBean> mLists;
    private UserBean mBean;
    public static RechangeDetailFragment newInstance() {
        return new RechangeDetailFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        completeRefreshAndLoad();
        if(!TextUtils.isEmpty(msg)){
            ToastUtils.show(mActivity,msg+"");
        }
    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {
        completeRefreshAndLoad();
        if(isRefresh){
            ToastUtils.show(mActivity,"暂无数据");
            if(mAdapter!=null){
                mAdapter.notifyDataSetChanged();
            }
        }else{
            ToastUtils.show(mActivity,"没有了更多");
        }
    }

    @Override
    public void setPresenter(RechangeDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initViewData() {


        mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean==null){
            return;
        }
        if(!TextUtils.isEmpty(mBean.getUserPhone())){
            if(mPresenter!=null){
                mPresenter.rechangeDetailHttpRequest(mBean.getUserPhone(),true);
            }
        }


    }

    @Override
    public void initRecycleView() {

        mLists = new ArrayList<>();
        mAdapter = new RechangeDetailAdapter(mActivity,mLists);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rechangedetailRecycleview.setLayoutManager(manager);
        rechangedetailRecycleview.setAdapter(mAdapter);
    }

    @Override
    public void initRefreshView() {

        rechangedetailRefreshview.setAutoLoadMore(false);
        rechangedetailRefreshview.setPinnedTime(1000);
        rechangedetailRefreshview.setMoveForHorizontal(true);
        rechangedetailRefreshview.setPullLoadEnable(true);
        rechangedetailRefreshview.setPullLoadEnable(true);
        rechangedetailRefreshview.setPullRefreshEnable(true);

        rechangedetailRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {

                if(!TextUtils.isEmpty(mBean.getUserPhone())){
                    mPresenter.rechangeDetailHttpRequest(mBean.getUserPhone(),true);
                }

            }

            @Override
            public void onLoadMore(boolean isSilence) {

                if(!TextUtils.isEmpty(mBean.getUserPhone())){
                    mPresenter.rechangeDetailHttpRequest(mBean.getUserPhone(),false);
                }
            }
        });


    }

    @Override
    public void showRechangeDetailData(List<RechangeDetailBean> mlists, boolean isRefresh) {

        //停止刷新
        completeRefreshAndLoad();

        if (mlists == null) {
            return;
        }

        if (isRefresh) {
            if (mLists != null && mLists.size() > 0) {
                mLists.clear();
            }
        }

        mLists.addAll(mlists);
        mAdapter.setData(mLists);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void completeRefreshAndLoad() {

        if (rechangedetailRefreshview != null) {
            rechangedetailRefreshview.stopRefresh();
            rechangedetailRefreshview.stopLoadMore();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rechangedetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new RechangeDetailPresenter(mActivity, this);
        initRecycleView();
        initRefreshView();
        initViewData();


    }

}
