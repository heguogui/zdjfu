package com.hz.zdjfu.application.modle.ztprogect;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.LookImageBean;
import com.hz.zdjfu.application.data.bean.ZTLoanerBean;
import com.hz.zdjfu.application.data.bean.ZTLoanerFiles;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.modle.image.LookImagesDisplayActivity;
import com.hz.zdjfu.application.utils.TimeUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.view.DragCustInfoScrollView;
import com.hz.zdjfu.application.widget.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * [类功能说明]
 * 项目详情
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class ZTFragmentDetail extends BaseFragment {


    @BindView(R.id.product_detail_zt_describe)
    TextView productDetailZTDescribe;
    @BindView(R.id.product_detail_zt_loaner_tv)
    TextView productDetailZtLoanerTv;
    @BindView(R.id.product_detail_zt_loaner_one_tv)
    TextView productDetailZtLoanerOneTv;
    @BindView(R.id.product_detail_zt_lender_one)
    TextView productDetailZtLenderOne;
    @BindView(R.id.product_detail_zt_loaner_two_tv)
    TextView productDetailZtLoanerTwoTv;
    @BindView(R.id.product_detail_zt_lender_two)
    TextView productDetailZtLenderTwo;
    @BindView(R.id.product_detail_zt_loaner_three_tv)
    TextView productDetailZtLoanerThreeTv;
    @BindView(R.id.product_detail_zt_lender_three)
    TextView productDetailZtLenderThree;
    @BindView(R.id.product_detail_zt_loaner_four_tv)
    TextView productDetailZtLoanerFourTv;
    @BindView(R.id.product_detail_zt_lender_four)
    TextView productDetailZtLenderFour;
    @BindView(R.id.product_detail_zt_loaner_five_tv)
    TextView productDetailZtLoanerFiveTv;
    @BindView(R.id.product_detail_zt_lender_five)
    TextView productDetailZtLenderFive;
    @BindView(R.id.product_detail_zt_loaner_credentials_gv)
    MyGridView productDetailZtLoanerCredentialsGv;
    @BindView(R.id.product_detail_zt_user)
    TextView productDetailZtUser;
    @BindView(R.id.product_detail_zt_scrollView)
    DragCustInfoScrollView productDetailScrollView;
    @BindView(R.id.product_detail_zt_loaner_five_ll)
    LinearLayout productDetailZTLoanerFiveLL;


    Unbinder unbinder;
    private ZTProductDetailBean mZTProductDetailBean;

    private ProductDetailGridviewAdapter mAdapter;
    private List<ZTLoanerFiles> mlists;

    public static ZTFragmentDetail newInstance() {
        return new ZTFragmentDetail();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zt_detail;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mZTProductDetailBean = (ZTProductDetailBean) mBundle.getSerializable("PRODUCTDETAIL");
        }

        productDetailScrollView.setOnTouchListener(new View.OnTouchListener() {

            private float mPosY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        float mCurPosY = event.getY();
                        if (mCurPosY - mPosY > 0 && (Math.abs(mCurPosY - mPosY) > 25) && productDetailScrollView.getScrollY() == 0) {//判断是否是向下滚动并且Scrollview 已经滑动到顶部
                            if (mActivity != null && ((ZTProductDetailActivity) mActivity).mProductDetailFragment != null) {
                                ((ZTProductDetailActivity) mActivity).mProductDetailFragment.toHeadInfo();
                            }
                        }
                        break;
                }
                return false;
            }
        });

        mlists = new ArrayList<>();
        mAdapter = new ProductDetailGridviewAdapter(mActivity,mlists);
        productDetailZtLoanerCredentialsGv.setAdapter(mAdapter);
        productDetailZtLoanerCredentialsGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<LookImageBean> images = new ArrayList<>();
                for(int i=0;i<mlists.size();i++){
                    if(!TextUtils.isEmpty(mlists.get(i).getFileUrl())){
                        LookImageBean mBean = new LookImageBean();
                        mBean.setM_id(mlists.get(i).getId()+"");
                        mBean.setM_name(mlists.get(i).getFileName());
                        mBean.setM_url(mlists.get(i).getFileUrl());
                        images.add(mBean);
                    }
                }

                if(images.size()>0){
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("imgUrls",images);
                    mBundle.putString("imgIndex",position+"");
                    startActivity(LookImagesDisplayActivity.makeIntent(mActivity,mBundle));

                }

            }
        });

        showDetailData(((ZTProductDetailActivity) mActivity).mZTProductDetailBean);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void showDetailData(ZTProductDetailBean mZTProductDetailBean) {

        if (mZTProductDetailBean != null) {

            //项目概述
            if (productDetailZTDescribe != null) {
                if (!TextUtils.isEmpty(mZTProductDetailBean.getProductDesc())) {
                    productDetailZTDescribe.setText(Html.fromHtml(mZTProductDetailBean.getProductDesc()) + "");
                }
            }

            //借款用途及还款保障
            if (productDetailZtUser != null) {
                if (!TextUtils.isEmpty(mZTProductDetailBean.getLendUse())) {
                    productDetailZtUser.setText(Html.fromHtml(mZTProductDetailBean.getLendUse()) + "");
                }
            }

            //出借人信息
            ZTLoanerBean mLoanerBean = mZTProductDetailBean.getLoaner();
            if (mLoanerBean != null) {
                //对象
                if (mLoanerBean.getLoanerType() == 1) {//个人

                    if(productDetailZtLoanerTv!=null){
                        productDetailZtLoanerTv.setText("借款个人信息");
                    }

                   if(productDetailZtLoanerOneTv!=null){
                       productDetailZtLoanerOneTv.setText("姓名:");
                   }


                    if(productDetailZtLoanerTwoTv!=null){
                        productDetailZtLoanerTwoTv.setText("年龄:");
                    }

                    if(productDetailZtLoanerThreeTv!=null){
                        productDetailZtLoanerThreeTv.setText("性别:");
                    }

                    if(productDetailZtLoanerFourTv!=null){
                        productDetailZtLoanerFourTv.setText("婚姻状态:");
                    }

                    if(productDetailZtLoanerFiveTv!=null){
                        productDetailZtLoanerFiveTv.setText("地址:");
                    }


                    try {

                        if (productDetailZtLenderOne != null) {
                            if (!TextUtils.isEmpty(mLoanerBean.getName())) {
                                productDetailZtLenderOne.setText(mLoanerBean.getName().substring(0, 1) + "**");
                            }
                        }

                        if (productDetailZtLenderTwo != null && !TextUtils.isEmpty(mLoanerBean.getAge())) {
                            productDetailZtLenderTwo.setText(mLoanerBean.getAge() + "");
                        }

                        if (productDetailZtLenderThree != null && !TextUtils.isEmpty(mLoanerBean.getSex())) {
                            if (mLoanerBean.getSex().equals("1")) {
                                productDetailZtLenderThree.setText("男");
                            } else if (mLoanerBean.getSex().equals("2")) {
                                productDetailZtLenderThree.setText("女");
                            }
                        }

                        if (productDetailZtLenderFour != null && !TextUtils.isEmpty(mLoanerBean.getMarital())) {
                            if (mLoanerBean.getMarital().equals("1")) {
                                productDetailZtLenderFour.setText("已婚");
                            } else if (mLoanerBean.getMarital().equals("-1")) {
                                productDetailZtLenderFour.setText("未婚");
                            }
                        }

                        if (productDetailZtLenderFive != null && !TextUtils.isEmpty(mLoanerBean.getAddress())) {
                            productDetailZtLenderFive.setText(mLoanerBean.getAddress() + "");
                        }

                    } catch (Exception e) {

                    }


                } else if (mLoanerBean.getLoanerType() == 2) {//企业


                    if(productDetailZtLoanerTv!=null){
                        productDetailZtLoanerTv.setText("借款企业信息");
                    }

                    if(productDetailZtLoanerOneTv!=null){
                        productDetailZtLoanerOneTv.setText("企业名称:");
                    }


                    if(productDetailZtLoanerTwoTv!=null){
                        productDetailZtLoanerTwoTv.setText("法定代表:");
                    }

                    if(productDetailZtLoanerThreeTv!=null){
                        productDetailZtLoanerThreeTv.setText("注册地址:");
                    }

                    if(productDetailZtLoanerFourTv!=null){
                        productDetailZtLoanerFourTv.setText("注册资本:");
                    }

                    if(productDetailZtLoanerFiveTv!=null){
                        productDetailZtLoanerFiveTv.setText("注册时间:");
                    }



                    try {

                        if (productDetailZtLenderOne != null) {
                            if (!TextUtils.isEmpty(mLoanerBean.getCompName())) {
                                if (mLoanerBean.getCompName().length() > 4) {
                                    productDetailZtLenderOne.setText(mLoanerBean.getCompName().substring(0, 4) + "**");
                                } else {
                                    productDetailZtLenderOne.setText(mLoanerBean.getCompName().substring(0, 1) + "**");
                                }
                            }
                        }

                        if (productDetailZtLenderTwo != null) {
                            if (!TextUtils.isEmpty(mLoanerBean.getName())) {
                                productDetailZtLenderTwo.setText(mLoanerBean.getName().substring(0, 1) + "**");
                            }
                        }

                        if (productDetailZtLenderThree != null && !TextUtils.isEmpty(mLoanerBean.getCompAddress())) {
                            if (mLoanerBean.getCompAddress().length() > 6) {
                                productDetailZtLenderThree.setText(mLoanerBean.getCompAddress().substring(0, 6) + "***************");
                            } else {
                                productDetailZtLenderThree.setText(mLoanerBean.getCompAddress().substring(0, 3) + "***************");
                            }
                        }


                        if (productDetailZtLenderFour != null && !TextUtils.isEmpty(mLoanerBean.getRegMoney())) {
                            productDetailZtLenderFour.setText(UiUtils.formatMoneyToBigDecimal(mLoanerBean.getRegMoney(), getResources().getString(R.string.defalut_money_separator)) + "万元整");
                        }


                        if (productDetailZtLenderFive != null && !TextUtils.isEmpty(mLoanerBean.getRegDate())) {
                            productDetailZTLoanerFiveLL.setVisibility(View.VISIBLE);
                            productDetailZtLenderFive.setText(mLoanerBean.getRegDate() + "");
                        } else {
                            productDetailZTLoanerFiveLL.setVisibility(View.GONE);
                        }

                    } catch (Exception e) {

                    }

                }


                //证件图片
                if (mLoanerBean.getLoanerFiles() != null && mLoanerBean.getLoanerFiles().size() > 0 && mAdapter != null) {
                    if(mlists!=null&&mlists.size()>0){
                        mlists.clear();
                    }
                    mlists.addAll(mLoanerBean.getLoanerFiles());
                    mAdapter.setData(mlists);
                    mAdapter.notifyDataSetChanged();
                }

            }
        }

    }

}
