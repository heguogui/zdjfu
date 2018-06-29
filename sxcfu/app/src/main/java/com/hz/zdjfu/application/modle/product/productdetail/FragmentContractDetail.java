package com.hz.zdjfu.application.modle.product.productdetail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.FileBean;
import com.hz.zdjfu.application.data.bean.LookImageBean;
import com.hz.zdjfu.application.data.bean.ProductImageBean;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.modle.image.LookImagesDisplayActivity;
import com.hz.zdjfu.application.widget.view.DragCustInfoScrollView;
import com.hz.zdjfu.application.widget.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 * 合同材料
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/8 0008.
 */

public class FragmentContractDetail extends BaseFragment {

    @BindView(R.id.contractdetail_gv)
    MyGridView contractdetailGv;
    @BindView(R.id.contractdetail_other_gv)
    MyGridView contractdetailOtherGv;
    @BindView(R.id.product_detail_scrollView)
    DragCustInfoScrollView productDetailScrollView;

    private GridviewAdapter mOneAdapter;
    private GridviewAdapter mTwoAdapter;

    private List<FileBean> magreementLists;
    private List<FileBean> motherLists;
    private ProductInformBean mBean;
    private ProductInformBean mProductInformBean;

    public static FragmentContractDetail newInstance() {
        return new FragmentContractDetail();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frgment_contractdetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        Bundle mBundle= getArguments();
        if(mBundle!=null){
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


        magreementLists = new ArrayList<>();
        mOneAdapter = new GridviewAdapter(mActivity, magreementLists);
        contractdetailGv.setAdapter(mOneAdapter);

        motherLists = new ArrayList<>();
        mTwoAdapter = new GridviewAdapter(mActivity, motherLists);
        contractdetailOtherGv.setAdapter(mTwoAdapter);

        mProductInformBean =((ProductDetailActivity)mActivity).mProductInformBean;
        showDetailData(mProductInformBean);

        contractdetailGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(magreementLists!=null&&magreementLists.size()>0) {
                    Bundle mBundle = new Bundle();
                    ArrayList<LookImageBean> images = new ArrayList<>();
                    for (int i = 0; i < magreementLists.size(); i++) {
                        if (!TextUtils.isEmpty(magreementLists.get(i).getmUrl())) {
                            LookImageBean mBean = new LookImageBean();
                            mBean.setM_id(i + "");
                            mBean.setM_name(magreementLists.get(i).getmName());
                            mBean.setM_url(magreementLists.get(i).getmUrl());
                            images.add(mBean);
                        }
                    }
                    mBundle.putSerializable("imgUrls", images);
                    mBundle.putString("imgIndex",position + "");
                    startActivity(LookImagesDisplayActivity.makeIntent(mActivity, mBundle));
                }

            }
        });
        contractdetailOtherGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(motherLists!=null&&motherLists.size()>0) {
                    Bundle mBundle = new Bundle();
                    ArrayList<LookImageBean> images = new ArrayList<>();
                    for (int i = 0; i < motherLists.size(); i++) {
                        if (!TextUtils.isEmpty(motherLists.get(i).getmUrl())) {
                            LookImageBean mBean = new LookImageBean();
                            mBean.setM_id(i + "");
                            mBean.setM_name(motherLists.get(i).getmName());
                            mBean.setM_url(motherLists.get(i).getmUrl());
                            images.add(mBean);
                        }
                    }
                    mBundle.putSerializable("imgUrls", images);
                    mBundle.putString("imgIndex",position + "");
                    startActivity(LookImagesDisplayActivity.makeIntent(mActivity, mBundle));
                }

            }
        });


    }


    public void showDetailData(ProductInformBean mProductInformBean){

        if(mProductInformBean!=null&&mProductInformBean.getProductImgList()!=null&&mProductInformBean.getProductImgList().size()>0){
            //抵押实拍
            List<ProductImageBean> fileLists =mProductInformBean.getProductImgList();
            if(fileLists!=null&&fileLists.size()>0){
                //合同
                if(magreementLists!=null&&magreementLists.size()>0){
                    magreementLists.clear();
                }
                for(int i =0;i<fileLists.size();i++){
                    if(fileLists.get(i).getFile_type()==3){
                        FileBean bean =new FileBean();
                        bean.setmName(fileLists.get(i).getFile_name());
                        bean.setmUrl(fileLists.get(i).getFile_url());
                        magreementLists.add(bean);
                    }
                }
                if(magreementLists.size()>0){
                    mOneAdapter.setData(magreementLists);
                    mOneAdapter.notifyDataSetChanged();
                }

                //其他
                if(motherLists!=null&&motherLists.size()>0){
                    motherLists.clear();
                }
                for(int i =0;i<fileLists.size();i++){
                    if(fileLists.get(i).getFile_type()==4){
                        FileBean bean =new FileBean();
                        bean.setmName(fileLists.get(i).getFile_name());
                        bean.setmUrl(fileLists.get(i).getFile_url());
                        motherLists.add(bean);
                    }
                }
                if(motherLists.size()>0){
                    mTwoAdapter.setData(motherLists);
                    mTwoAdapter.notifyDataSetChanged();
                }

            }
        }



    }





}
