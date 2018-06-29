package com.hz.zdjfu.application.modle.mine.personcenter.openshanghaibankaccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.BankTypeBean;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.WheelViewDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 *绑定银行卡
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/10/12 0012.
 */

public class BindBankCardFragment extends OpenSHBankBaseFragment implements BindBankCardContract.View{

    private static final String TAG =BindBankCardFragment.class.getName();

    @BindView(R.id.bindbankcard_bankcard_et)
    EditText bindbankcardBankcardEt;
    @BindView(R.id.bindbankcard_phone_et)
    EditText bindbankcardPhoneEt;
    @BindView(R.id.bindbankcard_code_et)
    EditText bindbankcardCodeEt;
    @BindView(R.id.bindbankcard_getcode_tv)
    TextView bindbankcardGetcodeTv;
    @BindView(R.id.bindbankcard_explain_tv)
    TextView bindbankcardExplainTv;
    @BindView(R.id.bindbankcard_next_btn)
    Button bindbankcardNextBtn;
    @BindView(R.id.bindbankcard_type_tv)
    TextView bindbankcardTypeTt;
    @BindView(R.id.openshbankaccount_type_rl)
    RelativeLayout openshbankaccountTypeRl;
    @BindView(R.id.bindbankcard_province_tv)
    TextView bindbankcardProvinceTt;
    @BindView(R.id.openshbankaccount_province_rl)
    RelativeLayout openshbankaccountProvinceRl;



    private BindBankCardContract.Presenter mPresenter;

   //当前省的名称
    protected String mCurrentProviceName;
   //当前市的名称
    protected String mCurrentCityName;
    //银行类型
    protected BankTypeBean mBankTypeBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bindbankcard;
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

        bindbankcardBankcardEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >1 && bindbankcardCodeEt.getText().toString().length() >3 && bindbankcardPhoneEt.getText().toString().length() == 11) {
                    bindbankcardNextBtn.setEnabled(true);
                } else {
                    bindbankcardNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bindbankcardPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11 && bindbankcardCodeEt.getText().toString().length() >3 && bindbankcardBankcardEt.getText().toString().length() >1) {
                    bindbankcardNextBtn.setEnabled(true);
                } else {
                    bindbankcardNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bindbankcardCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >3 && bindbankcardPhoneEt.getText().toString().length() == 11 && bindbankcardBankcardEt.getText().toString().length() >1) {
                    bindbankcardNextBtn.setEnabled(true);
                } else {
                    bindbankcardNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        initViewData();

    }


    @Override
    public void showBindBankCardSuccess(String state) {

        if(TextUtils.isEmpty(state)){
            ToastUtils.show(mActivity,"返回的URL为空");
            return;
        }

        if(state.equals("支付密码已经设置")){
            ToastUtils.show(mActivity,"绑卡成功");
            mActivity.finish();
        }else{
            Intent mIntent = new Intent(mActivity, WebViewActivity.class);
            mIntent.putExtra("WebView_URL",state);
            mIntent.putExtra("WebView_TITLE","设置支付密码");
            mIntent.putExtra("WebView_PARENT","BINDBANKCARDSUCCESS");
            mActivity.startActivity(mIntent);
            mActivity.finish();
        }


    }

    @Override
    public void showProviceLists(ProviceLists lists) {
        if(lists==null){
            return;
        }
        WheelViewDialog provice = new WheelViewDialog(mActivity, lists.getDataList(), new WheelViewDialog.ViewOnCleckListener() {
            @Override
            public void callBack(String type) {
                mCurrentProviceName =type;
                bindbankcardProvinceTt.setText(mCurrentProviceName+"");
            }
        });
        provice.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


    }

    @Override
    public void showCityLists(CityLists lists) {

        if(lists==null){
            return;
        }
        WheelViewDialog city = new WheelViewDialog(mActivity, lists.getDataList(), new WheelViewDialog.ViewOnCleckListener() {
            @Override
            public void callBack(String type) {
                mCurrentCityName =type;
//                bindbankcardCityTt.setText(mCurrentCityName+"");
            }
        });
        city.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    @Override
    public void showBankCardTypeLists(BankTypeLists lists) {

        if(lists==null){
            return;
        }

        List<String> mList = new ArrayList<>();
        for(int i=0;i<lists.getDataList().size();i++){

            String bean = lists.getDataList().get(i).getName();
            mList.add(bean);
        }

        if(mList==null)
            return;


        WheelViewDialog banktype = new WheelViewDialog(mActivity,mList, new WheelViewDialog.ViewOnCleckListener() {
            @Override
            public void callBack(String type) {
                if(type==null){
                    return;
                }
                for (int i=0;i<lists.getDataList().size();i++){
                    if(lists.getDataList().get(i).getName().equals(type)){
                        mBankTypeBean = lists.getDataList().get(i);
                        break;
                    }
                }
                if(mBankTypeBean!=null){
                    bindbankcardTypeTt.setText(mBankTypeBean.getName()+"");
                }

            }
        });
        banktype.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


    }





    @OnClick({R.id.bindbankcard_getcode_tv, R.id.bindbankcard_explain_tv, R.id.bindbankcard_next_btn,R.id.openshbankaccount_type_rl, R.id.openshbankaccount_province_rl})
    public void onViewClicked(View view) {

        UserBean mUserBean = UserInfoManager.getInstance().getLocationUserData();

        switch (view.getId()) {
            case R.id.bindbankcard_getcode_tv:
                if(StringUtils.isFastClick()){
                    return;
                }
                String bankcardno =bindbankcardBankcardEt.getText().toString().trim();
                if(TextUtils.isEmpty(bankcardno)){
                    ToastUtils.show(mActivity,"银行账号不能为空");
                    return;
                }

                if(mBankTypeBean==null||TextUtils.isEmpty(mBankTypeBean.getNum())){
                    ToastUtils.show(mActivity,"开户银行不能为空");
                    return;
                }


                if(TextUtils.isEmpty(mCurrentProviceName)){
                    ToastUtils.show(mActivity,"开户省份不能为空");
                    return;
                }


                if(TextUtils.isEmpty(mCurrentCityName)){
                    ToastUtils.show(mActivity,"开卡城市不能为空");
                    return;
                }

                String phoneNum = bindbankcardPhoneEt.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    showPhoneNumInvalid();
                    return;
                }


                if(mUserBean==null||TextUtils.isEmpty(mUserBean.getUserPhone())){
                    return;
                }

                if (mPresenter != null) {
                    mPresenter.sendCode(mUserBean.getUserPhone(),bankcardno,phoneNum, Constants.OPEN_BANK_TYPE_RERSON,mCurrentProviceName,mCurrentCityName,mBankTypeBean.getNum());
                }

                break;
            case R.id.bindbankcard_explain_tv:
                Intent mIntent = new Intent(mActivity,WebViewActivity.class);
                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_BANK_LIMIT_AMOUNT);
                mIntent.putExtra("WebView_TITLE","查看支持银行及限额");
                startActivity(mIntent);

                break;
            case R.id.bindbankcard_next_btn:
                if(StringUtils.isFastClick()){
                    return;
                }
                String bankcardnumber =bindbankcardBankcardEt.getText().toString().trim();
                if(TextUtils.isEmpty(bankcardnumber)){
                    ToastUtils.show(mActivity,"银行账号不能为空");
                    return;
                }

                if(mBankTypeBean==null||TextUtils.isEmpty(mBankTypeBean.getNum())){
                    ToastUtils.show(mActivity,"开户银行不能为空");
                    return;
                }


                //验证码
                String validcode = bindbankcardCodeEt.getText().toString();
                if (TextUtils.isEmpty(validcode)) {
                    showCodeInvalid();
                    return;
                }

                if(TextUtils.isEmpty(mCurrentProviceName)){
                    ToastUtils.show(mActivity,"开户省份不能为空");
                    return;
                }


                if(TextUtils.isEmpty(mCurrentCityName)){
                    ToastUtils.show(mActivity,"开卡城市不能为空");
                    return;
                }

                //预留手机号
                String otherPhone = bindbankcardPhoneEt.getText().toString();

                if (TextUtils.isEmpty(otherPhone)) {
                    showPhoneNumInvalid();
                    return;
                }

                if(mUserBean==null||TextUtils.isEmpty(mUserBean.getUserPhone())){
                    return;
                }


                if (mPresenter != null) {
                   mPresenter.bindBankCardHttpRequest(mUserBean.getUserPhone(),bankcardnumber,validcode,otherPhone,Constants.OPEN_BANK_TYPE_RERSON,mCurrentProviceName,mCurrentCityName,mBankTypeBean.getNum());
                }


                break;
            case R.id.openshbankaccount_type_rl:
                if(StringUtils.isFastClick()){
                    return;
                }
                if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                    mPresenter.getBankCardTypeHttpRequest(mUserBean.getUserPhone());
                }

                break;
            case R.id.openshbankaccount_province_rl:
                if(StringUtils.isFastClick()){
                    return;
                }
                if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                    mPresenter.getProviceHttpRequest(mUserBean.getUserPhone());
                }

                break;

        }
    }


    @Override
    public void showCodeInvalid() {
        ToastUtils.show(mActivity, getString(R.string.regist_code_error));
    }

    @Override
    public void showPhoneNumInvalid() {
        ToastUtils.show(
                mActivity,
                "请输入正确的银行预留手机号码"
        );
    }

    @Override
    public void triggerCountDown() {

        bindbankcardGetcodeTv.setClickable(false);
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
    public void setCodeTips(String phone) {
        if(!TextUtils.isEmpty(phone)){
            bindbankcardGetcodeTv.setTextColor(getResources().getColor(R.color.gray2));
            ToastUtils.show(mActivity, "短信验证码已发送至"+phone+",请查收");
        }
    }

    public void setSendCodeBtnEnable(boolean flag) {
        if (bindbankcardGetcodeTv != null) {
            bindbankcardGetcodeTv.setEnabled(flag);
        }
    }


    public void focusOnCodeEdit() {
        bindbankcardCodeEt.requestFocus();
    }


    @Override
    public void showErrorTips(String msg) {
        if(!TextUtils.isEmpty(msg)){
            ToastUtils.show(mActivity, ""+msg);
        }
    }

    @Override
    public void initViewData() {

        new BindBankCardPresenter(mActivity,this);

    }

    @Override
    public void showDateEmptyView(boolean isRefresh) {

    }

    @Override
    public void setPresenter(BindBankCardContract.Presenter presenter) {
        this.mPresenter =presenter;
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
                bindbankcardGetcodeTv.setText(msg.what + "s\n" + "后重发");
            } else {
                bindbankcardGetcodeTv.setText("重新发送");
                bindbankcardGetcodeTv.setTextColor(getResources().getColor(R.color.blue5));
                bindbankcardGetcodeTv.setClickable(true);
            }
        }
    };






}
