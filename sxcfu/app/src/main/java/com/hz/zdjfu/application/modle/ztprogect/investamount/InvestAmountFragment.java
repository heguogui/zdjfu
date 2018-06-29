package com.hz.zdjfu.application.modle.ztprogect.investamount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.DiscountBean;
import com.hz.zdjfu.application.data.bean.ProductDetail;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.ZTProductDetailBean;
import com.hz.zdjfu.application.data.bean.ZTUserDetailBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.discount.CheckCouponType;
import com.hz.zdjfu.application.modle.discount.DiscountActivity;
import com.hz.zdjfu.application.modle.mine.rechange.RechangeActivity;
import com.hz.zdjfu.application.modle.ztprogect.discount.ZTDiscountActivity;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.view.AddAndSubView;
import com.hz.zdjfu.application.widget.view.InvestAmountView;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *直投投资金额
 * @author HeGuoGui
 * @version 2.0.0
 * @time 2018/04/26
 */

public class InvestAmountFragment extends BaseFragment implements InvestAmonutContract.View {


    @BindView(R.id.view_start_amont)
    TextView viewStartAmont;
    @BindView(R.id.view_lase_amont_tv)
    TextView viewLaseAmontTv;
    @BindView(R.id.view_coupon_next_iv)
    ImageView viewCouponNextIv;
    @BindView(R.id.view_coupon_price_tv)
    TextView viewCouponPriceTv;
    @BindView(R.id.view_balance_price_tv)
    TextView viewBalancePriceTv;
    @BindView(R.id.login_submit_btn)
    Button loginSubmitBtn;
    @BindView(R.id.view_addandsub_tv)
    InvestAmountView viewAddandSubTv;
    @BindView(R.id.view_invest_agreement)
    TextView viewInvestAgreement;
    @BindView(R.id.view_invest_agreement_cb)
    CheckBox viewInvestAgreementCb;


    private InvestAmonutContract.Presenter mPresenter;
    private String mBalance = "500";
    private UserBean mUserBean;
    private ZTProductDetailBean mProductDetail;
    private double investAmount =0.00;
    private CheckCouponType mCheckCoupon;
    private String checkInform;
    private DiscountBean mDiscountBean;

    private boolean isSelect =false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zt_investamount;
    }

    public static InvestAmountFragment newInstance() {
        return new InvestAmountFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new InvestAmountPresenter(mActivity, this);

        Intent mIntent = mActivity.getIntent();
        if (mIntent != null) {
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if (mBundle != null) {
                mProductDetail = (ZTProductDetailBean) mBundle.getSerializable("ZTPRODUCTDETAILBEAN");
            }
        }
        initViewData();
        if(savedInstanceState!=null){
            investAmount =savedInstanceState.getDouble("AMOUNT");
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
        this.isSelect =false;
        this.mUserBean = UserInfoManager.getInstance().getLocationUserData();
        if (viewInvestAgreementCb != null) {
            viewInvestAgreementCb.setChecked(true);
        }
        SpannableString sp = new SpannableString("我已阅读并同意《借款协议范本》");
        sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue5)), 8, 14, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        viewInvestAgreement.setText(sp);


        viewAddandSubTv.setButton(loginSubmitBtn);
        viewAddandSubTv.setCoupon(0.00);

        //设置年化率
        viewAddandSubTv.setYearRate(mProductDetail.getIncome());

        //设置收益天数
        viewAddandSubTv.setEarnDay(mProductDetail.getReturnDays());


        if(mProductDetail.getPayMin()>0){
            //起投金额
            viewStartAmont.setText("起投金额:"+ UiUtils.formatMoneyToBigDecimal(mProductDetail.getPayMin()+"", getResources().getString(R.string.defalut_money_separator))+"元");
            viewAddandSubTv.startAmount(mProductDetail.getPayMin());
        }

        viewAddandSubTv.setAccountBalance("0.00");
        viewCouponPriceTv.setText("请选择优惠券");


        viewAddandSubTv.setOnNumChangeListener(new InvestAmountView.OnNumChangeListener() {
            @Override
            public void onNumChange(double num) {
                investAmount =num;
                //清理所选的优惠数据
                clearCoupon();
            }
        });

        //获取剩余可投金额
        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
            if(mPresenter!=null){
                mPresenter.userInform(mUserBean.getUserPhone());
            }
        }



        showBalance(mProductDetail.getBalance()+"");

    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {

    }

    @Override
    public void setPresenter(InvestAmonutContract.Presenter presenter) {
        this.mPresenter = presenter;
    }



    @Override
    public void refresh() {
        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
            if(mPresenter!=null){

                mPresenter.userInform(mUserBean.getUserPhone());
                //恢复原有红包
                if(investAmount>0){
                    if(viewAddandSubTv!=null){
                        viewAddandSubTv.startInvestAmount(investAmount);
                    }
                }
                //恢复优惠券
                if(mCheckCoupon!=null){
                    showCouponInform(mCheckCoupon);

                }
            }
        }
    }


    @Override
    public void investState(String state) {
        if(!TextUtils.isEmpty(state)){
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            mIntent.putExtra("WebView_URL",state);
            mIntent.putExtra("WebView_TITLE","投资");
            mActivity.startActivity(mIntent);
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY ="INVEST_AMOUNT";
            mActivity.finish();
        }
    }

    @Override
    public void showZTUserDetail(ZTUserDetailBean mZTUserDetailBean) {
        if (mZTUserDetailBean!=null) {
            this.mBalance =String.valueOf(mZTUserDetailBean.getBalance());
            //设置 账户余额
            if(viewAddandSubTv!=null){
                viewAddandSubTv.setAccountBalance(mBalance);
            }
            viewBalancePriceTv.setText("¥" + UiUtils.formatMoneyToBigDecimal(mBalance, getResources().getString(R.string.defalut_money_separator)));
        }
    }

    @Override
    public void showDiscountCoupon(DiscountBean bean) {

        if(bean!=null){
            this.mDiscountBean =bean;
            if(bean.getRecommend()!=null&&bean.getRecommend().size()>0){
                mCheckCoupon =new CheckCouponType();
               for(int i=0;i<bean.getRecommend().size();i++){
                   if(bean.getRecommend().get(i).getType().equals("1")){
                       mCheckCoupon.setmRedPacket(true);
                       mCheckCoupon.setmCheckRedPacket(bean.getRecommend().get(i));
                   }else if(bean.getRecommend().get(i).getType().equals("2")){
                       mCheckCoupon.setmAddInterest(true);
                       mCheckCoupon.setmCheckAddInterest(bean.getRecommend().get(i));
                   }else if(bean.getRecommend().get(i).getType().equals("3")){
                       mCheckCoupon.setmZjzNum(true);
                       mCheckCoupon.setmCheckZJZBean(bean.getRecommend().get(i));
                   }
               }

                StringBuffer mBuffer = new StringBuffer();
                double discount =0.00;
                if(mCheckCoupon.ismRedPacket()){
                    if(mCheckCoupon.getmCheckRedPacket()!=null){
                        discount =mCheckCoupon.getmCheckRedPacket().getAmount();
                    }
                }
                if(mCheckCoupon.ismZjzNum()){
                    if(mCheckCoupon.getmCheckZJZBean()!=null){
                        discount =discount+mCheckCoupon.getmCheckZJZBean().getAmount();
                    }
                }
                if(discount>0){
                    mBuffer.append("抵扣¥"+discount);
                    viewAddandSubTv.setCoupon(discount);
                }else{
                    if(!mCheckCoupon.ismAddInterest()){
                        mBuffer.append("暂无可用优惠券");
                    }
                    viewAddandSubTv.setCoupon(0.00);
                }

                if(discount>0){
                    if(mCheckCoupon.ismAddInterest()){
                        mBuffer.append(",加息"+mCheckCoupon.getmCheckAddInterest().getAmount()+"%");
                    }
                }else{
                    if(mCheckCoupon.ismAddInterest()){
                        mBuffer.append("加息"+mCheckCoupon.getmCheckAddInterest().getAmount()+"%");
                    }
                }

                if(mCheckCoupon.ismAddInterest()){
                    viewAddandSubTv.setAddInterest(mCheckCoupon.getmCheckAddInterest().getAmount());
                }else{
                    viewAddandSubTv.setAddInterest(0.00);
                }

                checkInform ="请选择优惠券";

            }else{
                viewAddandSubTv.setCoupon(0.00);
                viewAddandSubTv.setAddInterest(0.00);
                checkInform ="暂无可用优惠券";
            }

        }else{
            viewAddandSubTv.setCoupon(0.00);
            viewAddandSubTv.setAddInterest(0.00);
            checkInform ="暂无可用优惠券";
        }

        if(!TextUtils.isEmpty(investAmount+"")&&mProductDetail!=null){
            Bundle mBundle = new Bundle();
            mBundle.putString("AMOUNT",investAmount+"");
            mBundle.putString("INCOMEDAYS",mProductDetail.getReturnDays()+"");
            mBundle.putString("PRODUCT_ID",mProductDetail.getId()+"");

            if(mCheckCoupon!=null){//选中的优惠券
                mBundle.putSerializable("CHECKCOUPON",mCheckCoupon);
            }
            startActivityForResult(DiscountActivity.makeIntent(mActivity,mBundle),2);
        }


    }




    public void clearCoupon(){


        if(!isSelect){//如果是其他页面返回则不需要情清空 保留选择优惠券
            if(mCheckCoupon!=null){
                mCheckCoupon =null;
            }
            viewAddandSubTv.setCoupon(0.00);
            viewAddandSubTv.setAddInterest(0.00);
            viewCouponPriceTv.setText("请选择优惠券");
        }

    }




    @Override
    public void showBalance(String investBalance) {

        //设置剩余可投金额
        if(viewAddandSubTv!=null&&!TextUtils.isEmpty(investBalance)){
            viewAddandSubTv.setCanInvestAmount(Double.parseDouble(investBalance));
        }

        //剩余可投
        if (viewLaseAmontTv != null&&!TextUtils.isEmpty(investBalance)) {
            viewLaseAmontTv.setText("剩余可投:" +UiUtils.formatMoneyToBigDecimal(investBalance, getResources().getString(R.string.defalut_money_separator)) + "元");
        }
    }

    @Override
    public void goStartInvest() {
            //当可投金额大于100时 则输入的投资金额最好100起投
            if(mProductDetail.getBalance()>mProductDetail.getPayMin()&&investAmount<mProductDetail.getPayMin()){
                ToastUtils.show(mActivity,"最低起投金额"+mProductDetail.getPayMin()+"元");
                return;
            }

            this.isSelect =true;

            try{
                //余额不足 跳转至 充值界面
                String mContent = loginSubmitBtn.getText().toString();
                if(!TextUtils.isEmpty(mContent)){
                    if(mContent.contains("余额不足")){
                        ZDJFUApplication.getInstance().setParent("ZTINVESTFRAGMENT");

                        String[] content =mContent.split("值");
                        Bundle mBundle = new Bundle();
                        String rechange =content[1].substring(0,content[1].length()-1);
                        if(rechange.contains(",")){
                            rechange =rechange.replace(",","");
                        }
                        mBundle.putString("NEEDMONEY",rechange);
                        startActivity(RechangeActivity.makeIntent(mActivity,mBundle));
                    }else{
                        //去投资
                        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())&&investAmount>0&&mProductDetail!=null){
                            if(mPresenter!=null){
                                if(mCheckCoupon!=null){//选了优惠券
                                    double discount =0.00;
                                    String user_coupon_id="";
                                    String coin="";
                                    String user_gift_id="";
                                    if(mCheckCoupon.ismRedPacket()){
                                        discount =mCheckCoupon.getmCheckRedPacket().getAmount();
                                        user_gift_id =mCheckCoupon.getmCheckRedPacket().getNode_id();
                                    }
                                    if(mCheckCoupon.ismZjzNum()){
                                        discount =mCheckCoupon.getmCheckZJZBean().getAmount();
                                        coin =mCheckCoupon.getmCheckZJZBean().getAmount()+"";
                                    }
                                    if(mCheckCoupon.ismAddInterest()){
                                        user_coupon_id =mCheckCoupon.getmCheckAddInterest().getNode_id();
                                    }
                                    if(!TextUtils.isEmpty(coin)&&Double.parseDouble(coin)>0){
                                        mPresenter.investHttpRequest(mProductDetail.getId()+"",investAmount+"",user_coupon_id,user_gift_id,"1");
                                    }else{
                                        mPresenter.investHttpRequest(mProductDetail.getId()+"",investAmount+"",user_coupon_id,user_gift_id,"0");
                                    }
                                }else{
                                    mPresenter.investHttpRequest(mProductDetail.getId()+"",investAmount+"","","","0");
                                }
                            }
                        }

                    }
                }

            }catch (Exception e){

            }
    }

    @Override
    public void showCouponInform(CheckCouponType mCheckCoupon) {

        if(mCheckCoupon!=null){
            StringBuffer mBuffer = new StringBuffer();
            double discount =0.00;
            if(mCheckCoupon.ismRedPacket()){
                discount =mCheckCoupon.getmCheckRedPacket().getAmount();
            }
            if(mCheckCoupon.ismZjzNum()){
                discount =mCheckCoupon.getmCheckZJZBean().getAmount();
            }

            if(discount>0){
                viewAddandSubTv.setCoupon(discount);
                mBuffer.append("抵扣¥"+discount);
            }else{
                if(!mCheckCoupon.ismAddInterest()){
                    mBuffer.append("请选择优惠券");
                }
                viewAddandSubTv.setCoupon(0.00);
            }

            if(discount>0){
                if(mCheckCoupon.ismAddInterest()){
                    mBuffer.append(",加息"+mCheckCoupon.getmCheckAddInterest().getAmount()+"%");
                }
            }else{
                if(mCheckCoupon.ismAddInterest()){
                    mBuffer.append("加息"+mCheckCoupon.getmCheckAddInterest().getAmount()+"%");
                }
            }

            if(mCheckCoupon.ismAddInterest()){
                viewAddandSubTv.setAddInterest(mCheckCoupon.getmCheckAddInterest().getAmount());
            }else{
                viewAddandSubTv.setAddInterest(0.00);
            }
            checkInform =mBuffer.toString();

        }else{
            viewAddandSubTv.setAddInterest(0.00);
            viewAddandSubTv.setCoupon(0.00);
            checkInform ="请选择优惠券";
        }
        if(viewCouponPriceTv!=null){
            viewCouponPriceTv.setText(checkInform+"");
        }
    }


    @OnClick({R.id.view_invest_agreement, R.id.login_submit_btn,R.id.view_coupon_price_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_invest_agreement:
                if(mProductDetail!=null&&mProductDetail.getLoaner()!=null){
                    Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                    if(mProductDetail.getLoaner().getLoanerType()==1){//个人
                        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_ZT_INVEST_AGREEMENT_HELP+"loanType=1");
                    }else if(mProductDetail.getLoaner().getLoanerType()==2){//企业
                        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_ZT_INVEST_AGREEMENT_HELP+"loanType=2");
                    }
                    mIntent.putExtra("WebView_TITLE","借款协议范本");
                    mActivity.startActivity(mIntent);
                }
                break;
            case R.id.login_submit_btn:
                if(StringUtils.isFastClick()){
                    return;
                }

                if(viewInvestAgreementCb!=null){
                    if(!viewInvestAgreementCb.isChecked()){
                        ToastUtils.show(mActivity,"请选择我已阅读并同意《借款协议范本》");
                        return;
                    }
                }

                goStartInvest();

                break;
            case R.id.view_coupon_price_rl:

                if(!TextUtils.isEmpty(investAmount+"")&&mProductDetail!=null){
                    Bundle mBundle = new Bundle();
                    mBundle.putString("AMOUNT",investAmount+"");
                    mBundle.putString("PRODUCT_ID",mProductDetail.getId()+"");
                    if(mCheckCoupon!=null){//选中的优惠券
                        mBundle.putSerializable("CHECKCOUPON",mCheckCoupon);
                    }
                    startActivityForResult(ZTDiscountActivity.makeIntent(mActivity,mBundle),2);
                }

                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2){
            if(resultCode== Activity.RESULT_OK){
                if(data!=null){
                    Bundle mBundle =data.getBundleExtra("CHECKCOUPONBUNDLE");
                    if(mBundle!=null){
                        mCheckCoupon = (CheckCouponType) mBundle.getSerializable("CHECKCOUPON");
                    }
                    showCouponInform(mCheckCoupon);
                }else{
                    viewAddandSubTv.setAddInterest(0.00);
                    viewAddandSubTv.setCoupon(0.00);
                    checkInform ="请选择优惠券";
                    viewCouponPriceTv.setText(checkInform+"");
                }

            }

        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble("AMOUNT",investAmount);
        super.onSaveInstanceState(outState);
    }








}
