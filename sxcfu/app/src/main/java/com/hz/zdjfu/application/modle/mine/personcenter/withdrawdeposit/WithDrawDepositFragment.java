package com.hz.zdjfu.application.modle.mine.personcenter.withdrawdeposit;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.bean.WithDrawBean;
import com.hz.zdjfu.application.data.bean.WithDrawCouponList;
import com.hz.zdjfu.application.data.bean.WithDrawFreeBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.AmountUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.CustomProgressDialog;
import com.hz.zdjfu.application.widget.view.EditTextWithClear;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 提现Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class WithDrawDepositFragment extends BaseFragment implements WithDrawDepositContract.View {

    private static final String TAG = WithDrawDepositFragment.class.getName();
    @BindView(R.id.coupon_notication_tv)
    TextView couponNoticationTv;
    @BindView(R.id.coupon_notication_unuser_tv)
    TextView couponNoticationUnuserTv;
    @BindView(R.id.withdraw_deposit_icon)
    ImageView withdrawDepositIcon;
    @BindView(R.id.withdraw_deposit_bankname)
    TextView withdrawDepositBankname;
    @BindView(R.id.withdraw_deposit_explain)
    TextView withdrawDepositExplain;
    @BindView(R.id.withdraw_deposit_balance_tv)
    TextView withdrawDepositBalanceTv;
    @BindView(R.id.withdraw_deposit_amount_et)
    EditTextWithClear withdrawDepositAmountEt;
    @BindView(R.id.withdraw_deposit_cost_tv)
    TextView withdrawDepositCostTv;
    @BindView(R.id.withdraw_deposit_coupon_tv)
    TextView withdrawDepositCouponTv;
    @BindView(R.id.withdraw_deposit_deduct_tv)
    TextView withdrawDepositDeductTv;
    @BindView(R.id.withdraw_deposit_explaint_tv)
    TextView withdrawDepositExplaintTv;
    @BindView(R.id.withdraw_deposit_submit_btn)
    Button withdrawDepositSubmitBtn;
    @BindView(R.id.withdraw_deposit_explain_ll)
    LinearLayout withdrawDepositExplainLl;
    @BindView(R.id.withdraw_deposit_servicerechange_ll)
    LinearLayout withdrawDepositServicerechangeLl;
    @BindView(R.id.coupon_title_notication_rl)
    RelativeLayout couponTitleNotication_rl;

    private WithDrawDepositContract.Presenter mPresenter;
    private CustomProgressDialog mDialog;
    private String mAmount;
    private WithDrawBean bean ;
    private UserBean mUserBean;
    private boolean isSelectCoupon =false;
    private String mCoupon_id;
    private WithDrawCouponList mWithDrawCouponList;
    public static WithDrawDepositFragment newInstance() {
        return new WithDrawDepositFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(WithDrawDepositContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_withdrawdeposit;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new WithDrawDepositPresenter(mActivity, this);
        initViewData();

    }

    @Override
    public void initView() {

    }

    @Override
    public void showBeyondDemand() {

    }

    @Override
    public void showBankCardDetail(WithDrawBean mbean) {
        if(mbean==null)
            return;

        this.bean=mbean;
        if(!TextUtils.isEmpty(bean.getAlias())){
            UiUtils.CreaterBankCardIcon();
            String[] icon =UiUtils.getBankCardIcon(bean.getAlias());
            if(TextUtils.equals("0",icon[1])) {//没有
                withdrawDepositIcon.setBackgroundResource(R.drawable.ic_launcher);
            }else{
                withdrawDepositIcon.setBackgroundResource(Integer.parseInt(icon[1]));
            }
            withdrawDepositBankname.setText(bean.getAlias_name()+"(尾号"+bean.getBankno().substring(bean.getBankno().length()-4)+")");

        }else{
            withdrawDepositIcon.setBackgroundResource(R.drawable.ic_launcher);
        }


        StringBuffer mBuffer = new StringBuffer();
        if(!TextUtils.isEmpty(bean.getSingle_max())){
            if(Double.parseDouble(bean.getSingle_max())>10000){
                mBuffer.append("单笔限额"+Double.parseDouble(bean.getSingle_max())/10000+"万元，");
            }else{
                mBuffer.append("单笔限额"+Double.parseDouble(bean.getSingle_max())+"元，");
            }
        }

        if(!TextUtils.isEmpty(bean.getDay_max())){
            if(Double.parseDouble(bean.getDay_max())>10000){
                mBuffer.append("单日限额"+Double.parseDouble(bean.getDay_max())/10000+"万元");
            }else{
                mBuffer.append("单日限额"+Double.parseDouble(bean.getDay_max())+"元");
            }
        }

        withdrawDepositExplain.setText(mBuffer.toString()+"");

        withdrawDepositBalanceTv.setText("¥"+ UiUtils.formatMoneyToBigDecimal(bean.getBalance(),getResources().getString(R.string.defalut_money_separator)));


    }

    @Override
    public void showCoupon() {

    }

    @Override
    public void showWhitDrawApplyState(String utl) {
//        if(state){
//            if(!TextUtils.isEmpty(mAmount)){
//
//                Bundle mBundle = new Bundle();
//                if(!TextUtils.isEmpty(mAmount)){
//                    mBundle.putString("AMOUNT",mAmount);
//                }
//                startActivity(ApplySuccessActivity.makeIntent(mActivity,mBundle));
//            }
//        }
        if(!TextUtils.isEmpty(utl)){
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            mIntent.putExtra("WebView_URL", utl);
            mActivity.startActivity(mIntent);
        }

    }


    @Override
    public void showUserWithDrawCoupon(WithDrawCouponList result) {

//        this.mWithDrawCouponList =result;
//        if(result!=null&&result.getDataList().size()>0){
//            withdrawDepositCouponTv.setText("使用提现券");
//            withdrawDepositCouponTv.setEnabled(true);
//            withdrawDepositCouponTv.setTextColor(getResources().getColor(R.color.blue5));
//            withdrawDepositCouponTv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
//        }else{
//            withdrawDepositCouponTv.setText("暂无提现卷");
//            withdrawDepositCouponTv.setEnabled(false);
//            withdrawDepositCouponTv.setTextColor(getResources().getColor(R.color.gray3));
//            withdrawDepositCouponTv.setBackgroundResource(R.drawable.stroke_gray_solid_white_radius5);
//        }

    }

    @Override
    public void showWithdrawFree(WithDrawFreeBean withDrawFreeBean) {

//        if(withDrawFreeBean==null){
//            return;
//        }
//
//        if(!TextUtils.isEmpty(withDrawFreeBean.getUser_fee())){
//            mAmount =withdrawDepositAmountEt.getText().toString().trim();
//            if(TextUtils.isEmpty(mAmount)||(isSelectCoupon&&!TextUtils.isEmpty(mCoupon_id))){
//                clearUserFree(true);
//            }else{
//                withdrawDepositCostTv.setText(UiUtils.formatMoneyToBigDecimal(withDrawFreeBean.getUser_fee(),getResources().getString(R.string.defalut_money_separator)));
//                withdrawDepositDeductTv.setText(UiUtils.formatMoneyToBigDecimal(withDrawFreeBean.getUser_fee(),getResources().getString(R.string.defalut_money_separator)));
//            }
//        }else{
//            clearUserFree(true);
//            withdrawDepositCostTv.setText("0.00");
//            withdrawDepositDeductTv.setText("0.00");
//        }
//
//        if(!TextUtils.isEmpty(withDrawFreeBean.getWithdraw_prompt())){
//            withdrawDepositServicerechangeLl.setVisibility(View.VISIBLE);
//        }else {
//            withdrawDepositServicerechangeLl.setVisibility(View.GONE);
//        }


    }

    @Override
    public void initViewData() {

        isSelectCoupon =false;


        mUserBean =UserInfoManager.getInstance().getUserBean();
        //获取银行信息
        if(mPresenter!=null){
            if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                mPresenter.userBankCard(mUserBean.getUserPhone());
                mPresenter.getUserCoupon(mUserBean.getUserPhone());
            }
        }

        withdrawDepositAmountEt.addTextChangedListener(new MyEditTextListener() );

        if(withdrawDepositAmountEt!=null){
            String amount =withdrawDepositAmountEt.getText().toString();
            if(bean!=null&&!TextUtils.isEmpty(bean.getSingle_max())){
                if(!TextUtils.isEmpty(amount)&&Double.parseDouble(amount)>Double.parseDouble(bean.getSingle_max())){
                    withdrawDepositExplainLl.setVisibility(View.VISIBLE);
                }else{
                    withdrawDepositExplainLl.setVisibility(View.GONE);
                }
            }else{
                withdrawDepositExplainLl.setVisibility(View.GONE);
            }
        }else{
            withdrawDepositExplainLl.setVisibility(View.GONE);
        }






    }




    @OnClick({R.id.coupon_notication_unuser_tv, R.id.withdraw_deposit_coupon_tv, R.id.withdraw_deposit_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.coupon_notication_unuser_tv:

                if(isSelectCoupon){
                    isSelectCoupon(false);
                }

                break;
            case R.id.withdraw_deposit_coupon_tv:
                if(!isSelectCoupon){//未选择这选择
                    mAmount =withdrawDepositAmountEt.getText().toString().trim();
                    if(!TextUtils.isEmpty(mAmount)&&Double.parseDouble(mAmount)>0){
                        isSelectCoupon(true);
                    }

                }
                break;
            case R.id.withdraw_deposit_submit_btn:

                if(StringUtils.isFastClick()){
                    return;
                }
                //提现申请
                mAmount =withdrawDepositAmountEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mAmount)){
                    //账户余额小于1元 不可申请提现
//                    if(!TextUtils.isEmpty(bean.getBalance())&&(Double.parseDouble(bean.getBalance())<1||Double.parseDouble(bean.getBalance())==1)){
//                        ToastUtils.show(mActivity,"账户余额≤1元，不可提现");
//                        return;
//                    }
                  UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
                    if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                        if(isSelectCoupon&&!TextUtils.isEmpty(mCoupon_id)){
                            if(!TextUtils.isEmpty(mCoupon_id)){
                                mPresenter.submitWithDrawApply(mUserBean.getUserPhone(),mAmount,mCoupon_id);
                            }
                        }else{
                            mPresenter.submitWithDrawApply(mUserBean.getUserPhone(),mAmount,"");
                        }
                    }
                }


                break;
        }
    }

    private class MyEditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.toString().equals("") || charSequence.toString().equals(" ")) {
                setBtnStatus(false);
            }
            handleEtFormat(charSequence, withdrawDepositAmountEt);

        }

        @Override
        public void afterTextChanged(Editable editable) {

            //输入完 金额大于1元 请求一次 结算利息
            mAmount =withdrawDepositAmountEt.getText().toString().trim();
//            if(TextUtils.isEmpty(mAmount)){
//                clearUserFree(true);
//            }
            if (editable.toString().startsWith(".")) {
                withdrawDepositAmountEt.setText("0.");
                mAmount =withdrawDepositAmountEt.getText().toString().trim();
                withdrawDepositAmountEt.setSelection(mAmount.length());
            }



            //单笔超额判断
            try {
                if (!TextUtils.isEmpty(bean.getSingle_max())&&Double.parseDouble(editable.toString()) > Double.parseDouble(bean.getSingle_max())) {
                    setBtnStatus(false);
                    withdrawDepositExplainLl.setVisibility(View.VISIBLE);
                    return;
                } else if (Double.parseDouble(editable.toString()) == 0||Double.parseDouble(mAmount)==0.00) {
                    setBtnStatus(false);
                    withdrawDepositExplainLl.setVisibility(View.GONE);
                    return;
                } else {
                    withdrawDepositExplainLl.setVisibility(View.GONE);
                    setBtnStatus(true);
                }

            } catch (Exception e) {
            }

            //大于账户余额
            try {
               String amount =withdrawDepositAmountEt.getText().toString().trim();
                Double mAmount =Double.parseDouble(amount);
                if(mAmount==0.00){
                    setBtnStatus(false);
                }
                if (!TextUtils.isEmpty(bean.getBalance())&&!TextUtils.isEmpty(amount)) {

                    if(AmountUtils.compareToGreater(amount,bean.getBalance())){
                        withdrawDepositAmountEt.setText("0.00");
                        withdrawDepositAmountEt.setSelection(withdrawDepositAmountEt.getText().toString().length());
                        ToastUtils.show(mActivity,"账户余额不足");
                       // clearUserFree(true);
                        setBtnStatus(false);
                        return;
                    }else{
                        setBtnStatus(true);
                    }
                }
            } catch (Exception e) {
            }


//            if(!TextUtils.isEmpty(mAmount)&&Double.parseDouble(mAmount)>1){
//                if(mPresenter!=null&&mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
//                    if(isSelectCoupon&&!TextUtils.isEmpty(mCoupon_id)){
//                        mPresenter.withDrawDepositHttpRequest(mUserBean.getUserPhone(),mAmount,mCoupon_id);
//                    }else{
//                        mPresenter.withDrawDepositHttpRequest(mUserBean.getUserPhone(),mAmount,"");
//                    }
//                }
//            }

            String amount =withdrawDepositAmountEt.getText().toString().trim();
            if(!TextUtils.isEmpty(amount)){
                Double mAmount =Double.parseDouble(amount);
                if(mAmount==0.00){
                    setBtnStatus(false);
                }
            }

        }
    }


    public void setBtnStatus(boolean active) {
        if (withdrawDepositSubmitBtn != null) {
            if(active){
                withdrawDepositSubmitBtn.setBackground(getResources().getDrawable(R.drawable.btn_defualt_radius));
            }else{
                withdrawDepositSubmitBtn.setBackground(getResources().getDrawable(R.drawable.gray_radius5));
            }
            withdrawDepositSubmitBtn.setEnabled(active);
        }
    }


    /**
     * 输入框最多只能输入两位小数
     */
    private void handleEtFormat(CharSequence s, EditText editText) {

        try {
            if (s.toString().contains(".")) {

                //截取小数点后两位
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }

                // 屏蔽多个小数点
                if(s.toString().endsWith(".")){
                    int sum =0;
                    for(int i=0;i<s.toString().length();i++){
                        char c = s.toString().charAt(i);
                        if(s.toString().charAt(i)== '.'){
                            sum++;
                        }
                    }
                    if(sum>1){
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

            }

            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                editText.setText(s);
                editText.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    editText.setText(s.subSequence(0, 1));
                    editText.setSelection(1);
                    return;
                }
            }
        }catch (Exception e){

        }

    }


    private  void isSelectCoupon(boolean state){

        this.isSelectCoupon = state;

        if(state){
            withdrawDepositCouponTv.setText("已选提现券");
            couponNoticationTv.setText("您已使用提现券1张,本次提现免手续费");
            couponNoticationUnuserTv.setText("暂不使用");
            withdrawDepositCouponTv.setBackgroundResource(R.drawable.stroke_blue_solid_blue_radius5);
            withdrawDepositCouponTv.setTextColor(getResources().getColor(R.color.white0));
            couponNoticationUnuserTv.setEnabled(true);
            mCoupon_id =mWithDrawCouponList.getDataList().get(0).getId();
            showCouponState(true);
            clearUserFree(true);

        }else{
            withdrawDepositCouponTv.setText("使用提现券");
            withdrawDepositCouponTv.setBackgroundResource(R.drawable.stroke_blue_solid_white_radius5);
            withdrawDepositCouponTv.setTextColor(getResources().getColor(R.color.blue5));
            showCouponState(false);
            mCoupon_id="";
            //输入完 金额大于1元 请求一次 结算利息
            mAmount =withdrawDepositAmountEt.getText().toString().trim();
            if(!TextUtils.isEmpty(mAmount)&&Double.parseDouble(mAmount)>1){
                if(mPresenter!=null&&mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                    if(isSelectCoupon&&!TextUtils.isEmpty(mCoupon_id)){
                        mPresenter.withDrawDepositHttpRequest(mUserBean.getUserPhone(),mAmount,mCoupon_id);
                    }else{
                        mPresenter.withDrawDepositHttpRequest(mUserBean.getUserPhone(),mAmount,"");
                    }
                }

            }
        }


    }



    private void showCouponState(boolean isShow){
        if(isShow){
            couponTitleNotication_rl.setVisibility(View.VISIBLE);
        }else{
            couponTitleNotication_rl.setVisibility(View.GONE);
        }
    }



    private void  clearUserFree(boolean state){

        if(state){
            withdrawDepositCostTv.setText("0.00");
            withdrawDepositDeductTv.setText("0.00");
            withdrawDepositServicerechangeLl.setVisibility(View.GONE);
        }
    }

}
