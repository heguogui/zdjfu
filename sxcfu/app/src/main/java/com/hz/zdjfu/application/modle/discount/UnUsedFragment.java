package com.hz.zdjfu.application.modle.discount;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.DiscountZJZBean;
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

public class UnUsedFragment extends BaseFragment implements DiscountContract.View {


    @BindView(R.id.discount_unuse_rv)
    RecyclerView discountUnuseRv;
    @BindView(R.id.view_unuse_empty_data_layout)
    LinearLayout viewUnuseEmptyDataLayout;
    @BindView(R.id.discount_unuse_no_data_rl)
    RelativeLayout discountUnuseNoDataRl;
    private List<DiscountZJZBean> mList;
    private DiscountUnUserAdapter mAdapter;
    private DiscountContract.Presenter mPresenter;
    private UserBean mBean;
    private String income_days;
    private String amount;
    private String  mProduct_id;
    public static UnUsedFragment newInstance() {
        return new UnUsedFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_discount_unused_layout;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new DiscountPresenter(mActivity, this);
        initRecycleView();
        initViewData();
    }

    @Override
    public void showErrorTips(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }
    }

    @Override
    public void initViewData() {

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            income_days = mBundle.getString("INCOMEDAYS");
            amount = mBundle.getString("AMOUNT");
            mProduct_id =mBundle.getString("PRODUCT_ID");

            mBean = UserInfoManager.getInstance().getLocationUserData();
            if (mPresenter != null && mBean != null && !TextUtils.isEmpty(mBean.getUserPhone())&&!TextUtils.isEmpty(mProduct_id)) {
                mPresenter.allRightHttpRequest(mBean.getUserPhone(), income_days, amount,mProduct_id);
            }
        }

    }

    @Override
    public void showDateEmptyView(boolean state) {
        if (state) {
            viewUnuseEmptyDataLayout.setVisibility(View.GONE);
            discountUnuseNoDataRl.setVisibility(View.VISIBLE);
        } else {
            viewUnuseEmptyDataLayout.setVisibility(View.VISIBLE);
            discountUnuseNoDataRl.setVisibility(View.GONE);
        }
    }

    @Override
    public void initViewPager() {

    }

    @Override
    public void initRecycleView() {
        mList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        discountUnuseRv.setLayoutManager(manager);
        mAdapter = new DiscountUnUserAdapter(mActivity);
        discountUnuseRv.setAdapter(mAdapter);
    }

    @Override
    public void showAllDiscountCoupon(DiscountBean mbean) {

        if (mbean == null || mbean.getUnUser() == null) {
            showDateEmptyView(true);
            return;
        }

        mList = mbean.getUnUser();
        //显示title 总数
        try {
            if (mList!=null&&mList.size()>0) {
                ((DiscountActivity)mActivity).refreshRight(mList.size());
                showDateEmptyView(false);
            } else {
                ((DiscountActivity)mActivity).refreshRight(0);
                showDateEmptyView(true);
            }
        } catch (Exception e) {

        }

        if (mAdapter == null) {
            mAdapter = new DiscountUnUserAdapter(mActivity);
        }
        if(mAdapter!=null) {
            mAdapter.setNewData(mList);
        }

    }


    @Override
    public void setPresenter(DiscountContract.Presenter presenter) {
        this.mPresenter =presenter;
    }



}
