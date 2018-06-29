package com.hz.zdjfu.application.modle.ztprogect;

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
import com.hz.zdjfu.application.data.bean.ZTOtherBean;
import com.hz.zdjfu.application.data.bean.ZTPactBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
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

public class ZTFragmentContractDetail extends BaseFragment {

    @BindView(R.id.contractdetail_gv)
    MyGridView contractdetailGv;
    @BindView(R.id.contractdetail_other_gv)
    MyGridView contractdetailOtherGv;
    @BindView(R.id.product_detail_scrollView)
    DragCustInfoScrollView productDetailScrollView;

    private ZTGridviewAdapter mOneAdapter;
    private ZTGridviewAdapter mTwoAdapter;

    private List<FileBean> magreementLists;
    private List<FileBean> motherLists;
    private ZTProductDetailBean mZTProductDetailBean;

    public static ZTFragmentContractDetail newInstance() {
        return new ZTFragmentContractDetail();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frgment_contractdetail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


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
                            if(mActivity!=null&&((ZTProductDetailActivity) mActivity).mProductDetailFragment!=null){
                                ((ZTProductDetailActivity) mActivity).mProductDetailFragment.toHeadInfo();
                            }
                        }
                        break;
                }
                return false;
            }
        });


        magreementLists = new ArrayList<>();
        mOneAdapter = new ZTGridviewAdapter(mActivity, magreementLists);
        contractdetailGv.setAdapter(mOneAdapter);

        motherLists = new ArrayList<>();
        mTwoAdapter = new ZTGridviewAdapter(mActivity, motherLists);
        contractdetailOtherGv.setAdapter(mTwoAdapter);

        mZTProductDetailBean =((ZTProductDetailActivity)mActivity).mZTProductDetailBean;
        showDetailData(mZTProductDetailBean);

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


    public void showDetailData(ZTProductDetailBean mZTProductDetailBean){

        if(mZTProductDetailBean!=null){

            if(mZTProductDetailBean.getPact()!=null&&mZTProductDetailBean.getPact().size()>0){
                //抵押实拍
                List<ZTPactBean> mPactfileLists =mZTProductDetailBean.getPact();
                if(mPactfileLists!=null&&mPactfileLists.size()>0){
                    if(magreementLists!=null&&magreementLists.size()>0){
                        magreementLists.clear();
                    }
                    for(int i =0;i<mPactfileLists.size();i++){
                        FileBean bean =new FileBean();
                        bean.setmName(mPactfileLists.get(i).getFileName());
                        bean.setmUrl(mPactfileLists.get(i).getFileUrl());
                        magreementLists.add(bean);
                    }
                    if(magreementLists.size()>0){
                        mOneAdapter.setData(magreementLists);
                        mOneAdapter.notifyDataSetChanged();
                    }

                }
            }

            //其他
            List<ZTOtherBean> mOtherFileLists =mZTProductDetailBean.getOther();
            if(mOtherFileLists!=null&&mOtherFileLists.size()>0){
                if(motherLists!=null&&motherLists.size()>0){
                    motherLists.clear();
                }

                for(int i =0;i<mOtherFileLists.size();i++){
                    FileBean bean =new FileBean();
                    bean.setmName(mOtherFileLists.get(i).getFileName());
                    bean.setmUrl(mOtherFileLists.get(i).getFileUrl());
                    motherLists.add(bean);
                }

                if(motherLists.size()>0){
                    mTwoAdapter.setData(motherLists);
                    mTwoAdapter.notifyDataSetChanged();
                }
            }

        }



    }





}
