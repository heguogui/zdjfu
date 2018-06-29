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
import com.hz.zdjfu.application.data.bean.FileBean;
import com.hz.zdjfu.application.data.bean.LookImageBean;
import com.hz.zdjfu.application.data.bean.ProductDetail;
import com.hz.zdjfu.application.data.bean.ProductImageBean;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.SafeguardBean;
import com.hz.zdjfu.application.data.bean.ZTPledgedBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.modle.image.LookImagesDisplayActivity;
import com.hz.zdjfu.application.utils.json.GsonUtils;
import com.hz.zdjfu.application.widget.view.DragCustInfoScrollView;
import com.hz.zdjfu.application.widget.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 * 直投风控审核
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/8 0008.
 */

public class ZTFragmentRiskAudit extends BaseFragment {
    private static final  String TAG = ZTFragmentRiskAudit.class.getName();

    @BindView(R.id.asset_zt_detail)
    TextView assetDetail;
    @BindView(R.id.riskaudit_zt_pledge_gv)
    MyGridView riskauditPledgeGv;
    @BindView(R.id.product_zt_detail_scrollView)
    DragCustInfoScrollView productDetailScrollView;
    @BindView(R.id.riskaudit_zt_safeguard_tv)
    TextView riskauditSafeguardTv;


    private ZTGridviewAdapter mAdapter;
    private List<FileBean> mLists;
    private ZTProductDetailBean mZTProductDetailBean;


    public static ZTFragmentRiskAudit newInstance() {
        return new ZTFragmentRiskAudit();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zt_riskaudit;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

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
                            if(mActivity!=null&&((ZTProductDetailActivity) mActivity).mProductDetailFragment!=null){
                                ((ZTProductDetailActivity)mActivity).mProductDetailFragment.toHeadInfo();
                            }
                        }
                        break;
                }
                return false;
            }
        });


        mLists = new ArrayList<>();
        mAdapter =new ZTGridviewAdapter(mActivity,mLists);
        riskauditPledgeGv.setAdapter(mAdapter);
        mZTProductDetailBean =((ZTProductDetailActivity)mActivity).mZTProductDetailBean;
        showDetailData(mZTProductDetailBean);

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



    public void showDetailData(ZTProductDetailBean mZTProductDetailBean){

        if(mZTProductDetailBean!=null) {
            //财产保障信息
            if (!TextUtils.isEmpty(mZTProductDetailBean.getProtectMsg())) {
                assetDetail.setText(Html.fromHtml(mZTProductDetailBean.getProtectMsg()) + "");
            }

            if(!TextUtils.isEmpty(mZTProductDetailBean.getProtectMode())){
                riskauditSafeguardTv.setText(Html.fromHtml(mZTProductDetailBean.getProtectMode())+"");
            }

            //抵押实拍
            List<ZTPledgedBean> fileLists =mZTProductDetailBean.getPledged();
            if(fileLists!=null&&fileLists.size()>0){
                if(mLists!=null&&mLists.size()>0){
                    mLists.clear();
                }
                for(int i =0;i<fileLists.size();i++){
                    FileBean bean =new FileBean();
                    bean.setmName(fileLists.get(i).getFileName());
                    bean.setmUrl(fileLists.get(i).getFileUrl());
                    mLists.add(bean);
                }
                if(mLists.size()>0){
                    mAdapter.setData(mLists);
                    mAdapter.notifyDataSetChanged();
                }
            }

        }



    }


}
