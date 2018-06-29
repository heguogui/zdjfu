package com.hz.zdjfu.application.modle.product.returnmoneyplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.ProductInformBean;
import com.hz.zdjfu.application.data.bean.ReturnPlayBean;

import butterknife.BindView;


/**
 * [类功能说明]
 * 还款方式
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class ReturnMoneyPlayFragment extends BaseFragment {

    private static final String TAG = ReturnMoneyPlayFragment.class.getName();
    @BindView(R.id.payway_plan_rv)
    RecyclerView paywayPlanRv;
    private ReturnMoneyPlayAdapter mAdapter;
    private ProductInformBean mBean;
    public static ReturnMoneyPlayFragment newInstance() {
        return new ReturnMoneyPlayFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_returnmoneyplay;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        Intent mIntent = mActivity.getIntent();
        if (mIntent != null) {
            Bundle   mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                mBean = (ProductInformBean) mBundle.getSerializable("PRODUCTINFORM");
            }
        }

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        paywayPlanRv.setLayoutManager(manager);

        mAdapter = new ReturnMoneyPlayAdapter(mActivity);

        if(mBean!=null){
            if(mBean.getList_income()!=null&&mBean.getList_income().size()>0){
                for(int i=0;i<mBean.getList_income().size();i++){
                    ReturnPlayBean bean =  mBean.getList_income().get(i);
                    int count =i+1;
                    bean.setId(count+"");
                    mBean.getList_income().set(i,bean);

                }
                mAdapter.setNewData(mBean.getList_income());
            }
        }
        paywayPlanRv.setAdapter(mAdapter);

    }


}
