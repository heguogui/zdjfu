package com.hz.zdjfu.application.modle.discount;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableRow;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.modle.adapter.FragmentPagerAdapter;
import com.hz.zdjfu.application.modle.invest.invest.InvestActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 优惠券Fragment
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class DiscountFragment extends BaseFragment implements DiscountContract.View {

    private static final String TAG = DiscountFragment.class.getName();
    @BindView(R.id.discount_use_rb)
    RadioButton discountUseRb;
    @BindView(R.id.discount_unuse_rb)
    RadioButton discountUnuseRb;
    @BindView(R.id.discount_cursor)
    ImageView discountCursor;
    @BindView(R.id.discount_tableRow2)
    TableRow discountTableRow2;
    @BindView(R.id.discount_viewpager)
    ViewPager discountViewpager;
    @BindView(R.id.discount_sure_btn)
    Button discountSureBtn;



    private DiscountContract.Presenter mPresenter;
    private ArrayList<Fragment> mLists;
    private UnUsedFragment mUnUsedFragment;
    private UsedFragment mUsedFragment;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private Bundle mBundle;

    public static DiscountFragment newInstance() {
        return new DiscountFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discount;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new DiscountPresenter(mActivity, this);
        initViewPager();
        initViewData();
        initImageView();
    }


    @Override
    public void showErrorTips(String msg) {

    }

    @Override
    public void initViewData() {

        discountUseRb.setOnClickListener(new MyOnClickListener(0));
        discountUnuseRb.setOnClickListener(new MyOnClickListener(1));


    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }



    @Override
    public void initViewPager() {

        Intent mIntent = mActivity.getIntent();
        if (mIntent != null) {
            mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
        }
        mLists = new ArrayList<>();
        mUnUsedFragment = UnUsedFragment.newInstance();
        if (mBundle != null) {
            mUnUsedFragment.setArguments(mBundle);
        }

        mUsedFragment = UsedFragment.newInstance();
        if (mBundle != null) {
            mUsedFragment.setArguments(mBundle);
        }

        mLists.add(mUsedFragment);
        mLists.add(mUnUsedFragment);
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(), mLists);
        discountViewpager.setAdapter(mFragmentPagerAdapter);
        discountViewpager.setOffscreenPageLimit(1);
        discountViewpager.setCurrentItem(0);
        discountViewpager.addOnPageChangeListener(new MyViewPagerOnChangeClickListener());
    }

    @Override
    public void initRecycleView() {

    }

    @Override
    public void showAllDiscountCoupon(DiscountBean mbean) {

    }


    public void showUsedTitleNum(int num) {

        if(discountUseRb!=null){
            discountUseRb.setText("可用优惠券("+num+")");
        }
    }


    public void showUnUsedTitleNum(int num) {

        if(discountUnuseRb!=null){
            discountUnuseRb.setText("不可用优惠券("+num+")");
        }

    }


    public void initImageView() {

        bmpW = BitmapFactory.decodeResource(mActivity.getResources(), R.mipmap.ic_common_tabimg).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        discountCursor.setImageMatrix(matrix);// 设置动画初始位置

    }




    @OnClick(R.id.discount_sure_btn)
    public void onViewClicked() {

        //判断是否是可用的
        if(mUsedFragment!=null&&mUsedFragment.mCheckCoupon!=null){
            Intent mIntent = new Intent(mActivity,InvestActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("CHECKCOUPON",mUsedFragment.mCheckCoupon);
            mIntent.putExtra("CHECKCOUPONBUNDLE",mBundle);
            mActivity.setResult(Activity.RESULT_OK,mIntent);
            mActivity.finish();
        }

    }

    @Override
    public void setPresenter(DiscountContract.Presenter presenter) {

    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            discountViewpager.setCurrentItem(index);
        }
    }


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActivity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class MyViewPagerOnChangeClickListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int one = offset * 2 + bmpW;
            Animation animation = new TranslateAnimation(one * currIndex, one * position, 0, 0);
            currIndex = position;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            discountCursor.startAnimation(animation);
            switch (position) {
                case 0:
                    discountUseRb.setChecked(true);
                    break;
                case 1:
                    discountUnuseRb.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }





}
