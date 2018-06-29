package com.hz.zdjfu.application.modle.invest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.InvestRewordBean;
import com.hz.zdjfu.application.data.bean.InvestRewordLists;
import com.hz.zdjfu.application.data.bean.InvestRewordRank;
import com.hz.zdjfu.application.data.bean.InvestRewordRankList;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.XXRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class InvestRewordFragment extends BaseFragment implements InvestRewordContract.View {

    private static final String TAG =InvestRewordFragment.class.getName();


    @BindView(R.id.investreword_recycleview)
    RecyclerView investrewordRecycleview;
    @BindView(R.id.investreword_refreshview)
    XXRefreshView investrewordRefreshview;
    @BindView(R.id.investreword_stickhead_rl)
    RelativeLayout investrewordStickheadRl;
    @BindView(R.id.investreword_empty_data_rl)
    RelativeLayout investrewordEmptyData;


    private TextView quick_ranking_one_phone_tv;
    private TextView quick_ranking_one_zjz_tv;
    private TextView quick_ranking_two_phone_tv;
    private TextView quick_ranking_two_zjz_tv;
    private TextView quick_ranking_three_phone_tv;
    private TextView quick_ranking_three_zjz_tv;
    private TextView weight_ranking_one_phone_tv;
    private TextView weight_ranking_one_zjz_tv;
    private TextView weight_ranking_two_phone_tv;
    private TextView weight_ranking_two_zjz_tv;
    private TextView lase_ranking_one_phone_tv;
    private TextView lase_ranking_one_zjz_tv;


    private String productId;
    private Context context;
    private InvestRewordContract.Presenter mPresenter;
    private InvestRewordAdapter mAdapter;
    private List<InvestRewordBean> mLists;
    private LinearLayoutManager manager;
    private int headHeight;//悬浮置顶的高度
    private int mCurrentPosition = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investreword;
    }

    public static InvestRewordFragment newInstance() {
        return new InvestRewordFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new InvestRewordPresenter(mActivity,this);
        initViewData();

        Intent mIntent =mActivity.getIntent();
        if(mIntent!=null){
            Bundle mBundle =mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                productId =mBundle.getString("PRODUCTID");
            }
        }

        if(mPresenter!=null&&!TextUtils.isEmpty(productId)){
           // mPresenter.investRewordRankHttpRequest(productId);
            mPresenter.investRewordHttpRequest(productId,true);
        }

    }

    @Override
    public void showErrorTips(String msg) {
        completeRefreshAndLoad();
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }

    }

    @Override
    public void initViewData() {

        mLists = new ArrayList<>();
        manager = new LinearLayoutManager(getActivity());
        investrewordRecycleview.setLayoutManager(manager);
        mAdapter = new InvestRewordAdapter(getActivity(),mLists);
        investrewordRecycleview.setAdapter(mAdapter);
        //设置为空时的显示

        //设置悬浮
//        investrewordRecycleview.setHasFixedSize(true);
//        investrewordRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                // 当recyclerView的滑动改变改变的时候 实时拿到它的高度
//                headHeight =investrewordStickheadRl.getHeight();
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                View itemView = manager.findViewByPosition(1);
//                if (itemView != null) {
//                    if (itemView.getTop() <= headHeight) {
//                        investrewordStickheadRl.setY(0);
//                        investrewordStickheadRl.setVisibility(View.VISIBLE);
//                    } else {
//                        investrewordStickheadRl.setY(-(headHeight - itemView.getTop()));
//                        investrewordStickheadRl.setVisibility(View.GONE);
//                    }
//                }
//
//            }
//        });


        investrewordRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                if(mPresenter!=null&&!TextUtils.isEmpty(productId)){
                   // mPresenter.investRewordRankHttpRequest(productId);
                    mPresenter.investRewordHttpRequest(productId,true);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                if(mPresenter!=null&&!TextUtils.isEmpty(productId)){
                    mPresenter.investRewordHttpRequest(productId,false);
                }
            }
        });


    }

    @Override
    public void showDateEmptyView(boolean state) {
        if(investrewordEmptyData==null)
            return;
        if(state){
            investrewordEmptyData.setVisibility(View.VISIBLE);
        }else{
            investrewordEmptyData.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(InvestRewordContract.Presenter presenter) {
        this.mPresenter =presenter;
    }


    @Override
    public void showInvestRewordDetailData(InvestRewordLists lists, boolean isRefresh) {

        completeRefreshAndLoad();
        if((lists==null||lists.getDataList()==null||lists.getDataList().size()==0)&&isRefresh){
            showDateEmptyView(true);
            return;
        }else{
            showDateEmptyView(false);
        }

        if(isRefresh){
            if(mLists!=null&&mLists.size()>0){
                mLists.clear();
            }
        }


        mLists.addAll(lists.getDataList());

        //去除已投9
        for(int i=0;i<mLists.size();i++){
            if(mLists.get(i).getStatus().equals("9")){
                mLists.remove(i);
            }
        }

        if(mAdapter==null){
            mAdapter = new InvestRewordAdapter(getActivity(),lists.getDataList());
            investrewordRecycleview.setAdapter(mAdapter);
        }else{
            mAdapter.setProductRewordLists(mLists,isRefresh);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void completeRefreshAndLoad() {
        if(investrewordRefreshview!=null){
            investrewordRefreshview.stopRefresh();
            investrewordRefreshview.stopLoadMore();
        }

    }

    @Override
    public void showInvestRewordRank(InvestRewordRankList list) {

        if(list==null||list.getDataList()==null){
            return;
        }

        InvestRewordRank mlist =list.getDataList();
        //快手抢标
        if(mlist.getFast()!=null){
            int length =mlist.getFast().size();
            if(length==3){
                if(!TextUtils.isEmpty(mlist.getFast().get(0).getPhone())){
                    quick_ranking_one_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getFast().get(0).getPhone())+"");
                    quick_ranking_one_zjz_tv.setText("+"+mlist.getFast().get(0).getPropervalue());
                }

                if(!TextUtils.isEmpty(mlist.getFast().get(1).getPhone())){
                    quick_ranking_two_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getFast().get(1).getPhone())+"");
                    quick_ranking_two_zjz_tv.setText("+"+mlist.getFast().get(1).getPropervalue());
                }

                if(!TextUtils.isEmpty(mlist.getFast().get(2).getPhone())){
                    quick_ranking_three_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getFast().get(2).getPhone())+"");
                    quick_ranking_three_zjz_tv.setText("+"+mlist.getFast().get(2).getPropervalue());
                }
            }else if(length==2){
                if(!TextUtils.isEmpty(mlist.getFast().get(0).getPhone())){
                    quick_ranking_one_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getFast().get(0).getPhone())+"");
                    quick_ranking_one_zjz_tv.setText("+"+mlist.getFast().get(0).getPropervalue());
                }

                if(!TextUtils.isEmpty(mlist.getFast().get(1).getPhone())){
                    quick_ranking_two_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getFast().get(1).getPhone())+"");
                    quick_ranking_two_zjz_tv.setText("+"+mlist.getFast().get(1).getPropervalue());
                }

            }else if(length==1){

                if(!TextUtils.isEmpty(mlist.getFast().get(0).getPhone())){
                    quick_ranking_one_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getFast().get(0).getPhone())+"");
                    quick_ranking_one_zjz_tv.setText("+"+mlist.getFast().get(0).getPropervalue());
                }

            }
        }

        //重量级
        if(mlist.getHeavyweight()!=null){
            int length =mlist.getHeavyweight().size();
            if(length==2){
                if(!TextUtils.isEmpty(mlist.getHeavyweight().get(0).getPhone())){
                    weight_ranking_one_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getHeavyweight().get(0).getPhone())+"");
                    weight_ranking_one_zjz_tv.setText("+"+mlist.getHeavyweight().get(0).getPropervalue());
                }
                if(!TextUtils.isEmpty(mlist.getHeavyweight().get(1).getPhone())){
                    weight_ranking_two_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getHeavyweight().get(1).getPhone())+"");
                    weight_ranking_two_zjz_tv.setText("+"+mlist.getHeavyweight().get(1).getPropervalue());
                }

            }else if(length==1){
                if(!TextUtils.isEmpty(mlist.getHeavyweight().get(0).getPhone())){
                    weight_ranking_one_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getHeavyweight().get(0).getPhone())+"");
                    weight_ranking_one_zjz_tv.setText("+"+mlist.getHeavyweight().get(0).getPropervalue());
                }
//                weight_ranking_two_phone_tv.setText("");
//                weight_ranking_two_zjz_tv.setText("");
            }
        }

    //收尾抢标
        if(mlist.getTail()!=null){
            if(!TextUtils.isEmpty(mlist.getTail().getPhone())){
                lase_ranking_one_phone_tv.setText(StringUtils.desenPhoneNumber(mlist.getTail().getPhone())+"");
                lase_ranking_one_zjz_tv.setText("+"+mlist.getTail().getPropervalue()+"");
            }
        }

//        try{
//            if(mlist.getTail()!=null&&mlist.getHeavyweight()!=null&&mlist.getFast()!=null){
//                int faselength =mlist.getFast().size();
//                int heavyweightlength =mlist.getHeavyweight().size();
//                if(faselength>0&&heavyweightlength>0){
//                    if(!TextUtils.isEmpty(mlist.getHeavyweight().get(0).getPhone())&&!TextUtils.isEmpty(mlist.getTail().getPhone())&&!TextUtils.isEmpty(mlist.getFast().get(0).getPhone())&&mlist.getHeavyweight().get(0).getPhone().equals(mlist.getFast().get(0).getPhone())&&mlist.getHeavyweight().get(0).getPhone().equals(mlist.getTail().getPhone())){
//                        quick_ranking_two_phone_tv.setText("");
//                        quick_ranking_two_zjz_tv.setText("");
//                        quick_ranking_three_phone_tv.setText("");
//                        quick_ranking_three_zjz_tv.setText("");
//                    }
//                }
//
//                if(faselength>1){
//                    if(!TextUtils.isEmpty(mlist.getTail().getPhone())&&!TextUtils.isEmpty(mlist.getFast().get(1).getPhone())&&mlist.getFast().get(1).getPhone().equals(mlist.getTail().getPhone())){
//                        quick_ranking_three_phone_tv.setText("");
//                        quick_ranking_three_zjz_tv.setText("");
//                    }
//                }
//
//            }
//        }catch (Exception e){
//
//        }






    }


    public interface OnHeadViewInitListener {
        void onHeadViewInit(View headerView, int itemType);
    }


    /**
     * 监听广告初始化
     */
    private OnHeadViewInitListener initOnBannerViewListener = new OnHeadViewInitListener() {
        @Override
        public void onHeadViewInit(View headerView, int itemType) {


        }
    };


    private void initInvestProductRankingView(View headerView) {

        if (headerView == null) {
            return;
        }

        quick_ranking_one_phone_tv = (TextView) headerView.findViewById(R.id.ranking_quick_one_phone);
        quick_ranking_one_zjz_tv = (TextView) headerView.findViewById(R.id.ranking_quick_one_zjz);
        quick_ranking_two_phone_tv = (TextView) headerView.findViewById(R.id.ranking_quick_two_phone);
        quick_ranking_two_zjz_tv = (TextView) headerView.findViewById(R.id.ranking_quick_two_zjz);
        quick_ranking_three_phone_tv = (TextView) headerView.findViewById(R.id.ranking_quick_three_phone);
        quick_ranking_three_zjz_tv = (TextView) headerView.findViewById(R.id.ranking_quick_three_zjz);
        weight_ranking_one_phone_tv = (TextView) headerView.findViewById(R.id.ranking_weight_one_phone);
        weight_ranking_one_zjz_tv = (TextView) headerView.findViewById(R.id.ranking_weight_one_zjz);
        weight_ranking_two_phone_tv = (TextView) headerView.findViewById(R.id.ranking_weight_two_phone);
        weight_ranking_two_zjz_tv = (TextView) headerView.findViewById(R.id.ranking_weight_two_zjz);
        lase_ranking_one_phone_tv = (TextView) headerView.findViewById(R.id.ranking__lase_one_phone);
        lase_ranking_one_zjz_tv = (TextView) headerView.findViewById(R.id.ranking__lase_one_zjz);


    }
}
