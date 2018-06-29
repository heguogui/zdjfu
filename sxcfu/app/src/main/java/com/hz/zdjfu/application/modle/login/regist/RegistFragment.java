package com.hz.zdjfu.application.modle.login.regist;

import android.content.Context;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.data.even.MainFromTagEven;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.MainActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.PasswordEditText;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 登录界面
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/19 0019
 */
public class RegistFragment extends BaseFragment implements RegistContract.View {

    private static final String TAG = RegistFragment.class.getName();

    @BindView(R.id.regist_input_phonenumber_et)
    EditText registInputPhonenumberEt;
    @BindView(R.id.regist_input_code_et)
    EditText registInputCodeEt;
    @BindView(R.id.regist_sms_code_tv)
    TextView registSmsCodeTv;
    @BindView(R.id.regist_input_password_et)
    PasswordEditText registInputPasswordEt;
    @BindView(R.id.regist_input_inviter_et)
    PasswordEditText registInputInviterEt;
    @BindView(R.id.regist_center_ll)
    LinearLayout registCenterLl;
    @BindView(R.id.regist_submit_btn)
    Button registSubmitBtn;
    @BindView(R.id.regist_agreement_cb)
    CheckBox registAgreementCb;
    @BindView(R.id.regist_agreement_tv)
    TextView registAgreementTv;
    @BindView(R.id.regist_input_phonenumber_iv)
    ImageView registInputPhonenumberIv;
    @BindView(R.id.regist_input_phonenumber_rl)
    RelativeLayout registInputPhonenumberRl;
    @BindView(R.id.regist_phone_tv)
    TextView registPhoneTv;
    @BindView(R.id.regist_input_code_iv)
    ImageView registInputCodeIv;
    @BindView(R.id.regist_code_tv)
    TextView registCodeTv;
    @BindView(R.id.regist_input_password_iv)
    ImageView registInputPasswordIv;
    @BindView(R.id.regist_password_tv)
    TextView registPasswordTv;
    @BindView(R.id.regist_invite_iv)
    ImageView registInviteIv;
    @BindView(R.id.regist_invite_ll)
    LinearLayout registInviteLl;
    @BindView(R.id.regist_input_inviter_iv)
    ImageView registInputInviterIv;
    @BindView(R.id.regist_input_inviter_ll)
    RelativeLayout registInputInviterLl;
    @BindView(R.id.regist_inviter_tv)
    TextView registInviterTv;

    String mForm =null;
    private RegistContract.Presenter mPresenter;
    private String inviterStr;
    public static RegistFragment newInstance() {
        return new RegistFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_regist;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        new RegistPresenter(mActivity, this);

        initViewData();

        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && null != mActivity.getCurrentFocus()) {
                InputMethodManager mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                return mInputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
            }
            return false;
        });

        registInputPhonenumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11 && registInputCodeEt.getText().toString().length() == 6 && registInputPasswordEt.getText().toString().length() >= 6 && registAgreementCb.isChecked()) {
                    registSubmitBtn.setEnabled(true);
                } else {
                    registSubmitBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mContent =registInputPhonenumberEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)){
                    registInputPhonenumberRl.setVisibility(View.VISIBLE);
                }else {
                    registInputPhonenumberRl.setVisibility(View.GONE);
                }
            }
        });

        registInputCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6 && registInputPhonenumberEt.getText().toString().length() == 11 && registInputPasswordEt.getText().toString().length() >= 6 && registAgreementCb.isChecked()) {
                    registSubmitBtn.setEnabled(true);
                } else {
                    registSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        registInputPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6 && registInputPhonenumberEt.getText().toString().length() == 11 && registInputCodeEt.getText().toString().length() == 6 && registAgreementCb.isChecked()) {
                    registSubmitBtn.setEnabled(true);
                } else {
                    registSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        registInputPhonenumberEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(true,false,false,false);
                }
            }
        });

        registInputCodeEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,true,false,false);
                }
            }
        });

        registInputPasswordEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,false,true,false);
                }
            }
        });

        registInputInviterEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,false,false,true);
                }
            }
        });


    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
        }

    }

    @Override
    public void initViewData() {

        Intent mIntent =mActivity.getIntent();
        if(mIntent!=null){
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                mForm =mBundle.getString("FROMPARENT");//
            }
        }


        //获取本地数据填充手机号

        registAgreementCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    String phoneNumStr = registInputPhonenumberEt.getText().toString();
                    String codeNumStr = registInputCodeEt.getText().toString();
                    String passwordNumStr = registInputPasswordEt.getText().toString();
                    if (!TextUtils.isEmpty(phoneNumStr) && phoneNumStr.length() == 11 && !TextUtils.isEmpty(codeNumStr) && codeNumStr.length() == 6 && !TextUtils.isEmpty(passwordNumStr) && passwordNumStr.length() >= 6) {
                        registSubmitBtn.setEnabled(true);
                    }
                } else {
                    registSubmitBtn.setEnabled(false);
                }
            }
        });

        //默认进来是选中
        registAgreementCb.setChecked(true);
        SpannableString sp = new SpannableString(mActivity.getResources().getString(R.string.regist_agreement_hint));
        sp.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue5)),7,17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        registAgreementTv.setText(sp);

        changeLineColor(true,false,false,false);

    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(RegistContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void initView() {


    }

    @Override
    public void saveRegistData(UserBean userBean) {


    }


    @Override
    public void showPhoneNumInvalid() {
        ToastUtils.show(
                mActivity,
                "请输入正确的手机号码"
        );
    }

    @Override
    public void showCodeInvalid() {
        ToastUtils.show(mActivity, getString(R.string.regist_code_error));
    }

    @Override
    public void showPasswordInvalid() {
        ToastUtils.show(mActivity, getString(R.string.regist_password_error));
    }

    @Override
    public void skipActivity() {
        Intent intent = new Intent();
        intent.putExtra("WebView_URL",H5Urls.H5_URL_REGIST_AGREEMENT+"?user_device=android");
        intent.putExtra("WebView_TITLE", getResources().getString(R.string.regist_servers_title));
        intent.setClass(mActivity,WebViewActivity.class);
        startActivity(intent);

    }



    @Override
    public void showSmsCodeInvalid() {
        ToastUtils.show(mActivity, "请输入6位短信验证码");
    }

    @Override
    public void triggerCountDown() {

        registSmsCodeTv.setClickable(false);
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

    @Override
    public void setCodeTips(String  phone) {
        if(!TextUtils.isEmpty(phone)){
            registSmsCodeTv.setTextColor(getResources().getColor(R.color.gray2));
            ToastUtils.show(mActivity, "短信验证码已发送至"+phone+",请查收");
        }
    }

    @Override
    public void registSuccess(boolean state) {
        if (state) {

            if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("ProductDetailFragment")){
                startActivity(ProductDetailActivity.makeIntent(mActivity,null));
                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
                mActivity.finish();
                return;
            }


            //webview  新手
            if(!TextUtils.isEmpty(mForm)&&mForm.equals("WEB_VIEW")){//登录成功 重新刷
                Intent mIntent = new Intent(mActivity, WebViewActivity.class);
                mActivity.startActivity(mIntent);
                mActivity.finish();
                return;
            }

            if(!TextUtils.isEmpty(mForm)&&mForm.equals("WELCOME")){//从引导而来
                startActivity(MainActivity.makeIntent(mActivity,null));
                mActivity.finish();
                return;
            }


            if(!TextUtils.isEmpty(inviterStr)){
                mPresenter.noticationInvestFriends(inviterStr);
            }

            startActivity(MainActivity.makeIntent(mActivity,null));
            EventBus.getDefault().post(new MainFromTagEven("REGIST"));
            mActivity.finish();
        }
    }

    @Override
    public void setSendCodeBtnEnable(boolean flag) {
        if (registSmsCodeTv != null) {
            registSmsCodeTv.setEnabled(flag);
        }
    }

    @Override
    public void focusOnCodeEdit() {
        registInputCodeEt.requestFocus();
    }

    @Override
    public void changeLineColor(boolean phone, boolean code, boolean password, boolean invite) {

        if(phone){
            registPhoneTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            registInputPhonenumberIv.setImageResource(R.mipmap.phone_pressed);
        }else{
            registPhoneTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            registInputPhonenumberIv.setImageResource(R.mipmap.phone_normal);
        }

        if(code){
            registCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            registInputCodeIv.setImageResource(R.mipmap.phone_code_pressed);
        }else{
            registCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            registInputCodeIv.setImageResource(R.mipmap.phone_code_normal);
        }

        if(password){
            registPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            registInputPasswordIv.setImageResource(R.mipmap.password_pressed);
        }else{
            registPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            registInputPasswordIv.setImageResource(R.mipmap.password_normal);
        }

        if(invite){
            registInviterTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            registInputInviterIv.setImageResource(R.mipmap.invest_pressed);
        }else{
            registInviterTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            registInputInviterIv.setImageResource(R.mipmap.invest_normal);
        }



    }

    @Override
    public void showInvestFriends(String phone) {
        if(TextUtils.isEmpty(phone)){
            return;
        }
        mPresenter.noticationInvestFriends(phone);
    }


    @OnClick({R.id.regist_sms_code_tv, R.id.regist_submit_btn, R.id.regist_agreement_tv,R.id.regist_input_phonenumber_rl, R.id.regist_invite_ll,R.id.regist_input_phonenumber_et,R.id.regist_input_code_et,R.id.regist_input_password_et,R.id.regist_input_inviter_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist_sms_code_tv://获取验证码
                String phoneNum = registInputPhonenumberEt.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    showPhoneNumInvalid();
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.codeHttpRequest(phoneNum);
                }

                break;
            case R.id.regist_submit_btn://注册
                String phoneNumStr = registInputPhonenumberEt.getText().toString();
                String codeNumStr = registInputCodeEt.getText().toString();
                String passwordNumStr = registInputPasswordEt.getText().toString();
                inviterStr = registInputInviterEt.getText().toString();
                if (TextUtils.isEmpty(phoneNumStr) ||  phoneNumStr.length() != 11) {
                    showPhoneNumInvalid();
                    return;
                }

                if (TextUtils.isEmpty(codeNumStr)) {
                    showCodeInvalid();
                    return;
                }

                //不能为空 登录密码必须为6-16个字符，数字和英文的组合
                if (TextUtils.isEmpty(passwordNumStr) || TextUtils.isDigitsOnly(passwordNumStr) || passwordNumStr.matches("[a-zA-Z]+")||passwordNumStr.length()<6) {
                    showPasswordInvalid();
                    return;
                }

                if(TextUtils.isEmpty(inviterStr)){
                    inviterStr="";
                }

                if (mPresenter != null) {
                    mPresenter.registHttpRequest(phoneNumStr, passwordNumStr, codeNumStr, inviterStr);
                }
                break;
            case R.id.regist_agreement_tv:
                skipActivity();
                break;
            case R.id.regist_input_phonenumber_rl:

                registInputPhonenumberEt.setText("");
                registInputPhonenumberEt.setHint(getResources().getString(R.string.regist_input_phonenumber_hint));

                break;
            case R.id.regist_invite_ll:
                if(registInputInviterLl!=null&&registInputInviterLl.getVisibility()==View.VISIBLE){
                    registInputInviterLl.setVisibility(View.GONE);
                }else{
                    registInputInviterLl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.regist_input_phonenumber_et:
                changeLineColor(true,false,false,false);
                break;
            case R.id.regist_input_code_et:
                changeLineColor(false,true,false,false);
                break;
            case R.id.regist_input_password_et:
                changeLineColor(false,false,true,false);
                break;
            case R.id.regist_input_inviter_et:
                changeLineColor(false,false,false,true);
                break;
            default:
                break;
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
                registSmsCodeTv.setText(msg.what + "s\n" + "后重发");
            } else {
                registSmsCodeTv.setText("重新发送");
                registSmsCodeTv.setTextColor(getResources().getColor(R.color.blue5));
                registSmsCodeTv.setClickable(true);
            }
        }
    };

}
