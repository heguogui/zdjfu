package com.hz.zdjfu.application.modle.ztprogect.discount;

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
import com.hz.zdjfu.application.data.bean.ZTCouponBean;
import com.hz.zdjfu.application.data.bean.ZTGiftBean;
import com.hz.zdjfu.application.data.bean.ZTUnUserCouponBean;
import com.hz.zdjfu.application.data.bean.ZTUserCouponBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.discount.DiscountActivity;
import com.hz.zdjfu.application.modle.discount.DiscountContract;
import com.hz.zdjfu.application.modle.discount.DiscountPresenter;
import com.hz.zdjfu.application.modle.discount.DiscountUnUserAdapter;
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

public class ZTUnUsedFragment extends BaseFragment implements ZTDiscountContract.View {


    @BindView(R.id.discount_unuse_rv)
    RecyclerView discountUnuseRv;
    @BindView(R.id.view_unuse_empty_data_layout)
    LinearLayout viewUnuseEmptyDataLayout;
    @BindView(R.id.discount_unuse_no_data_rl)
    RelativeLayout discountUnuseNoDataRl;
    private List<DiscountZJZBean> mList;
    private ZTDiscountUnUserAdapter mAdapter;
    private ZTDiscountContract.Presenter mPresenter;
    private String amount;
    private String  mProduct_id;
    public static ZTUnUsedFragment newInstance() {
        return new ZTUnUsedFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_discount_unused_layout;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new ZTDiscountPresenter(mActivity, this);
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
            amount = mBundle.getString("AMOUNT");
            mProduct_id =mBundle.getString("PRODUCT_ID");
            if (mPresenter != null &&!TextUtils.isEmpty(mProduct_id)&&!TextUtils.isEmpty(amount)) {
                mPresenter.allRightHttpRequest(mProduct_id,amount);
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
        mAdapter = new ZTDiscountUnUserAdapter(mActivity);
        discountUnuseRv.setAdapter(mAdapter);
    }

    @Override
    public void showLeftCoupon(ZTUserCouponBean mbean) {

    }

    @Override
    public void showRightCoupon(ZTUnUserCouponBean mZTUnUserCouponBean) {

        if (mZTUnUserCouponBean == null) {
            showDateEmptyView(true);
            return;
        }


        //由于债转系统和直投系统不同 此处对债转相关类封装后运用到直投 保留原有逻辑
        DiscountBean mbean =packageRightCoupon(mZTUnUserCouponBean);
        if(mbean==null||mbean.getUnUser()==null){
            return;
        }
        mList = mbean.getUnUser();

        //显示title 总数
        try {
            if (mList!=null&&mList.size()>0) {
                ((ZTDiscountActivity)mActivity).refreshRight(mList.size());
                showDateEmptyView(false);
            } else {
                ((ZTDiscountActivity)mActivity).refreshRight(0);
                showDateEmptyView(true);
            }
        } catch (Exception e) {

        }

        if (mAdapter == null) {
            mAdapter = new ZTDiscountUnUserAdapter(mActivity);
        }
        if(mAdapter!=null) {
            mAdapter.setNewData(mList);
        }

    }

    @Override
    public DiscountBean packageLeftCoupon(ZTUserCouponBean mbean) {
        return null;
    }

    @Override
    public DiscountBean packageRightCoupon(ZTUnUserCouponBean mZTUnUserCouponBean) {
        DiscountBean mDiscountBean = new DiscountBean();
        List<DiscountZJZBean>  unUseCoupon = new ArrayList<>();

        //添加加息券
        List<ZTCouponBean>  mCoupon=mZTUnUserCouponBean.getCouponList();
        if(mCoupon!=null&&mCoupon.size()>0){
            for (int i=0 ;i<mCoupon.size();i++){
                ZTCouponBean bean=mCoupon.get(i);
                DiscountZJZBean mBean = new DiscountZJZBean();
                mBean.setAmount(bean.getInterest());
                mBean.setDates(bean.getStartDate()+"至"+bean.getEndDate());
                mBean.setInvest_amount(bean.getMinAmount());
                mBean.setInvest_dates(bean.getMinDays()+"");
                mBean.setName(bean.getCouponName());
                mBean.setNode_id(bean.getCouponId()+"");
                if(bean.isWillOut()){
                    mBean.setOverdue("1");
                }
                mBean.setType(2+"");
                mBean.setUse_type(bean.getUseType()+"");
                mBean.setStrAmount(bean.getUseProdType()+"");
                unUseCoupon.add(mBean);
            }
        }

        //添加红包
        List<ZTGiftBean>  mGift= mZTUnUserCouponBean.getGiftList();
        if(mGift!=null&&mGift.size()>0){
            for (int i=0 ;i<mGift.size();i++){
                ZTGiftBean bean=mGift.get(i);
                DiscountZJZBean mBean = new DiscountZJZBean();
                mBean.setAmount(bean.getAmount());
                mBean.setDates(bean.getStartDate()+"至"+bean.getEndDate());
                mBean.setInvest_amount(bean.getMinAmount()+"");
                mBean.setInvest_dates(bean.getMinDays()+"");
                mBean.setName(bean.getGiftName());
                mBean.setNode_id(bean.getGiftId()+"");
                if(bean.isWillOut()){
                    mBean.setOverdue("1");
                }
                mBean.setType(1+"");
                mBean.setUse_type(bean.getUseType()+"");
                mBean.setStrAmount(bean.getUseProdType()+"");
                unUseCoupon.add(mBean);
            }
        }
        mDiscountBean.setUnUser(unUseCoupon);
        return mDiscountBean;
    }

    @Override
    public List<DiscountZJZBean> packageRecommendCoupon(List<DiscountZJZBean> mlist) {
        return null;
    }


    @Override
    public void setPresenter(ZTDiscountContract.Presenter presenter) {
        this.mPresenter =presenter;
    }



}
