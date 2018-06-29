package com.hz.zdjfu.application.modle.mine.personcenter.forgetpsd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.login.LoginActivity;
import com.hz.zdjfu.application.utils.ActivityManagerUtil;
import com.hz.zdjfu.application.utils.KeyboardUtils;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.view.PasswordEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 * 忘记密码Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class ForgetPsdFragment extends BaseFragment implements ForgetPsdContract.View {

    private static final String TAG = ForgetPsdFragment.class.getName();
    @BindView(R.id.forgetpsd_input_phone_iv)
    ImageView forgetpsdInputPhoneIv;
    @BindView(R.id.forgetpsd_input_phone_et)
    EditText forgetpsdInputPhoneEt;
    @BindView(R.id.forgetpsd_input_phonenumber_rl)
    RelativeLayout forgetpsdInputPhonenumberRl;
    @BindView(R.id.forgetpsd_phone_line_tv)
    TextView forgetpsdPhoneLineTv;
    @BindView(R.id.forgetpsd_input_code_iv)
    ImageView forgetpsdInputCodeIv;
    @BindView(R.id.forgetpsd_input_code_et)
    EditText forgetpsdInputCodeEt;
    @BindView(R.id.forgetpsd_sms_code_tv)
    TextView forgetpsdSmsCodeTv;
    @BindView(R.id.forgetpsd_sms_code_line)
    TextView forgetpsdSmsCodeLine;
    @BindView(R.id.forgetpsd_code_tv)
    TextView forgetpsdCodeTv;
    @BindView(R.id.forgetpsd_input_password_iv)
    ImageView forgetpsdInputPasswordIv;
    @BindView(R.id.forgetpsd_input_password_et)
    PasswordEditText forgetpsdInputPasswordEt;
    @BindView(R.id.forgetpsd_password_tv)
    TextView forgetpsdPasswordTv;
    @BindView(R.id.forgetpsd_center_ll)
    LinearLayout forgetpsdCenterLl;
    @BindView(R.id.forgetpsd_submit_btn)
    Button forgetpsdSubmitBtn;

    private String mPhone;

    private ForgetPsdContract.Presenter mPresenter;

    public static ForgetPsdFragment newInstance() {
        return new ForgetPsdFragment();
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
    public void setPresenter(ForgetPsdContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forgetpsd;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && null != mActivity.getCurrentFocus()) {
                InputMethodManager mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                return mInputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
            }
            return false;
        });

        new ForgetPsdPresenter(mActivity, this);
        initViewData();

    }


    @Override
    public void initViewData() {

        Intent mIntent = mActivity.getIntent();
        if(mIntent!=null){
            Bundle mBundle =mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                mPhone =mBundle.getString("NEWPHONE");
            }
        }

        if(!TextUtils.isEmpty(mPhone)){
            forgetpsdInputPhoneEt.setText(mPhone+"");
            forgetpsdInputPhoneEt.setSelection(mPhone.length());
        }else{
            showDefaultPhone();
        }

        checkStateEt();

    }


    @Override
    public void checkStateEt() {

        forgetpsdInputPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11 && forgetpsdInputPasswordEt.getText().toString().length()>5&&forgetpsdInputCodeEt.getText().toString().length()==6) {
                    forgetpsdSubmitBtn.setEnabled(true);
                } else {
                    forgetpsdSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                String mContent =forgetpsdInputPhoneEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)){
                    forgetpsdInputPhonenumberRl.setVisibility(View.VISIBLE);
                }else {
                    forgetpsdInputPhonenumberRl.setVisibility(View.GONE);
                }

            }
        });

        forgetpsdInputPhoneEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mContent =forgetpsdInputPhoneEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)&&mContent.contains("****")){
                    forgetpsdInputPhoneEt.setText("");
                }
            }
        });

        forgetpsdInputCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==6 && forgetpsdInputPhoneEt.getText().toString().length() == 11&& forgetpsdInputPasswordEt.getText().toString().length() >5) {
                    forgetpsdSubmitBtn.setEnabled(true);
                } else {
                    forgetpsdSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        forgetpsdInputPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >5 && forgetpsdInputPhoneEt.getText().toString().length() == 11&&forgetpsdInputCodeEt.getText().toString().length()==6) {
                    forgetpsdSubmitBtn.setEnabled(true);
                } else {
                    forgetpsdSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        forgetpsdInputPhoneEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(true,false,false);
                }
            }
        });

        forgetpsdInputCodeEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,true,false);
                }
            }
        });

        forgetpsdInputPasswordEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,false,true);
                }
            }
        });


    }

    @Override
    public void showDefaultPhone() {

        //获取本地数据填充手机号
        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
            forgetpsdInputPhoneEt.setText(mBean.getUserPhone());
            forgetpsdInputPhoneEt.setSelection(mBean.getUserPhone().length());
        }

    }



    @Override
    public void showPhoneNumInvalid() {

        ToastUtils.show(mActivity, getString(R.string.forgetpsd_inputphone_error));

    }

    @Override
    public void showCodeInvalid() {
        ToastUtils.show(mActivity, getString(R.string.forgetpsd_code_error));
    }

    @Override
    public void showPasswordInvalid() {
        ToastUtils.show(mActivity, "手机号或密码错误");
    }

    @Override
    public void showImgCodeInvalid() {
        ToastUtils.show(mActivity, getString(R.string.forgetpsd_imgcode_error));
    }

    @Override
    public void showSmsCodeInvalid() {

    }


    @Override
    public void triggerCountDown() {

        forgetpsdSmsCodeTv.setClickable(false);
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
                forgetpsdSmsCodeTv.setText(msg.what + "s\n" + "后重发");
            } else {
                forgetpsdSmsCodeTv.setText("重新发送");
                forgetpsdSmsCodeTv.setTextColor(getResources().getColor(R.color.blue5));
                forgetpsdSmsCodeTv.setClickable(true);
            }
        }
    };


    @Override
    public void setCodeTips(String phone) {

        if(!TextUtils.isEmpty(phone)){
            forgetpsdSmsCodeTv.setTextColor(getResources().getColor(R.color.gray2));
            ToastUtils.show(mActivity, "短信验证码已发送至"+phone+",请查收");
        }
    }

    @Override
    public void setSendCodeBtnEnable(boolean flag) {
        if (forgetpsdSmsCodeTv != null) {
            forgetpsdSmsCodeTv.setEnabled(flag);
        }
    }

    @Override
    public void focusOnCodeEdit() {
        forgetpsdSmsCodeTv.requestFocus();
    }

    @Override
    public void changeLineColor(boolean phoneline, boolean codeline, boolean passwordline) {

        if(phoneline){
            forgetpsdPhoneLineTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            forgetpsdInputPhoneIv.setImageResource(R.mipmap.phone_pressed);
        }else{
            forgetpsdPhoneLineTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            forgetpsdInputPhoneIv.setImageResource(R.mipmap.phone_normal);
        }

        if(codeline){
            forgetpsdCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            forgetpsdInputCodeIv.setImageResource(R.mipmap.phone_code_pressed);
        }else{
            forgetpsdCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            forgetpsdInputCodeIv.setImageResource(R.mipmap.phone_code_normal);
        }

        if(passwordline){
            forgetpsdPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            forgetpsdInputPasswordIv.setImageResource(R.mipmap.password_pressed);
        }else{
            forgetpsdPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            forgetpsdInputPasswordIv.setImageResource(R.mipmap.password_normal);
        }


    }

    @Override
    public void forgetpPwdSuccess(boolean state) {
      if(state){
          Bundle mBundle  =new Bundle();
          mBundle.putString("FROMPARENT","FORGETPWD");
          UserBean mUserBean =UserInfoManager.getInstance().getLocationUserData();
          mUserBean.setLogin(false);
          UserInfoManager.getInstance().saveCurrentUserInfo(mUserBean);
          ActivityManagerUtil.getActivityManager().logout();
          startActivity(LoginActivity.makeIntent(mActivity,mBundle));
          mActivity.finish();
      }
    }


    @OnClick({ R.id.forgetpsd_sms_code_tv, R.id.forgetpsd_submit_btn,R.id.forgetpsd_input_phonenumber_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.forgetpsd_sms_code_tv:

                String phoneNum = forgetpsdInputPhoneEt.getText().toString();
                if(!TextUtils.isEmpty(phoneNum)&&phoneNum.contains("****")){
                    UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                    if(phoneNum.equals(StringUtils.desenPhoneNumber(mBean.getUserPhone()))){
                        phoneNum =UserInfoManager.getInstance().getLocationUserData().getUserPhone();
                    }
                }
                if (TextUtils.isEmpty(phoneNum)) {
                    showPhoneNumInvalid();
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.sendCodeHttpRequest(phoneNum.replaceAll(" ", ""));
                }

                break;
            case R.id.forgetpsd_submit_btn:


                String phoneNumStr = forgetpsdInputPhoneEt.getText().toString();
                String inputCodeNumStr = forgetpsdInputCodeEt.getText().toString();
                String paswordNumStr = forgetpsdInputPasswordEt.getText().toString();

                if(!TextUtils.isEmpty(phoneNumStr)&&phoneNumStr.contains("*")){
                    phoneNumStr =UserInfoManager.getInstance().getLocationUserData().getUserPhone();
                }

                if (TextUtils.isEmpty(phoneNumStr)) {
                    showPhoneNumInvalid();
                    return;
                }

                if (TextUtils.isEmpty(inputCodeNumStr)) {
                    showCodeInvalid();
                    return;
                }

                //不能为空 登录密码必须为6-16个字符，数字和英文的组合
                if (TextUtils.isEmpty(paswordNumStr) || TextUtils.isDigitsOnly(paswordNumStr) || paswordNumStr.matches("[a-zA-Z]")) {
                    showPasswordInvalid();
                    return;
                }

                mPresenter.sureUpdatePsdHttpRequest( phoneNumStr, inputCodeNumStr,paswordNumStr);

                break;

            case R.id.forgetpsd_input_phonenumber_rl:
                forgetpsdInputPhoneEt.setText("");
                forgetpsdInputPhoneEt.setHint(getResources().getString(R.string.regist_input_phonenumber_hint));
                break;

            default:
                break;
        }
    }


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        KeyboardUtils.hideSoftInput(mActivity);
        switch (item.getItemId()) {
            case android.R.id.home:
                //关闭窗体动画显示
                mActivity.finish();
                //关闭activity动画显示
                mActivity.overridePendingTransition(0, R.anim.window_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
