package com.hz.zdjfu.application.modle.financial;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ViewFlipper;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.FinancialMarqueeBean;
import com.hz.zdjfu.application.data.bean.PublicNoticeRecordsBean;
import com.hz.zdjfu.application.modle.adapter.FragmentPagerAdapter;
import com.hz.zdjfu.application.modle.mine.redpacketcoupon.RedpacketCouponFragment;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.FinancialMarqueeView;
import com.hz.zdjfu.application.widget.view.MarqueeView;
import com.hz.zdjfu.application.widget.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;




/**
 * [类功能说明]
 * 理财
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class FinancialFragment extends BaseFragment implements FinancialContract.View {

    private static final String TAG = FinancialFragment.class.getName();

    @BindView(R.id.financial_viewflipper)
    FinancialMarqueeView financialViewflipper;
    @BindView(R.id.financial_zt_rb)
    RadioButton financialZtRb;
    @BindView(R.id.financial_zz_rb)
    RadioButton financialZzRb;
    @BindView(R.id.financial_viewpage)
    NoScrollViewPager financialViewpage;


    private FinancialContract.Presenter mPresenter;
    private ArrayList<Fragment> mLists;
    private ZTFinancialFragment mZTFinancialFragment;
    private ZZFinancialFragment mZZFinancialFragment;

    public static FinancialFragment newInstance() {
        return new FinancialFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_financial;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new FinancialPresenter(this, getActivity());
        initViewPager();
        onClickListener();
        initData();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            //请求产品
            mPresenter.financialMarquee();
        }
        super.onHiddenChanged(hidden);

    }

    @Override
    public void showErrorTips(String msg) {
        if (TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, "" + msg);
        }
    }

    @Override
    public void initViewData() {

    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {

    }

    @Override
    public void setPresenter(FinancialContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initData() {

    }


    @Override
    public void onClickListener() {
        //监听滚动
        financialViewflipper.setOnDisplayChagnedListener(new MarqueeView.OnDisplayChagnedListener() {
            @Override
            public void OnDisplayChildChanging(ViewFlipper view, int index) {

                List<PublicNoticeRecordsBean> mLists = (List<PublicNoticeRecordsBean>) view.getTag();
                if (mLists != null && index == mLists.size() - 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.financialMarquee();
                        }
                    }, 1000);
                }
            }
        });


        financialZtRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if(state){
                    financialViewpage.setCurrentItem(0);
                    if(mZTFinancialFragment!=null){
                        mZTFinancialFragment.refresh();
                    }
                }
            }
        });

        financialZzRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean state) {
                if(state){
                    financialViewpage.setCurrentItem(1);
                    if(mZZFinancialFragment!=null){
                        mZZFinancialFragment.refresh();
                    }
                }
            }
        });


    }


    @Override
    public void initvMarqueeView() {

    }

    @Override
    public void stopFlipping() {
        if (financialViewflipper == null) return;
        if (financialViewflipper.isFlipping()) {
            financialViewflipper.stopFlipping();
        }
    }

    @Override
    public void showFinancialMarquee(List<FinancialMarqueeBean> bean) {
        stopFlipping();
        if (financialViewflipper == null) return;
        if (bean == null) {
            financialViewflipper.setVisibility(View.GONE);
        } else {
            financialViewflipper.startWithList(bean);
            financialViewflipper.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initViewPager() {

        mLists = new ArrayList<>();
        mZTFinancialFragment =ZTFinancialFragment.newInstance();
        mZZFinancialFragment =ZZFinancialFragment.newInstance();
        mLists.add(mZTFinancialFragment);
        mLists.add(mZZFinancialFragment);
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(),mLists);
        financialViewpage.setAdapter(mFragmentPagerAdapter);
        financialViewpage.setOffscreenPageLimit(1);
        financialViewpage.setCurrentItem(0);
        financialViewpage.setNoScroll(true);


    }


}
