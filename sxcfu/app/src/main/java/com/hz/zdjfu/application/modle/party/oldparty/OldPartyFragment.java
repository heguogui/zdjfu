package com.hz.zdjfu.application.modle.party.oldparty;

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
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.CustomRefreshHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 * 往期活动界面
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class OldPartyFragment extends BaseFragment implements OldPartyContract.View {

    private static final String TAG = OldPartyFragment.class.getName();
    @BindView(R.id.oldparty_recycleview)
    RecyclerView oldpartyRecycleview;
    @BindView(R.id.oldparty_refreshview)
    XRefreshView oldpartyRefreshview;



    private OldPartyAdapter mOldPartyAdapter;
    private List<PartyBean> mPartyList;
    private OldPartyContract.Presenter mPresenter;

    public static OldPartyFragment newInstance() {
        return new OldPartyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oldparty;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new OldPartyPresenter(this, mActivity);
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
    public void setPresenter(OldPartyContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void completeRefreshAndLoad() {

        if (oldpartyRefreshview != null) {
            oldpartyRefreshview.stopLoadMore();
            oldpartyRefreshview.stopRefresh();
        }

    }

    @Override
    public void initRecyclerView() {
        mPartyList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        oldpartyRecycleview.setLayoutManager(manager);
        mOldPartyAdapter = new OldPartyAdapter(mActivity);
        oldpartyRecycleview.setAdapter(mOldPartyAdapter);

        oldpartyRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.oldparty_item_image_iv:
                        //h5 跳转查看活动详情
                        List<PartyBean> mLists = adapter.getData();
                        if (mLists != null && mLists.size() > 0) {
                            ToastUtils.show(mActivity, "h5跳转查看活动详情");
                            PartyBean mBean = mLists.get(position);
                        }
                        break;
                    case R.id.oldparty_item_oldparty_rl:
                        //查看当前的活动
                        mActivity.finish();
                        break;
                    default:
                        break;
                }

            }
        });

    }

    @Override
    public void initRefreshView() {

        oldpartyRefreshview.setAutoLoadMore(false);
        oldpartyRefreshview.setPinnedTime(1000);
        oldpartyRefreshview.setMoveForHorizontal(true);
        oldpartyRefreshview.setPullLoadEnable(true);
        oldpartyRefreshview.setPullLoadEnable(true);
        oldpartyRefreshview.setPullRefreshEnable(true);
        oldpartyRefreshview.enableRecyclerViewPullUp(true);
        oldpartyRefreshview.enablePullUpWhenLoadCompleted(true);
        oldpartyRefreshview.setCustomHeaderView(new CustomRefreshHeadView(mActivity));
        oldpartyRefreshview.setCustomFooterView(new XRefreshViewFooter(mActivity));
        oldpartyRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                if (mPresenter != null) {
                    mPresenter.partysHttpRequest(true);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if (mPresenter != null) {
                    mPresenter.partysHttpRequest(false);
                }
            }
        });
    }

    @Override
    public void showPartyViewData(List<PartyBean> list, boolean isRefresh) {

        completeRefreshAndLoad();
        if (list == null) {
            list = new ArrayList<>();
        }
        if (isRefresh) {
            if (mPartyList != null && mPartyList.size() > 0) {
                mPartyList.clear();
            }
        }
        mPartyList.addAll(list);
        if (mOldPartyAdapter != null) {
            mOldPartyAdapter.setNewData(mPartyList);
        }

    }

}
