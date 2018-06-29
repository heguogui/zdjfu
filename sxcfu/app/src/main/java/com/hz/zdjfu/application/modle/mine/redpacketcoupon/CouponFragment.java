package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.AddRateLists;
import com.hz.zdjfu.application.data.bean.CouponBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 * 卡券
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class CouponFragment extends BaseFragment implements CouponContract.View {

    @BindView(R.id.coupon_recycleview)
    RecyclerView couponRecycleview;
    @BindView(R.id.coupon_refreshview)
    XRefreshView couponRefreshview;

    private List<CouponBean> mList;
    private CouponAdapter mAdapter;
    private CouponContract.Presenter mPresenter;
    private UserBean mBean;
    private int mTotalCouponNum;
    public static CouponFragment newInstance() {
        return new CouponFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon_layout;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new CouponPresenter(mActivity,this);
        initRefreshView();
        initRecycleView();

    }

    @Override
    public void showErrorTips(String msg) {
        completeRefresh();
        completeLoadMore();
        if(!TextUtils.isEmpty(msg)){
            ToastUtils.show(mActivity,msg+"");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initViewData();
    }

    @Override
    public void initViewData() {

        if(mPresenter!=null){
            mPresenter.couponHttpRequest(true);
        }

    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {
        completeRefresh();
        completeLoadMore();
        if(isRefresh){
            if(mAdapter!=null){
                mAdapter.notifyDataSetChanged();
            }
        }else{
            ToastUtils.show(mActivity,"没有了更多");
        }

    }

    @Override
    public void setPresenter(CouponContract.Presenter presenter) {
        this.mPresenter =presenter;
    }

    @Override
    public void initRefreshView() {

        couponRefreshview.setAutoLoadMore(false);
        couponRefreshview.setPinnedTime(1000);
        couponRefreshview.setMoveForHorizontal(true);
        couponRefreshview.setPullLoadEnable(true);
        couponRefreshview.setPullLoadEnable(true);
        couponRefreshview.setPullRefreshEnable(true);
        couponRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {

                if(mPresenter!=null){
                    mPresenter.couponHttpRequest(true);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {


                if(mList!=null&&mList.size()>0&&mList.size()==mTotalCouponNum){//等于总数了就不用再加载更多
                    showDateEmptyView(false);
                }else{
                    if(!TextUtils.isEmpty(mBean.getUserPhone())){
                        if(mPresenter!=null){
                            mPresenter.couponHttpRequest(false);
                        }
                    }
                }

            }
        });

    }

    @Override
    public void initRecycleView() {
        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        couponRecycleview.setLayoutManager(manager);
        mAdapter = new CouponAdapter(mActivity,mList);
        couponRecycleview.setAdapter(mAdapter);

    }

    @Override
    public void showCouponData(List<CouponBean> mlists, boolean isrefresh) {

        if(mlists==null){
            return;
        }


        if(isrefresh){
            if(mList!=null&&mList.size()>0){
                mList.clear();
            }
            mList =mlists;
            completeRefresh();
        }else{
            mList.addAll(mlists);
            completeLoadMore();
        }
        if(mAdapter==null){
            mAdapter = new CouponAdapter(mActivity,mList);
            couponRecycleview.setAdapter(mAdapter);
        }else{
            mAdapter.setData(mList);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setAllTotalNum(int num) {
        this.mTotalCouponNum =num;
    }

    @Override
    public void setEnableUsedNum(int num) {
        try{
            ((RedpacketCouponActivity)mActivity).refreshCouponData(num);
        }catch (Exception e){

        }
    }


    @Override
    public void completeRefresh() {
        if(couponRefreshview!=null){
            couponRefreshview.stopRefresh();
        }
    }

    @Override
    public void completeLoadMore() {
        if(couponRefreshview!=null){
            couponRefreshview.stopLoadMore();
        }
    }

}
