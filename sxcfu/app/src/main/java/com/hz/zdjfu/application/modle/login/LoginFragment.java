package com.hz.zdjfu.application.modle.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.hz.zdjfu.application.modle.login.regist.RegistActivity;
import com.hz.zdjfu.application.modle.mine.personcenter.forgetpsd.ForgetPsdActivity;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.utils.AuthCodeUtils;
import com.hz.zdjfu.application.utils.StringUtils;
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
public class LoginFragment extends BaseFragment implements LoginContract.View {

    private static final String TAG = LoginFragment.class.getName();

    @BindView(R.id.login_title_iv)
    ImageView loginTitleIv;
    @BindView(R.id.login_input_phone_et)
    EditText loginInputPhoneEt;
    @BindView(R.id.login_input_password_et)
    PasswordEditText loginInputPasswordEt;
    @BindView(R.id.login_phone_password_ll)
    LinearLayout loginPhonePasswordLl;
    @BindView(R.id.login_forget_tv)
    TextView loginForgetTv;
    @BindView(R.id.login_submit_btn)
    Button loginSubmitBtn;
    @BindView(R.id.login_regist_ll)
    RelativeLayout loginRegistLl;
    @BindView(R.id.login_input_phone_iv)
    ImageView loginInputPhoneIv;
    @BindView(R.id.login_input_phonenumber_rl)
    RelativeLayout loginInputPhonenumberRl;
    @BindView(R.id.login_phone_line_tv)
    TextView loginPhoneLineTv;
    @BindView(R.id.login_image_code_iv)
    ImageView loginImageCodeIv;
    @BindView(R.id.login_image_code_et)
    EditText loginImageCodeEt;
    @BindView(R.id.login_image_code_ib)
    ImageButton loginImageCodeIb;
    @BindView(R.id.regist_sms_code_line)
    TextView registSmsCodeLine;
    @BindView(R.id.login_line_image_code_tv)
    TextView loginLineImageCodeTv;
    @BindView(R.id.login_input_password_iv)
    ImageView loginInputPasswordIv;
    @BindView(R.id.login_password_tv)
    TextView loginPasswordTv;
    @BindView(R.id.login_image_code_rl)
    RelativeLayout loginImageCodeRl;


    private LoginContract.Presenter mPresenter;

    private int mCount =1;
    String mForm =null;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        new LoginPresenter(mActivity, this);
        initViewData();
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && null != mActivity.getCurrentFocus()) {
                InputMethodManager mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                return mInputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
            }
            return false;
        });

        loginInputPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(loginImageCodeEt!=null&&loginImageCodeRl!=null&&loginImageCodeRl.getVisibility()==View.VISIBLE){
                    if (s.length() == 11 && loginInputPasswordEt.getText().toString().length() >= 6&& loginImageCodeEt.getText().toString().length() == 4) {
                        loginSubmitBtn.setEnabled(true);
                    } else {
                        loginSubmitBtn.setEnabled(false);
                    }
                }else{
                    if (s.length() == 11 && loginInputPasswordEt.getText().toString().length() >= 6) {
                        loginSubmitBtn.setEnabled(true);
                    } else {
                        loginSubmitBtn.setEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                String mContent =loginInputPhoneEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)){
                    loginInputPhonenumberRl.setVisibility(View.VISIBLE);
                }else {
                    loginInputPhonenumberRl.setVisibility(View.GONE);
                }

            }
        });

        loginInputPasswordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(loginImageCodeEt!=null&&loginImageCodeRl!=null&&loginImageCodeRl.getVisibility()==View.VISIBLE){
                    if (s.length() >= 6 && loginInputPhoneEt.getText().toString().length() == 11&& loginImageCodeEt.getText().toString().length() == 4) {
                        loginSubmitBtn.setEnabled(true);
                    } else {
                        loginSubmitBtn.setEnabled(false);
                    }
                }else{
                    if (s.length() >= 6 && loginInputPhoneEt.getText().toString().length() == 11) {
                        loginSubmitBtn.setEnabled(true);
                    } else {
                        loginSubmitBtn.setEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loginImageCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4 && loginInputPhoneEt.getText().toString().length() == 11 && loginInputPasswordEt.getText().toString().length() >= 6) {
                    loginSubmitBtn.setEnabled(true);
                } else {
                    loginSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loginInputPhoneEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mContent =loginInputPhoneEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mContent)&&mContent.contains("****")){
                    loginInputPhoneEt.setText("");
                }
            }
        });

        loginInputPhoneEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(true,false,false);
                }
            }
        });

        loginImageCodeEt.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeLineColor(false,true,false);
                }
            }
        });

        loginInputPasswordEt.setOnFocusChangeListener(new android.view.View.
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
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg + "");
            if(msg.equals("密码不正确")){
                mCount++;
            }
            isCheckThreeNumber();
        }
    }



    private void isCheckThreeNumber() {
        if(mCount>4||mCount==4){
            if(loginImageCodeRl!=null){
                loginImageCodeRl.setVisibility(View.VISIBLE);
            }

            if(loginImageCodeIb!=null){
                loginImageCodeIb.setImageBitmap(AuthCodeUtils.getInstance().getBitmap());
            }

            if(loginImageCodeEt!=null){
                if(TextUtils.isEmpty(loginImageCodeEt.getText().toString())){
                    loginSubmitBtn.setEnabled(false);
                }
                loginImageCodeEt.requestFocus();
            }
        }
    }

    @Override
    public void initViewData() {

        //获取本地数据填充手机号
        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean!=null&&!TextUtils.isEmpty(mBean.getUserPhone())){
            loginInputPhoneEt.setText(StringUtils.desenPhoneNumber(mBean.getUserPhone()));
            loginInputPhoneEt.setSelection(mBean.getUserPhone().length());
        }

        Intent mIntent =mActivity.getIntent();
        if(mIntent!=null){
            Bundle mBundle = mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                mForm =mBundle.getString("FROMPARENT");//
            }
        }

    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initView() {


    }

    @Override
    public void saveLoginData(UserBean userBean) {

        //保存用户登录信息


    }


    @OnClick({R.id.login_forget_tv, R.id.login_submit_btn, R.id.login_regist_ll,R.id.login_input_phonenumber_rl, R.id.login_image_code_ib})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget_tv:
                String mPhone = loginInputPhoneEt.getText().toString();
                Bundle mBundle = new Bundle();
                if(!TextUtils.isEmpty(mPhone)){
                    mBundle.putString("NEWPHONE",mPhone);
                }
                startActivity(ForgetPsdActivity.makeIntent(mActivity,mBundle));
                break;
            case R.id.login_submit_btn:
                String phoneNumStr = loginInputPhoneEt.getText().toString();
                String passwordNumStr = loginInputPasswordEt.getText().toString();
                String mImageCode =loginImageCodeEt.getText().toString().trim();
                if(!TextUtils.isEmpty(mImageCode)){
                    if(!mImageCode.equals(AuthCodeUtils.getInstance().getCode())){
                        ToastUtils.show(mActivity,"请输入正确的图片验证码");
                        loginImageCodeEt.setText("");
                        loginSubmitBtn.setEnabled(false);
                        loginImageCodeIb.setImageBitmap(AuthCodeUtils.getInstance().getBitmap());
                        return;
                    }
                }else{
                    if(loginImageCodeRl.getVisibility()==View.VISIBLE){
                        ToastUtils.show(mActivity,"请输入图片验证码");
                        return;
                    }
                }

                if(!TextUtils.isEmpty(phoneNumStr)&&phoneNumStr.contains("****")){
                    UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
                    if(phoneNumStr.equals(StringUtils.desenPhoneNumber(mBean.getUserPhone()))){
                        phoneNumStr =UserInfoManager.getInstance().getLocationUserData().getUserPhone();
                    }
                }

                if (TextUtils.isEmpty(phoneNumStr)|| phoneNumStr.length() != 11) {
                    showPhoneNumInvalid();
                    return;
                }

                if (TextUtils.isEmpty(passwordNumStr) || TextUtils.isDigitsOnly(passwordNumStr) || passwordNumStr.matches("[a-zA-Z]+")||passwordNumStr.length()<6) {
                    mCount++;
                    isCheckThreeNumber();
                    showPasswordNumInvalid();
                    return;
                }
                if (mPresenter != null) {
                    mPresenter.loginHttpRequest(phoneNumStr, passwordNumStr);
                }
                break;
            case R.id.login_regist_ll:
                skipActivity();
                break;
            case R.id.login_input_phonenumber_rl:
                loginInputPhoneEt.setText("");
                loginInputPhoneEt.setHint(getResources().getString(R.string.regist_input_phonenumber_hint));
                break;
            case R.id.login_image_code_ib:
                loginImageCodeIb.setImageBitmap(AuthCodeUtils.getInstance().getBitmap());
                break;
            default:

                break;
        }
    }


    @Override
    public void showPhoneNumInvalid() {
        ToastUtils.show(
                mActivity,
                "请输入正确的手机号"
        );
    }

    @Override
    public void showPasswordNumInvalid() {
        ToastUtils.show(
                mActivity,
                "手机号或密码错误"
        );
    }

    @Override
    public void skipActivity() {
        Bundle mBundle = new Bundle();
        if(!TextUtils.isEmpty(mForm)){
            mBundle.putString("FROMPARENT", mForm);
        }
       startActivity(RegistActivity.makeIntent(mActivity,mBundle));
    }

    @Override
    public void loginSuccess(boolean state) {


        if(!TextUtils.isEmpty(mForm)&&(mForm.equals("UPDATEPWD")||mForm.equals("FORGETPWD")||mForm.equals("MINE"))){
            startActivity(MainActivity.makeIntent(mActivity,null));
            EventBus.getDefault().post(new MainFromTagEven("LOGIN"));
        }


        if(!TextUtils.isEmpty(mForm)&&(mForm.equals("HOME"))){
            startActivity(MainActivity.makeIntent(mActivity,null));
            EventBus.getDefault().post(new MainFromTagEven("HOME"));
        }

        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("ProductDetailFragment")){
            startActivity(ProductDetailActivity.makeIntent(mActivity,null));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }

        //webview
        if(!TextUtils.isEmpty(mForm)&&mForm.equals("WEB_VIEW")){//登录成功 重新刷
           Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            mActivity.startActivity(mIntent);
        }

        mActivity.finish();

    }

    @Override
    public void changeLineColor(boolean phone, boolean code, boolean password) {

        if(phone){
            loginPhoneLineTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            loginInputPhoneIv.setImageResource(R.mipmap.phone_pressed);
        }else{
            loginPhoneLineTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            loginInputPhoneIv.setImageResource(R.mipmap.phone_normal);
        }

        if(code){
            loginLineImageCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            loginImageCodeIv.setImageResource(R.mipmap.code_pressed);
        }else{
            loginLineImageCodeTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            loginImageCodeIv.setImageResource(R.mipmap.code_normal);
        }

        if(password){
            loginPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.blue5));
            loginInputPasswordIv.setImageResource(R.mipmap.password_pressed);
        }else{
            loginPasswordTv.setBackgroundColor(mActivity.getResources().getColor(R.color.gray2));
            loginInputPasswordIv.setImageResource(R.mipmap.password_normal);
        }

    }

    @Override
    public void loginThreeNumberError(boolean state) {

        if(state){
            loginImageCodeRl.setVisibility(View.VISIBLE);
            loginImageCodeIb.setImageBitmap(AuthCodeUtils.getInstance().getBitmap());
        }else{
            loginImageCodeRl.setVisibility(View.GONE);
        }

    }




}
