package com.hz.zdjfu.application.modle.invest.investdetail;

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
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
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

public class InvestDetailFragment extends BaseFragment implements InvestDetailContract.View {


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
    private InvestDetailContract.Presenter mPresenter;
    private List<InvestRewordBean> mLists;
    private MyInvestBean myInvestBean;
    private UserBean mUserBean ;
    private String mProduct_id;
    private String mId;
    private InvestProductBean product;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investdetail;
    }

    public static InvestDetailFragment newInstance() {
        return new InvestDetailFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new InvestDetailPresenter(mActivity, this);
        initViewData();

        Intent mIntent = mActivity.getIntent();
        if (mIntent != null) {
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                myInvestBean = (MyInvestBean) mBundle.getSerializable("PRODUCTBEAN");
                mProduct_id =mBundle.getString("PRODUCT_ID");
                mId =mBundle.getString("ID");
            }
            if(myInvestBean!=null&&!TextUtils.isEmpty(myInvestBean.getId()+"")){
                mProduct_id =myInvestBean.getId()+"";
            }

        }

        mUserBean = UserInfoManager.getInstance().getLocationUserData();
        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
            if(mPresenter!=null){
                if (myInvestBean!=null&&!TextUtils.isEmpty(myInvestBean.getId()+"")) {
                    mPresenter.investDetailHttpRequest(mUserBean.getUserPhone(),myInvestBean.getId()+"",myInvestBean.getId()+"");
                }else if(!TextUtils.isEmpty(mProduct_id)&&!TextUtils.isEmpty(mId)){
                    mPresenter.investDetailHttpRequest(mUserBean.getUserPhone(),mProduct_id,mId);
                }
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
    public void setPresenter(InvestDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showInvestDetailData(InvestDetailBean mInvestDetailBean) {


        if(mInvestDetailBean!=null){

            product =mInvestDetailBean.getProduct();
            List<InvestReturnDayBean> mLists= mInvestDetailBean.getBuyIncome();

            if(product==null){
                return;
            }

            //1为履约中 2为回款 -2失败 其他一律不显示
            if(product.getStatus().equals("2")){
                investDetailTopTitleTv.setText("状态:已回款");
            }else if(product.getStatus().equals("1")){
                investDetailTopTitleTv.setText("状态:履行中");
            }else if(product.getStatus().equals("-2")){
                investDetailTopTitleTv.setText("状态:失败");
            }else{
                investDetailTopTitleTv.setText(" ");
            }
            //名称
            investDetailProgectNameTv.setText(product.getProduct_name()+"");
            //预期年化
            investDetailInterestTv.setText(product.getProduct_interest()+"%");
            //项目周期
            StringBuffer mStringBuffer = new StringBuffer();
            if(product.getStart_date().contains(" ")){
                String[] start_time =product.getStart_date().split(" ");
                mStringBuffer.append(start_time[0]+ "至");
            }else{
                mStringBuffer.append(product.getStart_date()+ "至");
            }

            if(product.getWill_end_date().contains(" ")){
                String[] end_time =product.getWill_end_date().split(" ");
                mStringBuffer.append(end_time[0]);
            }else{
                mStringBuffer.append(product.getWill_end_date());
            }

            investDetailProgectWeekTv.setText(mStringBuffer.toString()+"");

            //投资时间
//
//            if(myInvestBean!=null){
//                investDetailProgectPayTimeTv.setText(TimeUtils.getYMDtime(myInvestBean.getCreate_time()));
//            }
            if(!TextUtils.isEmpty(product.getCreate_time())){
                try {
                    if(product.getCreate_time().contains(" ")){
                        String[] end_time =product.getCreate_time().split(" ");
                        investDetailProgectPayTimeTv.setText(end_time[0]+ "");
                    }else{
                        investDetailProgectPayTimeTv.setText(product.getCreate_time()+ "");
                    }
                }catch (Exception e){

                }
            }

            //还款方式
            if(mInvestDetailBean.getReturn_method().equals("2")){
                investDetailReturnWayTv.setText("按日计息，按月付息，到期还本");
            }else if(mInvestDetailBean.getReturn_method().equals("1")){
                investDetailReturnWayTv.setText("按日计息，到期一次性还本付息");
            }

            //投资本金
            investDetailAmountTv.setText(UiUtils.formatMoneyToBigDecimal(product.getAmount(),getResources().getString(R.string.defalut_money_separator))+"元(实付"+UiUtils.formatMoneyToBigDecimal(product.getAct_pay_money(),getResources().getString(R.string.defalut_money_separator))+"元)");

            //优惠抵扣
            investDetailDiscountTv.setText(UiUtils.formatMoneyToBigDecimal(AmountUtils.sub(product.getAmount(),product.getAct_pay_money()),getResources().getString(R.string.defalut_money_separator))+"元");

            //预期收益
//            if(myInvestBean!=null){
//                investDetailEarningTv.setText(UiUtils.formatMoneyToBigDecimal(myInvestBean.getWill_income()+"",getResources().getString(R.string.defalut_money_separator))+"元");
//            }
            if(!TextUtils.isEmpty(product.getWill_income())){
                investDetailEarningTv.setText(UiUtils.formatMoneyToBigDecimal(product.getWill_income()+"",getResources().getString(R.string.defalut_money_separator))+"元");
            }

            SpannableString sp = new SpannableString("《债权转让协议》");
            //笔数颜色
            sp.setSpan(new ForegroundColorSpan(mActivity.getResources().getColor(R.color.blue5)),0,8, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            investDetailAgreementTv.setText(sp);

            //还款利息
            List<InvestReturnDayBean> mbuyIncome =mInvestDetailBean.getBuyIncome();
            if(mbuyIncome!=null&&mbuyIncome.size()>0){
                LinearLayoutManager manager = new LinearLayoutManager(mActivity);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                investmentRecyclerview.setLayoutManager(manager);
                InvestDetailAdapter mAdapter =new InvestDetailAdapter(mActivity);
                investmentRecyclerview.setAdapter(mAdapter);

                List<InvestReturnDayBean> mBuyIncome = new ArrayList<>();
                for(int i=0;i<mbuyIncome.size();i++){
                    InvestReturnDayBean bean= mbuyIncome.get(i);
                    bean.setNum(i+1);
                    mBuyIncome.add(bean);
                }

                mAdapter.setNewData(mBuyIncome);
                investmentRecyclerview.setVisibility(View.VISIBLE);
                txtNoDatas.setVisibility(View.GONE);
            }else{
                investmentRecyclerview.setVisibility(View.GONE);
                txtNoDatas.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public void cancleOrderState(boolean state) {

    }

    @Override
    public void resetPayOrder(boolean state) {

    }


    @OnClick({R.id.invest_detail_progect_name_tr, R.id.invest_detail_agreement_tr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.invest_detail_progect_name_tr:
                if(!TextUtils.isEmpty(mProduct_id)){
                    Bundle mBundle = new Bundle();
                    mBundle.putString("PRODUCTID",mProduct_id+"");
                    startActivity(ProductDetailActivity.makeIntent(mActivity,mBundle));
                }
                break;
            case R.id.invest_detail_agreement_tr:
                if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())&&product!=null&&!TextUtils.isEmpty(product.getAmount())&&!TextUtils.isEmpty(product.getWill_income())&&!TextUtils.isEmpty(product.getId())){
                    Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                    mIntent.putExtra("WebView_URL", H5Urls.H5_URL_INVEST_AGREEMENT_HELP+"?type=1&user_name="+mUserBean.getUserPhone()+"&amt="+product.getAmount()+"&totalIncome="+product.getWill_income()+"&proId="+product.getId());
                    mIntent.putExtra("WebView_TITLE","债权转让协议");
                    mActivity.startActivity(mIntent);
                }

                break;
        }
    }


}
