package com.hz.zdjfu.application.modle.mine.personcenter.paypwdmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.hz.zdjfu.application.R;
import com.hz.zdjfu.application.base.BaseFragment;
import com.hz.zdjfu.application.base.ZDJFUApplication;
import com.hz.zdjfu.application.data.bean.ResultDataList;
import com.hz.zdjfu.application.data.bean.UserBean;
import com.hz.zdjfu.application.manager.UserInfoManager;
import com.hz.zdjfu.application.utils.StringUtils;
import com.hz.zdjfu.application.utils.ToastUtils;
import com.hz.zdjfu.application.widget.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * [类功能说明]
 * 支付密码管理Fragment
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/9/20 0020
 */
public class PayPwdManagerFragment extends BaseFragment implements PayPwdManagerContract.View {

    private static final String TAG = PayPwdManagerFragment.class.getName();
    @BindView(R.id.paypwdmanager_callback_phone_rl)
    RelativeLayout paypwdmanagerCallbackPhoneRl;
    @BindView(R.id.paypwdmanager_update_pwd_rl)
    RelativeLayout paypwdmanagerUpdatePwdRl;
    @BindView(R.id.paypwdmanager_forget_pwd_rl)
    RelativeLayout paypwdmanagerForgetPwdRl;

    private ResultDataList lists;
    private PayPwdManagerContract.Presenter mPresenter;

    public static PayPwdManagerFragment newInstance() {
        return new PayPwdManagerFragment();
    }


    @Override
    public void showErrorTips(String msg) {

        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(mActivity, msg);
        }
    }

    @Override
    public void initViewData() {
            new PayPwdManagerPresenter(mActivity,this);

    }

    @Override
    public void showDateEmptyView(boolean isShow) {

    }

    @Override
    public void setPresenter(PayPwdManagerContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_paywadmanager;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initViewData();
    }


    @Override
    public void showH5Url(ResultDataList list) {
        this.lists =list;
    }


    @Override
    public void fundAttestationPhone(String url) {
        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL",url);
        startActivity(mIntent);
    }

    @Override
    public void resetPayPwd(String url) {
        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL",url);
        startActivity(mIntent);
    }

    @Override
    public void updataPayPwd(String url) {
        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
        mIntent.putExtra("WebView_URL", url);
        startActivity(mIntent);
    }

    @Override
    public void withoutCodePwd(String url) {
        Intent mIntent = new Intent(mActivity,WebViewActivity.class);
        mIntent.putExtra("WebView_URL",url);
        startActivity(mIntent);
    }


    @OnClick({R.id.paypwdmanager_callback_phone_rl, R.id.paypwdmanager_update_pwd_rl, R.id.paypwdmanager_forget_pwd_rl,R.id.paypwdmanager_open_without_code_rl})
    public void onViewClicked(View view) {

        if(StringUtils.isFastClick()){
            return;
        }
        UserBean mBean = UserInfoManager.getInstance().getLocationUserData();
        if(mBean==null||TextUtils.isEmpty(mBean.getUserPhone())){
            return;
        }
        switch (view.getId()) {
            case R.id.paypwdmanager_callback_phone_rl:
                mPresenter.fundAttestationPhone(mBean.getUserPhone());
                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY ="PASSWORDMANAGER";
                break;
            case R.id.paypwdmanager_update_pwd_rl:
               mPresenter.updataPayPwd(mBean.getUserPhone());
                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY ="PASSWORDMANAGER";
                break;
            case R.id.paypwdmanager_forget_pwd_rl:
                mPresenter.resetPayPwd(mBean.getUserPhone());
                ZDJFUApplication.getInstance().MCURRENT_ACTIVITY ="PASSWORDMANAGER";
                break;
            case R.id.paypwdmanager_open_without_code_rl:
                ToastUtils.show(mActivity,"该功能正在紧张开发中，敬请期待");
                break;
        }
    }
}
