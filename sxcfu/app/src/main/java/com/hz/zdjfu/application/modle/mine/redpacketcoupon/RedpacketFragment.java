package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.RedPacketLists;
import com.hz.zdjfu.application.data.bean.RedpacketBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RedpacketFragment extends BaseFragment implements RedpacketContract.View{


    @BindView(R.id.redpacket_recycleview)
    RecyclerView redpacketRecycleview;
    @BindView(R.id.redpacket_refreshview)
    XRefreshView redpacketRefreshview;

    private RedpacketAdapter mAdapter;
    private RedpacketContract.Presenter mRedpacketPresenter;
    private List<RedpacketBean> mList;
    private UserBean mBean;
    private int mTotalRedPacketNum;


    public static RedpacketFragment newInstance() {
        return new RedpacketFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_redpacket_layout;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new RedpacketPresenter(mActivity,this);
        initRefreshView();
        initRecycleView();
        initViewData();
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
    public void initViewData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mRedpacketPresenter!=null&&mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
            mRedpacketPresenter.redpacketHttpRequest(mBean.getUserPhone(),true);
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
    public void setPresenter(RedpacketContract.Presenter presenter) {
        this.mRedpacketPresenter =presenter;
    }

    @Override
    public void initRefreshView() {
        redpacketRefreshview.setAutoLoadMore(false);
        redpacketRefreshview.setPinnedTime(1000);
        redpacketRefreshview.setMoveForHorizontal(true);
        redpacketRefreshview.setPullLoadEnable(true);
        redpacketRefreshview.setPullLoadEnable(true);
        redpacketRefreshview.setPullRefreshEnable(true);
        redpacketRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {

                if(!TextUtils.isEmpty(mBean.getUserPhone())){
                    mRedpacketPresenter.redpacketHttpRequest(mBean.getUserPhone(),true);
                }

            }

            @Override
            public void onLoadMore(boolean isSilence) {

                if(mList!=null&&mList.size()>0&&mList.size()==mTotalRedPacketNum){//等于总数了就不用再加载更多
                    showDateEmptyView(false);
                }else{
                    if(!TextUtils.isEmpty(mBean.getUserPhone())){
                        mRedpacketPresenter.redpacketHttpRequest(mBean.getUserPhone(),false);
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
        redpacketRecycleview.setLayoutManager(manager);
        mAdapter = new RedpacketAdapter(mActivity,mList);
        redpacketRecycleview.setAdapter(mAdapter);

//        redpacketRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
//            @Override
//            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//                EventBus.getDefault().post(new MainFromTagEven("REDPACKFRAGMENT"));
//                mActivity.finish();
//            }
//        });

    }

    @Override
    public void completeRefresh() {
        if(redpacketRefreshview!=null){
            redpacketRefreshview.stopRefresh();
        }
    }

    @Override
    public void completeLoadMore() {
        if(redpacketRefreshview!=null){
            redpacketRefreshview.stopLoadMore();
        }
    }

    @Override
    public void showRedPacketData(List<RedpacketBean> lists, boolean isrefresh) {

        if(lists==null){
            return;
        }


        if(isrefresh){
            if(mList!=null&&mList.size()>0){
                mList.clear();
            }
            mList =lists;
            completeRefresh();
        }else{
            mList.addAll(lists);
            completeLoadMore();
        }

        if(mAdapter==null){
            mAdapter = new RedpacketAdapter(mActivity,mList);
            redpacketRecycleview.setAdapter(mAdapter);
        }else{
            mAdapter.setData(mList);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setAllTotalNum(int num) {
        this.mTotalRedPacketNum =num;
    }

    @Override
    public void setEnableUsedNum(int num) {
        try{
            ((RedpacketCouponActivity)mActivity).refreshRedpacketData(num);
        }catch (Exception e){

        }
    }

    @Override
    public void showCoinNum(String num) {
        if(mActivity!=null){
           //((RedpacketCouponActivity)mActivity).showCoinNum(num);
        }
    }
}
