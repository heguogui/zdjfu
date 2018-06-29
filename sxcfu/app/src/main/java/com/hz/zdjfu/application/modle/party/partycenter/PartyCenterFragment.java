package com.hz.zdjfu.application.modle.party.partycenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.PartyBean;
import com.hz.zdjfu.application.modle.party.oldparty.OldPartyActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.CustomRefreshHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 * 登录界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class PartyCenterFragment extends BaseFragment implements PartyCenterContract.View {

    private static final String TAG = PartyCenterFragment.class.getName();
    @BindView(R.id.partycenter_recycleview)
    RecyclerView partycenterRecycleview;
    @BindView(R.id.partycenter_refreshview)
    XRefreshView partycenterRefreshview;


    private PartyCenterAdapter mPartyCenterAdapter;
    private List<PartyBean> mPartyList;
    private PartyCenterContract.Presenter mPresenter;

    public static PartyCenterFragment newInstance() {
        return new PartyCenterFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_partycenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new PartyCenterPresenter(this, mActivity);
        initRefreshView();
        initRecyclerView();
        mPresenter.partysHttpRequest(true);

    }


    @Override
    public void showErrorTips(String msg) {
        completeRefreshAndLoad();
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }

    }

    @Override
    public void initViewData() {


    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(PartyCenterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void completeRefreshAndLoad() {

        if(partycenterRefreshview!=null){
            partycenterRefreshview.stopLoadMore();
            partycenterRefreshview.stopRefresh();
        }

    }

    @Override
    public void initRecyclerView() {
        mPartyList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        partycenterRecycleview.setLayoutManager(manager);
        mPartyCenterAdapter = new PartyCenterAdapter(mActivity);
        partycenterRecycleview.setAdapter(mPartyCenterAdapter);

        partycenterRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.partycenter_item_image_iv:
                        //h5 跳转查看活动详情
                        List<PartyBean> mLists = adapter.getData();
                        if(mLists!=null&&mLists.size()>0){
                            ToastUtils.show(mActivity,"h5跳转查看活动详情");
                            PartyBean mBean =mLists.get(position);
                        }
                        break;
                    case R.id.partycenter_item_oldparty_rl:

                        startActivity(OldPartyActivity.makeIntent(mActivity,null));

                        break;
                    default:
                        break;
                }

            }
        });

    }

    @Override
    public void initRefreshView() {
        partycenterRefreshview.setAutoLoadMore(false);
        partycenterRefreshview.setPinnedTime(1000);
        partycenterRefreshview.setMoveForHorizontal(true);
        partycenterRefreshview.setPullLoadEnable(true);
        partycenterRefreshview.setPullLoadEnable(true);
        partycenterRefreshview.setPullRefreshEnable(true);
        partycenterRefreshview.enableRecyclerViewPullUp(true);
        partycenterRefreshview.enablePullUpWhenLoadCompleted(true);
        partycenterRefreshview.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        partycenterRefreshview.setCustomFooterView(new XRefreshViewFooter(mActivity));
        partycenterRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                if(mPresenter!=null){
                    mPresenter.partysHttpRequest(true);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if(mPresenter!=null){
                    mPresenter.partysHttpRequest(false);
                }
            }
        });
    }

    @Override
    public void showPartyViewData(List<PartyBean> list, boolean isRefresh) {

        completeRefreshAndLoad();
        if(list==null){
            list = new ArrayList<>();
        }
        if(isRefresh){
            if(mPartyList!=null&&mPartyList.size()>0){
                mPartyList.clear();
            }
        }

        mPartyList.addAll(list);
        if(mPartyCenterAdapter!=null){
            mPartyCenterAdapter.setNewData(mPartyList);
        }

    }

}
