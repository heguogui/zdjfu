package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.unbindbankcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.data.bean.BankCardBean;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 银行卡管理Fragment
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class UnbindBankCardFragment extends BaseFragment implements UnbindBankCardContract.View {

    private static final String TAG = UnbindBankCardFragment.class.getName();
    @BindView(R.id.unbindbankcard_icon)
    ImageView unbindbankcardIcon;
    @BindView(R.id.unbindbankcard_bankname)
    TextView unbindbankcardBankname;
    @BindView(R.id.unbindbankcard_phone_et)
    EditText unbindbankcardPhoneEt;
    @BindView(R.id.unbindbankcard_code_et)
    EditText unbindbankcardCodeEt;
    @BindView(R.id.unbindbankcard_getcode_btn)
    TextView unbindbankcardGetcodeBtn;
    @BindView(R.id.unbindbankcard_submit_btn)
    Button unbindbankcardSubmitBtn;

    private String mPhone;
    private Intent mIntent;
    private Bundle mBundle;
    private BankCardBean mBean;
    private UnbindBankCardContract.Presenter mPresenter;
    private String mTicket;


    public static UnbindBankCardFragment newInstance() {
        return new UnbindBankCardFragment();
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
    public void setPresenter(UnbindBankCardContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unbindbankcard;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new UnBindBankCardPresenter(mActivity, this);
        initViewData();
        onClickListener();

    }

    @Override
    public void initViewData() {

        mIntent = mActivity.getIntent();
        if(mIntent!=null){
            mBundle =mIntent.getBundleExtra(mActivity.BUNDLE);
            if(mBundle!=null){
                mBean = (BankCardBean) mBundle.getSerializable("BANKDETAIL");
                if(mBean!=null){
                    try{
                        if(!TextUtils.isEmpty(mBean.getBank_alias())){
                             String[] icon = UiUtils.getBankCardIcon(mBean.getBank_alias());
                            unbindbankcardIcon.setBackgroundResource(Integer.parseInt(icon[1]));
                            unbindbankcardBankname.setText(icon[2]+"(尾号"+mBean.getBank_no().substring(mBean.getBank_no().length()-4,mBean.getBank_no().length())+")");
                        }

                    }catch (Exception e){

                    }
                }

            }
        }

    }


    @Override
    public void onClickListener() {

        unbindbankcardPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==11&& unbindbankcardCodeEt.getText().toString().length() == 6) {
                    unbindbankcardSubmitBtn.setEnabled(true);
                } else {
                    unbindbankcardSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        unbindbankcardCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==6&& unbindbankcardPhoneEt.getText().toString().length() == 11) {
                    unbindbankcardSubmitBtn.setEnabled(true);
                } else {
                    unbindbankcardSubmitBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void triggerCountDown() {

        unbindbankcardGetcodeBtn.setClickable(false);
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
    public void setCodeTips(boolean unRegisted) {
        if(unRegisted){
            if(!TextUtils.isEmpty(mPhone)){
                unbindbankcardGetcodeBtn.setTextColor(getResources().getColor(R.color.gray2));
                ToastUtils.show(mActivity,"短信验证码已发送至"+mPhone+"，请查收");
            }
        }
    }

    @Override
    public void unbindBankCardSuccess(boolean state) {
        if(state){
            ToastUtils.show(mActivity,"银行解绑成功");
            mActivity.finish();
        }
    }



    @Override
    public void setSendCodeBtnEnable(boolean flag) {
        if (unbindbankcardGetcodeBtn != null) {
            unbindbankcardGetcodeBtn.setEnabled(flag);
        }
    }

    @Override
    public void focusOnCodeEdit() {
        unbindbankcardCodeEt.requestFocus();
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
        ToastUtils.show(mActivity,getString(R.string.regist_code_error));
    }

    @Override
    public void showTicket(String ticket) {
        this.mTicket=ticket;
    }


    @OnClick({R.id.unbindbankcard_getcode_btn, R.id.unbindbankcard_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.unbindbankcard_getcode_btn://获取验证码

                mPhone =unbindbankcardPhoneEt.getText().toString().trim();

                if(StringUtils.isFastClick()){
                    return;
                }
                if (TextUtils.isEmpty(mPhone) || mPhone.length() != 11) {
                    showPhoneNumInvalid();
                    return;
                }
                if(mPresenter!=null){
                    mPresenter.getCodeHttpRequest(mPhone);
                }

                break;
            case R.id.unbindbankcard_submit_btn://解绑

                if(StringUtils.isFastClick()){
                    return;
                }

                UserBean mBean = UserInfoManager.getInstance().getLocationUserData();

                if(mBean==null||TextUtils.isEmpty(mBean.getUserPhone())){
                    return;
                }

                String mCode =unbindbankcardCodeEt.getText().toString().trim();
                if(TextUtils.isEmpty(mCode)){
                    return;
                }

                if(mPresenter!=null){
                    mPresenter.unbindBankCardHttpRequest(mTicket,mCode);
                }

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
                unbindbankcardGetcodeBtn.setText(msg.what + "s\n" + "后重发");
            } else {
                unbindbankcardGetcodeBtn.setText("重新获取");
                unbindbankcardGetcodeBtn.setTextColor(getResources().getColor(R.color.blue4));
                unbindbankcardGetcodeBtn.setClickable(true);
            }
        }
    };


    // 返回上一个画面的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mActivity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
