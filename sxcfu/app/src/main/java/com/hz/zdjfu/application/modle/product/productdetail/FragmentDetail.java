package com.hz.zdjfu.application.modle.product.productdetail;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.LenderBean;
import com.hz.zdjfu.application.data.bean.LenderImageBean;
import com.hz.zdjfu.application.data.bean.LoanBean;
import com.hz.zdjfu.application.data.bean.LoanImageBean;
import com.hz.zdjfu.application.data.bean.LookImageBean;
import com.hz.zdjfu.application.data.bean.ProductDetail;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.modle.image.LookImagesDisplayActivity;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.utils.image.ImageLoader;
import com.hz.zdjfu.application.widget.view.DragCustInfoScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * [类功能说明]
 * 项目详情
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class FragmentDetail extends BaseFragment {


    @BindView(R.id.product_detail_describe)
    TextView productDetailDescribe;
    @BindView(R.id.lender_name)
    TextView lenderName;
    @BindView(R.id.lender_age)
    TextView lenderAge;
    @BindView(R.id.lender_sex)
    TextView lenderSex;
    @BindView(R.id.lender_marry_state)
    TextView lenderMarryState;
    @BindView(R.id.lender_address)
    TextView lenderAddress;
    @BindView(R.id.lender_credentials_detail_z_iv)
    ImageView lenderCredentialsDetailZIv;
    @BindView(R.id.lender_credentials_detail_f_iv)
    ImageView lenderCredentialsDetailFIv;
    @BindView(R.id.borrower_name)
    TextView borrowerName;
    @BindView(R.id.borrower_age)
    TextView borrowerAge;
    @BindView(R.id.borrower_sex)
    TextView borrowerSex;
    @BindView(R.id.borrower_marry_state)
    TextView borrowerMarryState;
    @BindView(R.id.borrower_address)
    TextView borrowerAddress;
    @BindView(R.id.borrower_credentials_detail_z_iv)
    ImageView borrowerCredentialsDetailZIv;
    @BindView(R.id.product_detail_user)
    TextView productDetailUser;
    @BindView(R.id.product_detail_scrollView)
    DragCustInfoScrollView productDetailScrollView;
    @BindView(R.id.borrower_credentials_detail_f_iv)
    ImageView borrowerCredentialsDetailFIv;
    @BindView(R.id.product_detail_view4)
    TextView productDetailView4;
    Unbinder unbinder;
    private ProductInformBean mBean;

    private ProductInformBean mProductInformBean;


    public static FragmentDetail newInstance() {
        return new FragmentDetail();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mBean = (ProductInformBean) mBundle.getSerializable("PRODUCTDETAIL");
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
                            if(mActivity!=null&&((ProductDetailActivity) mActivity).mProductDetailFragment!=null){
                                ((ProductDetailActivity) mActivity).mProductDetailFragment.toHeadInfo();
                            }
                        }
                        break;
                }
                return false;
            }
        });

        showDetailData(((ProductDetailActivity)mActivity).mProductInformBean);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void showDetailData(ProductInformBean mProductInformBean) {

        if (mProductInformBean != null) {
            this.mProductInformBean = mProductInformBean;
            ProductDetail mProductDetail = mProductInformBean.getProduct();
            if (mProductDetail != null) {
                //项目概述

                if(productDetailDescribe!=null){
                    if (!TextUtils.isEmpty(mProductDetail.getProduct_desc())) {
                        productDetailDescribe.setText(Html.fromHtml(mProductDetail.getProduct_desc())+" ");
                    } else {
                        productDetailDescribe.setText(" ");
                    }
                }

                //借款用途及还款保障
                if(productDetailUser!=null){
                    if (!TextUtils.isEmpty(mProductDetail.getLend_use())) {
                        productDetailUser.setText(Html.fromHtml(mProductDetail.getLend_use()) + " ");
                    } else {
                        productDetailUser.setText(" ");
                    }
                }
            }


            //出借人信息
            LenderBean mLenderBean = mProductInformBean.getLender();
            if (mLenderBean != null) {

                //姓名
                if(lenderName!=null){
                    if (!TextUtils.isEmpty(mLenderBean.getName())) {
                        lenderName.setText(mLenderBean.getName().substring(0, 1) + "**");
                    }
                }

                if(lenderAge!=null){
                    lenderAge.setText(mLenderBean.getAge() + "");
                }

                if(lenderSex!=null){
                    //性别
                    if (!TextUtils.isEmpty(mLenderBean.getSex())) {
                        lenderSex.setText(mLenderBean.getSex()+"");
                    }
                }


                //婚姻状况
                if(lenderMarryState!=null){
                    if (mLenderBean.getMarital() == 1) {
                        lenderMarryState.setText("已婚");
                    } else if (mLenderBean.getMarital() == -1) {
                        lenderMarryState.setText("未婚");
                    }
                }

                if(lenderAddress!=null){
                    //地址
//                    if (!TextUtils.isEmpty(mLenderBean.getAddress())) {
//                        if(mLenderBean.getAddress().contains("市")){
//                            String content[] =mLenderBean.getAddress().split("市");
//                            lenderAddress.setText(content[0]+"市**********");
//                        }else {
//                            lenderAddress.setText(mLenderBean.getAddress().substring(0,6)+"**********");
//                        }
//                    }

                    if(!TextUtils.isEmpty(mLenderBean.getAddress())){
                        lenderAddress.setText(mLenderBean.getAddress());
                    }

                }

            }
            try{
                //证件信息
                List<LenderImageBean> mLists = mProductInformBean.getLenderImgList();
                if(lenderCredentialsDetailZIv!=null){
                    if (mLists != null && mLists.size() > 0) {

                        if (mLists.size() == 1) {
                            //正面
                            if (!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(mLists.get(0).getFile_url()))) {
                                ImageLoader.getInstance().displayImage(mActivity,UiUtils.URLEncoderFileImage(mLists.get(0).getFile_url()), lenderCredentialsDetailZIv);
                            } else {
                                ImageLoader.getInstance().displayImage(mActivity, R.mipmap.ic_main_advertising_defulat, lenderCredentialsDetailZIv);
                            }
                        } else if (mLists.size() == 2) {

                            //正面
                            if (!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(mLists.get(0).getFile_url()))) {
                                ImageLoader.getInstance().displayImage(mActivity,UiUtils.URLEncoderFileImage(mLists.get(0).getFile_url()), lenderCredentialsDetailZIv);
                            } else {
                                ImageLoader.getInstance().displayImage(mActivity, R.mipmap.ic_main_advertising_defulat, lenderCredentialsDetailZIv);
                            }

                            //反面
                            if (!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(mLists.get(1).getFile_url()))) {
                                ImageLoader.getInstance().displayImage(mActivity,UiUtils.URLEncoderFileImage(mLists.get(1).getFile_url()), lenderCredentialsDetailFIv);
                            } else {
                                ImageLoader.getInstance().displayImage(mActivity, R.mipmap.ic_main_advertising_defulat, lenderCredentialsDetailFIv);
                            }
                        }

                    }
                }
            }catch (Exception e){

            }
            //借款人信息
            LoanBean mLoanBean = mProductInformBean.getLoan();
            if (mLoanBean != null) {

                if(borrowerName!=null){
                    //姓名
                    if (!TextUtils.isEmpty(mLoanBean.getName())) {
                        borrowerName.setText(mLoanBean.getName().substring(0, 1) + "**");
                    }
                }


                //年龄
                if(borrowerAge!=null){
                    borrowerAge.setText(mLoanBean.getAge() + "");
                }

                if(borrowerSex!=null){
                    //性别
                    if (!TextUtils.isEmpty(mLoanBean.getSex())) {
                        borrowerSex.setText(mLoanBean.getSex()+"");
                    }
                }

                if(borrowerMarryState!=null){
                    //婚姻状况
                    if (mLoanBean.getMarital() == 1) {
                        borrowerMarryState.setText("已婚");
                    } else if (mLenderBean.getMarital() == -1) {
                        borrowerMarryState.setText("未婚");
                    }
                }

                if(borrowerAddress!=null){
                    //地址
//                    if (!TextUtils.isEmpty(mLoanBean.getAddress())) {
//                        if(mLoanBean.getAddress().contains("市")){
//                            String content[] =mLoanBean.getAddress().split("市");
//                            borrowerAddress.setText(content[0]+"市**********");
//                        }else {
//                            borrowerAddress.setText(mLoanBean.getAddress().substring(0,6)+"**********");
//                        }
//                    }
                    if(!TextUtils.isEmpty(mLoanBean.getAddress())){
                        borrowerAddress.setText(mLoanBean.getAddress());
                    }
                }

            }
            try{

            //证件信息
            List<LoanImageBean> mloanLists = mProductInformBean.getLoanImgList();
                    if(borrowerCredentialsDetailZIv!=null){
                        if (mloanLists != null && mloanLists.size() > 0) {

                            if (mloanLists.size() == 1) {
                                //正面
                                if (!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(mloanLists.get(0).getFile_url()))) {
                                    ImageLoader.getInstance().displayImage(mActivity,UiUtils.URLEncoderFileImage(mloanLists.get(0).getFile_url()), borrowerCredentialsDetailZIv);
                                } else {
                                    ImageLoader.getInstance().displayImage(mActivity, R.mipmap.ic_main_advertising_defulat, borrowerCredentialsDetailZIv);
                                }
                            } else if (mloanLists.size() == 2) {
                                //正面
                                if (!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(mloanLists.get(0).getFile_url()))) {
                                    ImageLoader.getInstance().displayImage(mActivity,UiUtils.URLEncoderFileImage(mloanLists.get(0).getFile_url()), borrowerCredentialsDetailZIv);
                                } else {
                                    ImageLoader.getInstance().displayImage(mActivity, R.mipmap.ic_main_advertising_defulat, borrowerCredentialsDetailZIv);
                                }
                                //反面
                                if (!TextUtils.isEmpty(UiUtils.URLEncoderFileImage(mloanLists.get(1).getFile_url()))) {
                                    ImageLoader.getInstance().displayImage(mActivity,UiUtils.URLEncoderFileImage(mloanLists.get(1).getFile_url()), borrowerCredentialsDetailFIv);
                                } else {
                                    ImageLoader.getInstance().displayImage(mActivity, R.mipmap.ic_main_advertising_defulat, borrowerCredentialsDetailFIv);
                                }
                            }

                        }
                    }

            } catch (Exception e){

            }

        }


    }


    @OnClick({R.id.lender_credentials_detail_z_iv, R.id.lender_credentials_detail_f_iv, R.id.borrower_credentials_detail_z_iv, R.id.borrower_credentials_detail_f_iv})
    public void onViewClicked(View view) {

        Bundle mBundle = new Bundle();
        ArrayList<LookImageBean> images = new ArrayList<>();
        switch (view.getId()) {
            case R.id.lender_credentials_detail_z_iv:
                if(mProductInformBean!=null){
                    List<LenderImageBean> lists = mProductInformBean.getLenderImgList();
                    if(lists!=null&&lists.size()>0){
                        for(int i=0;i<lists.size();i++){
                            if(!TextUtils.isEmpty(lists.get(i).getFile_url())){
                                LookImageBean mBean = new LookImageBean();
                                mBean.setM_id(lists.get(i).getId()+"");
                                mBean.setM_name(lists.get(i).getFile_name());
                                mBean.setM_url(lists.get(i).getFile_url());
                                images.add(mBean);
                            }
                        }
                        mBundle.putSerializable("imgUrls",images);
                        mBundle.putString("imgIndex",0+"");
                        startActivity(LookImagesDisplayActivity.makeIntent(mActivity,mBundle));
                    }
                }

                break;
            case R.id.lender_credentials_detail_f_iv:
                if(mProductInformBean!=null){
                    List<LenderImageBean> flists = mProductInformBean.getLenderImgList();
                    if(flists!=null&&flists.size()>0){
                        for(int i=0;i<flists.size();i++){
                            if(!TextUtils.isEmpty(flists.get(i).getFile_url())){
                                LookImageBean mBean = new LookImageBean();
                                mBean.setM_id(flists.get(i).getId()+"");
                                mBean.setM_name(flists.get(i).getFile_name());
                                mBean.setM_url(flists.get(i).getFile_url());
                                images.add(mBean);
                            }
                        }

                        mBundle.putSerializable("imgUrls",images);
                        mBundle.putString("imgIndex",1+"");
                        startActivity(LookImagesDisplayActivity.makeIntent(mActivity,mBundle));
                    }
                }


                break;
            case R.id.borrower_credentials_detail_z_iv:
                if(mProductInformBean!=null){
                    List<LoanImageBean> loanimg = mProductInformBean.getLoanImgList();
                    if(loanimg!=null&&loanimg.size()>0){
                        for(int i=0;i<loanimg.size();i++){
                            if(!TextUtils.isEmpty(loanimg.get(i).getFile_url())){
                                LookImageBean mBean = new LookImageBean();
                                mBean.setM_id(loanimg.get(i).getId()+"");
                                mBean.setM_name(loanimg.get(i).getFile_name());
                                mBean.setM_url(loanimg.get(i).getFile_url());
                                images.add(mBean);
                            }
                        }

                        mBundle.putSerializable("imgUrls",images);
                        mBundle.putString("imgIndex",0+"");
                        startActivity(LookImagesDisplayActivity.makeIntent(mActivity,mBundle));
                    }
                }



                break;
            case R.id.borrower_credentials_detail_f_iv:
                if(mProductInformBean!=null){
                    List<LoanImageBean> floanimg = mProductInformBean.getLoanImgList();
                    if(floanimg!=null){
                        for(int i=0;i<floanimg.size();i++){
                            if(!TextUtils.isEmpty(floanimg.get(i).getFile_url())){
                                LookImageBean mBean = new LookImageBean();
                                mBean.setM_id(floanimg.get(i).getId()+"");
                                mBean.setM_name(floanimg.get(i).getFile_name());
                                mBean.setM_url(floanimg.get(i).getFile_url());
                                images.add(mBean);
                            }
                        }
                        mBundle.putSerializable("imgUrls",images);
                        mBundle.putString("imgIndex",1+"");
                        startActivity(LookImagesDisplayActivity.makeIntent(mActivity,mBundle));
                    }
                }

                break;

        }
    }
}
