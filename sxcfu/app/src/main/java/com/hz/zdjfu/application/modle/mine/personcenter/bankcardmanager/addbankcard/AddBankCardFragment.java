package com.hz.zdjfu.application.modle.mine.personcenter.bankcardmanager.addbankcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.config.Constants;
import com.hz.zdjfu.application.config.H5Urls;
import com.hz.zdjfu.application.data.bean.BankTypeBean;
import com.hz.zdjfu.application.data.bean.BankTypeLists;
import com.hz.zdjfu.application.data.bean.CityLists;
import com.hz.zdjfu.application.data.bean.ProviceLists;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.modle.product.productdetail.ProductDetailActivity;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.dialog.PCWheelViewDialog;
import com.hz.zdjfu.application.widget.dialog.WheelViewDialog;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * [类功能说明]
 * 添加银行卡Fragment
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class AddBankCardFragment extends BaseFragment implements AddBankCardContract.View {

    private static final String TAG = AddBankCardFragment.class.getName();
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
    TextView bindbankcardTypeTv;
    @BindView(R.id.bindbankcard_province_tv)
    TextView bindbankcardProvinceTv;



    private String mPhone;

    //当前省的名称
    protected String mCurrentProviceName;
    //当前市的名称
    protected String mCurrentCityName;
    //银行类型
    protected BankTypeBean mBankTypeBean;
    private AddBankCardContract.Presenter mPresenter;
    private PCWheelViewDialog provice;
    private boolean isSelecte =false;
    private UserBean mUserBean = null;
    public static AddBankCardFragment newInstance() {
        return new AddBankCardFragment();
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
    public void setPresenter(AddBankCardContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bindbankcard;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        new AddBankCardPresenter(mActivity, this);
        initViewData();
        onClickListener();

    }

    @Override
    public void initViewData() {

        bindbankcardNextBtn.setText(getString(R.string.common_sure));
        mUserBean = UserInfoManager.getInstance().getUserBean();
    }


    @Override
    public void onClickListener() {


        bindbankcardBankcardEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >0&& bindbankcardPhoneEt.getText().toString().length() == 11&&bindbankcardCodeEt.getText().toString().length() == 6) {
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
                if (s.length() ==11&& bindbankcardCodeEt.getText().toString().length() == 6&&bindbankcardBankcardEt.getText().toString().length()>0) {
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
                if (s.length() ==6&& bindbankcardPhoneEt.getText().toString().length() == 11&&bindbankcardBankcardEt.getText().toString().length()>0) {
                    bindbankcardNextBtn.setEnabled(true);
                } else {
                    bindbankcardNextBtn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void addBankCardSuccess(boolean state) {
        if (state) {
            ToastUtils.show(mActivity, "添加成功");
            mActivity.finish();
        }
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
    public void setCodeTips(String otherphone) {
        if(!TextUtils.isEmpty(otherphone)){
            bindbankcardGetcodeTv.setTextColor(getResources().getColor(R.color.gray2));
            ToastUtils.show(mActivity,"短信验证码已发送至"+otherphone+"，请查收");
        }
    }



    @Override
    public void setSendCodeBtnEnable(boolean flag) {
        if (bindbankcardGetcodeTv != null) {
            bindbankcardGetcodeTv.setEnabled(flag);
        }
    }

    @Override
    public void focusOnCodeEdit() {
        bindbankcardCodeEt.requestFocus();
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
    public void lookBankCardExplain() {

    }

    @Override
    public void showProviceLists(ProviceLists lists) {

//        if(lists==null){
//            return;
//        }
//        WheelViewDialog provice = new WheelViewDialog(mActivity, lists.getDataList(), new WheelViewDialog.ViewOnCleckListener() {
//            @Override
//            public void callBack(String type) {
//                mCurrentProviceName =type;
//                bindbankcardProvinceTv.setText(mCurrentProviceName+"");
//            }
//        });
//        provice.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


        if(lists==null){
            return;
        }

        isSelecte =false;
        if(provice==null){
            provice = new PCWheelViewDialog(mActivity, lists.getDataList(), new PCWheelViewDialog.ViewOnCleckListener() {
                @Override
                public void callBack(String type) {
                    if(StringUtils.isFastClick()){
                        return;
                    }

                    if(isSelecte){//选择城市
                        mCurrentCityName =type;
                        if(bindbankcardProvinceTv!=null){
                            bindbankcardProvinceTv.setText(mCurrentProviceName+mCurrentCityName+"");
                        }
                        provice.dismiss();
                        provice =null;
                    }else{
                        mCurrentProviceName =type;
                        if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                            mPresenter.getCityHttpRequest(mUserBean.getUserPhone(),mCurrentProviceName);
                        }
                    }
                }
            },new PCWheelViewDialog.ChangeOnCleckListener(){

                @Override
                public void callBack() {
                    if(StringUtils.isFastClick()){
                        return;
                    }
                    if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                        mPresenter.getProviceHttpRequest(mUserBean.getUserPhone());
                    }
                }
            });
        }else{
            isSelecte =false;
            mCurrentCityName =null;
            mCurrentProviceName =null;
            provice.setCityTextView(mCurrentProviceName);
            provice.notifyData(lists.getDataList());
        }

        provice.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        provice.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isSelecte =false;
                if(provice!=null){
                    provice=null;
                }
            }
        });






    }

    @Override
    public void showCityLists(CityLists lists) {

        if(lists==null){
            return;
        }
//        WheelViewDialog city = new WheelViewDialog(mActivity, lists.getDataList(), new WheelViewDialog.ViewOnCleckListener() {
//            @Override
//            public void callBack(String type) {
//                mCurrentCityName =type;
//                bindbankcardCityTv.setText(mCurrentCityName+"");
//            }
//        });
//        city.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        if(lists==null){
            return;
        }
        isSelecte =true;
        provice.setCityTextView(mCurrentProviceName);
        provice.notifyData(lists.getDataList());


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
                    bindbankcardTypeTv.setText(mBankTypeBean.getName()+"");
                }

            }
        });
        banktype.showAtLocation(mActivity.findViewById(R.id.openshbankaccount_main_ll), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    @Override
    public void showBindBankCardSuccess(String state) {

        if(TextUtils.isEmpty(state)){
            ToastUtils.show(mActivity,"返回的URL为空");
            return;
        }

        if(ZDJFUApplication.getInstance().MCURRENT_ACTIVITY!=null&&ZDJFUApplication.getInstance().MCURRENT_ACTIVITY.equals("INVESTDETAIL")){
            startActivity(ProductDetailActivity.makeIntent(mActivity,null));
            ZDJFUApplication.getInstance().MCURRENT_ACTIVITY=null;
        }

        ToastUtils.show(mActivity,"银行卡绑定成功");
        mActivity.finish();

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
                bindbankcardGetcodeTv.setText("重新获取");
                bindbankcardGetcodeTv.setTextColor(getResources().getColor(R.color.blue5));
                bindbankcardGetcodeTv.setClickable(true);
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



    @OnClick({R.id.bindbankcard_getcode_tv, R.id.bindbankcard_explain_tv, R.id.bindbankcard_next_btn,R.id.openshbankaccount_type_rl,R.id.openshbankaccount_province_rl})
    public void onViewClicked(View view) {

//        UserBean mUserBean = UserInfoManager.getInstance().getUserBean();

        switch (view.getId()) {
            case R.id.bindbankcard_getcode_tv:

                String phoneNum = bindbankcardPhoneEt.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    showPhoneNumInvalid();
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

                if(TextUtils.isEmpty(mBankTypeBean.getName())||TextUtils.isEmpty(mBankTypeBean.getNum())){
                    ToastUtils.show(mActivity,"卡户银行不能为空");
                    return;
                }

                String bankcardno =bindbankcardBankcardEt.getText().toString().trim();
                if(TextUtils.isEmpty(bankcardno)||TextUtils.isEmpty(mBankTypeBean.getNum())){
                    ToastUtils.show(mActivity,"银行账号不能为空");
                    return;
                }

                if(mUserBean==null||TextUtils.isEmpty(mUserBean.getUserPhone())){
                    return;
                }


                if (mPresenter != null) {
                    mPresenter.getCodeHttpRequest(mUserBean.getUserPhone(),bankcardno,phoneNum, Constants.OPEN_BANK_TYPE_RERSON,mCurrentProviceName,mCurrentCityName,mBankTypeBean.getNum());
                }


                break;
            case R.id.bindbankcard_explain_tv:

                Intent mIntent = new Intent(mActivity,WebViewActivity.class);
                mIntent.putExtra("WebView_URL", H5Urls.H5_URL_BANK_LIMIT_AMOUNT);
                mIntent.putExtra("WebView_TITLE","查看支持银行及限额");
                startActivity(mIntent);

                break;
            case R.id.bindbankcard_next_btn:

                //预留手机号
                String otherPhone = bindbankcardPhoneEt.getText().toString();
                if (TextUtils.isEmpty(otherPhone)) {
                    showPhoneNumInvalid();
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

                if(TextUtils.isEmpty(mBankTypeBean.getName())){
                    ToastUtils.show(mActivity,"卡户银行不能为空");
                    return;
                }

                if(mUserBean==null||TextUtils.isEmpty(mUserBean.getUserPhone())){
                    return;
                }

                String bankcardnumber =bindbankcardBankcardEt.getText().toString().trim();
                if(TextUtils.isEmpty(bankcardnumber)){
                    ToastUtils.show(mActivity,"银行账号不能为空");
                    return;
                }

                if (mPresenter != null) {
                    mPresenter.addBankCardHttpRequest(mUserBean.getUserPhone(),bankcardnumber,validcode,otherPhone,Constants.OPEN_BANK_TYPE_RERSON,mCurrentProviceName,mCurrentCityName,mBankTypeBean.getNum());
                }
                break;
            case R.id.openshbankaccount_type_rl://选择银行类型

                if(StringUtils.isFastClick()){
                    return;
                }

                if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                    mPresenter.getBankCardTypeHttpRequest(mUserBean.getUserPhone());
                }

                break;
            case R.id.openshbankaccount_province_rl://选择开户省份
                if(StringUtils.isFastClick()){
                    return;
                }

                if(mUserBean!=null&&!TextUtils.isEmpty(mUserBean.getUserPhone())){
                    mPresenter.getProviceHttpRequest(mUserBean.getUserPhone());
                }
                break;
        }
    }
}
