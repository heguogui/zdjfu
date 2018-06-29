package com.hz.zdjfu.application.modle.mine.redpacketcoupon;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.adapter.FragmentPagerAdapter;
import com.hz.zdjfu.application.modle.rechangecenter.RechangeCenterActivity;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;



/**
 * [类功能说明]
 * 红包卡券Fragment
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/9/29 0029.
 */

public class RedpacketCouponFragment extends BaseFragment implements RedpacketCouponContract.View{

    private static final String TAG = RedpacketCouponFragment.class.getName();
    @BindView(R.id.redpacketcoupon_redpacket_rb)
    RadioButton redpacketcouponRedpacketRb;
    @BindView(R.id.redpacketcoupon_addinterestcoupon_rb)
    RadioButton redpacketcouponAddinterestcouponRb;
    @BindView(R.id.redpacketcoupon_rg)
    RadioGroup redpacketcouponRg;
    @BindView(R.id.redpacketcoupon_cursor)
    ImageView redpacketcouponCursor;
    @BindView(R.id.redpacketcoupon_tableRow2)
    TableRow redpacketcouponTableRow2;
    @BindView(R.id.redpacketcoupon_viewpager)
    ViewPager redpacketcouponViewpager;
    @BindView(R.id.redpacketcoupon_banlance_title_tv)
    TextView redpacketcouponBanlanceTitleTv;
    @BindView(R.id.redpacketcoupon_banlance_tv)
    TextView redpacketcouponBanlanceTv;
    @BindView(R.id.redpacketcoupon_yuan_tv)
    TextView redpacketcouponYuanTv;
    @BindView(R.id.redpacketcoupon_help_tv)
    TextView redpacketcouponHelpTv;
    @BindView(R.id.redpacketcoupon_rechange_center_tv)
    TextView redpacketcouponRechangeCenterTv;

    private RedpacketCouponContract.Presenter mPresenter;
    private ArrayList<Fragment> mLists;
    private RedpacketFragment mRedpacketFragment;
    private CouponFragment mCouponFragment;
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度


    public static RedpacketCouponFragment newInstance() {
        return new RedpacketCouponFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_redpacketcoupon;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new RedpacketCouponPresenter(mActivity,this);
        initViewPager();
        initViewData();
        initImageView();
    }


    @OnClick(R.id.redpacketcoupon_rechange_center_tv)
    public void onViewClicked() {
        //兑换中心
        //startActivity(RechangeCenterActivity.makeIntent(mActivity,null));
    }

    @Override
    public void showErrorTips(String msg) {

    }

    @Override
    public void initViewData() {

        redpacketcouponRedpacketRb.setOnClickListener(new MyOnClickListener(0));
        redpacketcouponAddinterestcouponRb.setOnClickListener(new MyOnClickListener(1));

    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(RedpacketCouponContract.Presenter presenter) {
        this.mPresenter =presenter;
    }



    @Override
    public void initViewPager() {

        mLists = new ArrayList<>();
        mRedpacketFragment = RedpacketFragment.newInstance();
        mCouponFragment =CouponFragment.newInstance();
        mLists.add(mRedpacketFragment);
        mLists.add(mCouponFragment);
        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(),mLists);
        redpacketcouponViewpager.setAdapter(mFragmentPagerAdapter);
        redpacketcouponViewpager.setOffscreenPageLimit(1);
        redpacketcouponViewpager.setCurrentItem(0);
        redpacketcouponViewpager.addOnPageChangeListener(new MyViewPagerOnChangeClickListener());
    }

    @Override
    public void initImageView() {

        bmpW = BitmapFactory.decodeResource(mActivity.getResources(),R.mipmap.ic_common_tabimg).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        redpacketcouponCursor.setImageMatrix(matrix);// 设置动画初始位置

    }

    @Override
    public void showAllZJZNum(String num) {
        if(!TextUtils.isEmpty(num)){
            redpacketcouponBanlanceTv.setText(num+"");
        }
    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            redpacketcouponViewpager.setCurrentItem(index);
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



    public class MyViewPagerOnChangeClickListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int one = offset * 2 + bmpW;
            Animation animation = new TranslateAnimation(one * currIndex, one* position, 0, 0);
            currIndex = position;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            redpacketcouponCursor.startAnimation(animation);
            switch (position) {
                case 0:
                    redpacketcouponRedpacketRb.setChecked(true);
                    break;
                case 1:
                    redpacketcouponAddinterestcouponRb.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    public void refreshRedpacketData(int num){
        if(num>0&&redpacketcouponRedpacketRb!=null){
            redpacketcouponRedpacketRb.setText("红包("+num+")");
        }
    }

    public void refreshCouponData(int num){

        if(num>0&&redpacketcouponRedpacketRb!=null){
            redpacketcouponAddinterestcouponRb.setText("加息券("+num+")");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }



}
