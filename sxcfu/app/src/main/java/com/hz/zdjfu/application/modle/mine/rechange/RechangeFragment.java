package com.hz.zdjfu.application.modle.mine.rechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.RechangeDetail;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.mine.rechange.rechangehelp.RechangeHelpActivity;
import com.hz.zdjfu.application.modle.mine.rechange.rechangesuccess.RechangeSuccessActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;
import com.hz.zdjfu.application.widget.dialog.SureSMSDialog;
import com.hz.zdjfu.application.widget.view.EditTextWithClear;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 我的资产界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RechangeFragment extends BaseFragment implements RechangeContract.View {

    private static final String TAG = RechangeFragment.class.getName();
    @BindView(R.id.rechange_bank_icon)
    ImageView rechangeBankIcon;
    @BindView(R.id.rechange_bank_number)
    TextView rechangeBankNumber;
    @BindView(R.id.rechange_bank_stipulate)
    TextView rechangeBankStipulate;
    @BindView(R.id.rechange_account_balance_tv)
    TextView rechangeAccountBalanceTv;
    @BindView(R.id.rechange_input_amount_et)
    EditTextWithClear rechangeInputAmountEt;
    @BindView(R.id.rechange_out_amount_tv)
    TextView rechangeOutAmountTv;
    @BindView(R.id.rechange_sure_btn)
    Button rechangeSureBtn;


    private RechangeContract.Presenter mPresenter;
    private BankCardBean mBean;
    private SureSMSDialog mDialog;
    private String rechangeAmount;
    private String mAccountBalance;
    private String inputMoneyNew = "";
    private String MAX_NUM ="100000";
    private Button resetCodeBtn;
    private boolean reset;
    private String mAmount;
    private RechangeDetail mRechangeDetail;
    private UserBean mUserBean;
    public static RechangeFragment newInstance() {
        return new RechangeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rechange;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new RechangePresenter(mActivity, this);

        Intent mIntent =mActivity.getIntent();
        if(mIntent!=null){
            Bundle mBundle =mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                mAmount =mBundle.getString("NEEDMONEY");
            }
        }

        if(!TextUtils.isEmpty(mAmount)){
            rechangeInputAmountEt.setText(mAmount);
            rechangeInputAmountEt.setSelection(mAmount.length());
            setBtnStatus(true);
        }


        initViewData();

    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }

    }

    @Override
    public void initViewData() {
        rechangeInputAmountEt.addTextChangedListener(new MyEditTextListener() );
        SpannableString sp = new SpannableString("当前充值金额超出限额,请分笔充值或使用其他充值方式>");
        sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue5)),17,26, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        rechangeOutAmountTv.setText(sp);
        rechangeOutAmountTv.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserBean = UserInfoManager.getInstance().getUserBean();
        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
            mPresenter.rechangeDetailHttpRequest(mUserBean.getUserPhone());
        }
    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(RechangeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }



    @Override
    public void showRechangeDetail(RechangeDetail result) {

        if(result==null){
            return;
        }
        this.mRechangeDetail =result;
        if(!TextUtils.isEmpty(result.getAlias())){
            try{
                UiUtils.CreaterBankCardIcon();
                String[] icon =UiUtils.getBankCardIcon(result.getAlias());
                if(TextUtils.equals("0",icon[1])) {//没有
                    rechangeBankIcon.setBackgroundResource(R.drawable.ic_launcher);
                }else{
                    rechangeBankIcon.setBackgroundResource(Integer.parseInt(icon[1]));
                }
                rechangeBankNumber.setText(icon[2]+"(尾号"+result.getBankno().substring(result.getBankno().length()-4)+")");
            }catch (Exception e){

            }

        }else{
            rechangeBankIcon.setBackgroundResource(R.drawable.ic_launcher);
        }

         StringBuffer mBuffer = new StringBuffer();
        if(!TextUtils.isEmpty(result.getSingle_max())){
            if(Double.parseDouble(result.getSingle_max())>10000){
                mBuffer.append("单笔限额"+Double.parseDouble(result.getSingle_max())/10000+"万元，");
            }else{
                mBuffer.append("单笔限额"+Double.parseDouble(result.getSingle_max())+"元，");
            }
        }

        if(!TextUtils.isEmpty(result.getDay_max())){
            if(Double.parseDouble(result.getDay_max())>10000){
                mBuffer.append("单日限额"+Double.parseDouble(result.getDay_max())/10000+"万元");
            }else{
                mBuffer.append("单日限额"+Double.parseDouble(result.getDay_max())+"元");
            }
        }

        rechangeBankStipulate.setText(mBuffer.toString()+"");
        this.mAccountBalance =result.getBalance();
        rechangeAccountBalanceTv.setText("¥"+ UiUtils.formatMoneyToBigDecimal(result.getBalance(),getResources().getString(R.string.defalut_money_separator)));




    }



    @Override
    public void rightOnClickViewListener() {

//        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
//        mIntent.putExtra("WebView_URL", H5Urls.H5_URL_RECHANGE_HELP);
//        mIntent.putExtra("WebView_TITLE","帮助");
//        mActivity.startActivity(mIntent);

    }


    @Override
    public void validCodeSuccess(boolean state) {
        if(mDialog!=null&&mDialog.isShowing()){
            mDialog.dismiss();
            mDialog =null;
        }
        if(state){
            ToastUtils.show(mActivity,"充值成功");
            Bundle mBundle = new Bundle();
            if(!TextUtils.isEmpty(rechangeAmount)){
                if(mRechangeDetail!=null&&!TextUtils.isEmpty(mRechangeDetail.getBalance())){
                    mBundle.putString("BALANCE",mRechangeDetail.getBalance()+"");
                }
                mBundle.putString("AMOUNT",rechangeAmount);
            }
           startActivity(RechangeSuccessActivity.makeIntent(mActivity,mBundle));
        }else {
            ToastUtils.show(mActivity,"充值失败");
        }
    }

    @Override
    public void showAccountBalance(String balance) {
        this.mAccountBalance =balance;
        if(rechangeAccountBalanceTv!=null){
            if(TextUtils.isEmpty(balance)){
                rechangeAccountBalanceTv.setText("0.00");
            }else{
                rechangeAccountBalanceTv.setText(UiUtils.formatMoneyToBigDecimal(balance,getResources().getString(R.string.defalut_money_separator)));
            }
        }

    }


    @OnClick({R.id.rechange_out_amount_tv, R.id.rechange_sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rechange_out_amount_tv://跳转至大额充值方式
                startActivity(RechangeHelpActivity.makeIntent(mActivity,null));
                break;
            case R.id.rechange_sure_btn:

                if(StringUtils.isFastClick()){
                    return;
                }
                String mAmount =rechangeInputAmountEt.getText().toString().trim();
                if(TextUtils.isEmpty(mAmount)){
                    ToastUtils.show(mActivity,"充值金额不能为空");
                }

                UserBean mUserBean = UserInfoManager.getInstance().getUserBean();
                if(mUserBean==null||TextUtils.isEmpty(mUserBean.getUserPhone())){
                    return;
                }

                this.rechangeAmount =mAmount;
                if(mPresenter!=null){
                    mPresenter.rechange(mAmount);
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
            handleEtFormat(charSequence, rechangeInputAmountEt);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().startsWith(".")) {
                rechangeInputAmountEt.setText("0.");
                rechangeInputAmountEt.setSelection(rechangeInputAmountEt.getText().toString().length());
            }

            try {
                if (Double.parseDouble(editable.toString()) >Double.parseDouble(mRechangeDetail.getSingle_max())) {
                    setBtnStatus(false);
                    rechangeOutAmountTv.setVisibility(View.VISIBLE);

                } else if (Float.parseFloat(editable.toString()) == 0||Double.parseDouble(rechangeInputAmountEt.getText().toString())==0.00) {
                    setBtnStatus(false);
                    rechangeOutAmountTv.setVisibility(View.GONE);
                } else {
                    rechangeOutAmountTv.setVisibility(View.GONE);
                    setBtnStatus(true);
                }


            } catch (Exception e) {
            }
        }
    }


    public void setBtnStatus(boolean active) {
        if (rechangeSureBtn != null) {
            rechangeSureBtn.setEnabled(active);
        }
    }

    @Override
    public void showSHrechangeUrl(String url) {
        if(!TextUtils.isEmpty(url)){
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            mIntent.putExtra("WebView_URL",url);
            mIntent.putExtra("WebView_TITLE","充值");
            mActivity.startActivity(mIntent);
        }
    }


    /**
     * 输入框最多只能输入两位小数
     */
    private void handleEtFormat(CharSequence s, EditText editText) {

        try {
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }

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



    @Override
    public void triggerCountDown() {
        if(resetCodeBtn!=null){
            resetCodeBtn.setBackgroundResource(R.drawable.gray_radius5);
            resetCodeBtn.setClickable(false);
            new Thread(new Runnable() {
                int sum = 60;

                @Override
                public void run() {
                    while (sum >= 0) {
                        if (mActivity == null) {
                            return;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sum--;
                        mHandler.sendEmptyMessage(sum);
                    }
                }
            }).start();

        }

    }



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            if (mActivity == null) {
                return;
            }
            if (msg.what > 0) {
                resetCodeBtn.setText(msg.what + "s");
            } else {
                resetCodeBtn.setText("重新获取");
                resetCodeBtn.setBackground(getResources().getDrawable(R.drawable.stroke_blue_solid_blue_radius5));
                resetCodeBtn.setClickable(true);
            }
        }
    };



}
