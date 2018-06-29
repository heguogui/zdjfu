package com.hz.zdjfu.application.modle.mine.invitefriend.invitereword;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.InviteRewordBean;
import com.hz.zdjfu.application.data.bean.InviteRewordLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.XXRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 *邀请记录Fragment
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class InviteRewordFragment extends BaseFragment implements InviteRewordContract.View {

    private static final String TAG = InviteRewordFragment.class.getName();
    @BindView(R.id.invitereword_recycleview)
    RecyclerView inviterewordRecycleview;
    @BindView(R.id.invitereword_xrefreshview)
    XXRefreshView inviterewordXrefreshview;
    @BindView(R.id.view_empty_data_rl)
    RelativeLayout viewEmptyDataRl;
    @BindView(R.id.tab_repayment_plan)
    TableLayout vieTabRepaymentPlan;
    @BindView(R.id.invitereword_total_tv)
    TextView inviterewordTotalTv;

    private UserBean mBean;

    private InviteRewordAdapter mAdapter;
    private InviteRewordContract.Presenter mPresenter;
    private List<InviteRewordBean> mLists;

    public static InviteRewordFragment newInstance() {
        return new InviteRewordFragment();
    }


    @Override
    public void showErrorTips(String msg) {
        completeRefreshAndLoad();
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {




    }

    @Override
    public void setPresenter(InviteRewordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initViewData() {

        inviterewordXrefreshview.setAutoLoadMore(false);
        inviterewordXrefreshview.setPinnedTime(1000);
        inviterewordXrefreshview.setMoveForHorizontal(true);
        inviterewordXrefreshview.setPullLoadEnable(true);
        inviterewordXrefreshview.setPullLoadEnable(true);
        inviterewordXrefreshview.setPullRefreshEnable(true);
        inviterewordXrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
                    if (mPresenter != null) {
                        mPresenter.allInviteReWordHttpRequest(mBean.getUserPhone(), true);
                    }
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
                    if (mPresenter != null) {
                        mPresenter.allInviteReWordHttpRequest(mBean.getUserPhone(), false);
                    }
                }
            }
        });


    }

    @Override
    public void showInviteRewordData(InviteRewordLists mlists, boolean isRefresh) {
        //停止刷新
        completeRefreshAndLoad();

        if (mlists == null) {
            vieTabRepaymentPlan.setVisibility(View.GONE);
            viewEmptyDataRl.setVisibility(View.VISIBLE);
            inviterewordRecycleview.setVisibility(View.GONE);
            inviterewordTotalTv.setVisibility(View.GONE);
            return;
        }

        if (isRefresh) {

            if (mLists != null && mLists.size() > 0) {
                mLists.clear();
            }

            if(mlists==null||mlists.getDataList()==null||mlists.getDataList().size()==0){
                viewEmptyDataRl.setVisibility(View.VISIBLE);
                inviterewordRecycleview.setVisibility(View.GONE);
                vieTabRepaymentPlan.setVisibility(View.GONE);
                inviterewordTotalTv.setVisibility(View.GONE);
                return;
            }else{
                viewEmptyDataRl.setVisibility(View.GONE);
                inviterewordRecycleview.setVisibility(View.VISIBLE);
                vieTabRepaymentPlan.setVisibility(View.VISIBLE);
                inviterewordTotalTv.setVisibility(View.VISIBLE);
                if(inviterewordTotalTv!=null){
                    inviterewordTotalTv.setText("您已邀请"+mlists.getTotal()+"位好友");
                }

                mLists.addAll(mlists.getDataList());
            }

        }else{

            if(mlists==null||mlists.getDataList()==null||mlists.getDataList().size()==0){
                ToastUtils.show(mActivity,"没有更多了!");
                return;
            }else{
                mLists.addAll(mlists.getDataList());
            }

        }


        if (mAdapter == null) {
            mAdapter = new InviteRewordAdapter(mActivity);
            mAdapter.setNewData(mLists);
            LinearLayoutManager manager = new LinearLayoutManager(mActivity);
            inviterewordRecycleview.setLayoutManager(manager);
            inviterewordRecycleview.setAdapter(mAdapter);
        } else {
            mAdapter.setNewData(mLists);
        }

    }


    @Override
    public void completeRefreshAndLoad() {

        if (inviterewordXrefreshview != null) {
            inviterewordXrefreshview.stopRefresh();
            inviterewordXrefreshview.stopLoadMore();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invitereword;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new InviteRewordPresenter(mActivity,this);
        mLists = new ArrayList<>();
        initViewData();
        mBean = UserInfoManager.getInstance().getLocationUserData();
        if (mBean != null&&!TextUtils.isEmpty(mBean.getUserPhone())) {
            if(mPresenter!=null){
                mPresenter.allInviteReWordHttpRequest(mBean.getUserPhone(),true);
            }
        }

    }

}
