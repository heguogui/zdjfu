package com.hz.zdjfu.application.modle.party.noobcontest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.NoobContestBean;
import com.hz.zdjfu.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * [类功能说明]
 * 新手赢奖界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class NoobContestFragment extends BaseFragment implements NoobContestContract.View {

    private static final String TAG = NoobContestFragment.class.getName();
    @BindView(R.id.noobcontest_recycleview)
    RecyclerView noobcontestRecycleview;
    @BindView(R.id.noobcontest_scrollview)
    ScrollView noobcontestScrollview;



    private NoobContestAdapter mNoobContestAdapter;
    private List<NoobContestBean> mNoobContestList;
    private NoobContestContract.Presenter mPresenter;

    public static NoobContestFragment newInstance() {
        return new NoobContestFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_noobcontest;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new NoobContestPresenter(this, mActivity);
        initRecyclerView();
        mPresenter.noobContestHttpRequest();
        noobcontestScrollview.smoothScrollTo(0,0);
    }


    @Override
    public void showErrorTips(String msg) {
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
    public void setPresenter(NoobContestContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initRecyclerView() {
        mNoobContestList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        noobcontestRecycleview.setLayoutManager(manager);
        mNoobContestAdapter = new NoobContestAdapter(mActivity);
        noobcontestRecycleview.setAdapter(mNoobContestAdapter);
        noobcontestRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.noobcontest_item_state) {
                    List<NoobContestBean> mLists = adapter.getData();
                    if (mLists != null && mLists.size() > 0) {
                        NoobContestBean mBean = mLists.get(position);
                        //跳转
                        ToastUtils.show(mActivity, "点击了" + position);
                    }
                }
            }
        });
    }

    @Override
    public void showNoobContestData(List<NoobContestBean> list) {

        if (list == null) {
            return;
        }
        mNoobContestList.addAll(list);
        if (mNoobContestAdapter != null) {
            mNoobContestAdapter.setNewData(mNoobContestList);
        }
    }

    @Override
    public void showCompleteResult(boolean state) {


    }

    @Override
    public void onClickTopRightView() {
        ToastUtils.show(mActivity, "点击了左边");
    }

}
