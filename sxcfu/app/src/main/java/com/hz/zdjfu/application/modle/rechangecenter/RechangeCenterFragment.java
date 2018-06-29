package com.hz.zdjfu.application.modle.rechangecenter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.RechangeCenterBean;
import com.hz.zdjfu.application.data.bean.RechangeZJZLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.rechangecenter.rechangedetail.RechangeDetailActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.MyAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 *兑换中心
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RechangeCenterFragment extends BaseFragment implements RechangeCenterContract.View {


    @BindView(R.id.rechangecenter_balance_tv)
    TextView rechangecenterZjzbanlanceTv;
    @BindView(R.id.rechangecenter_recycleview)
    RecyclerView rechangecenterRecycleview;
    @BindView(R.id.empty_data_rl)
    RelativeLayout emptyDataRl;
    @BindView(R.id.rechangecenter_sv)
    ScrollView rechangecenterSv;

    private RechangeCenterAdapter mAdapter;
    private List<RechangeCenterBean> mLists;
    private RechangeCenterContract.Presenter mPresenter;
    private UserBean mUserBean;
    private MyAlertDialog mDialog;
    private String mCoinStreamNumber;
    public static RechangeCenterFragment newInstance() {
        return new RechangeCenterFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rechangecenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new RechangeCenterPresenter(mActivity, this);
        initViewData();
        //请求所有可对兑换

        mUserBean = UserInfoManager.getInstance().getLocationUserData();
        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
            if(mPresenter!=null){
                mPresenter.rechangeCenterHttpRequest();
            }
        }

    }

    @Override
    public void showErrorTips(String msg) {
        if(!TextUtils.isEmpty(msg)){
            ToastUtils.show(mActivity,msg);
        }
    }

    @Override
    public void initViewData() {

        mLists = new ArrayList<>();
        mAdapter = new RechangeCenterAdapter(mActivity);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity,2);
        rechangecenterRecycleview.setLayoutManager(gridLayoutManager);
        rechangecenterRecycleview.setAdapter(mAdapter);
        rechangecenterRecycleview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if(StringUtils.isFastClick()){
                    return;
                }
                List<RechangeCenterBean> lists = adapter.getData();
                if (lists != null && lists.size() > 0) {
                    RechangeCenterBean mBean = mLists.get(position);
                    if(!TextUtils.isEmpty(mCoinStreamNumber)){
                        if(Double.parseDouble(mBean.getCoinCost())>Double.parseDouble(mCoinStreamNumber)){
                            ToastUtils.show(mActivity,"你的正经值余额不足！");
                            return;
                        }
                    }
                    //兑换
                    isSureRechangeDialog(mBean);
                }
            }
        });
        rechangecenterSv.smoothScrollTo(0,0);

    }

    @Override
    public void showDateEmptyView(boolean isShow) {


    }

    @Override
    public void setPresenter(RechangeCenterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showData(RechangeZJZLists lists) {

        if(lists==null){
            emptyDataRl.setVisibility(View.GONE);
            rechangecenterRecycleview.setVisibility(View.GONE);
            return;
        }

        if(!TextUtils.isEmpty(lists.getCoinBalance())){
            showsCoinStreamNumber(lists.getCoinBalance());
        }
        List<RechangeCenterBean> mlists = lists.getGoodsList();


        if(lists==null||mlists==null||mlists.isEmpty()){
            emptyDataRl.setVisibility(View.GONE);
            rechangecenterRecycleview.setVisibility(View.GONE);
        }else{
            emptyDataRl.setVisibility(View.GONE);
            rechangecenterRecycleview.setVisibility(View.VISIBLE);
            mLists.addAll(mlists);
            mAdapter.setNewData(mLists);
        }
    }


    @Override
    public void rechangeState(String balance) {

        ToastUtils.show(mActivity,"兑换成功!");
        showsCoinStreamNumber(balance);
    }

    @Override
    public void isSureRechangeDialog(RechangeCenterBean bean) {
        if(bean==null){
            return;
        }
         mDialog = new MyAlertDialog(mActivity,"", "确定消耗" + bean.getCoinCost() + "正经值进行兑换", new MyAlertDialog.ConfirmListener() {
            @Override
            public void callback() {
                mDialog.dismiss();
                if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                    mPresenter.rechangeHttpRequest(bean.getId()+"");
                }
            }
        }, new MyAlertDialog.CancelListener() {
            @Override
            public void callback() {
                mDialog.dismiss();
            }
        });
        mDialog.show();

    }

    @Override
    public void showsCoinStreamNumber(String num) {
        if(!TextUtils.isEmpty(num)){
            this.mCoinStreamNumber =num;
            rechangecenterZjzbanlanceTv.setText(num);
        }
    }


    @OnClick(R.id.rechangecenter_user_detail_tv)
    public void onViewClicked() {
        startActivity(RechangeDetailActivity.makeIntent(mActivity, null));
    }

}
