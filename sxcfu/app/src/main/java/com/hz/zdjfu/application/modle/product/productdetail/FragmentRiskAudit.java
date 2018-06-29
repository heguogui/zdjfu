package com.hz.zdjfu.application.modle.product.productdetail;

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
import com.hz.zdjfu.application.data.bean.FileBean;
import com.hz.zdjfu.application.data.bean.LookImageBean;
import com.hz.zdjfu.application.data.bean.ProductDetail;
import com.hz.zdjfu.application.data.bean.ProductImageBean;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.SafeguardBean;
import com.hz.zdjfu.application.modle.image.LookImagesDisplayActivity;
import com.hz.zdjfu.application.utils.json.GsonUtils;
import com.hz.zdjfu.application.widget.view.DragCustInfoScrollView;
import com.hz.zdjfu.application.widget.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 * 风控审核
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/8 0008.
 */

public class FragmentRiskAudit extends BaseFragment {
    private static final  String TAG =FragmentRiskAudit.class.getName();

    @BindView(R.id.asset_detail)
    TextView assetDetail;
    @BindView(R.id.riskaudit_pledge_gv)
    MyGridView riskauditPledgeGv;
    @BindView(R.id.riskaudit_from)
    TextView riskauditFrom;
    @BindView(R.id.riskaudit_risk_audit)
    TextView riskauditRiskAudit;
    @BindView(R.id.riskaudit_diya_manager)
    TextView riskauditDiyaManager;
    @BindView(R.id.riskaudit_law_supervise)
    TextView riskauditLawSupervise;
    @BindView(R.id.riskaudit_capital_supervise)
    TextView riskauditCapitalSupervise;
    @BindView(R.id.riskaudit_object_return)
    TextView riskauditObjectReturn;
    @BindView(R.id.product_detail_scrollView)
    DragCustInfoScrollView productDetailScrollView;
    @BindView(R.id.riskaudit_safeguard_tv)
    TextView riskauditSafeguardTv;
    @BindView(R.id.riskaudit_safeguard_ll)
    LinearLayout riskauditSafeguardLl;


    private GridviewAdapter mAdapter;
    private List<FileBean> mLists;
    private ProductInformBean mBean;

    private ProductInformBean mProductInformBean;



    public static FragmentRiskAudit newInstance() {
        return new FragmentRiskAudit();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_riskaudit;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Bundle mBundle= getArguments();
        if(mBundle!=null){
            mBean = (ProductInformBean) mBundle.getSerializable("PRODUCTDETAIL");
        }

        productDetailScrollView.setOnTouchListener(new View.OnTouchListener() {

            private float mPosY=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        float mCurPosY=event.getY();
                        if (mCurPosY - mPosY > 0 && (Math.abs(mCurPosY - mPosY) > 25)&&productDetailScrollView.getScrollY()==0) {//判断是否是向下滚动并且Scrollview 已经滑动到顶部
                            if(mActivity!=null&&((ProductDetailActivity) mActivity).mProductDetailFragment!=null){
                                ((ProductDetailActivity)mActivity).mProductDetailFragment.toHeadInfo();
                            }
                        }
                        break;
                }
                return false;
            }
        });


        mLists = new ArrayList<>();
        mAdapter =new GridviewAdapter(mActivity,mLists);
        riskauditPledgeGv.setAdapter(mAdapter);
        mProductInformBean =((ProductDetailActivity)mActivity).mProductInformBean;
        showDetailData(mProductInformBean);

        riskauditPledgeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(mLists!=null&&mLists.size()>0) {
                    Bundle mBundle = new Bundle();
                    ArrayList<LookImageBean> images = new ArrayList<>();
                    for (int i = 0; i < mLists.size(); i++) {
                        if (!TextUtils.isEmpty(mLists.get(i).getmUrl())) {
                            LookImageBean mBean = new LookImageBean();
                            mBean.setM_id(i + "");
                            mBean.setM_name(mLists.get(i).getmName());
                            mBean.setM_url(mLists.get(i).getmUrl());
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

        if(mProductInformBean!=null&&mProductInformBean.getProduct()!=null){
            ProductDetail mProductDetail =mProductInformBean.getProduct();
            //财产保障信息
            if(!TextUtils.isEmpty(mProductDetail.getProtect_msg())){
                assetDetail.setText(Html.fromHtml(mProductDetail.getProtect_msg())+"");
            }else{
                assetDetail.setText("");
            }

           String mContent = mProductDetail.getProtect_mode();
           if(!TextUtils.isEmpty(mContent)){
               if(mContent.contains("protectModeTitle")){//老系统
                   riskauditSafeguardTv.setVisibility(View.GONE);
                   riskauditSafeguardLl.setVisibility(View.VISIBLE);
                  List<SafeguardBean>   mLists =  GsonUtils.GsonToList(mContent,SafeguardBean.class);
                   try{
                       if(mLists!=null){
                           riskauditFrom.setText(mLists.get(0).getProtectModeText()+"");
                           riskauditRiskAudit.setText(mLists.get(1).getProtectModeText()+"");
                           riskauditDiyaManager.setText(mLists.get(2).getProtectModeText()+"");
                           riskauditLawSupervise.setText(mLists.get(3).getProtectModeText()+"");
                           riskauditCapitalSupervise.setText(mLists.get(4).getProtectModeText()+"");
                           riskauditObjectReturn.setText(mLists.get(5).getProtectModeText()+"");
                       }

                   }catch (Exception e){

                   }

               }else {
                   riskauditSafeguardTv.setVisibility(View.VISIBLE);
                   riskauditSafeguardTv.setText(Html.fromHtml(mContent)+"");
                   riskauditSafeguardLl.setVisibility(View.GONE);
               }
           }

           //抵押实拍
            List<ProductImageBean> fileLists =mProductInformBean.getProductImgList();
            if(fileLists!=null&&fileLists.size()>0){
                if(mLists!=null&&mLists.size()>0){
                    mLists.clear();
                }
                for(int i =0;i<fileLists.size();i++){
                    if(fileLists.get(i).getFile_type()==1){
                        FileBean bean =new FileBean();
                        bean.setmName(fileLists.get(i).getFile_name());
                        bean.setmUrl(fileLists.get(i).getFile_url());
                        mLists.add(bean);
                    }
                }
                if(mLists.size()>0){
                    mAdapter.setData(mLists);
                    mAdapter.notifyDataSetChanged();
                }
            }

        }



    }


}
