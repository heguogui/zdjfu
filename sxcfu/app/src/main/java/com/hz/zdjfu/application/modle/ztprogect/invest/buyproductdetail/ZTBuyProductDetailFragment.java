package com.hz.zdjfu.application.modle.ztprogect.invest.buyproductdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.InvestDetailBean;
import com.hz.zdjfu.application.data.bean.InvestProductBean;
import com.hz.zdjfu.application.data.bean.InvestReturnDayBean;
import com.hz.zdjfu.application.data.bean.InvestRewordBean;
import com.hz.zdjfu.application.data.bean.MyInvestBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.ZTBuyProductDetailBean;
import com.hz.zdjfu.application.data.bean.ZTRefundLists;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailAdapter;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailContract;
import com.hz.zdjfu.application.modle.invest.investdetail.InvestDetailPresenter;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.modle.ztprogect.ZTProductDetailActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *投资的项目详情
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/7 0007.
 */

public class ZTBuyProductDetailFragment extends BaseFragment implements ZTBuyProductDetailContract.View {


    @BindView(R.id.invest_detail_top_title_tv)
    TextView investDetailTopTitleTv;
    @BindView(R.id.invest_detail_progect_name_tv)
    TextView investDetailProgectNameTv;
    @BindView(R.id.invest_detail_interest_tv)
    TextView investDetailInterestTv;
    @BindView(R.id.invest_detail_progect_week_tv)
    TextView investDetailProgectWeekTv;
    @BindView(R.id.invest_detail_return_way_tv)
    TextView investDetailReturnWayTv;
    @BindView(R.id.invest_detail_amount_tv)
    TextView investDetailAmountTv;
    @BindView(R.id.invest_detail_earning_tv)
    TextView investDetailEarningTv;
    @BindView(R.id.invest_detail_agreement_tv)
    TextView investDetailAgreementTv;
    @BindView(R.id.investment_recyclerview)
    RecyclerView investmentRecyclerview;
    @BindView(R.id.txt_no_datas)
    TextView txtNoDatas;
    @BindView(R.id.invest_detail_progect_pay_time_tv)
    TextView investDetailProgectPayTimeTv;
    @BindView(R.id.scrollview_info)
    ScrollView scrollviewInfo;
    @BindView(R.id.invest_detail_progect_name_tr)
    TableRow investDetailProgectNameTr;
    @BindView(R.id.invest_detail_agreement_tr)
    TableRow investDetailAgreementTr;
    @BindView(R.id.invest_detail_discount_tv)
    TextView investDetailDiscountTv;
    private ZTBuyProductDetailContract.Presenter mPresenter;
    private List<InvestRewordBean> mLists;
    private MyInvestBean myInvestBean;
    private UserBean mUserBean ;
    private String mBuy_id;
    private String mId;
    private ZTBuyProductDetailBean mZTBuyProductDetailBean;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investdetail;
    }

    public static ZTBuyProductDetailFragment newInstance() {
        return new ZTBuyProductDetailFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new ZTBuyProductDetailPresenter(mActivity, this);
        initViewData();

        Intent mIntent = mActivity.getIntent();
        if (mIntent != null) {
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                myInvestBean = (MyInvestBean) mBundle.getSerializable("BUYPRODUCTBEAN");
                mBuy_id =mBundle.getString("BUY_ID");
            }
            if(myInvestBean!=null&&!TextUtils.isEmpty(myInvestBean.getId()+"")){
                mBuy_id =myInvestBean.getId()+"";
            }

        }

        if(mPresenter!=null){
            if (!TextUtils.isEmpty(mBuy_id)) {
                mPresenter.buyProductDetailHttpRequest(mBuy_id);
            }
        }

    }

    @Override
    public void showErrorTips(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }

    }

    @Override
    public void initViewData() {


    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {

    }

    @Override
    public void setPresenter(ZTBuyProductDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showBuyProductDetailData(ZTBuyProductDetailBean ztBuyProductDetailBean) {


        if(ztBuyProductDetailBean!=null){

            try{

                mZTBuyProductDetailBean =ztBuyProductDetailBean;

                //1为履约中 2为回款 -2失败 其他一律不显示
                if(mZTBuyProductDetailBean.getPrdStatus()==6){
                    investDetailTopTitleTv.setText("状态:已回款");
                }else if(mZTBuyProductDetailBean.getPrdStatus()==5||mZTBuyProductDetailBean.getPrdStatus()==7||mZTBuyProductDetailBean.getPrdStatus()==71){
                    investDetailTopTitleTv.setText("状态:履约中");
                }else if(mZTBuyProductDetailBean.getPrdStatus()==4){
                    if(mZTBuyProductDetailBean.getProductType()==2){
                        investDetailTopTitleTv.setText("状态:锁定期");
                    }else if(mZTBuyProductDetailBean.getProductType()==1){//债转
                        investDetailTopTitleTv.setText("状态:履约中");
                    }
                }else if(mZTBuyProductDetailBean.getPrdStatus()==9){
                    investDetailTopTitleTv.setText("处理中");
                }
                else{
                    investDetailTopTitleTv.setText(" ");
                }
                //名称
                investDetailProgectNameTv.setText(mZTBuyProductDetailBean.getProductCode()+"");
                //预期年化
                investDetailInterestTv.setText(mZTBuyProductDetailBean.getInterest()+"%");
                //项目周期
                if(!TextUtils.isEmpty(mZTBuyProductDetailBean.getStartDate())&&!TextUtils.isEmpty(mZTBuyProductDetailBean.getEndDate())){
                    investDetailProgectWeekTv.setText(mZTBuyProductDetailBean.getStartDate()+"至"+mZTBuyProductDetailBean.getEndDate());
                }else{
                    investDetailProgectWeekTv.setText("--");
                }

                //投资时间
                investDetailProgectPayTimeTv.setText(mZTBuyProductDetailBean.getBuyDate()+ "");

                //还款方式
                if(mZTBuyProductDetailBean.getRefundMethod()==2){
                    investDetailReturnWayTv.setText("按日计息，按月付息，到期还本");
                }else if(mZTBuyProductDetailBean.getRefundMethod()==1){
                    investDetailReturnWayTv.setText("按日计息，到期一次性还本付息");
                }

                //投资本金
                investDetailAmountTv.setText(UiUtils.formatMoneyToBigDecimal(mZTBuyProductDetailBean.getInvestAmt()+"",getResources().getString(R.string.defalut_money_separator))+"元(实付"+UiUtils.formatMoneyToBigDecimal(AmountUtils.sub(mZTBuyProductDetailBean.getInvestAmt()+"",mZTBuyProductDetailBean.getDiscounts()+""),getResources().getString(R.string.defalut_money_separator))+"元)");

                //优惠抵扣
                investDetailDiscountTv.setText(UiUtils.formatMoneyToBigDecimal(mZTBuyProductDetailBean.getDiscounts()+"",getResources().getString(R.string.defalut_money_separator))+"元");

                //预期收益
                if(mZTBuyProductDetailBean.getPrdStatus()==4||mZTBuyProductDetailBean.getPrdStatus()==9){
                    if(mZTBuyProductDetailBean.getProductType()==2){
                        investDetailEarningTv.setText("--");
                    }else if(mZTBuyProductDetailBean.getProductType()==1){//债转
                        investDetailEarningTv.setText(UiUtils.formatMoneyToBigDecimal(mZTBuyProductDetailBean.getIncome()+"",getResources().getString(R.string.defalut_money_separator))+"元");
                    }
                }else{
                    investDetailEarningTv.setText(UiUtils.formatMoneyToBigDecimal(mZTBuyProductDetailBean.getIncome()+"",getResources().getString(R.string.defalut_money_separator))+"元");
                }

                SpannableString sp= null;
                if(mZTBuyProductDetailBean.getProductType()==1){//债转
                    sp = new SpannableString("《债权转让协议》");
                    sp.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.blue5)),0,8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }else if(mZTBuyProductDetailBean.getProductType()==2){//直投
                    sp = new SpannableString("《借款协议》");
                    sp.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.blue5)),0,6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }

                if(mZTBuyProductDetailBean.getPrdStatus()==4){
                    if(mZTBuyProductDetailBean.getProductType()==2){
                        investDetailAgreementTv.setText("--");
                    }else if(mZTBuyProductDetailBean.getProductType()==1){//债转
                        investDetailAgreementTv.setText(sp);
                    }
                }else if(mZTBuyProductDetailBean.getPrdStatus()==9){
                    investDetailAgreementTv.setText("--");
                }
                else{
                    investDetailAgreementTv.setText(sp);
                }


                //还款利息
                List<ZTRefundLists> mLists= ztBuyProductDetailBean.getReturnList();

                if(mLists!=null&&mLists.size()>0){
                    LinearLayoutManager manager = new LinearLayoutManager(mActivity);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    investmentRecyclerview.setLayoutManager(manager);
                    ZTBuyProductDetailAdapter mAdapter = new ZTBuyProductDetailAdapter(mActivity);
                    investmentRecyclerview.setAdapter(mAdapter);
                    mAdapter.setNewData(mLists);
                    investmentRecyclerview.setVisibility(View.VISIBLE);
                    txtNoDatas.setVisibility(View.GONE);
                }else{
                    investmentRecyclerview.setVisibility(View.GONE);
                    txtNoDatas.setVisibility(View.VISIBLE);
                }
            }catch (Exception e){

            }

        }

    }



    @OnClick({R.id.invest_detail_progect_name_tr, R.id.invest_detail_agreement_tr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.invest_detail_progect_name_tr:
                if(mZTBuyProductDetailBean!=null){
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mZTBuyProductDetailBean.getProductId()+"");
                    if(mZTBuyProductDetailBean.getProductType()==1){//债转
                        startActivity(ProductDetailActivity.makeIntent(mActivity,mBundle));
                    }else if(mZTBuyProductDetailBean.getProductType()==2){
                        startActivity(ZTProductDetailActivity.makeIntent(mActivity,mBundle));
                    }
                }
                break;
            case R.id.invest_detail_agreement_tr:

                if(mZTBuyProductDetailBean!=null){
                    String mContent =investDetailAgreementTv.getText().toString();
                    if(!TextUtils.isEmpty(mContent)&&!mContent.equals("--")){
                        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                        if(mZTBuyProductDetailBean.getProductType()==1){//债转
                            UserBean mUserBean =UserInfoManager.getInstance().getLocationUserData();
                            mIntent.putExtra("WebView_URL", H5Urls.H5_URL_INVEST_AGREEMENT_HELP+"?type=1&user_name="+mUserBean.getUserPhone()+"&amt="+mZTBuyProductDetailBean.getInvestAmt()+"&totalIncome="+mZTBuyProductDetailBean.getIncome()+"&proId="+mBuy_id);
                            mIntent.putExtra("WebView_TITLE","债权转让协议");
                        }else if(mZTBuyProductDetailBean.getProductType()==2){//直投
                            if(!TextUtils.isEmpty(mBuy_id)){
                                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_ZT_INVEST_AGREEMENT_HELP+"buyId="+mBuy_id);
                                mIntent.putExtra("WebView_TITLE","借款协议");
                            }
                        }
                        mActivity.startActivity(mIntent);
                    }
                }

                break;
        }
    }


}
