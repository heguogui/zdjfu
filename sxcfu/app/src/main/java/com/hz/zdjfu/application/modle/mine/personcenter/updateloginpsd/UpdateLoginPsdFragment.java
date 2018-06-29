package com.hz.zdjfu.application.modle.mine.personcenter.updateloginpsd;

import android.content.Context;
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
 * 个人中心Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class UpdateLoginPsdFragment extends BaseFragment implements UpdateLoginPsdContract.View {

    private static final String TAG = UpdateLoginPsdFragment.class.getName();


    @BindView(R.id.updateloginpsd_input_phone_iv)
    ImageView updateloginpsdInputPhoneIv;
    @BindView(R.id.updateloginpsd_input_phone_et)
    EditText updateloginpsdInputPhoneEt;
    @BindView(R.id.updateloginpsd_input_phonenumber_rl)
    RelativeLayout updateloginpsdInputPhonenumberRl;
    @BindView(R.id.updateloginpsd_phone_line_tv)
    TextView updateloginpsdPhoneLineTv;
    @BindView(R.id.updateloginpsd_input_code_iv)
    ImageView updateloginpsdInputCodeIv;
    @BindView(R.id.updateloginpsd_input_code_et)
    EditText updateloginpsdInputCodeEt;
    @BindView(R.id.updateloginpsd_sms_code_tv)
    TextView updateloginpsdSmsCodeTv;
    @BindView(R.id.updateloginpsd_sms_code_line)
    TextView updateloginpsdSmsCodeLine;
    @BindView(R.id.updateloginpsd_code_tv)
    TextView updateloginpsdCodeTv;
    @BindView(R.id.updateloginpsd_input_password_iv)
    ImageView updateloginpsdInputPasswordIv;
    @BindView(R.id.updateloginpsd_input_password_et)
    PasswordEditText updateloginpsdInputPasswordEt;
    @BindView(R.id.updateloginpsd_password_tv)
    TextView updateloginpsdPasswordTv;
    @BindView(R.id.updateloginpsd_center_ll)
    LinearLayout updateloginpsdCenterLl;
    @BindView(R.id.updateloginpsd_submit_btn)
    Button updateloginpsdSubmitBtn;


    private UpdateLoginPsdContract.Presenter mPresenter;

    public static UpdateLoginPsdFragment newInstance() {
        return new UpdateLoginPsdFragment();
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
    public void setPresenter(UpdateLoginPsdContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_updateloginpsd;
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

        new UpdateLoginPsdPresenter(mActivity,this);
        initViewData();

    }


    @Override
    public void initViewData() {
        checkStateEt();
        showDefaultPhone();

    }


    @Override
    public void checkStateEt() {

        updateloginpsdInputPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11 && updateloginpsdInputPasswordEt.getText().toString().length()>5&&updateloginpsdInputCodeEt.getText().toString().length()==6) {
                    updateloginpsdSubmitBtn.setEnabled(true);
                } else {
                    updateloginpsdSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                String mContent =updateloginpsdInputPhoneEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)){
                    updateloginpsdInputPhonenumberRl.setVisibility(View.VISIBLE);
                }else {
                    updateloginpsdInputPhonenumberRl.setVisibility(View.GONE);
                }

            }
        });
        updateloginpsdInputPhoneEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mContent =updateloginpsdInputPhoneEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)&&mContent.contains("****")){
                    updateloginpsdInputPhoneEt.setText("");
                }
            }
        });


        updateloginpsdInputCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==6 && updateloginpsdInputPhoneEt.getText().toString().length() == 11&& updateloginpsdInputPasswordEt.getText().toString().length() >5) {
                    updateloginpsdSubmitBtn.setEnabled(true);
                } else {
                    updateloginpsdSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        updateloginpsdInputPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >5 && updateloginpsdInputPhoneEt.getText().toString().length() == 11&&updateloginpsdInputCodeEt.getText().toString().length()==6) {
                    updateloginpsdSubmitBtn.setEnabled(true);
                } else {
                    updateloginpsdSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        updateloginpsdInputPhoneEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(true,false,false);
                }
            }
        });

        updateloginpsdInputCodeEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,true,false);
                }
            }
        });

        updateloginpsdInputPasswordEt.setOnFocusChangeListener(new android.view.View.
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
            updateloginpsdInputPhoneEt.setText(StringUtils.desenPhoneNumber(mBean.getUserPhone()));
            updateloginpsdInputPhoneEt.setSelection(mBean.getUserPhone().length());
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

        updateloginpsdSmsCodeTv.setClickable(false);
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
                updateloginpsdSmsCodeTv.setText(msg.what + "s\n" + "后重发");
            } else {
                updateloginpsdSmsCodeTv.setText("重新发送");
                updateloginpsdSmsCodeTv.setTextColor(getResources().getColor(R.color.blue5));
                updateloginpsdSmsCodeTv.setClickable(true);
            }
        }
    };


    @Override
    public void setCodeTips(String phone) {

        if(!TextUtils.isEmpty(phone)){
            updateloginpsdSmsCodeTv.setTextColor(getResources().getColor(R.color.gray2));
            ToastUtils.show(mActivity, "短信验证码已发送至"+phone+",请查收");
        }
    }

    @Override
    public void setSendCodeBtnEnable(boolean flag) {
        if (updateloginpsdSmsCodeTv != null) {
            updateloginpsdSmsCodeTv.setEnabled(flag);
        }
    }

    @Override
    public void focusOnCodeEdit() {
        updateloginpsdSmsCodeTv.requestFocus();
    }

    @Override
    public void changeLineColor(boolean phoneline, boolean codeline, boolean passwordline) {

        if(phoneline){
            updateloginpsdPhoneLineTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            updateloginpsdInputPhoneIv.setImageResource(R.mipmap.phone_pressed);
        }else{
            updateloginpsdPhoneLineTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            updateloginpsdInputPhoneIv.setImageResource(R.mipmap.phone_normal);
        }

        if(codeline){
            updateloginpsdCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            updateloginpsdInputCodeIv.setImageResource(R.mipmap.phone_code_pressed);
        }else{
            updateloginpsdCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            updateloginpsdInputCodeIv.setImageResource(R.mipmap.phone_code_normal);
        }

        if(passwordline){
            updateloginpsdPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            updateloginpsdInputPasswordIv.setImageResource(R.mipmap.password_pressed);
        }else{
            updateloginpsdPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            updateloginpsdInputPasswordIv.setImageResource(R.mipmap.password_normal);
        }


    }

    @Override
    public void updataSuccess(boolean state) {
        if(state){
            Bundle mBundle  =new Bundle();
            mBundle.putString("FROMPARENT","UPDATEPWD");
            UserBean mUserBean =UserInfoManager.getInstance().getLocationUserData();
            mUserBean.setLogin(false);
            UserInfoManager.getInstance().saveCurrentUserInfo(mUserBean);
            ActivityManagerUtil.getActivityManager().logout();
            startActivity(LoginActivity.makeIntent(mActivity,mBundle));
            mActivity.finish();
        }
    }


    @OnClick({ R.id.updateloginpsd_sms_code_tv, R.id.updateloginpsd_submit_btn,R.id.updateloginpsd_input_phonenumber_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.updateloginpsd_sms_code_tv:

                String phoneNum = updateloginpsdInputPhoneEt.getText().toString();

                if(!TextUtils.isEmpty(phoneNum)&&phoneNum.contains("****")){
                    UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                    if(phoneNum.equals(StringUtils.desenPhoneNumber(mBean.getUserPhone()))){
                        phoneNum =UserInfoManager.getInstance().getLocationUserData().getUserPhone();
                    }
                }
                if (TextUtils.isEmpty(phoneNum)||phoneNum.length()!=11) {
                    showPhoneNumInvalid();
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.sendCodeHttpRequest(phoneNum.replaceAll(" ", ""));
                }

                break;
            case R.id.updateloginpsd_submit_btn:


                String phoneNumStr = updateloginpsdInputPhoneEt.getText().toString();
                String inputCodeNumStr = updateloginpsdInputCodeEt.getText().toString();
                String paswordNumStr = updateloginpsdInputPasswordEt.getText().toString();

                if(!TextUtils.isEmpty(phoneNumStr)&&phoneNumStr.contains("*")){
                    phoneNumStr =UserInfoManager.getInstance().getLocationUserData().getUserPhone();
                }
                if (TextUtils.isEmpty(phoneNumStr) || phoneNumStr.length()!=11) {
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

            case R.id.updateloginpsd_input_phonenumber_rl:
                updateloginpsdInputPhoneEt.setText("");
                updateloginpsdInputPhoneEt.setHint(getResources().getString(R.string.regist_input_phonenumber_hint));
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
